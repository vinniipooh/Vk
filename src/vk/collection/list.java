package vk.collection;



import vk.bd.BD;

import java.sql.SQLException;
import java.util.ArrayList;

public class list {

    private ArrayList <String> usersID = new ArrayList<>();

    public void addID(String id){
        usersID.add(id);
    }

    public ArrayList<String> getUsersID() {
        return usersID;
    }

    public void addFromBD(){
        BD.getIDDB();
        try{
            while (BD.resSet.next()){
                boolean flag = false;

                for (int i = 0; i < usersID.size();i++){

                    if(usersID.get(i).equals(BD.resSet.getString("id"))){
                        flag = true;
                    }
                }
                if(!flag){
                    usersID.add(BD.resSet.getString("id"));
                }else continue;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteFromList(String id){
        for(int i = 0; i < usersID.size(); i++){
            if(usersID.get(i).equals(id)){
                usersID.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        BD.connectionDB();

    }
}
