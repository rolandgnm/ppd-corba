package br.com.agendacorba.server.control;

import br.com.agendacorba.server.control.ServerController;

/**
 * Created by Roland on 9/15/16.
 */
public class ServerMain {


    private static ServerController controller;

    public static void main(String[] args) {

        controller =  new ServerController(args);

    }
}
