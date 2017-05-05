package vk.interfaces.impl;

import vk.interfaces.SeachDataInterface;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchForData implements SeachDataInterface {

    @Override
    public boolean checkInternetConnection() {
        try {

            URL url = new URL("https://api.vk.com/method/users.get?user_ids=vinniipuh&fields=online,photo_50&v=5.63");

            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1000 * 20); // mTimeout is in seconds
            urlc.connect();
            if (urlc.getResponseCode() == 200) {
                return true;
            }

        } catch (MalformedURLException e1) {
        } catch (IOException e) {
        }
        return false;
    }

    @Override
    public boolean checkId(String id) {
        String info = searchForData(id);

        String error = info.substring(2, 3);
        if (error.equals("e")) {
            return false;
        } else if (info.substring(13, 14).equals("]")) {
            return false;
        }else return true;
    }

    @Override
    public String searchForData(String id) {
        String s = "";

        try {
            URL url = new URL("https://api.vk.com/method/users.get?user_ids=" + id + "&fields=online,photo_50&v=5.63");

            try {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                String string = reader.readLine();
                while (string != null) {
                    s += string;
                    string = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
//        System.out.println(s);
        return s;
    }

    @Override
    public synchronized boolean isOnline(String id) {
        String info = searchForData(id);

        int i = info.indexOf("online");
        i += 8;
        int d = i + 1;
        String status = info.substring(i, d);
        return status.equals("1");
    }

    @Override
    public String getFullName(String id) {
        String info = searchForData(id);

        String firstName = "", lastName = "";
        int i = info.indexOf("first_name");
        i += 13;
        int d = info.indexOf(",", i);
        d--;

        firstName = info.substring(i, d);

        int x = info.indexOf("last_name");
        x += 12;
        int y = info.indexOf(",", x);
        y--;
        lastName = info.substring(x, y);

        return firstName + " " + lastName;

    }

    @Override
    public String getStatus(String id) {
        if (isOnline(id))
            return "online";
        else return "offline";
    }

}
