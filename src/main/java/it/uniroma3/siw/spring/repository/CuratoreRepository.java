package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Curatore;


public interface CuratoreRepository extends CrudRepository<Curatore, Long> {

	public List<Curatore> findByFirstname(String firstNome);

	public List<Curatore> findByLastname(String lastname);
}
