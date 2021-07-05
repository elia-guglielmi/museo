package it.uniroma3.siw.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("opere")
@Entity
public class Collezione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Lob
	@Column(nullable = false)
	private String description;
	
	@ManyToOne
	private Curatore curatore;
	
	@OneToMany(mappedBy = "collezione", cascade = {CascadeType.REMOVE})
	private List<Opera> opere;

	/*costruttori*/
	public Collezione(String name) {
		this.name = name;
		this.opere= new ArrayList<Opera>();
	}
	
	public Collezione() {
		this.opere= new ArrayList<Opera>();
	}

	/*getters e setters*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Curatore getCuratore() {
		return curatore;
	}

	public void setCuratore(Curatore curatore) {
		this.curatore = curatore;
	}

	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}

	@Override
	public String toString() {
		return "Collezione [id=" + id + ", name=" + name + ", description=" + description + ", curatore=" + curatore
				+ ", opere=" + opere + "]";
	}

	
	
	
	
	

}
