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

	int contarTorn = 0;
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
	
	@GetMapping(path="/RealitzarTorn/{id}")
	public @ResponseBody String FerTorn(@PathVariable int id) {
		List<Integer> resultadosDados = resultadosDados();
		Monstre monstreActual = monstreServices.findById(id);
		if (monstreActual.isEleminat()) {
			return "Aquest monstre està eliminat";
		} else {
			String fraseFinal="";
			fraseFinal+="Es el torn del monstre amb el nom de "+monstreActual.getNom()+".<br>";
			fraseFinal+= "Els resultats de les tirades son: " + resultadosDados.get(0) + " uns, "
					+ resultadosDados.get(1) + " dosos, " + resultadosDados.get(2) + " tresos, "
					+ resultadosDados.get(3) + " d'energia, " + resultadosDados.get(4) + " garres i "
					+ resultadosDados.get(5) + " cors.<br>";

			if (resultadosDados.get(0) >= 3) {
				switch (resultadosDados.get(0)) {
				case 3:
					fraseFinal+=SumarPuntsMonstre(monstreActual, 1);
					break;
				case 4:
					fraseFinal+=SumarPuntsMonstre(monstreActual, 2);
					break;
				case 5:
					fraseFinal+=SumarPuntsMonstre(monstreActual, 3);
					break;
				case 6:
					fraseFinal += SumarPuntsMonstre(monstreActual, 4);
					break;
				}
			}
			// En cas de sumar punts per daus amb el numeru 2
			if (resultadosDados.get(1) >= 3) {
				switch (resultadosDados.get(1)) {
				case 3:
					fraseFinal += SumarPuntsMonstre(monstreActual, 2);
					break;
				case 4:
					fraseFinal += SumarPuntsMonstre(monstreActual, 3);
					break;
				case 5:
					fraseFinal += SumarPuntsMonstre(monstreActual, 4);
					break;
				case 6:
					fraseFinal += SumarPuntsMonstre(monstreActual, 5);
					break;
				}
			}
			// En cas de sumar punts per daus amb el numeru 3
			if (resultadosDados.get(2) >= 3) {
				switch (resultadosDados.get(2)) {
				case 3:
					fraseFinal += SumarPuntsMonstre(monstreActual, 3);
					break;
				case 4:
					fraseFinal += SumarPuntsMonstre(monstreActual, 4);
					break;
				case 5:
					fraseFinal += SumarPuntsMonstre(monstreActual, 5);
					break;
				case 6:
					fraseFinal += SumarPuntsMonstre(monstreActual, 6);
					break;
				}
			}
			// En cas de sumar punt d'Energia
			if (resultadosDados.get(3) >= 1) {
				fraseFinal += SumarPuntsEnergiaMonstre(monstreActual, resultadosDados.get(3));
			} // En cas de que tingui garres if(resultadosDados.get(4)>=1) {
				// MonstrePega(JugActiu,
				// resultadosDados.get(4)); } //en cas de que tregui cors
			if (resultadosDados.get(5) >= 1) {
				fraseFinal += MonstreCuracio(monstreActual, resultadosDados.get(5));
			}

			return fraseFinal;
		}

	}

	String SumarPuntsMonstre(Monstre monstreActual, int suma) {
		String fraseRetorn = "Els punts de victoria inicial del monstre " + monstreActual.getNom() + " son "
				+ monstreActual.getP_victoria() + ".<br>";
		monstreActual.setP_victoria(monstreActual.getP_victoria() + suma);
		monstreServices.editar(monstreActual);
		fraseRetorn += "Els punts de victoria que suma son " + suma + ", acaba amb un total de "
				+ monstreActual.getP_victoria() + ".<br>";

		return fraseRetorn;

	}

	String SumarPuntsEnergiaMonstre(Monstre monstreActual, int suma) {
		String fraseRetorn = "Els punts d'energia inicial del monstre " + monstreActual.getNom() + " son "
				+ monstreActual.getEnergia() + ".<br>";
		monstreActual.setEnergia(monstreActual.getEnergia() + suma);
		monstreServices.editar(monstreActual);
		fraseRetorn += "Els punts d'energia que suma son " + suma + ", acaba amb un total de "
				+ monstreActual.getEnergia() + ".<br>";

		return fraseRetorn;

	}

	String MonstreCuracio(Monstre monstreActual, int suma) {
		String fraseRetorn = "Els punts de vida inicial del monstre " + monstreActual.getNom() + " son "
				+ monstreActual.getVides() + ".<br>";

		if (monstreActual.isToquio()) {
			fraseRetorn += "Com el monstre está a Tokyo no es cura " + suma + ".<br>";
		} else {

			monstreActual.setVides(monstreActual.getVides() + suma);
			monstreServices.editar(monstreActual);
			fraseRetorn += "Els punts de vida que suma son " + suma + ", acaba amb un total de "
					+ monstreActual.getVides() + ".<br>";
		}

		return fraseRetorn;

	}

	@GetMapping(path = "/MonstreMaxPuntVictoria")
	public @ResponseBody Monstre monstreMaxPuntVictoria() {
		List<Monstre> monstrePunts = monstreServices.findByEleminatAndIsCartaOrderByPvictoriaDesc(false, false);
		Monstre mons = monstrePunts.get(0);
		return mons;
	}

	@GetMapping(path = "/ListMostresViusContrincants/{idMonstre}")
	public @ResponseBody List<Monstre> listMonstresViusContrincants(@PathVariable int idMonstre) {
		List<Monstre> monstresVius = monstreServices.findByEleminatAndIsCarta(false, false);
		List<Monstre> monstresContrincants = new ArrayList<Monstre>();
		for (Monstre monstre : monstresVius) {
			if (monstre.getId() != idMonstre) {
				monstresContrincants.add(monstre);
			}
		}
		return monstresContrincants;
	}

	@GetMapping(path = "/ListMonstrePoderLliure")
	public @ResponseBody List<Monstre> listMonstrePoderLliure() {
		List<Monstre> monstresVius = monstreServices.findByIsCartaAndMonstreCarta(true, null);
		return monstresVius;
	}
	
	@GetMapping(path="/AssignarTorn")
	public @ResponseBody String assignarTorn() {
		List<Monstre> monstresVius = monstreServices.findByEleminatAndIsCarta(false, false);
		Monstre mons;
		if(contarTorn == monstresVius.size()) {
			mons = monstresVius.get(0);
			contarTorn = 1;
		} else {
			mons = monstresVius.get(contarTorn);
			contarTorn++;
		}
		return "Es el torn del monstre " + mons.getNom();		
	}
	@GetMapping(path = "/getTokio") // Map de prova, landing
	public @ResponseBody List<Monstre> isTokio() {
		return monstreServices.findByToquio();
	}
	
	@GetMapping(path = "/actualitzarVius") // Map de prova, landing
	public @ResponseBody String updateVius() {
		
		List<Monstre> mons = monstreServices.findByVides();
		
		for (Monstre monstre : mons) {
			monstre.setEleminat(true);
			monstreServices.editar(monstre);
		}
		return "Monstres editats correctament" ;
	}
	
	@GetMapping(path = "/alguienEnTokyo") // Map de prova, landing
	public @ResponseBody String checkTokyo() {
		
		List<Monstre> mons = monstreServices.findByToquio();
		
		if (mons.size() > 0) {
			return "Hi ha el monstre " + mons.get(0).getNom() + " ocupant tokyo.";
		}else {
			return "No hi ha ningu a tokyo.";
		}
		
	}

}
