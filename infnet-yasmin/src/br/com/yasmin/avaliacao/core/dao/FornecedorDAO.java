package br.com.yasmin.avaliacao.core.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.yasmin.avaliacao.core.domain.Fornecedor;
import br.com.yasmin.avaliacao.core.helper.BasicDAOObject;

/**
 * Classe DAO para manipulação de dados da classe de dominio Fornecedor.
 * 
 * Classe <code>FornecedorDAO</code>
 * 
 * @author yasminfarias
 * @version 1.0 (02/05/2015)
 */
@Repository
public class FornecedorDAO extends BasicDAOObject<Fornecedor> {
	
	/**
	 * Consultar Fornecedor
	 * 
	 * @param Fornecedor
	 *            Fornecedor
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<Fornecedor> consultar(final Fornecedor fornecedor) {
		
		Criteria criteria = this.getCurrentSession().createCriteria(Fornecedor.class);
		
		if (fornecedor != null) {
		
			if (fornecedor.getIdFornecedor() != null) {
				criteria.add(Restrictions.eq("idFornecedor", fornecedor.getIdFornecedor()));
			}
	
			if (StringUtils.isNotBlank(fornecedor.getCnpj())) {
				criteria.add(Restrictions.eq("cnpj", fornecedor.getCnpj()));
			}
			
			if (StringUtils.isNotBlank(fornecedor.getInscricaoMunicipal())) {
				criteria.add(Restrictions.eq("inscricaoMunicipal", fornecedor.getInscricaoMunicipal()));
			}
			
			if (StringUtils.isNotBlank(fornecedor.getRazaoSocial())) {
				criteria.add(Restrictions.eq("razaoSocial", fornecedor.getRazaoSocial()));
			}
			
			if (fornecedor.getReceitaBruta() != null) {
				criteria.add(Restrictions.eq("receitaBruta", fornecedor.getReceitaBruta()));
			}
			
			if (fornecedor.getEndereco() != null) {
				
				if (fornecedor.getEndereco().getIdEndereco() != null)
				criteria.add(Restrictions.eq("endereco.idEndereco", fornecedor.getEndereco().getIdEndereco()));
			}
		
		}

		criteria.addOrder(Order.asc("idFornecedor"));

		return criteria.list();
	}

}
