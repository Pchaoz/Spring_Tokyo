package KingOfMelones.Repositoris;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import KingOfMelones.Model.Jugador;
import KingOfMelones.Model.Monstre;

public interface MonstreRepository extends JpaRepository<Monstre,Integer>{

	List<Monstre> findByToquio(boolean var);
	List<Monstre>findByJugador(Jugador jugador);
	List<Monstre> findByEleminat(boolean bool);
	List<Monstre> findByVides(int vides);

}
