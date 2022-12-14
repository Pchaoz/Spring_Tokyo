package KingOfMelones.Repositoris;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import KingOfMelones.Model.Jugador;
import KingOfMelones.Model.Monstre;

public interface MonstreRepository extends JpaRepository<Monstre,Integer>{


	List<Monstre> findByEleminatAndIsCarta(boolean bool, boolean bool2);
	List<Monstre>findByJugador(Jugador jugador);
	List<Monstre> findByEleminatAndIsCartaOrderByPvictoriaDesc(boolean bool, boolean bool2);
	List<Monstre> findByIsCartaAndMonstreCarta(boolean bool, Monstre mons);
	Monstre findByToquio(boolean bool);
	List<Monstre> findByToquio(boolean var);
	List<Monstre> findByEleminat(boolean bool);
	List<Monstre> findByVides(int vides);

}
