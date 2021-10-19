package by.bsuir.room.controller;

import by.bsuir.room.entity.Room;
import by.bsuir.room.service.RoomService;
import by.bsuir.room.service.ServiceFactory;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class RestRoomController {

    @GetMapping("/updateRoomData")
    public Room updateRoomData(@RequestParam String nameOfRoom) throws IOException, ParseException {
        ServiceFactory factory = ServiceFactory.getInstance();
        RoomService roomService = factory.getRoomService();
        Room room = roomService.getRoomByName(nameOfRoom);

        return room;
    }

    @GetMapping("/changeLight")
    public void changeLight(@RequestParam String nameOfRoom) throws IOException, ParseException {
        ServiceFactory factory = ServiceFactory.getInstance();
        RoomService roomService = factory.getRoomService();
        roomService.changeLight(nameOfRoom);
    }

    @GetMapping("")
    public ModelAndView start() {
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/home")
    public ModelAndView execute(Map<String, Object> model) throws IOException, ParseException {
        ServiceFactory factory = ServiceFactory.getInstance();
        RoomService roomService = factory.getRoomService();

        List<Room> rooms = roomService.getRooms();
        model.put("rooms", rooms);

        return new ModelAndView("home_page");
    }

    @PostMapping("/home")
    public ModelAndView addRoom(@RequestParam String nameOfRoom, @RequestParam String countryOfRoom, Map<String, Object> model) throws IOException, ParseException {

        ServiceFactory factory = ServiceFactory.getInstance();
        RoomService roomService = factory.getRoomService();

        List<Room> rooms = roomService.getRooms();
        model.put("rooms", rooms);

        if (!roomService.saveRoom(nameOfRoom, countryOfRoom)){
            model.put("message", "Имя комнаты уже занято");
            return new ModelAndView("home_page");
        }

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/home/room")
    public ModelAndView enterRoom(@RequestParam String nameOfRoom, @RequestParam String country, @RequestParam String userCountry, Map<String, Object> model) throws IOException, ParseException {
        ServiceFactory factory = ServiceFactory.getInstance();
        RoomService roomService = factory.getRoomService();

        Room room = roomService.getRoomByName(nameOfRoom);

        if (country.equals(userCountry)){
            model.put("roomData", room);
            return new ModelAndView("room_page");
        } else {
            return new ModelAndView("redirect:/home");
        }

    }

}