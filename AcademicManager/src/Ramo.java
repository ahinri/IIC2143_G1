import java.util.*;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Ramo {

	public String horario;
	public String sala;
	public int seccion;
	public int cupos;
	public int anio;
	public int num_semestre;
	public Profesor profesor;
	public DescriptorRamo descriptor;
	public List<Alumno> lista_alumnos;

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
		lista_alumnos = new ArrayList<>();
	}
	
}
