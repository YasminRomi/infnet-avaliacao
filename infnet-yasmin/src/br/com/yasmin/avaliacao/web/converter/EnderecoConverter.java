package br.com.yasmin.avaliacao.web.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yasmin.avaliacao.core.dto.EnderecoDTO;
import br.com.yasmin.avaliacao.core.domain.Endereco;
import br.com.yasmin.avaliacao.web.controller.EnderecoController;

/**
 * Classe responsavel por realizar as conversoes necessarios com listas para a visao
 * 
 * Classe <code>EnderecoConverter</code>.
 * 
 * @author yasminfarias
 * @version 1.0 (28/09/2016)
 */
@Service
@FacesConverter(forClass = Endereco.class)
public class EnderecoConverter implements Converter {
	
	/** endereco service. */
	@Autowired
	private EnderecoController controller;

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

			EnderecoDTO endereco = new EnderecoDTO();
			endereco.setIdEndereco(val);

			List<EnderecoDTO> enderecos = this.controller.consultar(endereco);

			if (enderecos != null) {
				return enderecos.get(0);
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
		if (value instanceof EnderecoDTO) {
			if (((EnderecoDTO) value).getIdEndereco() != null) {
				return ((EnderecoDTO) value).getIdEndereco().toString();
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
