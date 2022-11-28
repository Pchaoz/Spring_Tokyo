package KingOfMelones.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import KingOfMelones.Model.Monstre;
import KingOfMelones.Repositoris.MonstreRepository;

@Service
public class MonstreServices {
	@Autowired
	MonstreRepository repository;
	public Monstre findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Monstre> findAll() {
		return repository.findAll();
	}
	
	public void delete(Integer id) {

		repository.deleteById(id);
	}
	
	public Monstre insertar(Monstre m) {
		return repository.save(m);
	}
	
	public Monstre editar(Monstre m) {
		return repository.save(m);
	}
	
	public List<Monstre> findByEleminat(boolean bool) {
		return repository.findByEleminat(bool);
	}
		
}
