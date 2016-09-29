package br.com.yasmin.avaliacao.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.yasmin.avaliacao.core.domain.AutoDeInfracao;
import br.com.yasmin.avaliacao.core.domain.Fornecedor;
import br.com.yasmin.avaliacao.core.helper.BasicDAOObject;

/**
 * Classe DAO para manipulação de dados da classe de dominio AutoDeInfracao.
 * 
 * Classe <code>AutoDeInfracaoDAO</code>
 * 
 * @author yasminfarias
 * @version 1.0 (02/05/2015)
 */
@Repository
public class AutoDeInfracaoDAO extends BasicDAOObject<AutoDeInfracao> {
	
	/**
	 * Consultar AutoDeInfracao
	 * 
	 * @param AutoDeInfracao
	 *            AutoDeInfracao
	 * @return AutoDeInfracao
	 */
	@SuppressWarnings("unchecked")
	public List<AutoDeInfracao> consultar(final AutoDeInfracao autoDeInfracao) {
		
		Criteria criteria = this.getCurrentSession().createCriteria(Fornecedor.class);
		
		if (autoDeInfracao != null) {
		
			if (autoDeInfracao.getIdAutoDeInfracao() != null) {
				criteria.add(Restrictions.eq("idAutoDeInfracao", autoDeInfracao.getIdAutoDeInfracao()));
			}
	
			if (autoDeInfracao.getGravidade() != null) {
				criteria.add(Restrictions.eq("gravidade", autoDeInfracao.getGravidade()));
			}
			
			if (autoDeInfracao.getMulta() != null) {
				criteria.add(Restrictions.eq("multa", autoDeInfracao.getMulta()));
			}
			
			if (autoDeInfracao.getProcesso() != null) {
				
				if (autoDeInfracao.getProcesso().getIdProcesso() != null)
				criteria.add(Restrictions.eq("processo.idProcesso", autoDeInfracao.getProcesso().getIdProcesso()));
			}
		
		}

		criteria.addOrder(Order.asc("idAutoDeInfracao"));

		return criteria.list();
	}

}
