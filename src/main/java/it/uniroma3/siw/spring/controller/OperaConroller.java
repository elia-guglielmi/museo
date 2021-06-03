package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.OperaValidator;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;
import it.uniroma3.siw.spring.service.OperaService;
import it.uniroma3.siw.spring.utils.FileUploadUtil;

@Controller
public class OperaConroller {
	@Autowired
	private OperaService operaService;
	@Autowired
	private ArtistaService artistaService ;
	@Autowired
	private CollezioneService collezioneService ;
	@Autowired
	private OperaValidator operaValidator;
	@Autowired
	private CuratoreService curatoreService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@RequestMapping(value = "/galleria", method = RequestMethod.GET)
	public String getAllOpere(Model model,String keyword,Integer keynumber) {
		logger.debug("getAllOpere");

		if(keyword!=null) {
			model.addAttribute("opere", this.operaService.findByKeyword(keyword));
		}else if(keynumber!=null){
			model.addAttribute("opere", this.operaService.findByYear(keynumber.intValue()));
		}else{
			model.addAttribute("opere", this.operaService.tutti());
		}
		return "galleria.html";
	}

	@RequestMapping(value = "/opera/{id}", method = RequestMethod.GET)
	public String getOpera(@PathVariable("id") Long id, Model model) {
		logger.debug("getOpera parte");
		model.addAttribute("opera", this.operaService.operaPerId(id));
		return "opera.html";
	}

	@RequestMapping(value = "/admin/opera", method = RequestMethod.GET)
	public String addOpera(Model model) {
		model.addAttribute("opera",new Opera());
		model.addAttribute("collezioni",this.collezioneService.tutti());
		model.addAttribute("artisti",this.artistaService.tutti());
		return "operaForm.html";
	}

	@RequestMapping(value = "/admin/opera", method = RequestMethod.POST)
	 public String addOpera(@ModelAttribute("opera") Opera opera,@RequestParam("file")MultipartFile file,
				Model model, BindingResult bindingResult) throws IOException {
		this.operaValidator.validate(opera, bindingResult);
		if (!bindingResult.hasErrors()) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		    opera.setPicture(fileName);   
			Opera savedOpera=this.operaService.inserisci(opera);
		    String uploadDir = "src/main/resources/static/images";
		    FileUploadUtil.saveFile(uploadDir, fileName, file);
			model.addAttribute("artisti", this.artistaService.tutti());
			model.addAttribute("collezioni", this.collezioneService.tutti());
			model.addAttribute("opere", this.operaService.tutti());
	 		model.addAttribute("curatori", this.curatoreService.tutti());
			return "admin/home.html";
		}
	//	model.addAttribute("opera",new Opera());
		model.addAttribute("collezioni",this.collezioneService.tutti());
		model.addAttribute("artisti",this.artistaService.tutti());
		return "operaForm.html";
		}
	
}