package it.uniroma3.siw.spring.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {

	@Autowired
	OperaRepository operaRepository;
	
	@Transactional
	public List<Opera> tutti(){
		return (List<Opera>) this.operaRepository.findAll();
	}
	
	
	@Transactional
	public List<Opera> findByKeyword(String keyword){
		return (List<Opera>) this.operaRepository.findByKeyword(keyword);
	}
	
	@Transactional
	public List<Opera> findByYear(int year){
		return (List<Opera>) this.operaRepository.findByYear(year);
	}
	
	@Transactional
	public List<Opera> operePerArtista(Artista artista){
		return this.operaRepository.findByArtista(artista);
	}
	
	@Transactional
	public List<Opera> operePerTitolo(String title){
		return this.operaRepository.findByTitle(title);
	}
	
	@Transactional
	public List<Opera> operePerCollezione(Collezione collezione){
		return this.operaRepository.findByCollezione(collezione);
	}
	
	@Transactional
	public void cancellaOperaPerId(Long id){
		this.operaRepository.deleteById(id);
	}
	
	@Transactional
	public Opera inserisci(Opera opera) {
		 return this.operaRepository.save(opera);
	}
	

	public boolean alreadyExists(Opera o) {
		List<Opera> opere = this.operaRepository.findByTitle(o.getTitle());
		if (opere.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
}
