/**
 * Created by ahinri on 11-10-2015.
 */
public class Malla {
	List<Ramo> ramos=new List<Ramo>();
	public Malla(String nombre){
		this.nombre=nombre;
	
	}
	public void agregar_ramos(Ramo r){
		ramos.add(r);
	}
	public void sacar_ramo(Ramo r){
		ramos.remove(r);
	}

}

