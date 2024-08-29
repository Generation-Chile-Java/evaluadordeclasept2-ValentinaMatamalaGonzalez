import java.util.Scanner;

public class Evaluador {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Ingresa las calificaciones para tres materias
        System.out.print("Ingrese la calificación para la materia 1: ");
        double calificacion1 = scanner.nextDouble();

        System.out.print("Ingrese la calificación para la materia 2: ");
        double calificacion2 = scanner.nextDouble();

        System.out.print("Ingrese la calificación para la materia 3: ");
        double calificacion3 = scanner.nextDouble();

        // Evalua la calificación para cada materia
        evaluarCalificacion(calificacion1);
        evaluarCalificacion(calificacion2);
        evaluarCalificacion(calificacion3);

        scanner.close();
    }

    public static void evaluarCalificacion(double calificacion) {
        if (calificacion < 0 || calificacion > 10) {
            System.out.println("Entrada inválida: la calificación debe estar entre 0 y 10.");
        } else if (calificacion <= 3) {
            System.out.println("No Aprobado");
        } else if (calificacion <= 5) {
            System.out.println("Insuficiente");
        } else if (calificacion <= 8) {
            System.out.println("Aceptable");
        } else if (calificacion == 10) {
            System.out.println("Excelente");
        } else {
            System.out.println("Calificación no definida");
        }
    }
}
