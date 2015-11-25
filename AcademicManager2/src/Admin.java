/**
 * Created by ahinri on 11-10-2015.
 */
public class Admin extends Usuario {

	Contenido cont = Contenido.getMi_instancia();

	public Admin(String nombre, int edad, String sexo, String rut,int id_usuario,String username,String password) {
		super(nombre, edad, sexo, rut,id_usuario,username,password);
	}

	public void crearMalla(int max_creditos_semestre, String carrera, int max_ramos_reprobados, int id_malla, String[] siglas)
	{
		Malla nueva = new Malla(max_creditos_semestre, carrera, max_ramos_reprobados, id_malla);
		for (String sigla : siglas) {
			DescriptorRamo descriptor = getDescriptor(sigla);
			nueva.agregar_ramos(descriptor);
		}
		cont.mallas.add(nueva);
	}
	
	public void crearRamo(int nrc, String horario, String sala, int seccion, int cupos, int anio, int num_semestre, Profesor profesor, DescriptorRamo descriptor)
	{
		Ramo nuevo = new Ramo(nrc,horario, sala, seccion, cupos, anio, num_semestre, profesor, descriptor);
		cont.ramos.add(nuevo);
	}
	
	public void editarMalla(Malla m)
	{
		m.max_creditos_semestre = 60; //cambiar a leer valor
		m.carrera = "Psicologia"; //cambiar a leer valor
		m.max_ramos_reprobados = 12; //cambiar a leer valor
	}
	
	public void editarRamo(Ramo ramo, String horario, String sala, int seccion, int cupos, int anio, int num_semestre, Profesor profesor, DescriptorRamo descriptor)
	{
		ramo.horario = horario;
		ramo.sala = sala;
		ramo.seccion = seccion;
		ramo.cupos = cupos;
		ramo.anio = anio;
		ramo.num_semestre = num_semestre;
		ramo.profesor = profesor;
		ramo.descriptor = descriptor;
	}

	public void crearDesc(String sigla, int creditos, String programa, int id) {
		DescriptorRamo nuevo = new DescriptorRamo(sigla, creditos, programa, id);
		cont.descriptores.add(nuevo);
	}

	public DescriptorRamo getDescriptor(String sigla) {
		for (DescriptorRamo desc : cont.descriptores) {
			if (desc.sigla.equals(sigla)) {
				return desc;
			}
		}
		return null;
	}
    
}
