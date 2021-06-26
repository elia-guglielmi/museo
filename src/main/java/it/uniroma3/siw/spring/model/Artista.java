package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String birthplace;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateofbirth;

	@Column(nullable = false)
	private String nationality;

	@OneToMany(mappedBy = "artista", cascade = {CascadeType.REMOVE})
	private List<Opera> opere;

	private String placeofdeath;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateofdeath;
	@Lob
	@Column(nullable = false)
	private String biography;
	
	

	public Artista(String firstName, String lastName, String nationality) {
		this.firstname = firstName;
		this.lastname = lastName;
		this.nationality = nationality;
		this.opere = new ArrayList<Opera>();
	}
	
	public Artista() {
		this.opere = new ArrayList<Opera>();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastName) {
		this.lastname = lastName;
	}
	
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}
	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public LocalDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getPlaceofdeath() {
		return placeofdeath;
	}

	public void setPlaceofdeath(String placeofdeath) {
		this.placeofdeath = placeofdeath;
	}

	public LocalDate getDateofdeath() {
		return dateofdeath;
	}

	public void setDateofdeath(LocalDate dateofdeath) {
		this.dateofdeath = dateofdeath;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	@Override
	public String toString() {
		return "Artista [id=" + id + ", firstName=" + firstname + ", lastName=" + lastname + ", birthPlace=" + birthplace
				+ ", dateOfBirth=" + dateofbirth + ", nationality=" + nationality + ", opere=" + opere + ", placeOfDeath="
				+ placeofdeath + ", dateOfDeath=" + dateofdeath + "]";
	}

}
