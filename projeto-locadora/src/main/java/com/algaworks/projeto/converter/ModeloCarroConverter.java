package com.algaworks.projeto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.projeto.dao.ModeloCarroDAO;
import com.algaworks.projeto.model.ModeloCarro;
import com.algaworks.projeto.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = ModeloCarro.class)
public class ModeloCarroConverter implements Converter {

	@Inject
	private ModeloCarroDAO modelos;
	
	public ModeloCarroConverter() {
		modelos = CDIServiceLocator.getBean(ModeloCarroDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ModeloCarro retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = modelos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			ModeloCarro modelo = (ModeloCarro) value;
			return modelo.getCodigo() == null ? null : modelo.getCodigo().toString();
		}
		
		return "";
	}

}
