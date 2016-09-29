package br.com.yasmin.avaliacao.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Esta classe representa informa��es sobre um Processo.
 * 
 * Classe <code>Processo</code>
 * 
 * @author yasminfarias
 * @version 1.0 (27/09/2016)
 *
 */
@Entity
@Table(name = "INFNET_PROCESSO_TB")
public class Processo implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = -863832999989928735L;
	
	/** id processo */
	@Id
	@Column(name = "ID_PROCESSO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "processo_id_seq")
	@SequenceGenerator(name = "processo_id_seq", sequenceName = "INFNET_PROCES_ID_PROCES_SQ", allocationSize = 1)
	private Integer idProcesso;

	/** fornecedor */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_FORNECEDOR", referencedColumnName = "ID_FORNECEDOR", nullable = true)
	private Fornecedor fornecedor;
	
	/** relato fiscalização */
	@Column(name = "RELATO_FISCALIZACAO")
	private StringBuilder relatoFiscalizacao;
	
	/** data relato */
	@Temporal(value = TemporalType.DATE)
	@Column(name = "DT_RELATO", nullable = false)
	private Date dataRelato;
	
	/** fiscal responsável */
	@Column(name = "FISCAL_RESPONSAVEL")
	private String fiscalResponsavel;

	/**
	 * Instancia um novo objeto Processo
	 */
	public Processo() {
		super();
	}

	/**
	 * Instancia um novo objeto Processo 
	 * 
	 * @param idProcesso
	 *  		  id processo
	 * @param fornecedor
	 *  		  fornecedor
	 * @param relatoFiscalizacao
	 *  		  relato fiscalização
	 * @param dataRelato
	 *  		  data relato
	 * @param fiscalResponsavel
	 *  		  fiscal responsável
	 */
	public Processo(Integer idProcesso, Fornecedor fornecedor, StringBuilder relatoFiscalizacao, Date dataRelato, String fiscalResponsavel) {
		super();
		this.idProcesso = idProcesso;
		this.fornecedor = fornecedor;
		this.relatoFiscalizacao = relatoFiscalizacao;
		this.dataRelato = dataRelato;
		this.fiscalResponsavel = fiscalResponsavel;
	}

	/**
	 * Obtém id processo
	 * 
	 * @return id processo
	 */
	public Integer getIdProcesso() {
		return idProcesso;
	}

	/**
	 * Define id processo
	 * 
	 * @param idProcesso
	 *  		  id processo
	 */
	public void setIdProcesso(Integer idProcesso) {
		this.idProcesso = idProcesso;
	}

	/**
	 * Obtém fornecedor
	 * 
	 * @return fornecedor
	 */
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	/**
	 * Define fornecedor
	 * 
	 * @param fornecedor
	 *  		  fornecedor
	 */
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	/**
	 * Obtém relato de fiscalização
	 * 
	 * @return relato de fiscalização
	 */
	public StringBuilder getRelatoFiscalizacao() {
		return relatoFiscalizacao;
	}

	/**
	 * Define relato de fiscalização
	 * 
	 * @param relatoFiscalizacao
	 *  		  relato de fiscalização
	 */
	public void setRelatoFiscalizacao(StringBuilder relatoFiscalizacao) {
		this.relatoFiscalizacao = relatoFiscalizacao;
	}

	/**
	 * Obtém data relato
	 * 
	 * @return data relato
	 */
	public Date getDataRelato() {
		return dataRelato;
	}

	/**
	 * Define data relato
	 * 
	 * @param dataRelato
	 *  		  data relato
	 */
	public void setDataRelato(Date dataRelato) {
		this.dataRelato = dataRelato;
	}

	/**
	 * Obtém fiscal responsável
	 * 
	 * @return fiscal responsável
	 */
	public String getFiscalResponsavel() {
		return fiscalResponsavel;
	}

	/**
	 * Define fiscal responsável
	 * 
	 * @param fiscalResponsavel
	 *  		  fiscal responsável
	 */
	public void setFiscalResponsavel(String fiscalResponsavel) {
		this.fiscalResponsavel = fiscalResponsavel;
	}

}
