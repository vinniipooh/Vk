package vk.observ;

import java.util.ArrayList;
import java.util.Observable;

public class PersonCollection extends Observable {
    private ArrayList<Person> personCollection = new ArrayList<>();

    public void addPerson(Person person){
        personCollection.add(person);
    }

    public void addPersonIndex(Person person, int index){
        personCollection.add(index, person);
    }

    public Person getPerson (int index){
        return personCollection.get(index);
    }
}
