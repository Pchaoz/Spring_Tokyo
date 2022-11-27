package KingOfMelones.Model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jugadors")
public class Jugador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Jugador", nullable = false)
	private int jugID;

	@Column(name = "Nom", length = 50 , nullable = false)
	private String Nom;

	@Column(name = "Cognom", length = 50 , nullable = false)
	private String Cognom;

	@OneToMany(mappedBy="jugador", cascade = CascadeType.ALL)
	private Set<Monstre> monstres;
	
	public Jugador() {
		super();
	}
	
	public Jugador(String nom, String cognom) {
		super();
		this.Nom = nom;
		this.Cognom = cognom;
	}

	public int getJugID() {
		return jugID;
	}

	public void setJugID(int jugID) {
		this.jugID = jugID;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getCognom() {
		return Cognom;
	}

	public void setCognom(String cognom) {
		Cognom = cognom;
	}

	@Override
	public String toString() {
		return "Jugador [jugID=" + jugID + ", Nom=" + Nom + ", Cognom=" + Cognom + "]";
	}
	
}
