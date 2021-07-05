package it.uniroma3.siw.spring.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;

@RestController
public class CollezioneRestController {
	
	@Autowired
	private CollezioneService collezioneService;
	
	@RequestMapping(value = "/rest/collezione/{id}", method = RequestMethod.GET)
	public Collezione getCollezione(@PathVariable("id") Long id) {
			return this.collezioneService.collezionePerId(id);
			}

	@RequestMapping(value = "/rest/collezioni", method = RequestMethod.GET)
	public List<Collezione> getCollezioni() {
		return this.collezioneService.tutti();
		}

}
