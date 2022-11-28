package KingOfMelones.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import KingOfMelones.Services.JugadorServices;
import KingOfMelones.Services.MonstreServices;
import KingOfMelones.Services.PartidaServices;

@Controller
public class MainController {
	@Autowired
	PartidaServices partidaServices;
	
	@Autowired
	JugadorServices jugadorServices;
	
	@Autowired
	MonstreServices monstreServices;
	
	@GetMapping(path = "/") // Map de prova, landing
	public @ResponseBody String welcome() {
		return "<h1> Funciona </h1>";
		
	}
	
	@GetMapping(path = "/error") // Map de prova, landing
	public @ResponseBody String error() {
		return "Ups, ha hagut un errorsito";
		
	}
	
	

}
