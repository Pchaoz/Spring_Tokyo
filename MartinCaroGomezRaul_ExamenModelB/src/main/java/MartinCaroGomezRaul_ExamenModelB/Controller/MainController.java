package MartinCaroGomezRaul_ExamenModelB.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import MartinCaroGomezRaul_ExamenModelB.Services.PokemonServices;

@Controller
public class MainController {

	@Autowired
	PokemonServices pokemonServices;
	
	@GetMapping(path = "/") // Map de prova, landing
	public @ResponseBody String welcome() {
		return "<h1> Spring Funciona </h1>";

	}
}
