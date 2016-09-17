package br.com.agendacorba.client;

public class ClientMain {

    static ClientController controller;

    public static void main(String[] args) {

        controller = new ClientController(args);

    }
}
