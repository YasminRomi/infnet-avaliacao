package br.com.yasmin.avaliacao.web.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yasmin.avaliacao.core.dto.FornecedorDTO;
import br.com.yasmin.avaliacao.core.domain.Fornecedor;
import br.com.yasmin.avaliacao.web.controller.FornecedorController;

/**
 * Classe responsavel por realizar as conversoes necessarios com listas para a visao
 * 
 * Classe <code>FornecedorConverter</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
@Service
@FacesConverter(forClass = Fornecedor.class)
public class FornecedorConverter implements Converter {
	
	/** fornecedor service. */
	@Autowired
	private FornecedorController controller;

	/*
	 * (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}

		try {
			final Integer val = Integer.parseInt(value);

			FornecedorDTO fornecedor = new FornecedorDTO();
			fornecedor.setIdFornecedor(val);

			List<FornecedorDTO> fornecedores = this.controller.consultar(fornecedor);

			if (fornecedores != null) {
				return fornecedores.get(0);
			}

			return null;

		} catch (final Throwable e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value == null) {
			return null;
		}
		if (value instanceof FornecedorDTO) {
			if (((FornecedorDTO) value).getIdFornecedor() != null) {
				return ((FornecedorDTO) value).getIdFornecedor().toString();
			} else {
				return "";
			}
		} else if (value instanceof Integer && value != null) {
			return ((Integer) value).toString();
		} else {
			return value.toString();
		}
	}

}
