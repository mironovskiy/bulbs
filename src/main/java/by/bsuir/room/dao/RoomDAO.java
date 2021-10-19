package by.bsuir.room.dao;

import by.bsuir.room.entity.Room;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface RoomDAO {
    boolean save(Room room) throws IOException, ParseException;
    List<Room> getRooms() throws IOException, ParseException;
    boolean changeLight(Room room) throws IOException, ParseException;
}
