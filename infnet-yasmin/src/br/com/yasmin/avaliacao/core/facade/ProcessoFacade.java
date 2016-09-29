package br.com.yasmin.avaliacao.core.facade;

import java.util.List;

import br.com.yasmin.avaliacao.core.dto.ProcessoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;

/**
 * Interface que define os serviços para um Processo.
 * 
 * Interface <code>ProcessoFacade</code>
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
public interface ProcessoFacade {

	/**
	 * Consulta fornecedor.
	 * 
	 * @param fornecedor
	 *            fornecedor
	 * @return list
	 * @throws ApplicationException
	 *             application exception
	 */
	List<ProcessoDTO> consultar(ProcessoDTO processo) throws ApplicationException;

	/**
	 * Salvar fornecedor.
	 * 
	 * @param fornecedor
	 *            fornecedor
	 * @throws ApplicationException
	 *             application exception
	 */
	void salvar(ProcessoDTO processo) throws ApplicationException;

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
