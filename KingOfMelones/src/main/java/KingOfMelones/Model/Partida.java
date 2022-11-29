package KingOfMelones.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "partides")
public class Partida {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Partida")
	private int partidaID;
	
	@Column(name = "Torn")
	private int Torn;

	@Column(name = "NumeroJugadors",nullable=true)
	private int Njugadors;

	@OneToMany(mappedBy="partida", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<Monstre> monstres;
	
	
	
	public Partida() {
		super();
		monstres=new HashSet<Monstre>();
		Torn=0;
		Njugadors=0;
	}

	public int getPartidaID() {
		return partidaID;
	}

	public void setPartidaID(int partidaID) {
		this.partidaID = partidaID;
	}

	public int getTorn() {
		return Torn;
	}

	public void setTorn(int torn) {
		Torn = torn;
	}

	
	
	public int getNjugadors() {
		return Njugadors;
	}

	public void setNjugadors(int njugadors) {
		Njugadors = njugadors;
	}

	public Set<Monstre> getMonstres() {
		return monstres;
	}

	public void setMonstres(Set<Monstre> monstres) {
		this.monstres = monstres;
	}

	@Override
	public String toString() {
		return "Partida [partidaID=" + partidaID + ", Nom=" + Torn + ", Njugadors=" + Njugadors + "]";
	}

	
	
	
}
