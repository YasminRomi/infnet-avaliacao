package br.com.yasmin.avaliacao.core.facade;

import java.util.List;

import br.com.yasmin.avaliacao.core.dto.FornecedorDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;

/**
 * Interface que define os serviços para um Fornecedor.
 * 
 * Interface <code>FornecedorFacade</code>
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
public interface FornecedorFacade {

	/**
	 * Consulta fornecedor.
	 * 
	 * @param fornecedor
	 *            fornecedor
	 * @return list
	 * @throws ApplicationException
	 *             application exception
	 */
	List<FornecedorDTO> consultar(FornecedorDTO fornecedor) throws ApplicationException;

	/**
	 * Salvar fornecedor.
	 * 
	 * @param fornecedor
	 *            fornecedor
	 * @throws ApplicationException
	 *             application exception
	 */
	void salvar(FornecedorDTO fornecedor) throws ApplicationException;

	/**
	 * Excluir.
	 * 
	 * @param id
	 *            id
	 * @throws ApplicationException
	 *             application exception
	 */
	void excluir(int id) throws ApplicationException;
}
