package perpetuals;

import java.util.Scanner;

//importes
import user.User;
import estructuras.Item;

public class Perpetuals {

    public static void main(String[] args) {
        menu();
    }
    
    private static void menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("PERPETUALS");
        System.out.println("1. Jugar");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opcion: ");
        String opcion = scanner.nextLine();
        switch (opcion) {
            case "1":
                    intro();
                break;
            case "2":         
                    System.out.println("Chao");
                break;
            default:
                    System.out.println("elije bien");
                break;
        }
    }
    private static void intro() {
            Scanner scanner = new Scanner(System.in);
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
                // Aquí podrías llamar a una función sala2()
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
    
}
