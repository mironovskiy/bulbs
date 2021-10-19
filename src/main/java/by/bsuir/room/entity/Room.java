package by.bsuir.room.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;


public class Room implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("lightOn")
    private boolean isLightOn;

    public Room() {
        
    }

    public Room(String name, String country, boolean isLightOn) {
        this.name = name;
        this.country = country;
        this.isLightOn = isLightOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isLightOn() {
        return isLightOn;
    }

    public void setLightOn(boolean lightOn) {
        isLightOn = lightOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return isLightOn() == room.isLightOn() &&
                Objects.equals(getName(), room.getName()) &&
                Objects.equals(getCountry(), room.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCountry(), isLightOn());
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", isLightOn=" + isLightOn +
                '}';
    }
}
