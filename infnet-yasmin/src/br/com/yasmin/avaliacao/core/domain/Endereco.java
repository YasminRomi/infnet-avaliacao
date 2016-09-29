package br.com.yasmin.avaliacao.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Esta classe representa informa��es sobre um endere�o.
 * 
 * Classe <code>Endereco</code>
 * 
 * @author yasminfarias
 * @version 1.0 (27/09/2016)
 *
 */
@Entity
@Table(name = "INFNET_ENDERECO_TB")
public class Endereco implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = 5208426073747192103L;
	
	/** id endereco */
	@Id
	@Column(name = "ID_ENDERECO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_seq")
	@SequenceGenerator(name = "endereco_id_seq", sequenceName = "INFNET_END_ID_END_SQ", allocationSize = 1)
	private Integer idEndereco;

	/** logradouro */
	@Column(name = "LOGRADOURO")
	private String logradouro;
	
	/** numero */
	@Column(name = "NUMERO")
	private String numero;
	
	/** complemento */
	@Column(name = "COMPLEMENTO")
	private String complemento;
	
	/** bairro */
	@Column(name = "BAIRRO")
	private String bairro;
	
	/** municipio */
	@Column(name = "MUNICIPIO")
	private String municipio;
	
	/** CEP */
	@Column(name = "CEP")
	private String cep;
	
	/** UF */
	@Column(name = "UF")
	private String uf;

	/**
	 * Instancia um novo objeto Endereço
	 */
	public Endereco() {
		super();
	}

	/**
	 * Instancia um novo objeto Endereço
	 * 
	 * @param idEndereco
	 *  		  id endereço
	 * @param logradouro
	 *  		  logradouro
	 * @param numero
	 *  		  numero
	 * @param complemento
	 *  		  complemento
	 * @param bairro
	 *  		  bairro
	 * @param municipio
	 *  		  municipio
	 * @param cep
	 *  		  cep
	 * @param uf
	 *  		  uf
	 */
	public Endereco(Integer idEndereco, String logradouro, String numero, String complemento, String bairro, String municipio, String cep, String uf) {
		super();
		this.idEndereco = idEndereco;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.municipio = municipio;
		this.cep = cep;
		this.uf = uf;
	}

	/**
	 * Obtém id endereço
	 * 
	 * @return id endereço
	 */
	public Integer getIdEndereco() {
		return idEndereco;
	}

	/**
	 * Define id endereço
	 * 
	 * @param idEndereco
	 *  		  id endereço
	 */
	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	/**
	 * Obtém logradouro
	 * 
	 * @return logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * Define logradouro
	 * 
	 * @param logradouro
	 *  		  logradouro
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * Obtém número
	 * 
	 * @return número
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Define número
	 * 
	 * @param numero
	 *  		  número
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Obtém complemento
	 * 
	 * @return complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Define complemento
	 * 
	 * @param complemento
	 *  		  complemento
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Obtém bairro
	 * 
	 * @return bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Define bairro
	 * 
	 * @param bairro
	 *  		  bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Obtém município
	 * 
	 * @return município
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * Define município
	 * 
	 * @param municipio
	 *  		  município
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * Obtém CEP
	 * 
	 * @return CEP
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Define CEP
	 * 
	 * @param cep
	 *  		  CEP
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Obtém UF
	 * 
	 * @return UF
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * Define UF
	 * 
	 * @param uf
	 *  		  UF
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}
}
