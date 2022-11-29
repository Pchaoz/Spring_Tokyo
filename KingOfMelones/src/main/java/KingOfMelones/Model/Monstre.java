package KingOfMelones.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "monstres")
public class Monstre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_monstre", nullable=false)
	private int id_monstre;
	
	@ManyToOne
	@JoinColumn(name="ID_Partida")
	@JsonManagedReference
	private Partida partida;
	
	@ManyToOne
	@JoinColumn(name="ID_Jugador")
	@JsonManagedReference
	private Jugador jugador;
	//Limitar 50 caracteres
	@Column(name="nom", nullable = false)
	private String nom;
	//Limitar 50 vides
	@Column(name="vides", nullable=false)
	private int vides;
	
	@Column(name="p_victoria", nullable=false)
	private int p_victoria;
	
	@Column(name="energia", nullable=false)
	private int energia;
	
	@Column(name="toquio", nullable=false)
	private boolean toquio;
	
	@Column(name="eliminat", nullable=false)
	private boolean eleminat;
	
	@Column(name="isCarta", nullable=false)
	private boolean isCarta;

	// NO POSAR CASCADETYPE EN EL CHILD JA QUE SI NO DONA ERROR DE PERSISTENCIA (detached entity passed to persist)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_monstreCarta")
	@JsonManagedReference
	private Monstre monstreCarta;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="monstreCarta")
	@JsonBackReference
	private Monstre monstreCartaAssignat;

	public Monstre() {
		super();
	}
	
	public Monstre(Partida partida, String nom) {
		super();
		this.nom = nom;
		this.vides = 10;
		this.p_victoria = 0;
		this.energia = 0;
		this.eleminat = false;
		this.toquio = false;
	}
	
	public Monstre(Partida partida, Jugador jugador, String nom, int vides, int p_victoria, int energia, boolean toquio,
			boolean eleminat, Monstre mons) {
		super();
		this.jugador = jugador;
		this.nom = nom;
		this.vides = vides;
		this.p_victoria = p_victoria;
		this.energia = energia;
		this.toquio = toquio;
		this.eleminat = eleminat;
		this.monstreCarta = mons;
	}

	public int getId() {
		return id_monstre;
	}

	public void setId(int id) {
		this.id_monstre = id;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVides() {
		return vides;
	}

	public void setVides(int vides) {
		this.vides = vides;
	}

	public int getP_victoria() {
		return p_victoria;
	}

	public void setP_victoria(int p_victoria) {
		this.p_victoria = p_victoria;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public boolean isToquio() {
		return toquio;
	}

	public void setToquio(boolean toquio) {
		this.toquio = toquio;
	}

	public boolean isEleminat() {
		return eleminat;
	}

	public void setEleminat(boolean eleminat) {
		this.eleminat = eleminat;
	}
	
	public boolean isCarta() {
		return isCarta;
	}

	public void setCarta(boolean isCarta) {
		this.isCarta = isCarta;
	}

	public Monstre getMonstreCarta() {
		return monstreCarta;
	}

	public void setMonstreCarta(Monstre monstreCarta) {
		this.monstreCarta = monstreCarta;
	}

	public Monstre getMonstreCartaAssignat() {
		return monstreCartaAssignat;
	}

	public void setMonstreCartaAssignat(Monstre monstreCartaAssignat) {
		this.monstreCartaAssignat = monstreCartaAssignat;
	}

	@Override
	public String toString() {
		return "Monstre [id_Monstre=" + id_monstre + /*", partida=" + partida + */", jugador=" + jugador + ", nom=" + nom + ", vides="
				+ vides + ", p_victoria=" + p_victoria + ", energia=" + energia + ", toquio=" + toquio + ", eleminat="
				+ eleminat + ", monstreCarta=" + monstreCarta + "]";
	}
	
	
}

