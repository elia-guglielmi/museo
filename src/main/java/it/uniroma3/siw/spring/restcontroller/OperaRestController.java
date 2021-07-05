package it.uniroma3.siw.spring.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.OperaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class OperaRestController {
	
	@Autowired
	private OperaService operaService;
	
	@RequestMapping(value = "/rest/opera/{id}", method = RequestMethod.GET)
	public Opera getOpera(@PathVariable("id") Long id) {
			return this.operaService.operaPerId(id);
			}

	@RequestMapping(value = "/rest/opere", method = RequestMethod.GET)
	public List<Opera> getOpere() {
		return this.operaService.tutti();
		}

}
