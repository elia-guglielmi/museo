package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Curatore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long matricola;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	private String phonenumber;

	//@Column(nullable = false)
	private String email;

	@OneToMany(mappedBy = "curatore")
	private List<Collezione> collezioni;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateofbirth;
	private String birthplace;
	
	/*costruttori*/
	public Curatore(String firstNome, String lastCognome, String email) {

		this.firstname = firstNome;
		this.lastname = lastCognome;
		this.email = email;
		this.collezioni=new ArrayList<Collezione>();
	}
	
	public Curatore() {
		this.collezioni=new ArrayList<Collezione>();
	}
	
	/*getters e setters*/
	public Long getMatricola() {
		return matricola;
	}
	
	public void setMatricola(Long matricola) {
		this.matricola = matricola;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstNome) {
		this.firstname = firstNome;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastCognome) {
		this.lastname = lastCognome;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}
	
	public void setPhonenumber(String phoneNumber) {
		this.phonenumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDateofbirth() {
		return dateofbirth;
	}
	
	public void setDateofbirth(LocalDate dateOfBirth) {
		this.dateofbirth = dateOfBirth;
	}
	
	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthPlace) {
		this.birthplace = birthPlace;
	}

	public List<Collezione> getCollezioni() {
		return collezioni;
	}
	
	public void setCollezioni(List<Collezione> collezioni) {
		this.collezioni = collezioni;
	}
	
	@Override
	public String toString() {
		return "Curatore [matricola=" + matricola + ", firstNome=" + firstname + ", lastCognome=" + lastname
				+ ", phoneNumber=" + phonenumber + ", email=" + email + ", collezioni=" + collezioni + ", dateOfBirth="
				+ dateofbirth + ", birthPlace=" + birthplace + "]";
	}




}
