import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
        //estas listas contienen los objetos creados a partir de la informacion almacenada en la base de datos y de lo que se crea en el programa
		ArrayList<DescriptorRamo> descriptores=new ArrayList<DescriptorRamo>();
        ArrayList<Ramo> ramos=new ArrayList<Ramo>();
        ArrayList<Profesor> profesores=new ArrayList<Profesor>();
        ArrayList<Alumno> alumnos=new ArrayList<Alumno>();
        ArrayList<Semestre> semestres=new ArrayList<Semestre>();
        ArrayList<Malla> mallas=new ArrayList<Malla>();

        //a continuacion nos conectamos a la base de datos con sqlite
        Connection c = null;
        java.sql.Statement statement1=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ing.db");
            statement1=c.createStatement();

            // creamos los Descriptores de ramos
            String consulta_descriptores="select * from descriptores";
            ResultSet rs_descriptores=statement1.executeQuery(consulta_descriptores);

            while (rs_descriptores.next()){
                String sigla_descriptor= rs_descriptores.getString("sigla");
                int creditos_descriptor=rs_descriptores.getInt("creditos");
                String programa_descriptor=rs_descriptores.getString("programa");
                int id_descriptor=rs_descriptores.getInt("id_ramo");
                descriptores.add(new DescriptorRamo(sigla_descriptor,creditos_descriptor,programa_descriptor,id_descriptor));
            }

            // creamos los Profesores

            String consulta_profesores="select * from usuarios where tipo='profesor'";
            ResultSet rs_profesores=statement1.executeQuery(consulta_profesores);
            while (rs_profesores.next()) {
                String nombre=rs_profesores.getString("nombre");
                int edad=rs_profesores.getInt("edad");
                String sexo=rs_profesores.getString("sexo");
                String rut=rs_profesores.getString("rut");
                int id_usuario=rs_profesores.getInt("id_usuario");
                String username=rs_profesores.getString("username");
                String clave=rs_profesores.getString("clave");
                profesores.add(new Profesor(nombre,edad,sexo,rut,id_usuario));

            }

            //creamos los Alumnos

            String consulta_alumnos="select * from usuarios where tipo='alumno'";
            ResultSet rs_alumnos=statement1.executeQuery(consulta_profesores);
            while (rs_profesores.next()) {
                String nombre=rs_alumnos.getString("nombre");
                int edad=rs_alumnos.getInt("edad");
                String sexo=rs_alumnos.getString("sexo");
                String rut=rs_alumnos.getString("rut");
                int id_usuario=rs_alumnos.getInt("id_usuario");
                String username=rs_alumnos.getString("username");
                String clave=rs_alumnos.getString("clave");
                alumnos.add(new Alumno(nombre,edad,sexo,rut,id_usuario));

            }

            // creamos los RAMOS
            String consulta_ramos="select * from ramos;";
            ResultSet rs_ramos=statement1.executeQuery(consulta_ramos);
            while (rs_ramos.next()){
                int id_ramo=rs_ramos.getInt("id");
                String horario=rs_ramos.getString("horario");
                String sala=rs_ramos.getString("sala");
                int seccion=rs_ramos.getInt("secion");
                int cupos=rs_ramos.getInt("cupos");
                int anio=rs_ramos.getInt("año");
                int semestre=rs_ramos.getInt("semestre");
                int id_profesor=rs_ramos.getInt("id_profesor");
                String id_alumnos=rs_ramos.getString("alumnos");

                
                System.out.println("ramo : "+id_ramo);
                System.out.println("horario: "+ horario);
                System.out.println("sala: "+sala);
                System.out.println("seccion: "+seccion);
                System.out.println("cupos: "+cupos);
                System.out.println("año: "+anio);
                System.out.println("Id Profesor: "+id_profesor);
                System.out.println("Id alumnos inscritos: " + id_alumnos);

                Profesor teacher=null;
                DescriptorRamo desc=null;
                for(Profesor x : profesores){if (x.id_usuario==id_profesor){teacher=x;break;}}
                for(DescriptorRamo x:descriptores){if(x.id_ramo==id_ramo){desc=x;break;}}

                if (teacher!=null & desc!=null){
                    ramos.add(new Ramo(horario,sala,seccion,cupos,anio,semestre,teacher,descriptores.get(0)));
                    System.out.println("Exito al añadir ramo");
                }

                else {System.out.println("Profesor o descriptor no encontrado");}

                System.out.println("----------------\n");
            }

            // creamos los Semestres con sus ramos y notas
            String consulta_semestres="Select * from semestres";
            ResultSet rs_semestres=statement1.executeQuery(consulta_semestres);
            while (rs_semestres.next()){
                int id_semestre=rs_semestres.getInt("id_semestre");
                String notas_semestre=rs_semestres.getString("notas");
                String ramos_semestre=rs_semestres.getString("ramos");

                Semestre auxiliar=new Semestre(id_semestre);

                for (String x:ramos_semestre.split(";")){
                    for(Ramo p:ramos){
                        if(p.descriptor.id_ramo==Integer.parseInt(x)){auxiliar.agregarRamo(p);}
                    }
                }
                /* ERROR DE INDEX PREGUNTAR ANDRES
                int contador=0;
                for (String x:ramos_semestre.split(";")){
                    auxiliar.notas.set(contador, Double.parseDouble(x));
                    contador++;
                }
                semestres.add(auxiliar);
                */
            }

            //creamos las Mallas

            String consulta_mallas="Select * from mallas;";
            ResultSet rs_mallas=statement1.executeQuery(consulta_mallas);
            while (rs_mallas.next()){
                int max_creditos=rs_mallas.getInt("max_creditos");
                String carrera=rs_mallas.getString("carrera");
                int maximos_reprobados=rs_mallas.getInt("max_ramos_reprobados");
                int id_malla= rs_mallas.getInt("id_malla");
                String id_descriptores=rs_mallas.getString("descriptores_ramos");

                mallas.add(new Malla(max_creditos,carrera,maximos_reprobados,id_malla));
            }

            //creamos los Historiales





        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);

        }
        System.out.println("Opened database successfully");




		/*
		Por simplificacion, en esta entrega no se implementa serializacion
		Debido a esto, los constructores y asignaciones a los atributos corresponden a nuevos objetos
		Esto ser� reemplazado para la pr�xima entrega

		Por ejemplo
		Entrega3: List<HistorialAcademico> historialAcademicos= new List<HistorialAcademico>();
		Entrega4: List<HistorialAcademico> historialAcademicos= deserializarHistoriales();
		 */
		/*
		ACLARACIONES:
			1. El avance curricular NO es una entidad, es el display grafico de la informacion obtenida de
			comparar los semestres que posee un historial con su malla asignada. El resultado de esto seria
			algo parecido a lo que muestra el SidIng
			2. Muchos metodos tienen tipo de retorno boolean por si el metodo llega a fallar
			3. El profesor es quien califica. Si fuera el alumno, el requerimiento de que el alumno no puede
			ingresar al sistema si reprobo cierta cantidad de ramos no tendria sentido (podria enga�ar al sistema)

		 */
		/*
		CAMBIOS RESPECTO AL DIAGRAMA DE CLASES ACTUAL:
			1. En HistorialAcademico se agregan los metodos
				obtenerAvance()
				agregars_ramosemstre();

			2. En el diagrama, un Admin debiese tener flechas hacia las entidades que es capaz de crear. Esto
			se debe a que, al usar el constructor de estas, conoce su estructura

			3. A Ramo se le agrega el campo int id
			4. En Semstre, el tipo de dato del campo notas se cambia a List<Double>
		 */

	}

}
