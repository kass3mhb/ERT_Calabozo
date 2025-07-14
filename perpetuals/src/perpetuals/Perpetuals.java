package perpetuals;

import java.util.Scanner;

//importes
import user.User;
import estructuras.Item;
import mapa.Mapa;

public class Perpetuals {

    private static Scanner scanner = new Scanner(System.in); // scanner global para no estar declarandolo en cada metodo
    
    public static void main(String[] args) {
        Mapa mapa = new Mapa();
        menu(mapa);
    }
    
    private static void menu(Mapa mapa){
        System.out.println("PERPETUALS");
        System.out.println("1. Jugar");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opcion: ");
        String opcion = scanner.nextLine();
        switch (opcion) {
            case "1":
                    intro(mapa);
                break;
            case "2":         
                    System.out.println("Chao");
                break;
            default:
                    System.out.println("elije bien");
                break;
        }
    }
    private static void intro(Mapa mapa) {
            User jugador = new User("Perpetuo");
            Narrar("La piedra está fría.\n" +
            "La humedad se filtra entre las grietas del suelo y el aire huele a óxido y encierro.\n" +
            "Una figura yace encorvada en la oscuridad: piel apagada, ojos hundidos, alma rota…\n\n" +

            "Es el Perpetuo.\n\n" +

            "Un ser humanoide, desgastado por el tiempo, sin memoria ni propósito.\n" +
            "Su cuerpo recuerda el combate, pero su mente es un abismo.\n\n" +

            "Lentamente, abre los ojos.\n\n" +

            "—¿Qué se supone que…? —murmura, entre jadeos.\n\n" +

            "Su cabeza le da vueltas, siente un zumbido constante como si miles de pensamientos quisieran nacer,\n" +
            "pero ninguno toma forma.\n\n" +

            "—¿Dónde estoy?\n");
        System.out.println("¿Que deseas hacer?");
        System.out.println("1. Levantarte.");
        System.out.println("2. Recostarte.");
        System.out.print("Selecciona una opción: ");
        String opcion = scanner.nextLine();
        switch (opcion) {
            case "1":
                Narrar("El Perpetuo se levanta con esfuerzo, tambaleante.\n" +
                       "—Aunque quisiese recostarme...\n" +
                       "la llama en mi pecho no me deja.\n\n" +
                       "¿Qué…?\n" +
                       "Una llave le brilla en tanta oscuridad.\n" +
                       "—¿Es esto acaso una llave?");
                break;
            case "2":
                Narrar("—Estoy tan cansado…\n" +
                       "Cae de rodillas sobre la piedra helada.\n" +
                       "—Aunque quisiera recostarme y descansar eternamente...\n" +
                       "la llama en mi pecho no me lo permite.\n\n" +
                       "Se percata que hay algo brillante delante de él.\n" +
                       "—¿Qué es lo que brilla…? ¿Una llave?");
                break;
            default:
                System.out.println("Elige bien.");
                return;
        }
        
        Narrar("\n\n¡Has obtenido: Llave de celda!\n");
        Narrar("[Llave de celda ha sido añadida a tu inventario]");
        jugador.agregarItem(new Item("Llave de celda", "llave"));
        System.out.println("¿Que deseas hacer?");
        System.out.println("1. Abrir celda");
        System.out.println("2. Ver inventario.");
        System.out.print("Selecciona una opción: ");
        opcion = scanner.nextLine();
        switch (opcion) {
    case "1":
        if (jugador.tieneItem("Llave de celda")) {
            Narrar("La cerradura cede con facilidad...\nLa puerta se entreabre, dejando escapar un viento rancio de piedra vieja.\n");
            System.out.println("¿Qué deseas hacer ahora?");
            System.out.println("1. Salir a la siguiente habitación.");
            System.out.println("2. Quedarte en la celda un momento más.");
            System.out.print("Elige: ");
            String decision = scanner.nextLine();
            if (decision.equals("1")) {
                Narrar("El Perpetuo atraviesa el umbral de la celda...\nAvanzas hacia la Sala 2.");
                jugador.setSalaActual(2);      // posición inicial al salir
                explorar(mapa, jugador);
            } else if (decision.equals("2")) {
                Narrar("Decides quedarte un momento más...\nLa oscuridad te acompaña en silencio.");
            } else {
                System.out.println("Decisión inválida.");
            }
        } else {
            Narrar("Intentas abrir la puerta...\nEstá cerrada. Necesitas una llave.");
        }
        break;

        case "2":
            jugador.mostrarInventario();
            break;

        default:
            System.out.println("Elige bien.");
            break;
    }
        
}
    
    
public static void Narrar(String texto) {
    for (int i = 0; i < texto.length(); i++) {
        char c = texto.charAt(i);
        System.out.print(c);
        System.out.flush();

        try {
            if (c == '.' || c == ',' || c == '…') {
                Thread.sleep(200); // pausa larga en signos
            } else {
                Thread.sleep(10);  // pausa normal por letra
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    System.out.println();
}

    private static void explorar(Mapa mapa, User jugador) {
        mapa.mostrarMapa(); // mostrar el grafo, la lista de adyacencia siempre la primera vez (en resources igual hay una foto del mapa)

        while (true) {
            int actual = jugador.getSalaActual(); // en actual guardamos la sala en la que esta el usuario la primera vez, (en 1 en la celda)
            mapa.List<Integer> vecinos = mapa.getSalasVecinas(actual); //lista de sala vecinas en base a la de la actual del jugador

            System.out.println("\n-------------------------------------");
            System.out.println("Estás en la sala " + actual + ". ¿Qué deseas hacer?");
            for (int i = 0; i < vecinos.size(); i++) {
                System.out.println((i + 1) + ". Ir a sala " + vecinos.get(i)); // recursovamente imprime las salas vecinas a la qu esta situada el usuario
            }
            System.out.println((vecinos.size() + 1) + ". Ver inventario");

            System.out.print("Elige una opción: ");
            String entrada = scanner.nextLine();

            try {
                int eleccion = Integer.parseInt(entrada);
                if (eleccion >= 1 && eleccion <= vecinos.size()) {
                    int destino = vecinos.get(eleccion - 1);
                    jugador.setSalaActual(destino);
                    Narrar("Avanzas hacia la sala " + destino + "...");
                    eventoSala(destino, jugador);
                } else if (eleccion == vecinos.size() + 1) {
                    jugador.mostrarInventario();
                } else {
                    System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        }
    }
    
    private static void eventoSala(int sala, User jugador) {
        switch (sala) {
            case 2:
                Narrar("ejemplo");
                break;
            case 3:
                Narrar("");
                break;
            case 4:
                Narrar("");
                break;
            case 5:
                Narrar("");
                break;
            default:
                break;
        }
    }
    
}
