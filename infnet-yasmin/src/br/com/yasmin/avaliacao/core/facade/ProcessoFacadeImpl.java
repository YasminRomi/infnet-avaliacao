package br.com.yasmin.avaliacao.core.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.yasmin.avaliacao.core.dao.ProcessoDAO;
import br.com.yasmin.avaliacao.core.domain.Fornecedor;
import br.com.yasmin.avaliacao.core.domain.Processo;
import br.com.yasmin.avaliacao.core.dto.FornecedorDTO;
import br.com.yasmin.avaliacao.core.dto.ProcessoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.helper.BasicDAOObject;
import br.com.yasmin.avaliacao.core.helper.BasicServiceObjectImpl;

/**
 * Classe que implementa os serviços da interface ProcessoFacade
 * 
 * Classe <code>ProcessoFacadeImpl</code>
 * 
 * @author yasminfarias
 * @version 1.0(28/09/2016)
 */
@Service("ProcessoService")
@Transactional(propagation = Propagation.REQUIRED)
public class ProcessoFacadeImpl extends BasicServiceObjectImpl<Processo> implements ProcessoFacade {

	/** Constante serialVersionUID */
	private static final long serialVersionUID = -5362036875097283445L;
	
	/** Processo dao */
	@Autowired
	private ProcessoDAO dao;

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.ProcessoFacade#consultar(br.com.yasmin.avaliacao.core.bean.ProcessoDTO)
	 */
	@Override
	public List<ProcessoDTO> consultar(ProcessoDTO processo) throws ApplicationException {
		
		List<ProcessoDTO> beans = new ArrayList<ProcessoDTO>();
		Processo dominio = new Processo();
		BeanUtils.copyProperties(processo, dominio);
		
		if (processo.getFornecedor() != null) {
			Fornecedor fornecedor = new Fornecedor();
			BeanUtils.copyProperties(processo.getFornecedor(), fornecedor);
			dominio.setFornecedor(fornecedor);
		}
		
		List<Processo> lista = this.dao.consultar(dominio);
		
		for (Processo unidade : lista) {
			ProcessoDTO bean = new ProcessoDTO();
			BeanUtils.copyProperties(unidade, bean);
			
			if (unidade.getFornecedor() != null) {
				FornecedorDTO fornecedor = new FornecedorDTO();
				BeanUtils.copyProperties(unidade.getFornecedor(), fornecedor);
				bean.setFornecedor(fornecedor);
			}
			
			beans.add(bean);
		}
		
		return beans;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.ProcessoFacade#salvar(br.com.yasmin.avaliacao.core.bean.ProcessoDTO)
	 */
	@Override
	public void salvar(ProcessoDTO processo) throws ApplicationException {
		Processo dominio = new Processo();
		BeanUtils.copyProperties(processo, dominio);
		
		if (processo.getFornecedor() != null) {
			Fornecedor fornecedor = new Fornecedor();
			BeanUtils.copyProperties(processo.getFornecedor(), fornecedor);
			dominio.setFornecedor(fornecedor);
		}
		
		this.save(dominio);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.ProcessoFacade#excluir(int)
	 */
	@Override
	public void excluir(int id) throws ApplicationException {
		Processo dominio = new Processo();
		dominio.setIdProcesso(id);
		this.delete(dominio);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.helper.BasicServiceObjectImpl#getDao()
	 */
	@Override
	protected BasicDAOObject<Processo> getDao() {
		return this.dao;
	}

}
