package com.github.jeremyrickard.svccatdocumentdb.model;

public class User {
    
    private String id;
    private String firstName;
    private String lastName;

    public String getId() {
       return this.id;
    }
 
    public void setId(String id) {
       this.id = id;
    }
 
    public String getFirstName() {
       return firstName;
    }
 
    public void setFirstName(String firstName) {
       this.firstName = firstName;
    }
 
    public String getLastName() {
       return lastName;
    }
 
    public void setLastName(String lastName) {
       this.lastName = lastName;
    }
 
    @Override
    public String toString() {
       return String.format("User: %s %s", firstName, lastName);
    }
}