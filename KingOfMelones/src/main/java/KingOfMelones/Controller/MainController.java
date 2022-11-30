package KingOfMelones.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import KingOfMelones.Model.Jugador;
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

	@GetMapping(path = "/errorPenal") // Map de prova, landing
	public @ResponseBody String error() {
		return "Ups, ha hagut un errorsito";

	}

	@GetMapping(path = "/findAll") // Map de prova, landing
	public @ResponseBody List<Monstre> findAll() {
		return monstreServices.findAll();

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



	@GetMapping(path="/ListarMonstreJugador/{id}")
	public @ResponseBody String ListarMonstreJugador(@PathVariable int id){
		Jugador jugadorBuscado=jugadorServices.findById(id);
		List<Monstre> LlistaMonstres= monstreServices.findByJugador(jugadorBuscado);
		Monstre monstreFinal =LlistaMonstres.get(0);

		if(monstreFinal!=null) {
			return("El nom del monstre es "+monstreFinal.getNom()+", la seva vida actual es de "+monstreFinal.getVides()+", la seva energia actual es de "
					+monstreFinal.getEnergia()+" i els seus punts de victoria actuals es de "+monstreFinal.getP_victoria()+".");
		}else {
			return ("Monstre no trobat!");
		}

	}

	@GetMapping(path="/ListMonstresVius")
	public @ResponseBody List <Monstre>findVius(){
		//aqui hay que actualizar los monstrups vivos
		List <Monstre> monstresVius=monstreServices.findByEleminatAndIsCarta(false,false);
		
		return monstresVius;
	}
	
	@GetMapping(path="/CountMonstresVius")
	public @ResponseBody String countMonstresVius(){
		
		List<Monstre> monstresVius = monstreServices.findByEleminatAndIsCarta(false, false);
		int count = monstresVius.size();
		
		return "Queden " + count + " monstres vius.";
	}
	
	@GetMapping(path="/MonstreViu")
	public @ResponseBody Monstre monstreViu() {
		List<Monstre> monstresVius = monstreServices.findByEleminatAndIsCarta(false, false);
		if(monstresVius.size() == 1) {
			return monstresVius.get(0);
		} else {
			return null;
		}	
	}
	
	@GetMapping(path="/MonstreMaxPuntVictoria")
	public @ResponseBody Monstre monstreMaxPuntVictoria() {
		List<Monstre> monstrePunts = monstreServices.findByEleminatAndIsCartaOrderByPvictoriaDesc(false, false);
		Monstre mons = monstrePunts.get(0);	
		return mons;
	}
	
	@GetMapping(path="/ListMostresViusContrincants/{idMonstre}")
	public @ResponseBody List<Monstre> listMonstresViusContrincants(@PathVariable int idMonstre) {
		List<Monstre> monstresVius = monstreServices.findByEleminatAndIsCarta(false, false);
		List<Monstre> monstresContrincants = new ArrayList<Monstre>();
		for (Monstre monstre : monstresVius) {
			if(monstre.getId() != idMonstre) {
				monstresContrincants.add(monstre);
			}
		}
		return monstresContrincants;
	}
	
	@GetMapping(path="/ListMonstrePoderLliure")
	public @ResponseBody List<Monstre> listMonstrePoderLliure() {
		List<Monstre> monstresVius = monstreServices.findByIsCartaAndMonstreCarta(true, null);
		return monstresVius;
	}
}
