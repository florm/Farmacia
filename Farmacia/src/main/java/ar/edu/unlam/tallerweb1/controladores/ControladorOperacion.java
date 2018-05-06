package ar.edu.unlam.tallerweb1.controladores;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorOperacion {
	
	@RequestMapping (path="/operacion/{operacion}/{operando1}/{operando2}", method = RequestMethod.GET)
	public ModelAndView calcular(
			@PathVariable String operacion,
			@PathVariable String operando1,
			@PathVariable String operando2) {
		
	ModelMap modelo = new ModelMap();
	double result = 0.0;
	Double op1 = Double.parseDouble(operando1);
	Double op2 = Double.parseDouble(operando2);
	String formatResultado = "";
	
	switch (operacion.toLowerCase()) {
	case "sumar":
		result = op1+op2;
		int suma = (int) result; 
		formatResultado = suma == result ? String.valueOf(suma) : String.valueOf(result);
		break;
	case "restar":
		result = op1-op2;
		int resta = (int) result; 
		formatResultado = resta == result ? String.valueOf(resta) : String.valueOf(result);
		break;
	case "multiplicar":
		result = new BigDecimal(op1*op2).setScale(2, RoundingMode.HALF_UP).doubleValue();
		int multiplicacion = (int) result; 
		formatResultado = multiplicacion == result ? String.valueOf(multiplicacion) : String.valueOf(result);
		break;
	case "dividir":
		if(op2 == 0){
			modelo.put("error", "No se puede realizar una division por 0");
			modelo.put("operacion", "");
			return new ModelAndView("error", modelo);
			
		}
		result = new BigDecimal(op1/op2).setScale(2, RoundingMode.HALF_UP).doubleValue();
		int division = (int) result; 
		formatResultado = division == result ? String.valueOf(division) : String.valueOf(result);
		break;
	default:
		modelo.put("error","No existe la operacion");
		modelo.put("operacion", operacion);
		return new ModelAndView("error", modelo);
	}

	
	modelo.put("resultado", formatResultado);
	modelo.put("operacion", operacion);
	modelo.put("op1", operando1);
	modelo.put("op2", operando2);
	
	return new ModelAndView("resultado", modelo);
	
	}
}
