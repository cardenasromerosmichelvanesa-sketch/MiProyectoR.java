package src.manager;

public class Main {
    public static void main(String[] args) {
        /**
         * Creamos una instancia de nuestro manager
         * MiManagerMesero contiene toda la lógica de las 5 funcionalidades
         */
        MiManagerMesero miManager = new MiManagerMesero();
        /**
         * Llamamos al menú principal
         * Aquí inicia el bucle do-while que muestra: 1.MESA, 2.MESERO y etc
         * El programa se queda corriendo hasta que el usuario ponga 0 (Salir)
         */
        miManager.mostrarMenuPrincipal();
    }
}