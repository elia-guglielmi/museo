package it.uniroma3.siw.spring.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class ArtistaRestcontroller {
	
	@Autowired
	private ArtistaService artistaService;
	
	@RequestMapping(value = "/rest/artista/{id}", method = RequestMethod.GET)
	public Artista getArtista(@PathVariable("id") Long id) {
			return this.artistaService.artistaPerId(id);
			}

	@RequestMapping(value = "/rest/artisti", method = RequestMethod.GET)
	public List<Artista> getArtisti() {
		return this.artistaService.tutti();
		}

}
