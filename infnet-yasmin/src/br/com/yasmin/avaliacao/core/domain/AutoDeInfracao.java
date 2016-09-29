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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Esta classe representa informa��es sobre um AutoDeInfracao.
 * 
 * Classe <code>AutoDeInfracao</code>
 * 
 * @author yasminfarias
 * @version 1.0 (27/09/2016)
 *
 */
@Entity
@Table(name = "INFNET_AUTO_INFRACAO_TB")
public class AutoDeInfracao implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = -1092962393118605228L;

	/** id auto de infração */
	@Id
	@Column(name = "ID_AUTO_INFRACAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_infracao_id_seq")
	@SequenceGenerator(name = "auto_infracao_id_seq", sequenceName = "INFNET_AUT_INFR_ID_AUT_INFR_SQ", allocationSize = 1)
	private Integer idAutoDeInfracao;
	
	/** processo */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PROCESSO", referencedColumnName = "ID_PROCESSO", nullable = true)
	private Processo processo;
	
	/** gravidade */
	@Column(name = "GRAVIDADE")
	private Integer gravidade;
	
	/** atenuante */
	@Column(name = "ATENUANTE")
	private boolean atenuante;
	
	/** agravante */
	@Column(name = "AGRAVANTE")
	private boolean agravante;
	
	/** multa */
	@Column(name = "MULTA")
	private BigDecimal multa;

	/**
	 * Instancia um novo objeto AutoDeInfracao
	 */
	public AutoDeInfracao() {
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
	public AutoDeInfracao(Integer idAutoDeInfracao, Processo processo,
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
	public Processo getProcesso() {
		return processo;
	}

	/**
	 * Define processo
	 * 
	 * @param processo
	 *  		  processo
	 */
	public void setProcesso(Processo processo) {
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
