package service;

import by.bsuir.room.dao.RoomDAO;
import by.bsuir.room.entity.Room;
import by.bsuir.room.service.RoomService;
import by.bsuir.room.service.impl.RoomServiceImpl;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class TestRoomService {

    @Mock
    private RoomDAO roomDAO;

    private RoomService roomService;

    private List<Room> rooms;

    private Room testRoom;

    @Before
    public void init(){
        roomService = new RoomServiceImpl(roomDAO);

        testRoom = new Room("test", "Россия", false);

        rooms = new ArrayList<>();
        rooms.add(new Room("first", "Беларусь", true));
        rooms.add(new Room("third", "Польша", true));
        rooms.add(testRoom);
    }

    @Test
    public void testGetRoomByName() throws IOException, ParseException {
        Mockito.when(roomDAO.getRooms()).thenReturn(rooms);
        Assert.assertEquals(testRoom, roomService.getRoomByName(testRoom.getName()));
    }

    @Test
    public void testCheckNameOfRoomForUniqueness() throws IOException, ParseException {
        Mockito.when(roomDAO.getRooms()).thenReturn(rooms);

        String nameOfRoom = "test";
        Assert.assertFalse(roomService.checkNameOfRoomForUniqueness(nameOfRoom));

        nameOfRoom = "test1";
        Assert.assertTrue(roomService.checkNameOfRoomForUniqueness(nameOfRoom));
    }

    @Test
    public void testSaveRoom() throws IOException, ParseException {
        Mockito.when(roomDAO.getRooms()).thenReturn(rooms);
        Mockito.when(roomDAO.save(Mockito.any(Room.class))).thenReturn(true);

        String nameOfRoom = "test1";
        String countryOfRoom = "Беларусь";
        Assert.assertTrue(roomService.saveRoom(nameOfRoom, countryOfRoom));

        nameOfRoom = "test";
        countryOfRoom = "Беларусь";
        Assert.assertFalse(roomService.saveRoom(nameOfRoom, countryOfRoom));
    }
}
