package vk.thread;

import javafx.scene.control.Label;
import vk.controllers.MainController;
import vk.interfaces.impl.SearchForData;

public class ThreadLook implements Runnable {

    private SearchForData sfd = new SearchForData();

    private String id;
    private Label label;
    private int threadsIndex;

    public ThreadLook(String id, Label label,int threadsIndex) {
        this.id = id;
        this.label = label;
        this.threadsIndex = threadsIndex;
    }

    @Override
    public void run() {

        while (true) {
            if (sfd.isOnline(id)) {
                MainController.checkShowDialog.get(threadsIndex).setShowORnot(1);

                while (sfd.isOnline(id)) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                label.setText(sfd.getStatus(id));
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
