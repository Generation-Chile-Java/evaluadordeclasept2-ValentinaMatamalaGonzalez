import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibretaDeNotas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, ArrayList<Double>> calificaciones = new HashMap<>();

        // pedir cantidad de alumnos
        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();

        // valir cantidad de alumnos
        while (cantidadAlumnos <= 0) {
            System.out.println("Ingrese un número positivo para la cantidad de alumnos");
            System.out.print("Ingrese la cantidad de alumnos: ");
            cantidadAlumnos = scanner.nextInt();
        }

        // pedir la cantidad de notas por alumno
        System.out.print("Ingrese la cantidad de notas por alumno: ");
        int cantidadNotas = scanner.nextInt();

        // valida cantidad de notas
        while (cantidadNotas <= 0) {
            System.out.println("Ingrese un número positivo para la cantidad de notas");
            System.out.print("Ingrese la cantidad de notas por alumno: ");
            cantidadNotas = scanner.nextInt();
        }

        scanner.nextLine();

        // ingresar datos de alumnos y notas
        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre del alumno " + (i + 1) + ": ");
            String nombreAlumno = scanner.nextLine();

            ArrayList<Double> notas = new ArrayList<>();

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la nota " + (j + 1) + " para " + nombreAlumno + ": ");
                double nota = scanner.nextDouble();

                // validar la nota ingresada
                while (nota < 0 || nota > 10) {
                    System.out.println("Nota inválida, debe estar entre 0 y 10");
                    System.out.print("Ingrese la nota " + (j + 1) + " para " + nombreAlumno + ": ");
                    nota = scanner.nextDouble();
                }

                notas.add(nota);
            }

            calificaciones.put(nombreAlumno, notas);
            scanner.nextLine();
        }

        // menú de opciones
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1- Mostrar el promedio de notas por estudiante");
            System.out.println("2- Mostrar si la nota es aprobatoria o reprobatoria por estudiante");
            System.out.println("3- Mostrar si la nota está por sobre o por debajo del promedio del curso por estudiante");
            System.out.println("0- Salir del menú");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarPromedios(calificaciones);
                    break;
                case 2:
                    verificarAprobacion(calificaciones, scanner);
                    break;
                case 3:
                    verificarSobreDebajoPromedio(calificaciones, scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del menú....");
                    break;
                default:
                    System.out.println("Opción inválida, intentelo de nuevo");
                    break;
            }
        }

        scanner.close();
    }

    // mostrar el promedio de notas por estudiante
    public static void mostrarPromedios(HashMap<String, ArrayList<Double>> calificaciones) {
        for (Map.Entry<String, ArrayList<Double>> entrada : calificaciones.entrySet()) {
            String nombreAlumno = entrada.getKey();
            ArrayList<Double> notas = entrada.getValue();
            double promedio = calcularPromedio(notas);
            System.out.println("Promedio de " + nombreAlumno + ": " + promedio);
        }
    }

    // calcular el promedio de notas
    public static double calcularPromedio(ArrayList<Double> notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    // verificar si una nota es aprobatoria o reprobatoria
    public static void verificarAprobacion(HashMap<String, ArrayList<Double>> calificaciones, Scanner scanner) {
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombreAlumno = scanner.nextLine();
        if (!calificaciones.containsKey(nombreAlumno)) {
            System.out.println("El estudiante no existe");
            return;
        }

        System.out.print("Ingrese la nota a verificar: ");
        double nota = scanner.nextDouble();
        if (nota >= 0 && nota <= 10) {
            if (nota >= 4) {
                System.out.println("La nota " + nota + " es aprobatoria");
            } else {
                System.out.println("La nota " + nota + " es reprobatoria");
            }
        } else {
            System.out.println("Nota inválida, tiene que estar entre 0 y 10");
        }
    }

    // verificar si la nota está por sobre o por debajo del promedio del curso
    public static void verificarSobreDebajoPromedio(HashMap<String, ArrayList<Double>> calificaciones, Scanner scanner) {
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombreAlumno = scanner.nextLine();
        if (!calificaciones.containsKey(nombreAlumno)) {
            System.out.println("El estudiante no existe");
            return;
        }

        System.out.print("Ingrese la nota a verificar: ");
        double nota = scanner.nextDouble();

        double promedioCurso = calcularPromedioCurso(calificaciones);
        if (nota >= 0 && nota <= 10) {
            if (nota >= promedioCurso) {
                System.out.println("La nota " + nota + " está por sobre el promedio del curso (" + promedioCurso + ").");
            } else {
                System.out.println("La nota " + nota + " está por debajo del promedio del curso (" + promedioCurso + ").");
            }
        } else {
            System.out.println("Nota inválida, debe estar entre 0 y 10");
        }
    }

    // calcular el promedio de notas del curso
    public static double calcularPromedioCurso(HashMap<String, ArrayList<Double>> calificaciones) {
        double sumaTotal = 0;
        int cantidadNotas = 0;

        for (ArrayList<Double> notas : calificaciones.values()) {
            for (double nota : notas) {
                sumaTotal += nota;
                cantidadNotas++;
            }
        }

        return cantidadNotas > 0 ? sumaTotal / cantidadNotas : 0;
    }
}