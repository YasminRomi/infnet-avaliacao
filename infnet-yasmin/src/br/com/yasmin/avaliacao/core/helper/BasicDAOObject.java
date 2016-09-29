package br.com.yasmin.avaliacao.core.helper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import oracle.jdbc.OracleTypes;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe contendo os métodos genéricos para manipulação de dados.
 * 
 * @author arquitetura - Brasilcap
 * @version 1.0 (02/09/2014)
 * @version 2.0 (13/11/2015)
 * @version 3.0 (18/12/2015)  
 * 
 * @param <ENTITY>
 *            Tipo genérico
 */
public class BasicDAOObject <ENTITY> {

	/** session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Obtem current session.
	 * 
	 * @return current session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	/**
	 * Método que obtém todos os resultados da tabela.
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<ENTITY> findAll() {
		final String clazzName = this.getClass().getSimpleName()
				.replace("DAO", "");
		final Query query = this.getCurrentSession().createQuery(
				"FROM " + clazzName);

		return query.list();
	}
	
	/**
	 * Salva ou atualiza um objeto de domínio no banco de dados.
	 * 
	 * @param entity
	 *            entity
	 * @return Objeto salvo
	 */
	public ENTITY save(final ENTITY entity) {
		
		this.getCurrentSession().saveOrUpdate(entity);
		this.getCurrentSession().flush();

		return entity;
	}
	
	/**
	 * Insere um objeto de domínio no banco de dados.
	 * 
	 * @param entity
	 *            entity
	 * @return Objeto salvo
	 */
	public ENTITY insert(ENTITY entity) {
		
		this.getCurrentSession().save(entity);
		this.getCurrentSession().flush();
		
		return entity;
	}
	
	/**
	 * Altera um objeto de domínio no banco de dados.
	 * 
	 * @param entity
	 *            entity
	 * @return Objeto salvo
	 */
	public ENTITY update(ENTITY entity) {
		
		this.getCurrentSession().update(entity);
		this.getCurrentSession().flush();
		
		return entity;
	}
	
	/**
	 * Busca um objeto de domínio pelo ID.
	 * 
	 * @param entity
	 *            - objeto de domínio com o ID informado.
	 * @return - objeto de domínio recuperado pelo ID ou nulo caso não encontre.
	 */
	@SuppressWarnings("unchecked")
	public ENTITY findById(final ENTITY entity) {
		if (entity == null) {
			return null;
		}
		final Object entityId = ReflectionHelper.getPersistenceIdValue(entity);
		if (entityId == null) {
			return null;
		}
		try {
			return (ENTITY) this.getCurrentSession().get(entity.getClass(),
					(Serializable) entityId);
		} catch (final Throwable e) {
			if (e instanceof NoResultException) {
				return null;
			}
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Método que obtém todos os resultados da stored procedure.
	 * 
	 * @param entity
	 *            entity
	 * @return list
	 * 
	 * @throws FrameworkException
	 */
	public Object storedProcedurefindById(final ENTITY entity) throws Exception {
		if (entity == null) {
			return null;
		}
		final Object entityId = ReflectionHelper.getPersistenceIdValue(entity);
		if (entityId == null) {
			return null;
		}

		final Connection con = ((SessionImpl) this.getCurrentSession())
				.connection();
		CallableStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareCall("{call "
					+ entity.getClass().getSimpleName().toLowerCase()
					+ "(?, ?) }");
			stmt.setInt(1, Integer.parseInt(String.valueOf(entityId)));
			stmt.registerOutParameter(2, OracleTypes.CURSOR);

			stmt.execute();

			rs = (ResultSet) stmt.getObject(2);

			final List<Field> classFields = ReflectionHelper
					.getClassFields(entity.getClass());

			while (rs.next()) {
				for (final Field f : classFields) {
					ReflectionHelper.setFieldValue(f.getName(),
							rs.getObject(f.getName()), entity);
				}
			}
		} catch (final SQLException e) {
			throw new Exception(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (final SQLException e) {
				throw new Exception(e);
			}
		}

		return entity;
	}
	
	/**
	 * Remove um objeto de domínio do banco de dados.
	 * 
	 * @param entity
	 *            entity
	 */
	public void delete(final ENTITY entity) {
		this.getCurrentSession().delete(this.findById(entity));
	}
	
	/**
	 * Obtem session factory.
	 * 
	 * @return session factory
	 */
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
