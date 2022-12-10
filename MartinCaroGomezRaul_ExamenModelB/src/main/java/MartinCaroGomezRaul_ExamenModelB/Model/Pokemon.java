package MartinCaroGomezRaul_ExamenModelB.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="pokemons")
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdPokemon", nullable=false)
	private int id_pokemon;
	
	@Column (name="Tipus",nullable=false)
	private Tipus tipus;
	
	@Column (name="Nom",length = 20,nullable=false)
	private String nom;
	
	@Column(name="Candy",nullable=false)
	private int candy;
	
	@Column(name="Evolucionat",nullable=false)
	private boolean evolucionat;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="IdPokemonEvolucionat")
	@JsonManagedReference
	private Pokemon pokemonEvoluciont;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="pokemonEvoluciont")
	private Pokemon pokemonEvolucioAssignada;
	
	
	public Pokemon() {
		super();
	}


	public Pokemon(Tipus tipus, String nom) {
		super();
		this.tipus = tipus;
		this.nom = nom;
		this.candy = 0;
		this.evolucionat = false;
		
	}

	public Pokemon(Tipus tipus, String nom, Pokemon pokemonEvoluciont) {
		super();
		this.tipus = tipus;
		this.nom = nom;
		this.candy = 0;
		this.evolucionat = true;
		this.pokemonEvoluciont = pokemonEvoluciont;
	}

	public int getId_pokemon() {
		return id_pokemon;
	}


	public void setId_pokemon(int id_pokemon) {
		this.id_pokemon = id_pokemon;
	}


	public Tipus getTipus() {
		return tipus;
	}


	public void setTipus(Tipus tipus) {
		this.tipus = tipus;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getCandy() {
		return candy;
	}


	public void setCandy(int candy) {
		this.candy = candy;
	}


	public boolean isEvolucionat() {
		return evolucionat;
	}


	public void setEvolucionat(boolean evolucionat) {
		this.evolucionat = evolucionat;
	}


	public Pokemon getPokemonEvoluciont() {
		return pokemonEvoluciont;
	}


	public void setPokemonEvoluciont(Pokemon pokemonEvoluciont) {
		this.pokemonEvoluciont = pokemonEvoluciont;
	}


	@Override
	public String toString() {
		return "Pokemon [id_pokemon=" + id_pokemon + ", tipus=" + tipus + ", nom=" + nom + ", candy=" + candy
				+ ", evolucionat=" + evolucionat + ", pokemonEvoluciont=" + pokemonEvoluciont + "]";
	}
	
	
	
}
