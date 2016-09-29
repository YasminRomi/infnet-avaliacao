package br.com.yasmin.avaliacao.core.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Fornece ações utilitárias para o Reflection do Java
 * 
 * Classe <code>ReflectionHelper</code>.
 * 
 * @author Arquitetura Brasilcap
 * @version 1.0 (14/10/2014)
 * @version 1.1 (07/06/2016)
 */
public class ReflectionHelper {

	/**
	 * Recupera os atributos de uma classe, ignorando o atributo
	 * serialVersionUID.
	 * 
	 * @param clazz
	 *            Classe a ter seus atributos exibidos.
	 * @return class Lista dos atributos recuperados da classe.
	 */
	public static List<Field> getClassFields(final Class<?> clazz) {
		final List<Field> result = new ArrayList<Field>();
		getClassFields(clazz, result);
		return result;
	}

	/**
	 * Recupera o valor de um atributo de um objeto.
	 * 
	 * @param field
	 *            Objeto representativo do atributo.
	 * @param sourceObj
	 *            Objeto de origem, onde o atributo reside.
	 * @return field Valor do atributo solicitado.
	 */
	public static Object getFieldValue(final Field field, final Object sourceObj) {
		if (field != null && sourceObj != null) {
			final String fieldName = field.getName();
			return getFieldValue(fieldName, sourceObj, field.getType());
		}
		return null;
	}

	/**
	 * Recupera o valor do atributo de um objeto.
	 * 
	 * @param fieldPath
	 *            Caminho para o atributo a partir do sourceObj. Por exemplo:
	 *            id.nome.
	 * @param sourceObj
	 *            Objeto de origem, onde o atributo pode ser navegado.
	 * @return field Valor do atributo ou nulo caso o atributo nÃ£o possa ser
	 *         lido.
	 */
	@Deprecated
	public static Object getFieldValue(String fieldPath, final Object sourceObj) {
		return getFieldValue(fieldPath, sourceObj, null);
	}
	
	/**
	 * Recupera o valor do atributo de um objeto.
	 * 
	 * @param fieldPath
	 *            Caminho para o atributo a partir do sourceObj. Por exemplo:
	 *            id.nome.
	 * @param sourceObj
	 *            Objeto de origem, onde o atributo pode ser navegado.
	 * @param type 
	 * @return field Valor do atributo ou nulo caso o atributo nÃ£o possa ser
	 *         lido.
	 */
	public static Object getFieldValue(String fieldPath, final Object sourceObj, Type type) {
		if (fieldPath == null || fieldPath.isEmpty() || sourceObj == null) {
			return null;
		}
		String fieldName = "";
		if (fieldPath.indexOf(".") >= 0) {
			fieldName = fieldPath.substring(0, fieldPath.indexOf("."));
		} else {
			fieldName = fieldPath;
		}
		
		String methodName = "get";
		
		if (type != null && type.toString().equals("boolean")){
			methodName = "is";
		}
		
		methodName += fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		
		try {
			final Object fieldValue = sourceObj.getClass()
					.getMethod(methodName, new Class[0])
					.invoke(sourceObj, new Object[0]);
			if (fieldValue == null) {
				return null;
			}
			if (fieldValue instanceof String && ((String) fieldValue).isEmpty()) {
				return null;
			}
			try {
				fieldPath = fieldPath.substring(fieldPath.indexOf(fieldName) + fieldName.length() + 1);
				return getFieldValue(fieldPath, fieldValue, null);
			} catch (final StringIndexOutOfBoundsException se) {
				return fieldValue;
			}
		} catch (final Throwable e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Informa o valor para o atributo de um objeto. Em caso de erros gera uma
	 * excecao do tipo RuntimeException.
	 * 
	 * @param fieldPath
	 *            Caminho para o atributo a partir do objeto especificado em
	 *            destObject.
	 * @param fieldValue
	 *            Valor a ser inserido no atributo do objeto.
	 * @param destObject
	 *            O objeto que ira sofrer a acao.
	 */
	public static void setFieldValue(final String fieldPath,
			final Object fieldValue, Object destObject) {
		String fieldName = fieldPath;
		if (fieldPath.indexOf(".") > -1) {
			final String refObjPath = fieldPath.substring(0,
					fieldPath.lastIndexOf("."));
			destObject = getFieldValue(refObjPath, destObject, null);
			fieldName = fieldPath.substring(fieldPath.lastIndexOf(".") + 1);
		}
		final String methodName = "set"
				+ fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		try {
			final Field field = findField(fieldName, destObject.getClass());
			if (field != null) {
				destObject.getClass()
						.getMethod(methodName, new Class[] { field.getType() })
						.invoke(destObject, new Object[] { fieldValue });
			}
		} catch (final Throwable e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Busca por um determinado campo na classe.
	 * 
	 * @param fieldName
	 *            Nome do campo a ser encontrado
	 * @param sourceClass
	 *            Classe de origem a ser encontrado o campo
	 * @return Campo solicitado, caso tenha sido encontrado
	 */
	private static Field findField(final String fieldName,
			final Class<?> sourceClass) {
		final List<Field> fields = getClassFields(sourceClass);
		for (final Field field : fields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * Recupera os atributos de uma classe, ignorando o atributo
	 * serialVersionUID.
	 * 
	 * @param clazz
	 *            Classe a ter seus atributos exibidos.
	 * @param fields
	 *            Lista dos atributos recuperados da classe.
	 */
	private static void getClassFields(final Class<?> clazz, List<Field> fields) {
		if (fields == null) {
			fields = new ArrayList<Field>();
		}
		final Field[] classFields = clazz.getDeclaredFields();
		for (final Field field : classFields) {
			if (!"serialVersionUID".equalsIgnoreCase(field.getName())) {
				fields.add(field);
			}
		}
		if (!clazz.getSuperclass().getName()
				.equalsIgnoreCase("java.lang.Object")) {
			getClassFields(clazz.getSuperclass(), fields);
		}
	}

	/**
	 * Find class annotation.
	 * 
	 * @param clazz
	 *            clazz
	 * @return annotation[]
	 */
	public static Annotation[] findClassAnnotation(final Class<?> clazz) {
		return clazz.getAnnotations();
	}

	/**
	 * Find method annotation.
	 * 
	 * @param clazz
	 *            clazz
	 * @param methodName
	 *            method name
	 * @return annotation[]
	 */
	public static Annotation[] findMethodAnnotation(final Class<?> clazz,
			final String methodName) {

		Annotation[] annotations = null;
		try {
			final Class<?>[] params = null;
			final Method method = clazz.getDeclaredMethod(methodName, params);
			if (method != null) {
				annotations = method.getAnnotations();
			}
		} catch (final SecurityException e) {
			e.printStackTrace();
		} catch (final NoSuchMethodException e) {
			e.printStackTrace();
		}
		return annotations;
	}

	/**
	 * Find field annotation.
	 * 
	 * @param clazz
	 *            clazz
	 * @param fieldName
	 *            field name
	 * @return annotation[]
	 */
	public static Annotation[] findFieldAnnotation(final Class<?> clazz,
			final String fieldName) {
		Annotation[] annotations = null;
		try {
			final Field field = clazz.getDeclaredField(fieldName);
			if (field != null) {
				annotations = field.getAnnotations();
			}
		} catch (final SecurityException e) {
			e.printStackTrace();
		} catch (final NoSuchFieldException e) {
			e.printStackTrace();
		}
		return annotations;
	}

	/**
	 * Método para verificar se determinado atributo é relacionado através do
	 * mapeamento LAZY.
	 * 
	 * @param field
	 *            - atributo a ser verificado.
	 * @return true caso haja o relacionamento LAZY ou false caso não haja.
	 */
	public static Boolean checkForLazyRelationship(final Field field) {
		if (field == null) {
			return false;
		}
		final OneToMany oneToMany = field.getAnnotation(OneToMany.class);
		final ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
		final OneToOne oneToOne = field.getAnnotation(OneToOne.class);
		final ManyToMany manyToMany = field.getAnnotation(ManyToMany.class);
		if (oneToMany != null && oneToMany.fetch() == FetchType.LAZY) {
			return true;
		}
		if (manyToOne != null && manyToOne.fetch() == FetchType.LAZY) {
			return true;
		}
		if (oneToOne != null && oneToOne.fetch() == FetchType.LAZY) {
			return true;
		}
		if (manyToMany != null && manyToMany.fetch() == FetchType.LAZY) {
			return true;
		}
		return false;
	}

	/**
	 * Recupera o valor do atributo com a anotaÃ§Ã£o Id ou EmbeddedId do JPA.
	 * 
	 * @param sourceObject
	 *            - objeto de origem, onde a anotaÃ§Ã£o do Id ou EmbeddedId
	 *            reside.
	 * @return valor do atributo anotado ou nulo caso nÃ£o encontre ou nÃ£o
	 *         esteja preenchido.
	 */
	public static Object getPersistenceIdValue(final Object sourceObject) {
		if (sourceObject == null) {
			return null;
		}
		final List<Field> fields = getClassFields(sourceObject.getClass());
		for (final Field field : fields) {
			final Annotation idAnnotation = field.getAnnotation(Id.class);
			final Annotation embeddedIdAnnotation = field
					.getAnnotation(EmbeddedId.class);
			if (idAnnotation != null || embeddedIdAnnotation != null) {
				return getFieldValue(field, sourceObject);
			}
		}
		return null;
	}

	/**
	 * Retorna o nome do ID da classe informada.
	 * 
	 * @param clazz
	 *            - objeto de origem, onde a anotação do Id ou EmbeddedId
	 *            reside.
	 * @return Campo ID encontrado
	 */
	public static Field getPersistenceIdName(final Object clazz) {
		if (clazz == null) {
			return null;
		}
		final List<Field> fields = getClassFields(clazz.getClass());
		for (final Field field : fields) {
			final Annotation idAnnotation = field.getAnnotation(Id.class);
			final Annotation embeddedIdAnnotation = field
					.getAnnotation(EmbeddedId.class);
			if (idAnnotation != null || embeddedIdAnnotation != null) {
				return field;
			}
		}
		return null;
	}

	/**
	 * Verifica se existe ao menos uma anotação do tipo passado por parametro
	 * nos métodos da classe.
	 * 
	 * @param clazz
	 *            Classe que deve ser feita a busca da anotação
	 * @param annotation
	 *            Tipo da anotação a ser buscada
	 * @return verdadeiro se existir a anotação no método
	 */
	public static boolean isAnnotationPresentInMethods(final Class<?> clazz,
			final Class<? extends Annotation> annotation) {
		final Method[] methods = clazz.getDeclaredMethods();
		boolean isAnnotationPresent = false;

		for (final Method m : methods) {
			if (m.isAnnotationPresent(annotation)) {
				isAnnotationPresent = true;
			}
		}

		return isAnnotationPresent;
	}

	/**
	 * Verifica se existe ao menos uma anotação do tipo passado por parâmetro
	 * nas propriedades da classe.
	 * 
	 * @param clazz
	 *            Classe que deve ser feita a busca da anotação
	 * @param annotation
	 *            Tipo da anotação a ser buscada
	 * @return verdadeiro se existir a anotação na propriedade
	 */
	public static boolean isAnnotationPresentInFields(final Class<?> clazz,
			final Class<? extends Annotation> annotation) {

		final List<Field> fields = getClassFields(clazz);
		boolean isAnnotationPresent = false;

		for (final Field f : fields) {
			final Annotation[] annotations = f.getAnnotations();
			for (final Annotation a : annotations) {
				if (a.annotationType().equals(annotation)) {
					isAnnotationPresent = true;
				}
			}
		}

		return isAnnotationPresent;
	}
}
