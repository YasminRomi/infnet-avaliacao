package br.com.yasmin.avaliacao.core.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.yasmin.avaliacao.core.dao.FornecedorDAO;
import br.com.yasmin.avaliacao.core.domain.Endereco;
import br.com.yasmin.avaliacao.core.domain.Fornecedor;
import br.com.yasmin.avaliacao.core.dto.EnderecoDTO;
import br.com.yasmin.avaliacao.core.dto.FornecedorDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.helper.BasicDAOObject;
import br.com.yasmin.avaliacao.core.helper.BasicServiceObjectImpl;

/**
 * Classe que implementa os serviços da interface FornecedorFacade
 * 
 * Classe <code>FornecedorFacadeImpl</code>
 * 
 * @author yasminfarias
 * @version 1.0(28/09/2016)
 */
@Service("FornecedorService")
@Transactional(propagation = Propagation.REQUIRED)
public class FornecedorFacadeImpl extends BasicServiceObjectImpl<Fornecedor> implements FornecedorFacade {

	/** Constante serialVersionUID */
	private static final long serialVersionUID = -8785216464399304001L;
	
	/** Fornecedor dao */
	@Autowired
	private FornecedorDAO dao;

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.FornecedorFacade#consultar(br.com.yasmin.avaliacao.core.bean.FornecedorDTO)
	 */
	@Override
	public List<FornecedorDTO> consultar(FornecedorDTO fornecedor) throws ApplicationException {
		
		List<FornecedorDTO> beans = new ArrayList<FornecedorDTO>();
		Fornecedor dominio = new Fornecedor();
		BeanUtils.copyProperties(fornecedor, dominio);
		
		if (fornecedor.getEndereco() != null) {
			Endereco endereco = new Endereco();
			BeanUtils.copyProperties(fornecedor.getEndereco(), endereco);
			dominio.setEndereco(endereco);
		}
		
		List<Fornecedor> lista = this.dao.consultar(dominio);
		
		for (Fornecedor unidade : lista) {
			FornecedorDTO bean = new FornecedorDTO();
			BeanUtils.copyProperties(unidade, bean);
			
			if (unidade.getEndereco() != null) {
				EnderecoDTO endereco = new EnderecoDTO();
				BeanUtils.copyProperties(unidade.getEndereco(), endereco);
				bean.setEndereco(endereco);
			}
			
			beans.add(bean);
		}
		
		return beans;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.FornecedorFacade#salvar(br.com.yasmin.avaliacao.core.bean.FornecedorDTO)
	 */
	@Override
	public void salvar(FornecedorDTO fornecedor) throws ApplicationException {
		Fornecedor dominio = new Fornecedor();
		BeanUtils.copyProperties(fornecedor, dominio);
		
		if (fornecedor.getEndereco() != null) {
			Endereco endereco = new Endereco();
			BeanUtils.copyProperties(fornecedor.getEndereco(), endereco);
			dominio.setEndereco(endereco);
		}
		
		this.save(dominio);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.FornecedorFacade#excluir(int)
	 */
	@Override
	public void excluir(int id) throws ApplicationException {
		Fornecedor dominio = new Fornecedor();
		dominio.setIdFornecedor(id);
		this.delete(dominio);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.helper.BasicServiceObjectImpl#getDao()
	 */
	@Override
	protected BasicDAOObject<Fornecedor> getDao() {
		return this.dao;
	}

}
