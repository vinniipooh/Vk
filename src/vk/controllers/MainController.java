package vk.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vk.bd.BD;
import vk.dialog.DialogWindows;
import vk.interfaces.impl.SearchForData;
import vk.thread.ThreadLook;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private VBox addPanel;

    private Stage addDialogStage;
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private enterIDController enterIDController = new enterIDController();
    private Stage mainStage;
    private SearchForData sfd = new SearchForData();
    private ArrayList<Thread> threadsList = new ArrayList<>();
    private static int threadIndex = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLoader();
        BD.connectionDB();
    }

    public void addButtonAction(ActionEvent actionEvent) {
        if (sfd.checkInternetConnection()) {
            showAddDialog();
            if (enterIDController.isFlag()) {
                if (addHBox(enterIDController.getId()) != null) {
                    addPanel.getChildren().add(addHBox(enterIDController.getId()));
                    enterIDController.setFlag(false);
                    BD.addIDDB(enterIDController.getId());
                    return;
                }
                return;
            }
            return;
        } else {
            DialogWindows.showErrorDialog("Ошибка", "Проверьте подключение к интернету");
            return;
        }
    }

    public HBox addHBox(String id) {
        if (sfd.checkId(id)) {
            try {
                HBox box = FXMLLoader.load(getClass().getResource("../fxml/addNode.fxml"));
                for (Node node1 : box.getChildren()) {
                    String idComponent = node1.getId();
                    switch (idComponent) {
                        case "nameLabel":
                            Label nameLabel = (Label) node1;
                            nameLabel.setText(sfd.getFullName(id));
                            break;
                        case "idLabel":
                            Label idLabel = (Label) node1;
                            idLabel.setText(id);
                            break;
                        case "statusLabel":
                            Label statusLabel = (Label) node1;
                            statusLabel.setText(sfd.getStatus(id));
                            break;
                    }
                }
                return box;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            DialogWindows.showErrorDialog("Ошибка", "Неправельно введён id");
            return null;
        }
        return null;
    }

    private void initLoader() {
        try {
            fxmlLoader.setLocation(getClass().getResource("../fxml/enterID.fxml"));
            fxmlEdit = fxmlLoader.load();
            enterIDController = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void showAddDialog() {
        if (addDialogStage == null) {
            addDialogStage = new Stage();

            addDialogStage.setTitle("Добавить пользователя");
            addDialogStage.setMinHeight(150);
            addDialogStage.setMinWidth(300);
            addDialogStage.setResizable(false);
            addDialogStage.setScene(new Scene(fxmlEdit));
            addDialogStage.initModality(Modality.WINDOW_MODAL);
            addDialogStage.initOwner(mainStage);
        }
        addDialogStage.showAndWait();

    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void createBoxStart() {
        for (String id :
                BD.getIDDB()) {
            addPanel.getChildren().add(addHBox(id));
        }
    }

    public void deleteBtn(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        HBox box = (HBox) (btn.getParent()).getParent();

        for (Node node : box.getChildren()) {
            if (node.getId().equals("idLabel")) {
                Label label = (Label) node;
                BD.deleteIDDB(label.getText());
            }
        }


        VBox vb = (VBox) box.getParent();
        vb.getChildren().remove(box);
    }

    public void loockingBtn(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String getName = btn.getText();
        btn.setId(String.valueOf(threadIndex));

        Label label;
        String id = "";
        HBox box = (HBox) (btn.getParent()).getParent();

        for (Node node1 : box.getChildren()) {
            String name = node1.getId();
            if (name.equals("idLabel")) {
                label = (Label) node1;
                id = label.getText();
                break;
            }
        }


        if (getName.equals("Следить")) {
            if (!threadsList.contains(threadIndex)) {
                Runnable addThread = new ThreadLook(id);
                Thread thread = new Thread(addThread);
                thread.start();
                btn.setText("Не следить");
            } else {
                threadsList.get(Integer.parseInt(btn.getId())).notify();
                btn.setText("Не следить");
            }
            return;
        } else if (getName.equals("Не следить")) {
            try {
                threadsList.get(Integer.parseInt(btn.getId())).wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            btn.setText("Следить");
            return;
        }
    }

    public void downloadPeople(ActionEvent actionEvent) {
        createBoxStart();
    }
}