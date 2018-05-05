package ar.edu.unlam.tallerweb1.controladores;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
public class ControladorOperacion {
	
	@RequestMapping (path="/operacion/{operacion}/{op1}/{op2}", method = RequestMethod.GET)
	public ModelAndView operar(
			@PathVariable String operacion,
			@PathVariable Double op1,
			@PathVariable Double op2) {
		
	Double result = null;
	
	switch (operacion) {
	case "sumar":
		result = op1+op2;
		break;
	case "restar":
		result = op1-op2;	
		break;
	case "multiplicar":
		result = op1*op2;
		break;
	case "dividir":
		if (op2==0) {
			return new ModelAndView("error");
		}else {
			result=op1/op2;
		}
		break;
	default:
		return new ModelAndView("error");
	}

	ModelMap mapa = new ModelMap();
	mapa.put("res", result);
	mapa.put("operacion", operacion);
	mapa.put("op1", op1);
	mapa.put("op2", op2);
	
	return new ModelAndView("resultado", mapa);
	}
}
