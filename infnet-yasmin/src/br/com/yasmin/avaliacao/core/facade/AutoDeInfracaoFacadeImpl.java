package br.com.yasmin.avaliacao.core.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.yasmin.avaliacao.core.dao.AutoDeInfracaoDAO;
import br.com.yasmin.avaliacao.core.domain.AutoDeInfracao;
import br.com.yasmin.avaliacao.core.domain.Processo;
import br.com.yasmin.avaliacao.core.dto.AutoDeInfracaoDTO;
import br.com.yasmin.avaliacao.core.dto.ProcessoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.helper.BasicDAOObject;
import br.com.yasmin.avaliacao.core.helper.BasicServiceObjectImpl;

/**
 * Classe que implementa os serviços da interface AutoDeInfracaoFacade
 * 
 * Classe <code>AutoDeInfracaoFacadeImpl</code>
 * 
 * @author yasminfarias
 * @version 1.0(28/09/2016)
 */
@Service("AutoDeInfracaoService")
@Transactional(propagation = Propagation.REQUIRED)
public class AutoDeInfracaoFacadeImpl extends BasicServiceObjectImpl<AutoDeInfracao> implements AutoDeInfracaoFacade {

	/** Constante serialVersionUID */
	private static final long serialVersionUID = -615186712799549156L;
	
	/** AutoDeInfracao dao */
	@Autowired
	private AutoDeInfracaoDAO dao;

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.AutoDeInfracaoFacade#consultar(br.com.yasmin.avaliacao.core.bean.AutoDeInfracaoDTO)
	 */
	@Override
	public List<AutoDeInfracaoDTO> consultar(AutoDeInfracaoDTO autoDeInfracao) throws ApplicationException {
		
		List<AutoDeInfracaoDTO> beans = new ArrayList<AutoDeInfracaoDTO>();
		AutoDeInfracao dominio = new AutoDeInfracao();
		BeanUtils.copyProperties(autoDeInfracao, dominio);
		
		if (autoDeInfracao.getProcesso() != null) {
			Processo processo = new Processo();
			BeanUtils.copyProperties(autoDeInfracao.getProcesso(), processo);
			dominio.setProcesso(processo);
		}
		
		List<AutoDeInfracao> lista = this.dao.consultar(dominio);
		
		for (AutoDeInfracao unidade : lista) {
			AutoDeInfracaoDTO bean = new AutoDeInfracaoDTO();
			BeanUtils.copyProperties(unidade, bean);
			
			if (unidade.getProcesso() != null) {
				ProcessoDTO processo = new ProcessoDTO();
				BeanUtils.copyProperties(unidade.getProcesso(), processo);
				bean.setProcesso(processo);
			}
			
			beans.add(bean);
		}
		
		return beans;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.AutoDeInfracaoFacade#salvar(br.com.yasmin.avaliacao.core.bean.AutoDeInfracaoDTO)
	 */
	@Override
	public void salvar(AutoDeInfracaoDTO autoDeInfracao) throws ApplicationException {
		AutoDeInfracao dominio = new AutoDeInfracao();
		BeanUtils.copyProperties(autoDeInfracao, dominio);
		
		if (autoDeInfracao.getProcesso() != null) {
			Processo processo = new Processo();
			BeanUtils.copyProperties(autoDeInfracao.getProcesso(), processo);
			dominio.setProcesso(processo);
		}
		
		this.save(dominio);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.facade.AutoDeInfracaoFacade#excluir(int)
	 */
	@Override
	public void excluir(int id) throws ApplicationException {
		AutoDeInfracao dominio = new AutoDeInfracao();
		dominio.setIdAutoDeInfracao(id);
		this.delete(dominio);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.yasmin.avaliacao.core.helper.BasicServiceObjectImpl#getDao()
	 */
	@Override
	protected BasicDAOObject<AutoDeInfracao> getDao() {
		return this.dao;
	}

}
