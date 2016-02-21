package ress;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Crew extends User{
    public String name;
    public CrewStatus status;
}
