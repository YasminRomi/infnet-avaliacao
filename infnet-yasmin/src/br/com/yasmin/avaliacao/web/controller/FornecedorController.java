package br.com.yasmin.avaliacao.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.yasmin.avaliacao.core.dto.FornecedorDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;
import br.com.yasmin.avaliacao.core.facade.FornecedorFacade;

/**
 * Classe de controle do cadastro de fornecedores.
 * Objetivo de delegar as necessidades das interfaces.
 * 
 * Classe <code>FornecedorController</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
@Controller
public class FornecedorController {
	
	/** fornecedor service. */
	@Autowired
	private FornecedorFacade fornecedorService;
	
	/**
	 * Instancia um novo Controller.
	 */
	public FornecedorController() {
		super();
	}
	
	/**
	 * Consultar fornecedor utilizando o filtro.
	 * 
	 * @param fornecedor
	 *            fornecedor
	 * @return the list
	 * @throws ApplicationException
	 *             application exception
	 */
	public List<FornecedorDTO> consultar(final FornecedorDTO fornecedor) throws ApplicationException {
		return this.fornecedorService.consultar(fornecedor);
	}


	/**
	 * Salvar - alterar ou incluir um fornecedor.
	 * 
	 * @param fornecedor
	 *            fornecedor.
	 * @throws ApplicationException
	 *             application exception
	 */
	public void salvar(final FornecedorDTO fornecedor) throws ApplicationException {
		this.fornecedorService.salvar(fornecedor);
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
		this.fornecedorService.excluir(id);
	}

}
