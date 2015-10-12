/**
 * Created by ahinri on 11-10-2015.
 */
public class DescriptorRamo {
	int creditos;
	String programa;
	List<Ramo> requisitos=new List<Ramos>()
	public DescriptorRamo(String sigla){
		this.sigla=sigla
	}
	
	public boolean agregarRequisitos(Ramo r){
        requisitos.add(r)
    }
	
	
}
