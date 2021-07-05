package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.CuratoreService;
import it.uniroma3.siw.spring.service.OperaService;
import it.uniroma3.siw.spring.utils.FileUploadUtil;

@Controller
public class MainController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private CollezioneService collezioneService;
	@Autowired
	private CuratoreService curatoreService;
	@Autowired
	private CredentialsService credentialsService;
	
	
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 @RequestMapping(value = "/", method = RequestMethod.GET)
	    public String getArtistiEcollezioni(Model model) {
				logger.debug("getArtistiEcollezioni");
	    		model.addAttribute("artisti", this.artistaService.tutti());
	    		model.addAttribute("collezioni", this.collezioneService.tutti());
	    		return "index.html";
	    }
	 
	 @RequestMapping(value = "/admin", method = RequestMethod.GET)
	    public String getTutto(Model model) {
				logger.debug("getTutto");
	    		model.addAttribute("artisti", this.artistaService.tutti());
	    		model.addAttribute("collezioni", this.collezioneService.tutti());
	    		model.addAttribute("opere", this.operaService.tutti());
	     		model.addAttribute("curatori", this.curatoreService.tutti());
	    		return "admin/home.html";
	    }
	 
	
	 
		
		
}
