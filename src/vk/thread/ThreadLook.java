package vk.thread;

import javafx.scene.control.Label;
import vk.interfaces.impl.SearchForData;

import javax.swing.*;
import java.awt.*;


public class ThreadLook implements Runnable {

    private SearchForData sfd = new SearchForData();

    private String id;


    public ThreadLook(String id) {
        this.id = id;

    }

    @Override
    public void run() {

        while (true) {
            if (sfd.isOnline(id)) {
                JOptionPane.showMessageDialog(null, sfd.getFullName(id)+ " появился в сети!");

                while (sfd.isOnline(id)) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {


                while (!sfd.isOnline(id)) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
