package br.com.yasmin.avaliacao.core.facade;

import java.util.List;

import br.com.yasmin.avaliacao.core.dto.EnderecoDTO;
import br.com.yasmin.avaliacao.core.exception.ApplicationException;

/**
 * Interface que define os serviços para um Endereco.
 * 
 * Interface <code>EnderecoFacade</code>
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
public interface EnderecoFacade {
	
	/**
	 * Consulta endereco.
	 * 
	 * @param endereco
	 *            endereco
	 * @return list
	 * @throws ApplicationException
	 *             application exception
	 */
	List<EnderecoDTO> consultar(EnderecoDTO endereco) throws ApplicationException;

	/**
	 * Salvar endereco.
	 * 
	 * @param empresa
	 *            empresa
	 * @throws ApplicationException
	 *             application exception
	 */
	void salvar(EnderecoDTO endereco) throws ApplicationException;

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
