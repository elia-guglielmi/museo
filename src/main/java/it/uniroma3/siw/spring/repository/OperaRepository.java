package it.uniroma3.siw.spring.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;

public interface OperaRepository extends CrudRepository<Opera, Long>{

	public List<Opera> findByArtista(Artista artista);

	public List<Opera> findByTitle(String title);

	public List<Opera> findByCollezione(Collezione collezione);
	
	public List<Opera> findByYear(int year);
	
	public void deleteById(Long id);
	
	
	@Query(value="select o.* from Opera o left join Artista a on o.artista_id=a.id left join Collezione c on o.collezione_id=c.id where a.firstname like %:keyword% or a.lastname like %:keyword% or o.title like %:keyword% or c.name like %:keyword%", nativeQuery = true)
	public List<Opera> findByKeyword(@Param("keyword") String keyword);
	
}
