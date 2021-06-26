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

import it.uniroma3.siw.spring.controller.validator.ArtistaValidator;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaValidator artistaValidator;
	
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private CollezioneService collezioneService;
	@Autowired
	private CuratoreService curatoreService;
	
	
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	
	 @RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
	    public String getArtista(@PathVariable("id") Long id, Model model) {
		 logger.debug("getArtista parte");
	    	model.addAttribute("artista", this.artistaService.artistaPerId(id));
	    	model.addAttribute("opere", this.operaService.operePerArtista(this.artistaService.artistaPerId(id)));
	    	return "artista.html";
	    }
	 
	 @RequestMapping(value = "/admin/artista", method = RequestMethod.GET)
		public String addArtista(Model model) {
			model.addAttribute("artista",new Artista());
			return "artistaForm.html";
		}
	 
	 @RequestMapping(value = "/admin/artista", method = RequestMethod.POST)
	    public String addArtista(@ModelAttribute("artista") Artista artista,
	    									Model model, BindingResult bindingResult) {
	    	this.artistaValidator.validate(artista, bindingResult);
	        if (!bindingResult.hasErrors()) {
	        	this.artistaService.inserisci(artista);
	        	model.addAttribute("artisti", this.artistaService.tutti());
	    		model.addAttribute("collezioni", this.collezioneService.tutti());
	    		model.addAttribute("opere", this.operaService.tutti());
	     		model.addAttribute("curatori", this.curatoreService.tutti());
	            return "admin/home.html";
	        }
	        return "artistaForm.html";
	    }
	 
	 @RequestMapping(value = "/admin/modificaArtista/{id}", method = RequestMethod.GET)
		public String modificaArtista(@PathVariable("id") Long id, Model model) {
			model.addAttribute("artista", this.artistaService.artistaPerId(id));
			return "modificaArtista.html";
		}
		
		@RequestMapping(value = "/admin/modificaArtista/{id}", method = RequestMethod.POST)
		public String modificaArtisa(@PathVariable("id") Long id,Model model,String firstname,String lastname,String biography,String nationality,String birthplace,String placeofdeath) {
			Artista artista=this.artistaService.artistaPerId(id);
			if(firstname!=null&&!firstname.equals("")) {
				artista.setFirstname(firstname);
			}
			if(lastname!=null&&!lastname.equals("")) {
				artista.setLastname(lastname);
			}if(biography!=null&&!biography.equals("")) {
				artista.setBiography(biography);
			}
			if(nationality!=null&&!nationality.equals("")) {
				artista.setNationality(nationality);
			}
			if(birthplace!=null&&!birthplace.equals("")) {
				artista.setBirthplace(birthplace);
			}
			if(placeofdeath!=null&&!placeofdeath.equals("")) {
				artista.setPlaceofdeath(placeofdeath);
			}
			
			this.artistaService.inserisci(artista);
			model.addAttribute("artisti", this.artistaService.tutti());
			model.addAttribute("collezioni", this.collezioneService.tutti());
			model.addAttribute("opere", this.operaService.tutti());
	 		model.addAttribute("curatori", this.curatoreService.tutti());
			return "admin/home.html";
		}
		
}
