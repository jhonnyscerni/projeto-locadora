package com.algaworks.projeto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.projeto.dao.FabricanteDAO;
import com.algaworks.projeto.model.Fabricante;
import com.algaworks.projeto.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = Fabricante.class)
public class FabricanteConverter implements Converter {

	@Inject
	private FabricanteDAO fabricantes;
	
	public FabricanteConverter() {
		fabricantes = CDIServiceLocator.getBean(FabricanteDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Fabricante retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = fabricantes.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Fabricante fabricante = (Fabricante) value;
			return fabricante.getCodigo() == null ? null : fabricante.getCodigo().toString();
		}
		
		return "";
	}

}
