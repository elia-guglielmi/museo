package it.uniroma3.siw.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.repository.CuratoreRepository;

@Service
public class CuratoreService {
	@Autowired
	CuratoreRepository curatoreRepository;
	
	@Transactional
	public List<Curatore> tutti(){
		return (List<Curatore>)this.curatoreRepository.findAll();
	}

	@Transactional
	public void cancellaCuratorePerId(Long id) {
		this.curatoreRepository.deleteById(id);
	}
	
	@Transactional
	public void inserisci(Curatore curatore) {
		this.curatoreRepository.save(curatore);
		
	}

	public boolean alreadyExists(Curatore o) {
		List<Curatore> curatori = this.curatoreRepository.findByLastname(o.getLastname());
		if (curatori.size() > 0)
			return true;
		else 
			return false;
	}
	
}
