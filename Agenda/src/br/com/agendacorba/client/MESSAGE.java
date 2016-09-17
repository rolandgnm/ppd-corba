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
            ("> Entre com um Nome para o novo contato: "),
    INSERT_TEL
            ("> Telefone: "),

    //Consultar 4-6
    SEARCH_NAME
            ("> Entre com um nome a ser consultado: "),
    SEARCHING
            ("- Buscando..."),

    CONTACT_FOUND
            ("* Resultado encontrado: "),

    //Atualizar 7-8
    UPDATE_CONTACT
            ("> Entre com o nome do contato a ser alterado: "),


    //Remover 9
    REMOVE_CONTACT
            ("> Entre com o nome do contato a ser removido: "),

    //sucesso 10
    SUCCESS
            ("* Operaracao realizada com sucesso!"),

    //Erros 11-15
    ERR_WRONG_OPTION
            ("\nERRO 0! Opcao nao existe!\n"),

    ERR_NOT_FOUND
            ("\nERRO 1! Nenhum contato encontrado!\n"),

    ERR_ALREADY_EXISTS
            ("\nERRO 2! Contato já existente!\n"),
    ERR_NO_SERVER_FOUND
            ("\nERRO 3! Nenhum servidor encontrado!\n"),
    ERR_MALFORMED_TEL
            ("\nERRO 4! Numero de telefone mal formatado!\n");

    private final String text;

    MESSAGE(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
