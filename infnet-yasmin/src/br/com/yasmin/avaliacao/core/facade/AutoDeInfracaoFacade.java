package br.com.yasmin.avaliacao.core.facade;

import java.util.List;

import br.com.yasmin.avaliacao.core.dto.AutoDeInfracaoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;

/**
 * Interface que define os serviços para um AutoDeInfracao.
 * 
 * Interface <code>AutoDeInfracaoFacade</code>
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
public interface AutoDeInfracaoFacade {
	
	/**
	 * Consulta autoDeInfracao.
	 * 
	 * @param autoDeInfracao
	 *            autoDeInfracao
	 * @return list
	 * @throws ApplicationException
	 *             application exception
	 */
	List<AutoDeInfracaoDTO> consultar(AutoDeInfracaoDTO autoDeInfracao) throws ApplicationException;

	/**
	 * Salvar autoDeInfracao.
	 * 
	 * @param autoDeInfracao
	 *            autoDeInfracao
	 * @throws ApplicationException
	 *             application exception
	 */
	void salvar(AutoDeInfracaoDTO autoDeInfracao) throws ApplicationException;

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
