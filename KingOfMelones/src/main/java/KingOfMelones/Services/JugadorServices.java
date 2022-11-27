package KingOfMelones.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import KingOfMelones.Model.Jugador;
import KingOfMelones.Repositoris.JugadorRepository;

@Service
public class JugadorServices {
	@Autowired
	JugadorRepository repository;
	public Jugador findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Jugador> findAll() {
		return repository.findAll();
	}
	
	public void delete(Integer id) {

		repository.deleteById(id);
	}
	
	public Jugador insertar(Jugador j) {
		return repository.save(j);
	}
	
	public Jugador editar(Jugador j) {
		return repository.save(j);
	}

}
