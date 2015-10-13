import java.util.ArrayList;

/**
 * Created by ahinri on 11-10-2015.
 */
public class DescriptorRamo {
	int creditos;
	String programa, sigla;
	ArrayList<Ramo> requisitos=new ArrayList<Ramo>();

	public DescriptorRamo(String sigla){
		this.creditos=creditos;
        this.sigla=sigla;
        this.programa=programa;
	}
	
	public boolean agregarRequisitos(Ramo r){
        requisitos.add(r);
        return true;
    }
	
	
}
