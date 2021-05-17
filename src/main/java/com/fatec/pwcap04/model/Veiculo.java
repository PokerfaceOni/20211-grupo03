package com.fatec.pwcap04.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 7, max = 7, message = "Placa deve ter 7 caracteres")
	@Column(unique = true)
	private String placa;
	@NotNull
	@Size(min = 1, max = 50, message = "Nome deve ser preenchido")
	private String nome;
	@NotNull
	private String cor;
	@NotNull
	private String modelo;
	@NotNull
	private String marca;
	@NotNull
	private String disponibilidade;
	private String descAdicio;

	public Veiculo() {
	}

	public Veiculo(@NotNull String nome, @NotNull String cor, @NotNull String modelo,
			@NotNull String marca, @NotNull String placa,
			@NotNull String disponibilidade, String descAdicio) {

		this.nome = nome;
		this.cor = cor;
		this.modelo = modelo;
		this.marca = marca;
		this.placa = placa;
		this.disponibilidade = disponibilidade;
		this.descAdicio = descAdicio;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public String getDescAdicio() {
		return descAdicio;
	}

	public void setDescAdicio(String descAdicio) {
		this.descAdicio = descAdicio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (descAdicio == null) {
			if (other.descAdicio != null)
				return false;
		} else if (!descAdicio.equals(other.descAdicio))
			return false;
		if (disponibilidade == null) {
			if (other.disponibilidade != null)
				return false;
		} else if (!disponibilidade.equals(other.disponibilidade))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", nome=" + nome + ", cor=" + cor + ", modelo=" + modelo
				+ ", marca=" + marca + ", placa=" + placa + ", disponibilidade="
				+ disponibilidade + ", descricoes adicionais=" + descAdicio + "]";
	}

}
