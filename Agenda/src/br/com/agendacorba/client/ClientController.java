package br.com.agendacorba.client;

import java.util.Scanner;

/**
 * Created by Roland on 9/15/16.
 */
public class ClientController {
    boolean KEEP_ALIVE = true;
    char choice;
    private String IP;
    private String PORT;
    AgendaService agendaService;
    //Todo Criar service


    private void cliStartup() {
        Scanner in = new Scanner(System.in);

        while (KEEP_ALIVE) {
            System.out.println(">Entre com o IP do Servidor: ");
            IP = in.nextLine();

            System.out.println(">Entre com a PORT: ");
            PORT = in.nextLine();

            agendaService.resolve(IP, PORT);

        }
    }

    private void cliInterface() {
        while (KEEP_ALIVE) {

            switch (choice) {

            }


        }

    }

}
