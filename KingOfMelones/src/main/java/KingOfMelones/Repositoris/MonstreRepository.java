package KingOfMelones.Repositoris;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import KingOfMelones.Model.Monstre;

public interface MonstreRepository extends JpaRepository<Monstre,Integer>{

	List<Monstre> findByEleminatAndIsCarta(boolean bool, boolean bool2);
	
}
