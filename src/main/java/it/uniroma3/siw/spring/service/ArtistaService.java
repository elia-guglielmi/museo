package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.ArtistaRepository;

@Service
public class ArtistaService {
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Transactional
	public List<Artista> tutti(){
		return (List<Artista>)artistaRepository.findAll();
	}
	
	@Transactional
	public List<Artista> artistaPerCognome(String lastname){
		return (List<Artista>)artistaRepository.findByLastname(lastname);
	}
	
	@Transactional
	public List<Artista> artistaPerNome(String firstname){
		return (List<Artista>)artistaRepository.findByFirstname(firstname);
	}
	
	@Transactional
	public void cancellaArtistaPerId(Long id){
		this.artistaRepository.deleteById(id);
	}
	
	
	@Transactional
	public Artista artistaPerId(Long id) {
		Optional<Artista> optional = artistaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public void inserisci(Artista artista) {
		this.artistaRepository.save(artista);
		
	}

	public boolean alreadyExists(Artista a) {
		List<Artista> artisti = this.artistaRepository.findByLastname(a.getLastname());
		if (artisti.size() > 0)
			return true;
		else 
			return false;
	}
}
