import java.util.Scanner;

public class SistemaDeNotas {

    static final double NOTA_MINIMA = 0.0;
    static final double LIMIT_APROVATORIO = 3.0;
    static final double NOTA_MAXIMA = 5.0;
    static int NUM_ESTUDIANTES;
    static final int NUM_MATERIAS = 3;
    static final int COLUMNAS_RESERVADAS = 2; //columnas reservadas para nombre y código
    static final int COLUMNAS = COLUMNAS_RESERVADAS + NUM_MATERIAS; 
    static Object[][] datosEstudiante;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Se pide el número de estudiantes
        System.out.println("Por favor ingrese el número de estudiantes:");
        NUM_ESTUDIANTES = scanner.nextInt();

        // Se inicializa la matriz con el número de estudiantes y las materias
        datosEstudiante = new Object[NUM_ESTUDIANTES][COLUMNAS];

        // Llamar método para registrar a los estudiantes
        registrarEstudiantes(scanner);
        
        // Llamar método para generar el reporte final
        generarReporteFinal();

        // Cerrar el Scanner al final del método main
        scanner.close();
    }

    public static void registrarEstudiantes(Scanner scanner){

        // Se recorre la matriz "datosEstudiante" para registrar los datos de los estudiantes 
        //(nombre, código, notas)
        for(int i = 0; i < NUM_ESTUDIANTES; i++){
            scanner.nextLine();   
            System.out.println("Por favor ingrese el nombre del Estudiante:");
            datosEstudiante[i][0] = scanner.nextLine();
            
            System.out.println("Por favor ingrese el código identificador del Estudiante:");
            datosEstudiante[i][1] = scanner.nextInt();

            // Se solicitan las notas de las materias y se almacenan en las columnas correspondientes
            for(int j = 0; j < NUM_MATERIAS; j++) {
                System.out.println("Por favor ingrese la nota de la materia " + (j+1) + ":");
                double nota = scanner.nextDouble();
                
                // Validación de notas
                while(nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
                    System.out.println("Nota inválida. Debe estar entre " + NOTA_MINIMA + " y " + NOTA_MAXIMA + ".");
                    System.out.println("Por favor ingrese nuevamente la nota de la materia " + (j+1) + ":");
                    nota = scanner.nextDouble();
                }
                
                datosEstudiante[i][COLUMNAS_RESERVADAS + j] = nota;
            }
        }
    }

    public static void generarReporteFinal(){
        // Recorrer la matriz de datosEstudiante y calcular el promedio de cada estudiante
        // para generar el reporte final
        for(int i = 0; i < NUM_ESTUDIANTES; i++){
            double promedio = 0; //acumulador

            // Se suman las notas de las materias para calcular el promedio
            for(int j = 0; j < NUM_MATERIAS; j++){
                promedio += (double) datosEstudiante[i][COLUMNAS_RESERVADAS+j]; // Casting
            }
            promedio = promedio / NUM_MATERIAS;

            // Se usa un operador ternario
            String estado = promedio >= LIMIT_APROVATORIO ? "Aprobado" : "Reprobado";

            System.out.println(datosEstudiante[i][0] + " ID: " + datosEstudiante[i][1] + 
            " Promedio: " + promedio + " - " + estado);
        }
    }

}
