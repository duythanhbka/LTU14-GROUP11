package server;

import interfaces.InforService;
import server.ui.controller.ProfileControllerSv;
import server.ui.model.InfoUpdate;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private static final int PORT = 1098;
    private static Registry registry;


    public static void startRegistry() throws RemoteException {

        // Tạo một bộ đăng ký (Registry) tại Server.
        registry =  LocateRegistry.createRegistry(PORT);
    }
    public static void registerObject(String name, Remote remoteObj)
            throws RemoteException, AlreadyBoundException {
        // Đăng ký đối tượng vào bộ đăng ký.
        // Nó được gắn với cái tên nào đó.
        // Client sẽ tìm trên bộ đăng ký với tên này để có thể gọi đối tượng.


        registry.bind(name, remoteObj);
        System.out.println("Registered: " + name + " -> "
                + remoteObj.getClass().getName() + "[" + remoteObj + "]");

    }
    public static InfoUpdate setInfo(){
        InfoUpdate infoUpdate = new InfoUpdate();
        infoUpdate.setName("Trường");
        infoUpdate.setSex(true);
        infoUpdate.setPhone("090909");
        infoUpdate.setEmail("hahaha@gmail.com");
        infoUpdate.setId("2015");
        infoUpdate.setHome("HP");
        infoUpdate.setAddress("HN");
        infoUpdate.setClasses("LTU14");
        infoUpdate.setAge("22");
        return infoUpdate;
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Server starting...");
        startRegistry();
        registerObject(InforService.class.getSimpleName(), new InfoServiceImpl(new InfoUpdate()));

        // Server đã được start, và đang lắng nghe các request từ Client.
        System.out.println("Server started!");
        ProfileControllerSv profileController = new ProfileControllerSv("Server");
        profileController.showProfile();

    }
}
