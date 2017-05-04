package vk.bd;

import java.sql.*;

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


    public static void addIDDB(String id) {
        try {
            statmt.execute("INSERT INTO usersID ('id') VALUES ('" + id + "');");
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


    public static void getIDDB() {
        try {
        resSet = statmt.executeQuery("SELECT * FROM usersID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connectionDB();
        getIDDB();
        try {
            while (resSet.next()){
                String id = resSet.getString("id");
                System.out.println(id);
                //deleteIDDB("false");
                //deleteIDDB("vinniipuh");
                //deleteIDDB("kenji20");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //for (int i = 0; i < 50; i++){
        //    deleteIDDB("vinniipuh");
        //}


    }
}
