import java.util.ArrayList;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Malla {

	int max_creditos_semestre;
	String carrera;
	int max_ramos_reprobados;
	int id_malla;

	ArrayList<DescriptorRamo> ramos=new ArrayList<DescriptorRamo>();

	public Malla(int max_creditos_semestre,String carrera, int max_ramos_reprobados,int id_malla){
		this.max_creditos_semestre=max_creditos_semestre;
		this.carrera=carrera;
		this.max_ramos_reprobados=max_ramos_reprobados;
		this.id_malla=id_malla;
	}

	public void agregar_ramos(DescriptorRamo descriptor){
		ramos.add(descriptor);
	}
	public void sacar_ramo(DescriptorRamo descriptor){
		ramos.remove(descriptor);
	}

}

