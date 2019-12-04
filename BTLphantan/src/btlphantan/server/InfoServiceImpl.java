package server;

import interfaces.InforService;
import client.ui.model.Info;
import server.ui.controller.ProfileControllerSv;
import server.ui.model.InfoUpdate;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InfoServiceImpl extends UnicastRemoteObject implements InforService {

    InfoUpdate infoUpdate;
    protected InfoServiceImpl(InfoUpdate infoUpdate) throws RemoteException {
        this.infoUpdate = infoUpdate;
    }

    @Override
    public synchronized Info getInfo() throws  RemoteException{
        if(infoUpdate ==  null){
            return new Info();
        }else {
            Info info = new Info();
            info.setName(infoUpdate.getName());
            info.setAddress(infoUpdate.getAddress());
            info.setAge(infoUpdate.getAge());
            info.setClasses(infoUpdate.getClasses());
            info.setHome(infoUpdate.getHome());
            info.setId(infoUpdate.getId());
            info.setEmail(infoUpdate.getEmail());
            info.setPhone(infoUpdate.getPhone());
            info.setSex(infoUpdate.isSex());
            return info;
        }
    }

    @Override
    public synchronized void updateInfo(Info info) throws RemoteException {
        infoUpdate.setName(info.getName());
        infoUpdate.setAddress(info.getAddress());
        infoUpdate.setAge(info.getAge());
        infoUpdate.setClasses(info.getClasses());
        infoUpdate.setHome(info.getHome());
        infoUpdate.setId(info.getId());
        infoUpdate.setEmail(info.getEmail());
        infoUpdate.setPhone(info.getPhone());
        infoUpdate.setSex(info.isSex());

        System.out.println(infoUpdate.getName());
//        ProfileControllerSv profileControllerSv = new ProfileControllerSv();
//        profileControllerSv.upDateInfo(infoUpdate);
    }

}
