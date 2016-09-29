package br.com.yasmin.avaliacao.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Esta classe representa informa��es sobre um fornecedor.
 * 
 * Classe <code>Fornecedor</code>
 * 
 * @author yasminfarias
 * @version 1.0 (27/09/2016)
 *
 */
@Entity
@Table(name = "INFNET_FORNECEDOR_TB")
public class Fornecedor implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = 5554461351886494317L;
	
	/** id fornecedor */
	@Id
	@Column(name = "ID_FORNECEDOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor_id_seq")
	@SequenceGenerator(name = "fornecedor_id_seq", sequenceName = "INFNET_FORNEC_ID_FORNEC_SQ", allocationSize = 1)
	private Integer idFornecedor;
	
	/** cnpj */
	@Column(name = "CNPJ")
	private String cnpj;
	
	/** razão social */
	@Column(name = "RAZAO_SOCIAL")
	private String razaoSocial;
	
	/** inscrição municipal */
	@Column(name = "INSCRICAO_MUNICIPAL")
	private String inscricaoMunicipal;
	
	/** receita bruta */
	@Column(name = "RECEITA_BRUTA")
	private BigDecimal receitaBruta;
	
	/** endereço */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID_ENDERECO", nullable = true)
	private Endereco endereco;

	/**
	 * Instancia um novo objeto Fornecedor
	 */
	public Fornecedor() {
		super();
	}

	/**
	 * Instancia um novo objeto Fornecedor
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
	public Fornecedor(Integer idFornecedor, String cnpj, String razaoSocial, String inscricaoMunicipal, BigDecimal receitaBruta, Endereco endereco) {
		super();
		this.idFornecedor = idFornecedor;
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
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Define endereço
	 * 
	 * @param endereco
	 *  		  endereço
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
