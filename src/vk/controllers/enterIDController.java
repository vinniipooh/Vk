package vk.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vk.dialog.DialogWindows;
import vk.collection.list;
import vk.interfaces.impl.SearchForData;


/**
 * Created by Александр on 29.04.2017.
 */
public class enterIDController {
    private SearchForData sfd = new SearchForData();

    @FXML
    private TextField giveID;

    private boolean flag = false;
    private String id = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void okButton(ActionEvent actionEvent) {
            id = giveID.getText();
            flag = true;
            cancelButton(actionEvent);
    }

    public void cancelButton(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
        giveID.setText("");
    }
}
