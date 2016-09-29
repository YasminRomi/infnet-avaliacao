package br.com.yasmin.avaliacao.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe de transporte de dados de AutoDeInfracao
 * 
 * Classe <code>AutoDeInfracaoDTO</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
public class AutoDeInfracaoDTO implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = -25734525822461817L;

	/** id auto de infração */
	private Integer idAutoDeInfracao;
	
	/** processo */
	private ProcessoDTO processo;
	
	/** gravidade */
	private Integer gravidade;
	
	/** atenuante */
	private boolean atenuante;
	
	/** agravante */
	private boolean agravante;
	
	/** multa */
	private BigDecimal multa;

	/**
	 * Instancia um novo objeto AutoDeInfracao
	 */
	public AutoDeInfracaoDTO() {
		super();
	}

	/**
	 * Instancia um novo objeto AutoDeInfracao
	 * 
	 * @param idAutoDeInfracao
	 *  		  id auto de infração
	 * @param processo
	 *  		  processo
	 * @param gravidade
	 *  		  gravidade
	 * @param atenuante
	 *  		  atenuante
	 * @param agravante
	 *  		  agravante
	 * @param multa
	 *  		  multa
	 */
	public AutoDeInfracaoDTO(Integer idAutoDeInfracao, ProcessoDTO processo,
			Integer gravidade, boolean atenuante, boolean agravante,
			BigDecimal multa) {
		super();
		this.idAutoDeInfracao = idAutoDeInfracao;
		this.processo = processo;
		this.gravidade = gravidade;
		this.atenuante = atenuante;
		this.agravante = agravante;
		this.multa = multa;
	}

	/**
	 * Obtém id auto de infração
	 * 
	 * @return id auto de infração
	 */
	public Integer getIdAutoDeInfracao() {
		return idAutoDeInfracao;
	}

	/**
	 * Define id auto de infração
	 * 
	 * @param idAutoDeInfracao
	 *  		  id auto de infração
	 */
	public void setIdAutoDeInfracao(Integer idAutoDeInfracao) {
		this.idAutoDeInfracao = idAutoDeInfracao;
	}

	/**
	 * Obtém processo
	 * 
	 * @return processo
	 */
	public ProcessoDTO getProcesso() {
		return processo;
	}

	/**
	 * Define processo
	 * 
	 * @param processo
	 *  		  processo
	 */
	public void setProcesso(ProcessoDTO processo) {
		this.processo = processo;
	}

	/**
	 * Obtém gravidade
	 * 
	 * @return gravidade
	 */
	public Integer getGravidade() {
		return gravidade;
	}

	/**
	 * Define gravidade
	 * 
	 * @param gravidade
	 *  		  gravidade
	 */
	public void setGravidade(Integer gravidade) {
		this.gravidade = gravidade;
	}

	/**
	 * Obtém atenuante
	 *  
	 * @return atenuante
	 */
	public boolean isAtenuante() {
		return atenuante;
	}

	/**
	 * Define atenuante
	 * 
	 * @param atenuante
	 *  		  atenuante
	 */
	public void setAtenuante(boolean atenuante) {
		this.atenuante = atenuante;
	}

	/**
	 * Obtém agravante
	 * 
	 * @return agravante
	 */
	public boolean isAgravante() {
		return agravante;
	}

	/**
	 * Define agravante
	 * 
	 * @param agravante
	 *  		  agravante
	 */
	public void setAgravante(boolean agravante) {
		this.agravante = agravante;
	}

	/**
	 * Obtém multa
	 * 
	 * @return multa
	 */
	public BigDecimal getMulta() {
		return multa;
	}

	/**
	 * Define multa
	 * 
	 * @param multa
	 *  		  multa
	 */
	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}


}
