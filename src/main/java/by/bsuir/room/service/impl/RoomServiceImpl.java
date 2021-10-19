package by.bsuir.room.service.impl;

import by.bsuir.room.dao.DAOFactory;
import by.bsuir.room.dao.RoomDAO;
import by.bsuir.room.entity.Room;
import by.bsuir.room.service.RoomService;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    private RoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();

    public RoomServiceImpl() {
    }

    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public List<Room> getRooms() throws IOException, ParseException {
          return roomDAO.getRooms();
    }

    @Override
    public synchronized boolean checkNameOfRoomForUniqueness(String nameOfRoom) throws IOException, ParseException {
        List<Room> rooms = roomDAO.getRooms();

        for (Room room : rooms) {
            if (room.getName().equals(nameOfRoom)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public synchronized boolean saveRoom(String nameOfRoom, String countryOfRoom) throws IOException, ParseException {
        if (!checkNameOfRoomForUniqueness(nameOfRoom)) {
            return false;
        }

        Room room = new Room(nameOfRoom, countryOfRoom, false);
        roomDAO.save(room);

        return true;
    }

    @Override
    public Room getRoomByName(String nameOfRoom) throws IOException, ParseException {
        Room room = new Room();

        List<Room> rooms = roomDAO.getRooms();
        for (Room value : rooms) {
            if (value.getName().equals(nameOfRoom)) {
                room = value;
                break;
            }
        }

        return room;
    }

    @Override
    public void changeLight(String nameOfRoom) throws IOException, ParseException {
        Room room = getRoomByName(nameOfRoom);
        roomDAO.changeLight(room);
    }

}
