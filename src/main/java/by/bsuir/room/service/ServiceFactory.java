package by.bsuir.room.service;

import by.bsuir.room.service.impl.RoomServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final RoomService roomService = new RoomServiceImpl();

    private ServiceFactory() {}

    public RoomService getRoomService() { return roomService; }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
