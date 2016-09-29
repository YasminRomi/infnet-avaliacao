package br.com.yasmin.avaliacao.core.resource;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

/**
 * MessageBundle.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2015)
 */
@Component
public class MessageBundle {

	/** MESSAGE_PROP_BASE. */
	public static final String MESSAGE_PROP_BASE = "br.com.yasmin.avaliacao.core.resource.message";

	/** prop base. */
	private String propBase;

	static {
		Locale.setDefault(new Locale("pt", "BR"));
	}

	/**
	 * Obtem bundle.
	 * 
	 * @return MESSAGE_PROPERTIES_BASE
	 */
	public ResourceBundle getBundle() {
		String base;
		if (null == this.propBase) {
			base = MESSAGE_PROP_BASE;
		} else {
			base = this.propBase;
		}
		return ResourceBundle.getBundle(base);
	}

	/**
	 * Obtem value.
	 * 
	 * @param key chave da propriedade
	 * @return valor da propriedade
	 */
	public String getValue(final String key) {
		return this.getBundle().getString(key);
	}

	/**
	 * Instancia um(a) novo(a) message bundle.
	 */
	public MessageBundle() {
		super();
	}

	/**
	 * Instancia um(a) novo(a) message bundle.
	 * 
	 * @param propBase pacote do arquivo de propriedades
	 */
	public MessageBundle(final String propBase) {
		super();
		this.propBase = propBase;
	}

	/**
	 * Obtem prop base.
	 * 
	 * @return  pacote do arquivo de propriedades
	 */
	public String getPropBase() {
		return this.propBase;
	}

	/**
	 * Define  pacote do arquivo de propriedades.
	 * 
	 * @param propBase novo pacote do arquivo de propriedades
	 * 
	 */
	public void setPropBase(final String propBase) {
		this.propBase = propBase;
	}

}
