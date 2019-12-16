package client;

import client.ui.controller.Controller;

import java.rmi.registry.Registry;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1098;
    private static Registry registry;


    public static void main(String[] args) throws Exception {

        Controller controller = new Controller();
        controller.showDetail();


    }

}
