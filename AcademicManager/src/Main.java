public class Main {

	public static void main(String[] args) {
		/*
		Por simplificacion, en esta entrega no se implementa serializacion
		Debido a esto, los constructores y asignaciones a los atributos corresponden a nuevos objetos
		Esto será reemplazado para la próxima entrega

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
			ingresar al sistema si reprobo cierta cantidad de ramos no tendria sentido (podria engañar al sistema)

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
