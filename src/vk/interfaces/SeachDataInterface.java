package vk.interfaces;

import javafx.scene.image.Image;

/**
 * Created by Александр on 29.04.2017.
 */
public interface SeachDataInterface {

    boolean checkInternetConnection();

    boolean checkId(String id);

    String searchForData(String id);

    String getFullName(String id);

    String getStatus(String id);

    boolean isOnline(String id);

    //Image getImage(String id);

}
