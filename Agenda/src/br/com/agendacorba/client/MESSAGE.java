package br.com.agendacorba.client;

/**
 * Created by Roland on 9/17/16.
 */
public enum MESSAGE {


    START_UP(
            "########################################\n" +
                    "########## BEM VINDO A AGENDA ##########\n" +
                    "########################################\n"),

    MENU(
            "\n######################\n" +
                    "> Entre com uma acao: " +
                    "\n\t\'i\' Inserir contato" +
                    "\n\t\'p\' Pesquisar contato" +
                    "\n\t\'l\' Listar contatos" +
                    "\n\t\'a\' Atualizar contato" +
                    "\n\t\'r\' Remover contato" +
                    "\n\t\'F\' Finalizar" +
                    ": "),

    //Inserir 2,3
    INSERT_NAME
            ("\t\t> Entre com um Nome para o novo contato: "),
    INSERT_TEL
            ("\t\t> Telefone: "),

    TABLE_HEAD
            ("\n\t\t>Nome,\t#Telefone"),

    //Consultar 4-6
    SEARCH_NAME
            ("\t\t> Entre com um nome a ser consultado: "),
    SEARCHING
            ("\n\t\t- Buscando..."),

    CONTACT_FOUND
            ("\n\t\t* Resultado(s) encontrado(s): "),

    //Atualizar 7-8
    UPDATE_CONTACT
            ("\n\t\t> Entre com o nome do contato a ser alterado: "),


    //Remover 9
    REMOVE_CONTACT
            ("\n\t\t>Entre com o nome do contato a ser removido: "),

    //sucesso 10
    SUCCESS
            ("\n\t\t* Operaracao realizada com sucesso!"),
    SERVER_FOUND
            ("\n* Servidor Encontrado!"),

    //Erros 11-15
    ERR_WRONG_OPTION
            ("\n\t\tERRO 0! Opcao nao existe!\n"),

    ERR_NOT_FOUND
            ("\n\t\tERRO 1! Nenhum contato encontrado!\n"),

    ERR_ALREADY_EXISTS
            ("\n\t\tERRO 2! Contato já existente!\n"),
    ERR_NO_SERVER_FOUND
            ("\n\t\tERRO 3! Nenhum servidor encontrado!\n"),
    ERR_MALFORMED_TEL
            ("\n\t\tERRO 4! Numero de telefone mal formatado!\n"),
    ERR_SERVER_NOT_FOUND
            ("\nERRO 5! Servidor não Encontrado!");

    private final String text;

    MESSAGE(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
