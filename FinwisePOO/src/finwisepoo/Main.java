public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando el sistema FinWise...");

        // 1. Inicializamos el gestor financiero (que a su vez carga los datos en memoria)
        GestorFinanciero gestorFinanciero = new GestorFinanciero();

        // 2. Inicializamos el menú de consola inyectándole el gestor
        MenuConsola menuConsola = new MenuConsola(gestorFinanciero);

        // 3. Arrancamos el ciclo interactivo de la aplicación
        menuConsola.iniciarSistema();
    }
}
