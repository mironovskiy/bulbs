package by.bsuir.room.service;

import by.bsuir.room.entity.Room;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface RoomService {
    List<Room> getRooms() throws IOException, ParseException;
    boolean checkNameOfRoomForUniqueness(String nameOfRoom) throws IOException, ParseException;
    boolean saveRoom(String nameOfRoom, String countryOfRoom) throws IOException, ParseException;
    Room getRoomByName(String nameOfRoom) throws IOException, ParseException;
    void changeLight(String nameOfRoom) throws IOException, ParseException;
}
