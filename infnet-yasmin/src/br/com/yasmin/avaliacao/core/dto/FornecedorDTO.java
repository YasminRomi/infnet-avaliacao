package br.com.yasmin.avaliacao.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe de transporte de dados de Fornecedor
 * 
 * Classe <code>FornecedorDTO</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
public class FornecedorDTO implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = 5554461351886494317L;
	
	/** id fornecedor */
	private Integer idFornecedor;
	
	/** cnpj */
	private String cnpj;
	
	/** razão social */
	private String razaoSocial;
	
	/** inscrição municipal */
	private String inscricaoMunicipal;
	
	/** receita bruta */
	private BigDecimal receitaBruta;
	
	/** endereço */
	private EnderecoDTO endereco;

	/**
	 * Instancia um novo objeto DTO do Fornecedor
	 */
	public FornecedorDTO() {
		super();
	}

	/**
	 * Instancia um novo objeto DTO do Fornecedor
	 * 
	 * @param idFornecedor
	 *  		  id fornecedor
	 * @param cnpj
	 *  		  cnpj
	 * @param razaoSocial
	 *  		  razão social
	 * @param inscricaoMunicipal
	 *  		  inscrição municipal
	 * @param receitaBruta
	 *  		  receita bruta
	 * @param endereco
	 *  		  endereço
	 */
	public FornecedorDTO(Integer idFormecedor, String cnpj, String razaoSocial, String inscricaoMunicipal, BigDecimal receitaBruta, EnderecoDTO endereco) {
		super();
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.inscricaoMunicipal = inscricaoMunicipal;
		this.receitaBruta = receitaBruta;
		this.endereco = endereco;
	}

	/**
	 * Obtém id fornecedor
	 * 
	 * @return id fornecedor
	 */
	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	/**
	 * Define id fornecedor
	 * 
	 * @param idFornecedor
	 *  		  id fornecedor
	 */
	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	/**
	 * Obtém cnpj
	 * 
	 * @return cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * Define cnpj
	 * 
	 * @param cnpj
	 *  		  cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Obtém razão social
	 * 
	 * @return razão social
	 */
	public String getRazaoSocial() {
		return razaoSocial;
	}

	/**
	 * Define razão social
	 * 
	 * @param razaoSocial
	 *  		  razão social
	 */
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	/**
	 * Obtém inscrição municipal
	 * 
	 * @return inscrição municipal
	 */
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	/**
	 * Define inscrição municipal
	 * 
	 * @param inscricaoMunicipal
	 *  		  inscrição municipal
	 */
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	/**
	 * Obtém receita bruta
	 * 
	 * @return receita bruta
	 */
	public BigDecimal getReceitaBruta() {
		return receitaBruta;
	}

	/**
	 * Define receita bruta
	 * 
	 * @param receitaBruta
	 *  		  receita bruta
	 */
	public void setReceitaBruta(BigDecimal receitaBruta) {
		this.receitaBruta = receitaBruta;
	}

	/**
	 * Obtém endereço
	 * 
	 * @return endereço
	 */
	public EnderecoDTO getEndereco() {
		return endereco;
	}

	/**
	 * Define endereço
	 * 
	 * @param endereco
	 *  		  endereço
	 */
	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
}
