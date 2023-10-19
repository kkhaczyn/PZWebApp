package com.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import jakarta.persistence.JoinColumn;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Index;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="projekt", indexes = {
	    @Index(name = "idx_nazwa", columnList = "nazwa"),
	    @Index(name = "idx_dataCzasUtworzenia", columnList = "dataczas_utworzenia"),
	    @Index(name = "idx_dataOddania", columnList = "data_oddania")})
public class Projekt {
	
	public Projekt() { }
	
	public Projekt(String nazwa, String opis) {
		this.nazwa = nazwa;
		this.opis = opis;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="projekt_id")
	private Integer projektId;
	
	@NotBlank(message = "Pole nazwa nie może być puste!")
	@Size(min = 3, max = 50, message = "Nazwa musi zawierać od {min} do {max} znaków!")
	@Column(nullable = false, length = 50)
	private String nazwa;
	
	@Column(nullable = true, length = 1000)
	private String opis;
	
	@CreationTimestamp
	@Column(name = "dataczas_utworzenia", nullable = false, updatable = false)
	private LocalDateTime dataCzasUtworzenia;
	
	@Column(name = "data_oddania", nullable = true)
	private LocalDate dataOddania;
	
	@OneToMany(mappedBy = "projekt")
	@JsonIgnoreProperties({"projekt"})
	private List<Zadanie> zadania;
	
	@ManyToMany
	@JoinTable(name = "projekt_student",
	joinColumns = {@JoinColumn(name="projekt_id")},
	inverseJoinColumns = {@JoinColumn(name="student_id")})
	private Set<Student> studenci;
	
	public List<Zadanie> getZadania() {
		return zadania;
	}

	public void setZadania(List<Zadanie> zadania) {
		this.zadania = zadania;
	}
	
	public Integer getProjektId() {
		return projektId;
	}
	public void setProjektId(Integer projektId) {
		this.projektId = projektId;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String getOpis() {
		return opis;
	}
	
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public LocalDateTime getDataCzasUtworzenia() {
		return dataCzasUtworzenia;
	}
	
	public void setDataCzasUtworzenia(LocalDateTime dataCzasUtworzenia) {
		this.dataCzasUtworzenia = dataCzasUtworzenia;
	}
	
	public LocalDate getDataOddania() {
		return dataOddania;
	}
	
	public void setDataOddania(LocalDate dataOddania) {
		this.dataOddania = dataOddania;
	}

	public Set<Student> getStudenci() {
		return studenci;
	}

	public void setStudenci(Set<Student> studenci) {
		this.studenci = studenci;
	}
}
