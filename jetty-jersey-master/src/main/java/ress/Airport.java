package ress;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Airport{
    public int identifier;
    public String ICAO_code;
}
