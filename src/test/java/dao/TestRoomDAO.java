package dao;

import by.bsuir.room.dao.RoomDAO;
import by.bsuir.room.dao.impl.Converter;
import by.bsuir.room.dao.impl.RoomDAOImpl;
import by.bsuir.room.entity.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.json.simple.parser.ParseException;
import org.junit.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestRoomDAO {

    private RoomDAO roomDAO;

    private String filePath = "test.json";

    private File file;

    private List<Room> testRooms;

    private Room testRoom;

    @Before
    public void init() throws IOException {
        file = new File(filePath);
        testRoom = new Room("test1", "Беларусь", true);

        if (!file.exists()){
            file.createNewFile();

            testRooms = new ArrayList<Room>();
            testRooms.add(testRoom);
            testRooms.add(new Room("test2", "Россия", false));
            testRooms.add(new Room("test3", "Польша", true));

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, testRooms);
        }

        roomDAO = new RoomDAOImpl(new Converter(filePath));
    }

    @After
    public void delete() throws IOException {
        System.gc();
        Files.delete(Paths.get(file.getPath()));
    }

    @Test
    public void testSaveRoom() throws IOException, ParseException {

        roomDAO.save(new Room("test4", "Беларусь", false));

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        Assert.assertEquals("[{\"lightOn\":true,\"name\":\"test1\",\"country\":\"Беларусь\"},{\"lightOn\":false,\"name\":\"test2\",\"country\":\"Россия\"},{\"lightOn\":true,\"name\":\"test3\",\"country\":\"Польша\"},{\"lightOn\":false,\"name\":\"test4\",\"country\":\"Беларусь\"}]",
                bufferedReader.readLine());

        bufferedReader.close();
    }

    @Test
    public void testGetRooms() throws IOException, ParseException {
        Assert.assertThat(roomDAO.getRooms(), CoreMatchers.is(testRooms));
    }

    @Test
    public void testChangeLight() throws IOException, ParseException {
        roomDAO.changeLight(testRoom);
        Assert.assertFalse(roomDAO.getRooms().get(0).isLightOn());
    }
}