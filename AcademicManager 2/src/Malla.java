import java.util.ArrayList;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Malla {

	int max_creditos_semestre;
	String facultad;
	int max_ramos_reprobados;
	int id_malla;

	ArrayList<Ramo> ramos=new ArrayList<Ramo>();

	public Malla(int max_creditos_semestre,String facultad, int max_ramos_reprobados,int id_malla){
		this.max_creditos_semestre=max_creditos_semestre;
		this.facultad=facultad;
		this.max_ramos_reprobados=max_ramos_reprobados;
		this.id_malla=id_malla;
	}

	public void agregar_ramos(Ramo r){
		ramos.add(r);
	}
	public void sacar_ramo(Ramo r){
		ramos.remove(r);
	}

}

