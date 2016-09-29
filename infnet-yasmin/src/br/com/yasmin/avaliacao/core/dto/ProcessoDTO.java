package br.com.yasmin.avaliacao.core.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe de transporte de dados de Processo
 * 
 * Classe <code>ProcessoDTO</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
public class ProcessoDTO implements Serializable {

	/** Constante serialVersionUID */
	private static final long serialVersionUID = 1056274506775751626L;

	/** id processo */
	private Integer idProcesso;
	
	/** fornecedor */
	private FornecedorDTO fornecedor;
	
	/** relato fiscalização */
	private StringBuilder relatoFiscalizacao;
	
	/** data relato */
	private Date dataRelato;
	
	/** fiscal responsável */
	private String fiscalResponsavel;

	/**
	 * Instancia um novo objeto Processo
	 */
	public ProcessoDTO() {
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
	public ProcessoDTO(Integer idProcesso, FornecedorDTO fornecedor, StringBuilder relatoFiscalizacao, Date dataRelato, String fiscalResponsavel) {
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
	public FornecedorDTO getFornecedor() {
		return fornecedor;
	}

	/**
	 * Define fornecedor
	 * 
	 * @param fornecedor
	 *  		  fornecedor
	 */
	public void setFornecedor(FornecedorDTO fornecedor) {
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
