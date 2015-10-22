/**
 * Created by ahinri on 11-10-2015.
 */
public class Admin extends Usuario {

	public Admin(String nombre, int edad, String sexo, String rut,int id_usuario) {
		super(nombre, edad, sexo, rut,id_usuario);
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
	
	public void crearRamo(Profesor p,DescriptorRamo d) //en realidad se van a seleccionar
	{
		String horario = "L3"; //cambiar a leer valor
		String sala = "A1"; //cambiar a leer valor
		int seccion = 1; //cambiar a leer valor
		int cupos = 50; //cambiar a leer valor
		int anio = 2016; //cambiar a leer valor
		int num_semestre = 1; //cambiar a leer valor
		Profesor profesor=p; //cambiar a seleccionar profesor
		DescriptorRamo descriptor=d; //cambiar a seleccionar descriptor
		Ramo nuevo = new Ramo(horario, sala, seccion, cupos, anio, num_semestre, profesor, descriptor);
		//agregarlo a un catalogo de ramos?
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
