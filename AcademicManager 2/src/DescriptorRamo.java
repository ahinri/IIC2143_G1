import java.util.ArrayList;

/**
 * Created by ahinri on 11-10-2015.
 */

public class DescriptorRamo {
	int creditos;
	int id_ramo;
	String programa, sigla;
	ArrayList<Ramo> requisitos=new ArrayList<Ramo>();

	public DescriptorRamo(String sigla,int creditos,String programa,int id){
		this.creditos=creditos;
        this.sigla=sigla;
        this.programa=programa;
		this.id_ramo=id;
	}
	
	public boolean agregarRequisitos(Ramo r){
        requisitos.add(r);
        return true;
    }

}
