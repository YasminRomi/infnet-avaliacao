package br.com.yasmin.avaliacao.core.dto;

import java.io.Serializable;

/**
 * Classe de transporte de dados de Endereco
 * 
 * Classe <code>EnderecoDTO</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
public class EnderecoDTO implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = -2046270822439349293L;
	
	/** id endereco */
	private Integer idEndereco;

	/** logradouro */
	private String logradouro;
	
	/** numero */
	private String numero;
	
	/** complemento */
	private String complemento;
	
	/** bairro */
	private String bairro;
	
	/** municipio */
	private String municipio;
	
	/** CEP */
	private String cep;
	
	/** UF */
	private String uf;

	/**
	 * Instancia um novo objeto Endereço
	 */
	public EnderecoDTO() {
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
	public EnderecoDTO(Integer idEndereco, String logradouro, String numero, String complemento, String bairro, String municipio, String cep, String uf) {
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
