package br.com.yasmin.avaliacao.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.yasmin.avaliacao.core.dto.AutoDeInfracaoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.facade.AutoDeInfracaoFacade;

/**
 * Classe de controle do cadastro de autos de infração.
 * Objetivo de delegar as necessidades das interfaces.
 * 
 * Classe <code>AutoDeInfracaoController</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
@Controller
public class AutoDeInfracaoController {
	
	/** auto de infracao service. */
	@Autowired
	private AutoDeInfracaoFacade autoDeInfracaoService;
	
	/**
	 * Instancia um novo Controller.
	 */
	public AutoDeInfracaoController() {
		super();
	}
	
	/**
	 * Consultar autos de infracao utilizando o filtro.
	 * 
	 * @param autoDeInfracao
	 *            auto de infracao
	 * @return the list
	 * @throws ApplicationException
	 *             application exception
	 */
	public List<AutoDeInfracaoDTO> consultar(final AutoDeInfracaoDTO autoDeInfracao) throws ApplicationException {
		return this.autoDeInfracaoService.consultar(autoDeInfracao);
	}


	/**
	 * Salvar - alterar ou incluir um auto de infracao.
	 * 
	 * @param autoDeInfracao
	 *            auto de infracao.
	 * @throws ApplicationException
	 *             application exception
	 */
	public void salvar(final AutoDeInfracaoDTO autoDeInfracao) throws ApplicationException {
		this.autoDeInfracaoService.salvar(autoDeInfracao);
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
		this.autoDeInfracaoService.excluir(id);
	}

}
