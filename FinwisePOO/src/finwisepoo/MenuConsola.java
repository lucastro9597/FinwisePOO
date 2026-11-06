package finwisepoo;

import java.util.Scanner;

public class MenuConsola {
    private Scanner scanner;
    private GestorFinanciero gestorFinanciero;

    public MenuConsola(GestorFinanciero gestorFinanciero) {
        this.scanner = new Scanner(System.in);
        this.gestorFinanciero = gestorFinanciero;
    }

    public void iniciarSistema() {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            if (opcion == 6) {
                continuar = false;
                System.out.println("\nSaliendo del sistema FinWise. ¡Hasta pronto!");
            } else {
                ejecutarOpcion(opcion);
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("       FINWISE - MENÚ PRINCIPAL");
        System.out.println("========================================");
        System.out.println("1. Registrar Transacción (Ingreso / Gasto)");
        System.out.println("2. Administrar Categorías");
        System.out.println("3. Gestionar Metas de Ahorro y Abonos");
        System.out.println("4. Consultar Resumen Financiero");
        System.out.println("5. Ver Listados y Progreso de Metas");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int leerOpcion() {
        int opcion = 0;
        if (scanner.hasNextInt()) {
            opcion = scanner.nextInt();
            scanner.nextLine(); // IMPORTANTE: Limpia el salto de línea pendiente para que no afecte la siguiente lectura
        } else {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpiar entrada incorrecta
            scanner.nextLine(); // Limpiar búfer
        }
        return opcion;
    }

    public void ejecutarOpcion(int opcion) {
        if (opcion == 1) {
            capturarIngresoGasto();
        } else if (opcion == 2) {
            capturarCategoria();
        } else if (opcion == 3) {
            capturarMetaAhorro();
        } else if (opcion == 4) {
            mostrarResumenFinanciero();
        } else if (opcion == 5) {
            mostrarReportes();
        } else {
            System.out.println("Opción no válida. Por favor, elija un número entre 1 y 6.");
        }
    }

    public void capturarIngresoGasto() {
        System.out.println("\n--- REGISTRAR TRANSACCIÓN ---");
        System.out.print("Ingrese el tipo (1. Ingreso, 2. Gasto): ");
        int tipo = leerOpcion();
        
        System.out.print("Ingrese el monto: ");
        if (scanner.hasNextDouble()) {
            double monto = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer de salto de línea
            
            System.out.print("Ingrese la descripción corta: ");
            String descripcion = scanner.nextLine();
            
            System.out.print("Ingrese el nombre de la categoría: ");
            String categoria = scanner.nextLine();

            if (tipo == 1) {
                gestorFinanciero.registrarIngreso(monto, descripcion, categoria);
            } else if (tipo == 2) {
                gestorFinanciero.registrarGasto(monto, descripcion, categoria);
            } else {
                System.out.println("Tipo de transacción incorrecto.");
            }
        } else {
            System.out.println("Monto ingresado inválido.");
            scanner.next();
            scanner.nextLine();
        }
    }

    public void capturarCategoria() {
        System.out.println("\n--- GESTIÓN DE CATEGORÍAS ---");
        System.out.print("Ingrese el nombre de la nueva categoría: ");
        String nombreCategoria = scanner.nextLine();
        gestorFinanciero.registrarCategoria(nombreCategoria);
    }

    public void capturarMetaAhorro() {
        System.out.println("\n--- GESTIÓN DE METAS DE AHORRO ---");
        System.out.println("1. Crear nueva meta de ahorro");
        System.out.println("2. Abonar a meta existente");
        System.out.print("Seleccione una opción: ");
        int opMeta = leerOpcion();

        if (opMeta == 1) {
            System.out.print("Ingrese el título de la meta: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el monto objetivo: ");
            if (scanner.hasNextDouble()) {
                double montoObjetivo = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
                gestorFinanciero.crearMetaAhorro(nombre, montoObjetivo);
            } else {
                System.out.println("Monto objetivo inválido.");
                scanner.next();
                scanner.nextLine();
            }
        } else if (opMeta == 2) {
            System.out.print("Ingrese el nombre de la meta a abonar: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el monto del abono: ");
            if (scanner.hasNextDouble()) {
                double abono = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
                gestorFinanciero.abonarMetaAhorro(nombre, abono);
            } else {
                System.out.println("Monto de abono inválido.");
                scanner.next();
                scanner.nextLine();
            }
        } else {
            System.out.println("Opción de meta inválida.");
        }
    }

    public void mostrarResumenFinanciero() {
        System.out.println("\n--- RESUMEN FINANCIERO ---");
        gestorFinanciero.consultarResumenFinanciero();
    }

    public void mostrarReportes() {
        System.out.println("\n--- REPORTES Y LISTADOS ---");
        gestorFinanciero.consultarListadosYProgreso();
    }
}
