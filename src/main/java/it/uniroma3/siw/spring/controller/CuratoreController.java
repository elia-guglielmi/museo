package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.CuratoreValidator;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class CuratoreController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private CollezioneService collezioneService;
	@Autowired
	private CuratoreService curatoreService;
	@Autowired
	private CuratoreValidator curatoreValidator;
	
	
	 @RequestMapping(value = "/admin/rimuovicuratore/{id}", method = RequestMethod.GET)
		public String rimuoviCollezione(@PathVariable("id") Long id, Model model) {
		curatoreService.cancellaCuratorePerId(id);
		model.addAttribute("artisti", this.artistaService.tutti());
 		model.addAttribute("collezioni", this.collezioneService.tutti());
 		model.addAttribute("opere", this.operaService.tutti());
 		model.addAttribute("curatori", this.curatoreService.tutti());
			return "admin/home.html";
		}
	 
	 @RequestMapping(value = "/admin/curatore", method = RequestMethod.GET)
		public String addCuratore(Model model) {
			model.addAttribute("curatore",new Curatore());
			return "curatoreForm.html";
		}
	 
	 @RequestMapping(value = "/admin/curatore", method = RequestMethod.POST)
	    public String addCuratore(@ModelAttribute("curatore") Curatore curatore,
	    									Model model, BindingResult bindingResult) {
	    	this.curatoreValidator.validate(curatore, bindingResult);
	        if (!bindingResult.hasErrors()) {
	        	this.curatoreService.inserisci(curatore);
	        	model.addAttribute("artisti", this.artistaService.tutti());
	    		model.addAttribute("collezioni", this.collezioneService.tutti());
	    		model.addAttribute("opere", this.operaService.tutti());
	     		model.addAttribute("curatori", this.curatoreService.tutti());
	            return "admin/home.html";
	        }
	        return "curatoreForm.html";
	    }

}
