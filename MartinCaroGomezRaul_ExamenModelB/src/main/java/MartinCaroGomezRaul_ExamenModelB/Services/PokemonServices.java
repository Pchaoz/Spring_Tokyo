package MartinCaroGomezRaul_ExamenModelB.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MartinCaroGomezRaul_ExamenModelB.Model.Pokemon;
import MartinCaroGomezRaul_ExamenModelB.Repositories.PokemonRepository;



@Service
public class PokemonServices {

	@Autowired
	PokemonRepository repository;
	public Pokemon findById(Integer id) {
		return repository.findById(id).orElse(null);
	}

	public List<Pokemon> findAll() {
		return repository.findAll();
	}

	public void delete(Integer id) {

		repository.deleteById(id);
	}

	public Pokemon insertar(Pokemon m) {
		return repository.save(m);
	}

	public Pokemon editar(Pokemon m) {
		return repository.save(m);
	}

}
