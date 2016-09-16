package br.com.agendacorba.client;

/**
 * Created by Roland on 9/15/16.
 */
public class ClientController {
    boolean KEEP_ALIVE = true;
    char choice;
    AgendaServiceHelper agendaService;
//    AgendaAccess agenda;

    //Todo Criar service


    public ClientController() {
        agendaService = new AgendaServiceHelper();

        runCLIInterface();
    }


    private void runCLIInterface() {
        while (KEEP_ALIVE) {
            System.out.printf(MESSAGE[0]);

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
            }


        }

    }

    private void listAllContacts() {
    }

    private void shutDown() {

    }

    private void removeContact() {

    }

    private void updateContact() {

    }

    private void searchContact() {

    }

    private void insertContact() {

    }

    String[] MESSAGE = {
            //Boas vindas 0
            "########################################" +
            "########## BEM VINDO A AGENDA ##########" +
            "########################################",

            //Menu Principal 1
            "> Entre com uma acao: \n" +
                    "\'i\' Inserir\t" +
                    "\'p\' Pesquisar\t" +
                    "\'l\' Listar\t" +
                    "\'a\' Atualizar\t" +
                    "\'r\' Remover\t" +
                    "\'F\' Fechar\t" +
                    ": ",

            //Inserir 2,3
            "> Entre com um Nome para o novo contato: ",

            "> Telefone: ",

            //Consultar 4-6
            "> Entre com um nome a ser consultado: ",

            "- Buscando...",

            "* Resultado encontrado: ",

            //Atualizar 7-8
            "> Entre com o nome do contato a ser alterado: ",

            "Telefone: ",

            //Remover 9
            "> Entre com o nome do contato a ser removido: ",

            //sucesso 10
            "* Operaracao realizada com sucesso!",

            //Erros 11-14
            "ERRO 1! Nenhum contato encontrado!",
            "ERRO 2! Contato já existente!",
            "ERRO 3! Nenhum servidor encontrado!",
            "ERRO 4! Numero de telefone mal formado!"

    };


}
