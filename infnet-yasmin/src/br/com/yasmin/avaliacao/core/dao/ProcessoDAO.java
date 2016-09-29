package br.com.yasmin.avaliacao.core.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.yasmin.avaliacao.core.domain.Processo;
import br.com.yasmin.avaliacao.core.helper.BasicDAOObject;

/**
 * Classe DAO para manipulação de dados da classe de dominio Processo.
 * 
 * Classe <code>ProcessoDAO</code>
 * 
 * @author yasminfarias
 * @version 1.0 (02/05/2015)
 */
@Repository
public class ProcessoDAO extends BasicDAOObject<Processo> {
	
	/**
	 * Consultar Processo
	 * 
	 * @param Processo
	 *            Processo
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<Processo> consultar(final Processo processo) {
		
		Criteria criteria = this.getCurrentSession().createCriteria(Processo.class);
		
		if (processo != null) {
		
			if (processo.getIdProcesso() != null) {
				criteria.add(Restrictions.eq("idProcesso", processo.getIdProcesso()));
			}
	
			if (StringUtils.isNotBlank(processo.getFiscalResponsavel())) {
				criteria.add(Restrictions.eq("fiscalResponsavel", processo.getFiscalResponsavel()));
			}
			
			if (StringUtils.isNotBlank(processo.getRelatoFiscalizacao())) {
				criteria.add(Restrictions.eq("relatoFiscalizacao", processo.getRelatoFiscalizacao()));
			}
			
			if (processo.getDataRelato() != null) {
				criteria.add(Restrictions.eq("dataRelato", processo.getDataRelato()));
			}
			
			if (processo.getFornecedor() != null) {
				
				if (processo.getFornecedor().getIdFornecedor() != null)
				criteria.add(Restrictions.eq("fornecedor.idFornecedor", processo.getFornecedor().getIdFornecedor()));
			}
		
		}

		criteria.addOrder(Order.asc("idProcesso"));

		return criteria.list();
	}

}
