package br.com.yasmin.avaliacao.core.helper;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Classe abstrata que representa um serviço da aplicação.
 * 
 * Classe <code>BasicServiceObjectImpl</code>.
 * 
 * @author @author arquitetura - Brasilcap
 * @version 1.0 (28/10/2014)
 * @param <ENTITY>
 *            o tipo genérico
 */
@Transactional(readOnly = true)
public abstract class BasicServiceObjectImpl<ENTITY> implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6374379474377436979L;

	/**
	 * Permite recuperar o objeto DAO da classe de serviço.
	 * 
	 * @return objeto DAO.
	 */
	protected abstract BasicDAOObject<ENTITY> getDao();

	/**
	 * Método que obtém todos os resultados da tabela
	 * 
	 * @return list
	 */
	public List<ENTITY> findAll() {
		return this.getDao().findAll();
	}

	/**
	 * Salva ou atualiza um objeto de domínio no banco de dados
	 * 
	 * @param entity
	 *            entity
	 */
	@Transactional(readOnly = false)
	public void save(final ENTITY entity) {
		this.getDao().save(entity);
	}

	/**
	 * Remove um objeto de domínio do banco de dados
	 * 
	 * @param entity
	 *            entity
	 */
	@Transactional(readOnly = false)
	public void delete(final ENTITY entity) {
		this.getDao().delete(entity);
	}

	/**
	 * Busca um objeto de domínio pelo ID.
	 * 
	 * @param entity
	 *            entity
	 * @return entity
	 */
	public ENTITY findById(final ENTITY entity) {
		return this.getDao().findById(entity);
	}

	/**
	 * Busca um registro em uma stored procedure pelo ID.
	 * 
	 * @param entity
	 *            entity
	 * @return entity
	 */
	public Object storedProcedurefindById(final ENTITY entity) {
		Object o = null;

		try {
			o = this.getDao().storedProcedurefindById(entity);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return o;
	}
}