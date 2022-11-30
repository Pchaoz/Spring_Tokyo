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
	
	@GetMapping(path="/TirarDaus")
	public @ResponseBody List<Integer> resultadosDados(){
		List<Integer> resultadosDados =new  ArrayList<Integer> ();
		for (int i = 0; i < 6; i++) {
			resultadosDados.add(0);
		}
		int var = 0;
		Random azar = new Random();
		int dau;
		for (int i = 0; i < 6; i++) {
			dau = azar.nextInt(1, 7);

			switch (dau) {
			case 1:
				var = resultadosDados.get(0) + 1;
				resultadosDados.set(0, var);
				break;
			case 2:
				var = resultadosDados.get(1) + 1;
				resultadosDados.set(1, var);
				break;
			case 3:
				var = resultadosDados.get(2) + 1;
				resultadosDados.set(2, var);
				break;
			case 4:
				var = resultadosDados.get(3) + 1;
				resultadosDados.set(3, var);
				break;
			case 5:
				var = resultadosDados.get(4) + 1;
				resultadosDados.set(4, var);
				break;
			case 6:
				var = resultadosDados.get(5) + 1;
				resultadosDados.set(5, var);
				break;
			}

		}
		return resultadosDados;
	}
}
