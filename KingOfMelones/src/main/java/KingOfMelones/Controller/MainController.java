package KingOfMelones.Controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import KingOfMelones.Model.Monstre;
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
	
	Random rand = new Random();
	
	@GetMapping(path = "/") // Map de prova, landing
	public @ResponseBody String welcome() {
		return "<h1> Funciona </h1>";
		
	}
	
	@GetMapping(path = "/error") // Map de prova, landing
	public @ResponseBody String error() {
		return "Ups, ha hagut un errorsito";
		
	}
	
	@GetMapping(path = "/SetMonstreTokioAleatori") // Funció MonstreTokioAleatori
	public @ResponseBody String SetMonstreTokioAleatori() {
		List<Monstre> llistaMonstres = monstreServices.findByEleminatAndIsCarta(false, false);
		int random = rand.nextInt(llistaMonstres.size());
		Monstre monstreAleatori = llistaMonstres.get(random);
		monstreAleatori.setToquio(true);
		monstreServices.editar(monstreAleatori);
				
		return "S' ha mogut el monstre " + monstreAleatori.getNom() + " a Tokio";
	}
	
	

}
