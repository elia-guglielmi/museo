package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class CollezioneController {
	
	@Autowired
	CollezioneValidator collezioneValidator;
	@Autowired
	CuratoreService curatoreService;
	@Autowired
	CollezioneService collezioneService;
	@Autowired
	private OperaService operaService;
	@Autowired
	ArtistaService artistaService;
	
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 

	 @RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
	    public String getCollezione(@PathVariable("id") Long id, Model model) {
		 logger.debug("getCollezione");
	    	model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
	    	model.addAttribute("opere", this.operaService.operePerCollezione(this.collezioneService.collezionePerId(id)));
	    	return "collezione.html";
	    }
	 
	 @RequestMapping(value = "/admin/collezione", method = RequestMethod.GET)
		public String addArtista(Model model) {
			model.addAttribute("collezione",new Collezione());
			model.addAttribute("curatori",this.curatoreService.tutti());
			return "collezioneForm.html";
		}
	 
	 @RequestMapping(value = "/admin/collezione", method = RequestMethod.POST)
	    public String addOpera(@ModelAttribute("collezione") Collezione collezione,
	    									Model model, BindingResult bindingResult) {
	    	this.collezioneValidator.validate(collezione, bindingResult);
	        if (!bindingResult.hasErrors()) {
	        	this.collezioneService.inserisci(collezione);
	        	model.addAttribute("artisti", this.artistaService.tutti());
	    		model.addAttribute("collezioni", this.collezioneService.tutti());
	    		model.addAttribute("opere", this.operaService.tutti());
	     		model.addAttribute("curatori", this.curatoreService.tutti());
	            return "admin/home.html";
	        }
	        collezione.setCuratore(null);
			model.addAttribute("curatori",this.curatoreService.tutti());
	        return "collezioneForm.html";
	    }
	 
	 @RequestMapping(value = "/admin/modificaCollezione/{id}", method = RequestMethod.GET)
		public String modificaOpera(@PathVariable("id") Long id, Model model) {
			model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
			return "modificaCollezione.html";
		}
		
		@RequestMapping(value = "/admin/modificaCollezione/{id}", method = RequestMethod.POST)
		public String modificaOpera(@PathVariable("id") Long id,Model model,String name,String description) {
			Collezione collezione=this.collezioneService.collezionePerId(id);
			if(name!=null&&!name.equals("")) {
				collezione.setName(name);
			}
			if(description!=null&&!description.equals("")) {
				collezione.setDescription(description);
			}
			this.collezioneService.inserisci(collezione);
			model.addAttribute("artisti", this.artistaService.tutti());
			model.addAttribute("collezioni", this.collezioneService.tutti());
			model.addAttribute("opere", this.operaService.tutti());
	 		model.addAttribute("curatori", this.curatoreService.tutti());
			return "admin/home.html";
		}
		
		 @RequestMapping(value = "/admin/rimuovicollezione/{id}", method = RequestMethod.GET)
			public String rimuoviCollezione(@PathVariable("id") Long id, Model model) {
				collezioneService.cancellaCollezionePerId(id);
				model.addAttribute("artisti", this.artistaService.tutti());
	    		model.addAttribute("collezioni", this.collezioneService.tutti());
	    		model.addAttribute("opere", this.operaService.tutti());
	     		model.addAttribute("curatori", this.curatoreService.tutti());
				return "admin/home.html";
			}
}
