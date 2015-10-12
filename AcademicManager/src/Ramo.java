import java.util.*;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Ramo {

	private String horario;
	private String sala;
	private int seccion;
	private int cupos;
	private int anio;
	private int num_semestre;
	private Profesor profesor;
	private DescriptorRamo descriptor;
	private List<Alumno> lista_alumnos;

	public Ramo(String horario, String sala, int seccion, int cupos, int anio, int num_semestre, Profesor profesor, DescriptorRamo descriptor)
	{
		this.horario = horario;
		this.sala = sala;
		this.seccion = seccion;
		this.cupos = cupos;
		this.anio = anio;
		this.num_semestre = num_semestre;
		this.profesor = profesor;
		this.descriptor = descriptor;
		lista_alumnos = new ArrayList<Alumno>();
	}
	
}
