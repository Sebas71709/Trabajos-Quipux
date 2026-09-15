import java.util.ArrayList;
import java.util.Scanner;

class Estudiante {
    private String nombre;
    private double nota1;
    private double nota2;
    private double nota3;

    public Estudiante(String nombre, double nota1, double nota2, double nota3) {
        this.nombre = nombre;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public double calcularPromedio() {
        return (nota1 + nota2 + nota3) / 3.0;
    }

    public boolean estaAprobado() {
        return calcularPromedio() >= 3.0;
    }

    public void mostrarInformacion() {
        double promedio = calcularPromedio();
        String estado = estaAprobado() ? "APROBADO" : "REPROBADO";

        System.out.printf(
                "Estudiante: %-15s | Notas: [%.1f, %.1f, %.1f] | Promedio: %.2f | Estado: %s%n",
                nombre, nota1, nota2, nota3, promedio, estado
        );
    }
    }

class SistemaCalificaciones {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== SISTEMA DE CALIFICACIONES ===");
            System.out.println("1. Registrar estudiante y notas");
            System.out.println("2. Mostrar reporte de estudiantes");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    System.out.print("\nIngrese el nombre del estudiante: ");
                    String nombre = scanner.nextLine();

                    double nota1 = leerNota(scanner, "Ingrese la Nota 1 (0.0 - 5.0): ");
                    double nota2 = leerNota(scanner, "Ingrese la Nota 2 (0.0 - 5.0): ");
                    double nota3 = leerNota(scanner, "Ingrese la Nota 3 (0.0 - 5.0): ");

                    listaEstudiantes.add(new Estudiante(nombre, nota1, nota2, nota3));
                    System.out.println("¡Estudiante registrado con éxito!");
                    break;

                case 2:
                    if (listaEstudiantes.isEmpty()) {
                        System.out.println("\nNo hay estudiantes registrados.");
                    } else {
                        System.out.println("\n--- REPORTE GENERAL ---");
                        for (Estudiante est : listaEstudiantes) {
                            est.mostrarInformacion();
                        }
                    }
                    break;

                case 3:
                    salir = true;
                    System.out.println("\nSaliendo del programa...");
                    break;

                default:
                    System.out.println("\nOpción inválida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    // Método auxilar para validar la entrada de notas entre 0.0 y 5.0
    private static double leerNota(Scanner scanner, String mensaje) {
        double nota;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                nota = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el buffer
                if (nota >= 0.0 && nota <= 5.0) {
                    return nota;
                } else {
                    System.out.println("La nota debe estar entre 0.0 y 5.0.");
                }
            } else {
                System.out.println("Entrada no válida. Ingrese un número decimal.");
                scanner.next(); // Descartar entrada no válida
            }
        }
    }

    // Método auxiliar para validar opciones del menú
    private static int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Opción inválida. Ingrese un número.");
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        return numero;
    }
}