package br.com.yasmin.avaliacao.core.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.yasmin.avaliacao.core.dao.EnderecoDAO;
import br.com.yasmin.avaliacao.core.domain.Endereco;
import br.com.yasmin.avaliacao.core.dto.EnderecoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.helper.BasicDAOObject;
import br.com.yasmin.avaliacao.core.helper.BasicServiceObjectImpl;

/**
 * Classe que implementa os serviços da interface EnderecoFacade
 * 
 * Classe <code>EnderecoFacadeImpl</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
@Service("EnderecoService")
@Transactional(propagation = Propagation.REQUIRED)
public class EnderecoFacadeImpl extends BasicServiceObjectImpl<Endereco> implements EnderecoFacade {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5279166661928506453L;

	/** endereco dao. */
	@Autowired
	private EnderecoDAO dao;
	
	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.EnderecoFacade#consultar(br.com.yasmin.avaliacao.core.bean.EnderecoDTO)
	 */
	@Override
	public List<EnderecoDTO> consultar(EnderecoDTO endereco) throws ApplicationException {
		
		List<EnderecoDTO> beans = new ArrayList<EnderecoDTO>();
		Endereco dominio = new Endereco();
		BeanUtils.copyProperties(endereco, dominio);
		
		List<Endereco> lista = this.dao.consultar(dominio);
		
		for (Endereco unidade : lista) {
			EnderecoDTO bean = new EnderecoDTO();
			BeanUtils.copyProperties(unidade, bean);
			beans.add(bean);
		}
		
		return beans;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.EnderecoFacade#salvar(br.com.yasmin.avaliacao.core.bean.EnderecoDTO)
	 */
	@Override
	public void salvar(EnderecoDTO endereco) throws ApplicationException {
		Endereco dominio = new Endereco();
		BeanUtils.copyProperties(endereco, dominio);
		this.save(dominio);
		
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.EnderecoFacade#excluir(int)
	 */
	@Override
	public void excluir(int id) throws ApplicationException {
		Endereco dominio = new Endereco();
		dominio.setIdEndereco(id);
		this.delete(dominio);
		
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.helper.BasicServiceObjectImpl#getDao()
	 */
	@Override
	protected BasicDAOObject<Endereco> getDao() {
		return this.dao;
	}

}
