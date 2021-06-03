package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.repository.CollezioneRepository;

@Service
public class CollezioneService {
	@Autowired
	CollezioneRepository collezioneRepository;
	
	@Transactional
	public List<Collezione> tutti(){
		return (List<Collezione>)this.collezioneRepository.findAll();
	}
	
	@Transactional
	public List<Collezione> collezionePerNome(String name){
		return (List<Collezione>)this.collezioneRepository.findByName(name);
	}
	
	@Transactional
	public void cancellaCollezionePerId(Long id){
    this.collezioneRepository.deleteById(id);
	}
	
	@Transactional
	public Collezione collezionePerId(Long id){
		Optional<Collezione> optional = collezioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	@Transactional
	public void inserisci(Collezione collezione) {
		this.collezioneRepository.save(collezione);
		
	}

	public boolean alreadyExists(Collezione collezione) {
		List<Collezione> collezioni = this.collezioneRepository.findByName(collezione.getName());
		if (collezioni.size() > 0)
			return true;
		else 
			return false;
	}
	

}
