/**
 * Created by ahinri on 11-10-2015.
 */
public class Admin extends Usuario {

	Contenido cont = Contenido.getMi_instancia();

	public Admin(String nombre, int edad, String sexo, String rut,int id_usuario,String username,String password) {
		super(nombre, edad, sexo, rut,id_usuario,username,password);
	}

	public void crearMalla()
	{
		int max_creditos_semestre = 50; //cambiar a leer valor
		String facultad = "Ingenieria"; //cambiar a leer valor
		int max_ramos_reprobados = 10; //cambiar a leer valor
		int id_malla=1;
		Malla nueva = new Malla(max_creditos_semestre, facultad, max_ramos_reprobados,id_malla);
		boolean agregandoRamos = true;
		//agregarla a un catalogo de mallas?
	}
	
	public void crearRamo(String horario, String sala, int seccion, int cupos, int anio, int num_semestre, Profesor profesor, DescriptorRamo descriptor)
	{
		Ramo nuevo = new Ramo(horario, sala, seccion, cupos, anio, num_semestre, profesor, descriptor);
		cont.ramos.add(nuevo);
	}
	
	public void editarMalla(Malla m)
	{
		m.max_creditos_semestre = 60; //cambiar a leer valor
		m.facultad = "Psicologia"; //cambiar a leer valor
		m.max_ramos_reprobados = 12; //cambiar a leer valor
	}
	
	public void editarRamo(Ramo r, Profesor p,DescriptorRamo d) //en realidad se van a seleccionar
	{
		r.horario = "M3"; //cambiar a leer valor
		r.sala = "B25"; //cambiar a leer valor
		r.seccion = 2; //cambiar a leer valor
		r.cupos = 70; //cambiar a leer valor
		r.anio = 2017; //cambiar a leer valor
		r.num_semestre = 2; //cambiar a leer valor
		r.profesor = p; //cambiar a seleccionar valor
		r.descriptor = d; //cambiar a seleccionar valor
	}
    
}
