package KingOfMelones.Controller;

import java.util.List;

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
		MonstreServices monstreServices = new MonstreServices();
		List<Monstre> llistaMonstres = monstreServices.findByEleminat(false);
		int random = (int) Math.random() * llistaMonstres.size();
		Monstre monstreAleatori = llistaMonstres.get(random);
		monstreServices.editar(monstreAleatori).setToquio(true);
		
		
		return "S' ha mogut el monstre " + monstreAleatori.getNom() + " a Tokio";
	}
	
	

}
