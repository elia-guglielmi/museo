package it.uniroma3.siw.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties({"artista","collezione"})
@Entity
public class Opera {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private int year;

	@Lob
	@Column(nullable = false)
	private String description;

	@ManyToOne
	private Collezione collezione;

	@ManyToOne
	private Artista artista;
	
	@Column(length = 64)
	private String picture;
	

	/*costruttori*/
	public Opera(String title, int year) {
		this.title = title;
		this.year = year;
	}

	public Opera() {
	
	}

	/*getters e setters*/
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public Collezione getCollezione() {
		return collezione;
	}

	public void setCollezione(Collezione collezione) {
		this.collezione = collezione;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	
	

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Transient
    public String getPictureImagePath() {
        if (picture == null || id == null) return null;
        String msg="/opera-picture/" + this.id + "/" + this.picture;
        return msg;
    }

	@Override
	public String toString() {
		return "Opera [id=" + id + ", title=" + title + ", year=" + year + ", description=" + description
				+ ", collezione=" + collezione + ", artista=" + artista + "]";
	}
	

}
