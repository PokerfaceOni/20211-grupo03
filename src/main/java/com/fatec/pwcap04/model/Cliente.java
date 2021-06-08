package com.fatec.pwcap04.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter; 

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/*@OneToMany(
			mappedBy = "cliente",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Locacao> locacao = new ArrayList<>();
	
	public void setLocacao(List<Locacao> locacao) {
		this.locacao = locacao;
	}*/
	
	@NotNull
	@Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	private String cpf;
	@NotNull
	@Size(min = 1, max = 50, message = "Nome deve ser preenchido")
	private String nome;
	@Email (message = "E-mail inválido.")
	private String email;
	@NotNull
	private String rg;
	@NotNull
	private String dataNasc;
	@NotNull
	private String sexo;
	@NotNull
	private String naturalidade;
	private String telefone;
	
	//private String dataCadastro;
	//private String dataRevisao; 


	public Cliente() {
	}

	public Cliente(@NotNull String cpf, @NotNull String nome, @NotNull String email, @NotNull String rg,
			@NotNull String dataNasc, @NotNull String sexo, @NotNull String naturalidade,
			@NotNull String telefone) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.rg = rg;
		this.dataNasc = dataNasc;
		this.sexo = sexo;
		this.naturalidade = naturalidade;
		this.telefone = telefone;
		
		//DateTime dataAtual = new DateTime();
		// setDataCadastro(dataAtual); 

	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	/*
	
	public void setDataCadastro(DateTime dataAtual) {
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		 this.dataCadastro = dataAtual.toString(fmt);
		 setDataRevisao();
		}
	
	public void setDataRevisao() {
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		 DateTime data = fmt.parseDateTime(getDataCadastro());
		 this.dataRevisao = data.plusDays(360).toString(fmt);
		}
	
	public Integer verificaRevisao() {
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		 DateTime dataAtual = fmt.parseDateTime(new DateTime().toString(fmt));
		 DateTime dataDevolucaoPrevista = fmt.parseDateTime(getDataRevisao());
		 int dias = Days.daysBetween(dataAtual, dataDevolucaoPrevista).getDays();
		 return dias;
		}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
		public String getDataRevisao() {
		return dataRevisao;
	}

	public void setDataRevisao(String dataRevisao) {
		this.dataRevisao = dataRevisao;
	}
	
	*/
	
	///////////////////////////////////////////////////////////////////////////////////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNasc == null) {
			if (other.dataNasc != null)
				return false;
		} else if (!dataNasc.equals(other.dataNasc))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (naturalidade == null) {
			if (other.naturalidade != null)
				return false;
		} else if (!naturalidade.equals(other.naturalidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", rg=" + rg
				+ ", data nascimento=" + dataNasc + ", sexo=" + sexo
				+ ", naturalidade=" + naturalidade + ", telefone=" + telefone + "]";
	}
}
