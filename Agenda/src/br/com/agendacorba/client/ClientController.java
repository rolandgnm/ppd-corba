package br.com.agendacorba.client;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.ContactAlreadyExistsException;
import br.com.agendacorba.agenda.MalformedTelNumberException;
import br.com.agendacorba.agenda.NoContactFoundException;
import br.com.agendacorba.agenda.access.AgendaAccess;
import br.com.agendacorba.server.access.AgendaAccessImpl;
import org.omg.CORBA.COMM_FAILURE;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.net.ConnectException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Roland on 9/15/16.
 */
public class ClientController {
    private boolean KEEP_ALIVE = true;
    private char choice;
    private AgendaClientServiceHelper agendaService;
    private AgendaAccess agendaStub;

    public ClientController(String[] args) {
        agendaService = new AgendaClientServiceHelper(args);

        findServantOrExit();

        System.out.printf(MESSAGE.START_UP.toString());


        try {
            runCLIInterface();
        } catch (Exception e) {
            agendaService.unbindCurrentServer();
            findServantOrExit();
        }

        //TODO tratar caso de não achar nada!
    }

    private void findServantOrExit() {
        try {
            agendaStub = agendaService.findServer();
        } catch (NotFound notFound) {
            System.out.printf(MESSAGE.ERR_NO_SERVER_FOUND.toString());
            System.exit(1);
        }
    }

    private void runCLIInterface() throws Exception {
        while (KEEP_ALIVE) {
            System.out.printf(MESSAGE.MENU.toString());
            choice = new Scanner(System.in)
                    .next()
                    .charAt(0);

            switch (choice) {
                case 'I':
                case 'i':
                    insertContact();
                    break;
                case 'P':
                case 'p':
                    searchContactByName();
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

    private void insertContact() throws Exception {
        String name = getNameFromInput(MESSAGE.INSERT_NAME);
        String telNumber = getNumberFromInput(MESSAGE.INSERT_TEL);

        Contact newContact = new Contact();
        newContact.name = name;
        newContact.telNumber = telNumber;

        try {
            agendaStub.create(newContact);
        } catch (ContactAlreadyExistsException e) {
            System.out.println(MESSAGE.ERR_ALREADY_EXISTS.toString());
        } catch (MalformedTelNumberException e) {
            System.out.println(MESSAGE.ERR_MALFORMED_TEL.toString());
            insertContact();
        }
    }

    private void searchContactByName() throws Exception {
        String name = getNameFromInput(MESSAGE.SEARCH_NAME);

        try {
            Contact contact = agendaStub.getByName(name);
            System.out.println(MESSAGE.CONTACT_FOUND.toString());
            System.out.println(MESSAGE.TABLE_HEAD.toString());
            System.out.println("\t\t " + contact.name + ", " + contact.telNumber);

        } catch (NoContactFoundException e) {
            System.out.println(MESSAGE.ERR_NOT_FOUND.toString());
        }


    }

    private void listAllContacts() throws Exception {

        try {
            Contact[] contactList = agendaStub.getAll();

            Iterator<Contact> contactI = Arrays.asList(contactList).iterator();
            Contact contact;
            System.out.println(MESSAGE.CONTACT_FOUND.toString());
            System.out.println(MESSAGE.TABLE_HEAD.toString());

            while (contactI.hasNext()) {
                contact = contactI.next();
                System.out.println("\t\t " + contact.name + ", " + contact.telNumber);
            }
        } catch (NoContactFoundException e) {
            System.out.println(MESSAGE.ERR_NOT_FOUND);
        }


    }

    private void updateContact() throws Exception {
        String name = getNameFromInput(MESSAGE.UPDATE_CONTACT);

        try {
            Contact contact = agendaStub.getByName(name);

            contact.telNumber = getNumberFromInput(MESSAGE.INSERT_TEL);

            agendaStub.update(contact);

            System.out.println(MESSAGE.SUCCESS);

        } catch (NoContactFoundException e) {
            System.out.println(MESSAGE.ERR_NOT_FOUND);
        } catch (MalformedTelNumberException e) {
            e.printStackTrace();
        }

    }

    private void removeContact() throws Exception {
        String name = getNumberFromInput(MESSAGE.REMOVE_CONTACT);

        try {
            agendaStub.deleteByName(name);
            System.out.println(MESSAGE.SUCCESS);
        } catch (NoContactFoundException e) {
            System.out.println(MESSAGE.ERR_NOT_FOUND.toString());
        }

    }

    private String getNumberFromInput(MESSAGE insertTel) {
        System.out.printf(insertTel.toString());
        return new Scanner(System.in)
                .next().trim();
    }

    private String getNameFromInput(MESSAGE message) {
        System.out.printf(message.toString());
        return new Scanner(System.in)
                .nextLine().trim();
    }

    private void shutDown() {
        KEEP_ALIVE = false;
    }


}
