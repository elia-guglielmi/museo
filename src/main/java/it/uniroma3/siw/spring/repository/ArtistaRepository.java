package it.uniroma3.siw.spring.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Opera;


public interface ArtistaRepository extends CrudRepository <Artista, Long>{

	public List<Artista> findByFirstname(String firstName);
	
	public List<Artista> findByLastname(String lastname);
	
	public void deleteById(Long id);

}
