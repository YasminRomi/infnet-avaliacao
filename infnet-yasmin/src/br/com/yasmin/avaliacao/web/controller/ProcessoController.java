package br.com.yasmin.avaliacao.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.yasmin.avaliacao.core.dto.ProcessoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.facade.ProcessoFacade;

/**
 * Classe de controle do cadastro de processos.
 * Objetivo de delegar as necessidades das interfaces.
 * 
 * Classe <code>ProcessoController</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
@Controller
public class ProcessoController {
	
	/** processo service. */
	@Autowired
	private ProcessoFacade processoService;
	
	/**
	 * Instancia um novo Controller.
	 */
	public ProcessoController() {
		super();
	}
	
	/**
	 * Consultar processo utilizando o filtro.
	 * 
	 * @param processo
	 *            processo
	 * @return the list
	 * @throws ApplicationException
	 *             application exception
	 */
	public List<ProcessoDTO> consultar(final ProcessoDTO processo) throws ApplicationException {
		return this.processoService.consultar(processo);
	}


	/**
	 * Salvar - alterar ou incluir um processo.
	 * 
	 * @param processo
	 *            processo.
	 * @throws ApplicationException
	 *             application exception
	 */
	public void salvar(final ProcessoDTO processo) throws ApplicationException {
		this.processoService.salvar(processo);
	}

	/**
	 * Excluir.
	 * 
	 * @param id
	 *            id
	 * @throws BusinessException
	 *             business exception
	 * @throws ApplicationException
	 *             application exception
	 */
	public void excluir(final int id) throws ApplicationException {
		this.processoService.excluir(id);
	}

}
