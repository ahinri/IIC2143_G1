import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
		ArrayList<DescriptorRamo> descriptores;
        ArrayList<Ramo> ramos;
        Connection c = null;
        java.sql.Statement statement1=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ing.db");
            statement1=c.createStatement();
            String consulta1="select * from ramos;";
            ResultSet rs=statement1.executeQuery(consulta1);
            while (rs.next()){
                int id=rs.getInt("id");
                String horario=rs.getString("horario");
                String sala=rs.getString("sala");
                int seccion=rs.getInt("secion");
                int cupos=rs.getInt("cupos");
                int año=rs.getInt("año");
                int semestre=rs.getInt("semestre");
                int id_profesor=rs.getInt("id_profesor");
                String id_alumnos=rs.getString("alumnos");



                System.out.println("ramo : "+id);
                System.out.println("horario: "+ horario);
                System.out.println("sala: "+sala);
                System.out.println("seccion: "+seccion);
                System.out.println("cupos: "+cupos);
                System.out.println("año: "+año);
                System.out.println("Id Profesor: "+id_profesor);
                System.out.println("Id alumnos inscritos: "+id_alumnos);

                System.out.println("----------------\n");

                ramos.add(new Ramo());
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
				agregarSemstre();

			2. En el diagrama, un Admin debiese tener flechas hacia las entidades que es capaz de crear. Esto
			se debe a que, al usar el constructor de estas, conoce su estructura

			3. A Ramo se le agrega el campo int id
			4. En Semstre, el tipo de dato del campo notas se cambia a List<Double>
		 */

	}

}
