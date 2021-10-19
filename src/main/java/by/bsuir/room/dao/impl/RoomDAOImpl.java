package by.bsuir.room.dao.impl;

import by.bsuir.room.dao.RoomDAO;
import by.bsuir.room.entity.Room;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;


public class RoomDAOImpl implements RoomDAO {

    private Converter converter = new Converter();

    public RoomDAOImpl() {
    }

    public RoomDAOImpl(Converter converter) {
        this.converter = converter;
    }

    @Override
    public synchronized boolean save(Room room) throws IOException, ParseException {
        List<Room> rooms = converter.convertJsonToRoom();
        rooms.add(room);
        return converter.convertRoomToJson(rooms);
    }

    @Override
    public List<Room> getRooms() throws IOException, ParseException {
        return converter.convertJsonToRoom();
    }

    @Override
    public synchronized boolean changeLight(Room room) throws IOException, ParseException {
        List<Room> rooms = converter.convertJsonToRoom();

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getName().equals(room.getName())){
                rooms.get(i).setLightOn(!rooms.get(i).isLightOn());
                return converter.convertRoomToJson(rooms);
            }
        }
        return true;
    }
}
