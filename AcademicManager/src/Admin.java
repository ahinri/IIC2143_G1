/**
 * Created by ahinri on 11-10-2015.
 */
public class Admin extends Usuario {
	
	public void crearMalla(String opcion)
	{
		int max_creditos_semestre = 50; //cambiar a leer valor
		String facultad = "Ingenieria"; //cambiar a leer valor
		int max_ramos_reprobados = 10; //cambiar a leer valor
		Malla nueva = new Malla(max_creditos_semestre, facultad, max_ramos_reprobados);
		boolean agregandoRamos = true;
		//agregarla a un catalogo de mallas?
	}
	
	public void crearRamo(Profesor p,DescriptorRamo d)
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
    
}
