package br.com.agendacorba.client;

import br.com.agendacorba.agenda.access.AgendaAccess;

import java.util.Scanner;

/**
 * Created by Roland on 9/15/16.
 */
public class ClientController {
    private boolean KEEP_ALIVE = true;
    private char choice;
    private Scanner in;

    private AgendaClientServiceHelper agendaService;
    private AgendaAccess agendaStub;

    public ClientController(String[] args) {
        agendaService = new AgendaClientServiceHelper(args);
        agendaStub = agendaService.findServer();

        if (agendaStub != null) {
            in = new Scanner(System.in);
            System.out.printf(MESSAGE.START_UP.toString());
            runCLIInterface();
        }
        else {
            System.out.printf(MESSAGE.ERR_NO_SERVER_FOUND.toString());
        }



        //TODO tratar caso de não achar nada!
    }


    private void runCLIInterface() {
        while (KEEP_ALIVE) {
            System.out.printf(MESSAGE.MENU.toString());
            choice = in.next().charAt(0);

            switch (choice) {
                case 'I':
                case 'i':
                    insertContact();
                    break;
                case 'P':
                case 'p':
                    searchContact();
                    break;
                case 'L':
                case 'l':
                    listAllContacts();
                    break;
                case 'A':
                case 'a':
                    updateContact();
                    break;
                case 'R':
                case 'r':
                    removeContact();
                    break;
                case 'F':
                    shutDown();
                    break;
                default:
                    System.out.printf(MESSAGE.ERR_WRONG_OPTION.toString());
                    break;
            }


        }

    }

    private void insertContact() {

    }

    private void searchContact() {

    }

    private void listAllContacts() {

    }

    private void updateContact() {

    }

    private void removeContact() {

    }

    private void shutDown() {
        KEEP_ALIVE = false;

    }


}
