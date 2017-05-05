package vk.observ;

import vk.interfaces.impl.SearchForData;

import java.util.Observable;

public class Person {
    private SearchForData sfd = new SearchForData();
    private String id;
    private String name;
    private String status;

    public Person(String id) {
        this.id = id;
        name = sfd.getFullName(id);
        status = sfd.getStatus(id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }


}
