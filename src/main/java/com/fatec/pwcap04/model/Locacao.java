package com.fatec.pwcap04.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter; 

@Entity
public class Locacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
	@Column(unique = true)
	private String cliente;
	
	@NotNull
	@Size(min = 7, max = 7, message = "Placa deve ter 7 caracteres")
	@Column(unique = true)
	private String veiculo;
	
	private String dataLoca;
	private String dataDevo;
	
	@NotNull
	private int diasLoca;
	
	private Double precoLoca;
	private Double precoDiario;
	
	public Locacao() {  
	} 
	
	public Locacao(String cliente, String veiculo, @NotNull int diasLoca, @NotNull Double precoLoca, @NotNull Double precoDiario) {
		this.cliente = cliente;
		this.veiculo = veiculo;
		this.diasLoca = diasLoca;
		this.precoLoca = precoLoca;
		this.precoDiario = precoDiario;
		
		DateTime dataAtual = new DateTime();
		setDataLoca(dataAtual); 
	}
	
///////////////////////////////////////////////////////////////////////////////
	
	public void setDataLoca(DateTime dataAtual) {
		if (dataAtual == null) {
			dataAtual = new DateTime();
		}
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY-MM-dd");
		this.dataLoca = dataAtual.toString(fmt);
		setDataRevisao();
	}
	
	public void setDataRevisao() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY-MM-dd");
		DateTime data = fmt.parseDateTime(getDataLoca());
		this.dataDevo = data.plusDays(diasLoca).toString(fmt);
	}
	
	public Integer verificaRevisao() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY-MM-dd");
		DateTime dataAtual = fmt.parseDateTime(new DateTime().toString(fmt));
		DateTime dataDevolucaoPrevista = fmt.parseDateTime(getDataDevo());
		int dias = Days.daysBetween(dataAtual, dataDevolucaoPrevista).getDays();
		return dias;
	}
	
	public String getDataLoca() {
		return dataLoca;
	}
	
	public String getDataDevo() {
		return dataDevo;
	}

///////////////////////////////////////////////////////////////////////////////////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	/*public String getDataLoca() {
		return dataLoca;
	}

	public void setDataLoca(String dataLoca) {
		this.dataLoca = dataLoca;
	}

	public String getDataDevo() {
		return dataDevo;
	}

	public void setDataDevo(String dataDevo) {
		this.dataDevo = dataDevo;
	}*/

	public int getDiasLoca() {
		return diasLoca;
	}

	public void setDiasLoca(int diasLoca) {
		this.diasLoca = diasLoca;
	}
	
	public Double getPrecoDiario() {
		return precoDiario;
	}

	public void setPrecoDiario(Double precoDiario) {
		this.precoDiario = precoDiario;
	}

	public String getPrecoLoca() {
		double preco = 0;
		preco = precoDiario * diasLoca;
		return "R$"+ preco;
	}

	public void setPrecoLoca(Double precoLoca) {
		this.precoLoca = precoLoca;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataDevo == null) {
			if (other.dataDevo != null)
				return false;
		} else if (!dataDevo.equals(other.dataDevo))
			return false;
		if (dataLoca == null) {
			if (other.dataLoca != null)
				return false;
		} else if (!dataLoca.equals(other.dataLoca))
			return false;
		if (diasLoca != other.diasLoca)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (precoDiario == null) {
			if (other.precoDiario != null)
				return false;
		} else if (!precoDiario.equals(other.precoDiario))
			return false;
		if (precoLoca == null) {
			if (other.precoLoca != null)
				return false;
		} else if (!precoLoca.equals(other.precoLoca))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + id + ", cliente=" + cliente + ", veiculo=" + veiculo + ", dataLoca=" + dataLoca
				+ ", dataDevo=" + dataDevo + ", diasLoca=" + diasLoca + ", precoLoca="
				+ precoLoca + ", precoDiario=" + precoDiario + "]";
	}

	
}
