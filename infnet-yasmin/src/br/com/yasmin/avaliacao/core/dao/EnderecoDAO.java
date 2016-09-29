package br.com.yasmin.avaliacao.core.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.yasmin.avaliacao.core.domain.Endereco;
import br.com.yasmin.avaliacao.core.helper.BasicDAOObject;

/**
 * Classe DAO para manipulação de dados da classe de dominio Endereco.
 * 
 * Classe <code>EnderecoDAO</code>
 * 
 * @author yasminfarias
 * @version 1.0 (02/05/2015)
 */
@Repository
public class EnderecoDAO extends BasicDAOObject<Endereco> {
	
	/**
	 * Consultar Endereco
	 * 
	 * @param Endereco
	 *            Endereco
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<Endereco> consultar(final Endereco endereco) {
		
		Criteria criteria = this.getCurrentSession().createCriteria(Endereco.class);
		
		if (endereco != null) {
		
			if (endereco.getIdEndereco() != null) {
				criteria.add(Restrictions.eq("idEndereco", endereco.getIdEndereco()));
			}
	
			if (StringUtils.isNotBlank(endereco.getBairro())) {
				criteria.add(Restrictions.eq("bairro", endereco.getBairro()));
			}
			
			if (StringUtils.isNotBlank(endereco.getCep())) {
				criteria.add(Restrictions.eq("cep", endereco.getCep()));
			}
			
			if (StringUtils.isNotBlank(endereco.getComplemento())) {
				criteria.add(Restrictions.eq("complemento", endereco.getComplemento()));
			}
			
			if (StringUtils.isNotBlank(endereco.getLogradouro())) {
				criteria.add(Restrictions.eq("logradouro", endereco.getLogradouro()));
			}
			
			if (StringUtils.isNotBlank(endereco.getMunicipio())) {
				criteria.add(Restrictions.eq("municipio", endereco.getMunicipio()));
			}
			
			if (StringUtils.isNotBlank(endereco.getNumero())) {
				criteria.add(Restrictions.eq("numero", endereco.getNumero()));
			}
			
			if (StringUtils.isNotBlank(endereco.getUf())) {
				criteria.add(Restrictions.eq("uf", endereco.getUf()));
			}
		
		}

		criteria.addOrder(Order.asc("idEndereco"));

		return criteria.list();
	}

}
