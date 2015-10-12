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
		while (agregandoRamos) //poner condicion de termino
		{
			Ramo r; //cambiar a seleccionar ramo
			nueva.agregar_ramos(r);
		}
		//agregarla a un catalogo de mallas?
	}
	
	public void crearRamo()
	{
		String horario = "L3"; //cambiar a leer valor
		String sala = "A1"; //cambiar a leer valor
		int seccion = 1; //cambiar a leer valor
		int cupos = 50; //cambiar a leer valor
		int anio = 2016; //cambiar a leer valor
		int num_semestre = 1; //cambiar a leer valor
		Profesor profesor; //cambiar a seleccionar profesor
		DescriptorRamo descriptor; //cambiar a seleccionar descriptor
		Ramo nuevo = new Ramo(horario, sala, seccion, cupos, anio, num_semestre, profesor, descriptor);
		//agregarlo a un catalogo de ramos?
	}
    
}
