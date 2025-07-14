package perpetuals;

import java.util.Scanner;

//importes
import user.User;
import estructuras.Item;
import mapa.Mapa;

public class Perpetuals {

    private static Scanner scanner = new Scanner(System.in); // scanner global para no estar declarandolo en cada metodo
    
    // variables para los eventoSala
    private static boolean llaveCofreObtenida = false;
    private static boolean cofreAbierto = false;
    
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
                    System.exit(0);
                break;
            default:
                    System.out.println("elige bien");
                break;
        }
    }
    private static void intro(Mapa mapa) {
            User jugador = new User("Perpetuo");
            
            Narrar("Una sombra cruza el cielo...");

            Narrar("El cuervo extiende sus alas y sobrevuela las ruinas del calabozo...");
            mapa.getGrafo().dfs(8);  
            Narrar("Son el numero de salas de este laberinto");
            
            Narrar("Las alas se repliegan. El viaje comenzará pronto...\n");
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
                jugador.setSalaActual(1);      // posición inicial al salir
                explorar(mapa, jugador);
            } else {
                System.out.println("Decisión inválida.");
            }
        } else {
            Narrar("Intentas abrir la puerta...\nEstá cerrada. Necesitas una llave.");
        }
        break;

        case "2":
            Narrar("Esta llave parece haber caido del cielo, ¿el pajaro me la lanzo?...\nCon esto puedo abrir la celda y avanzar a la sala 2.");
            jugador.mostrarInventario();
            jugador.setSalaActual(1);      
            explorar(mapa, jugador);
            
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
            case 1:
                if (jugador.isEventoLadronActivado() && !jugador.tieneItem("Llave secreta del ladrón")) {
                    Narrar("Encuentras un cuerpo sin vida apoyado contra la pared... Es el ladrón.");
                    Narrar("Tiene marcas de quemaduras arcanas y su cuerpo está retorcido en dolor.");
                    Narrar("Entre sus dedos rígidos, aún aprieta una pequeña llave con inscripciones extrañas.");
                    Narrar("Obtienes: Llave secreta del ladrón.");
                    jugador.agregarItem(new Item("Llave secreta del ladrón", "llave"));
                }
            break;

            case 3:
                if (!llaveCofreObtenida) {
                    Narrar("Encuentras algo en una esquina polvorienta...\n¡Es una Llave de cofre!");
                    jugador.agregarItem(new Item("Llave de cofre", "llave"));
                    llaveCofreObtenida = true;
                }
                break;

            case 4:
                if (!cofreAbierto) {
                    Narrar("Hay un cofre antiguo frente a ti...");
                    if (jugador.tieneItem("Llave de cofre")) {
                        Narrar("Usas la Llave de cofre y el cofre se abre lentamente...\n¡Obtienes un Talismán de protección!");
                        jugador.agregarItem(new Item("Talismán", "artefacto"));
                        cofreAbierto = true;
                    } else {
                        Narrar("El cofre está cerrado. Parece que necesita una llave especial...");
                    }
                }
                break;

            case 5:
                if (!jugador.tieneItem("Talismán")) {
                    Narrar("Una barrera mágica bloquea el paso.\nSin un talismán protector no puedes avanzar más allá...");
                    jugador.setSalaActual(2); // Reversa el movimiento
                } else {
                    Narrar("El talismán vibra al acercarte...\nLa barrera mágica desaparece. Puedes avanzar.");
                }
                break;

            case 6:
                Narrar("Encuentras dos caminos... hacia adelante... hay una puerta dentro de lo que parece ser un manantial (sala 9), se necesita un talismán, por otro lado una cueva con niebla (sala 7)");
                break;

        case 7:
            if (!jugador.isEventoLadronActivado()) {
                jugador.activarEventoLadron();
                Narrar("Entras a una sala oscura... entre la niebla densa ves lo que parece ser... ¡Un cofre! y una llave al lado.");
                Narrar("Escuchas pasos... un ladrón ha pasado corriendo y se lleva la llave.");
                Narrar("—¡Tonto! ¡Este tesoro es mío, ja ja ja!");
                Narrar("[El ladrón ha salido corriendo y ha traspasado una puerta que necesita un talismán.]");
                Narrar("[El ladrón ha sufrido severos daños por hacer eso... No debe de poder avanzar demasiado con esas heridas.]");
            } else if (jugador.tieneItem("Llave secreta del ladrón") && !jugador.tieneItem("Talisman de agua")) {
                Narrar("Usas la llave secreta del ladrón para abrir el cofre.");
                Narrar("Dentro encuentras un talismán con un brillo extraño...");
                jugador.agregarItem(new Item("Talisman de agua", "reliquia"));
            } else if (!jugador.tieneItem("Llave secreta del ladrón") && !jugador.tieneItem("Talisman de agua")) {
                Narrar("El cofre permanece cerrado. La llave fue robada por el ladrón.");
                Narrar("Ese desquiciado entró en un pozo sin salida al cruzar la barrera mágica sin protección...");
                Narrar("No debe de estar muy lejos.");
            }
            break;
            case 8:
                if (!jugador.tieneItem("Talisman de agua")) {
                    Narrar("Una barrera de agua bloquea el camino.\nCon un talismán de agua quizá me dejaría avanzar más allá...");
                    jugador.setSalaActual(6); 
                } else {
                    Narrar("La reliquia comienza a brillar intensamente al acercarte...");
                    Narrar("Una barrera de agua se forma a tu alrededor, protegiéndote del poder que emana del portal.");
                    Narrar("Das un paso... luego otro... y atraviesas la barrera sin resistencia.");
                    Narrar("Del otro lado, una luz cálida golpea tu rostro...");
                    Narrar("¡Es la luz del sol!");

                    Narrar("Sales del calabozo por una enorme abertura en la roca...\nEl aire fresco llena tus pulmones por primera vez en lo que parece una eternidad.");
                    Narrar("Desde el cielo desciende un majestuoso cuervo negro, de ojos antiguos y alas colosales.");
                    Narrar("—Perpetuo... —dice con voz grave y solemne—\nTu camino no ha acabado aquí.");
                    Narrar("Te recogeré de esta prisión olvidada y te llevaré a un nuevo reino, donde el verdadero propósito de tu existencia será revelado...");
                    Narrar("Eres uno entre muchos, un elegido entre los condenados.\nTe esperan más secretos, más acertijos, más pruebas.");

                    Narrar("El cuervo te alza con sus garras, y mientras te elevas, el calabozo queda atrás...");
                    Narrar("Tu historia apenas comienza...");

                    System.out.println("\n--- FIN DEL JUEGO (continuara...) ---");
                    System.exit(0);
                }
                break;
        }
    }    
}
