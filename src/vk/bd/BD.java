package vk.bd;

import java.sql.*;
import java.util.ArrayList;

public class BD {

    private static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;


    public static void connectionDB() {
        try {
            conn = null;
            conn = DriverManager.getConnection("jdbc:sqlite:D:/My project .java/Vk/src/vk/bd/usersID.s3db");
            statmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createColumn() {
        try {
            statmt = conn.createStatement();
            statmt.execute("CREATE TABLE if not exists 'usersID' ('id' text);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addIDDB(String id) {
        try {
            if (checkRepeat(id)) {
                statmt.execute("INSERT INTO usersID ('id') VALUES ('" + id + "');");
            }else {
                System.out.println(id);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteIDDB(String id) {
        try {
            statmt.execute("DELETE FROM usersID WHERE id='" + id + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> getIDDB() {
        ArrayList<String> people = new ArrayList<>();
        try {
            resSet = statmt.executeQuery("SELECT * FROM usersID");
            while(resSet.next()){
                people.add(resSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public static boolean checkRepeat(String id){
        for (String people : getIDDB()){
            if (people == id)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        connectionDB();
        for(String id : getIDDB()){
            System.out.println(id);
        }

        //for (int i = 0; i < 50; i++){
        //    deleteIDDB("vinniipuh");
        //}


    }
}
