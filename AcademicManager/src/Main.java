import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {

		ArrayList<DescriptorRamo> descriptores=new ArrayList<DescriptorRamo>();
        ArrayList<Ramo> ramos=new ArrayList<Ramo>();
        ArrayList<Profesor> profesores=new ArrayList<Profesor>();
        Connection c = null;
        java.sql.Statement statement1=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ing.db");
            statement1=c.createStatement();

            //Descriptores
            String consulta_descriptores="select * from descriptores";
            ResultSet rs_descriptores=statement1.executeQuery(consulta_descriptores);

            while (rs_descriptores.next()){
                String sigla_descriptor= rs_descriptores.getString("sigla");
                int creditos_descriptor=rs_descriptores.getInt("creditos");
                String programa_descriptor=rs_descriptores.getString("programa");
                int id_descriptor=rs_descriptores.getInt("id_ramo");
                descriptores.add(new DescriptorRamo(sigla_descriptor,creditos_descriptor,programa_descriptor,id_descriptor));
            }

            //Profesores

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


            //RAMOS
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
                for(Profesor x : profesores){
                    if (x.id_usuario==id_profesor){
                        teacher=x;
                        break;
                    }
                }

                for(DescriptorRamo x:descriptores){
                    if(x.id_ramo==id_ramo){
                        desc=x;
                    }
                }

                if (teacher!=null & desc!=null){
                    ramos.add(new Ramo(horario,sala,seccion,cupos,anio,semestre,teacher,descriptores.get(0)));
                    System.out.println("Exito al añadir ramo");
                }
                else {
                    System.out.println("Profesor o descriptor no encontrado");
                }

                System.out.println("----------------\n");

            }



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
