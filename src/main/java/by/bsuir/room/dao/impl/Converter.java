package by.bsuir.room.dao.impl;

import by.bsuir.room.entity.Room;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Converter {

    private File file = new File("room.json");

    public Converter() {
    }

    public Converter(String path) {
        this.file = new File(path);
    }

    public synchronized boolean convertRoomToJson(List<Room> rooms) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, rooms);
        return true;
    }

    public List<Room> convertJsonToRoom() throws ParseException, IOException {
        List<Room> rooms = new ArrayList<Room>();
        JSONParser parser = new JSONParser();

        checkFile();

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

        JSONArray jsonArr = (JSONArray) parser.parse(inputStreamReader/*new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)*/);

        for (Object o : jsonArr) {
            JSONObject jsonObj = (JSONObject) o;
            Room room = new Room();
            room.setName((String) jsonObj.get("name"));
            room.setCountry((String) jsonObj.get("country"));
            room.setLightOn((boolean) jsonObj.get("lightOn"));
            rooms.add(room);
        }

        inputStreamReader.close();

        return rooms;
    }

    public void checkFile() throws IOException {
        if (!file.exists()){
            file.createNewFile();

            List<Room> rooms = new ArrayList<Room>();
            rooms.add(new Room("first", "Беларусь", true));
            rooms.add(new Room("second", "Россия", false));
            rooms.add(new Room("third", "Польша", true));

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, rooms);
        }
    }
}