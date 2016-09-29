package br.com.yasmin.avaliacao.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.yasmin.avaliacao.core.dto.EnderecoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.facade.EnderecoFacade;

/**
 * Classe de controle do cadastro de endereços.
 * Objetivo de delegar as necessidades das interfaces.
 * 
 * Classe <code>EnderecoController</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
@Controller
public class EnderecoController {
	
	/** endereco service. */
	@Autowired
	private EnderecoFacade enderecoService;
	
	/**
	 * Instancia um novo Controller.
	 */
	public EnderecoController() {
		super();
	}
	
	/**
	 * Consultar enderecos utilizando o filtro.
	 * 
	 * @param endereco
	 *            endereco
	 * @return the list
	 * @throws ApplicationException
	 *             application exception
	 */
	public List<EnderecoDTO> consultar(final EnderecoDTO endereco) throws ApplicationException {
		return this.enderecoService.consultar(endereco);
	}


	/**
	 * Salvar - alterar ou incluir um endereco.
	 * 
	 * @param endereco
	 *            endereco.
	 * @throws ApplicationException
	 *             application exception
	 */
	public void salvar(final EnderecoDTO endereco) throws ApplicationException {
		this.enderecoService.salvar(endereco);
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
		this.enderecoService.excluir(id);
	}

}
