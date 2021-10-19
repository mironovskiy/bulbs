package by.bsuir.room.dao;

import by.bsuir.room.dao.impl.RoomDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final  RoomDAO roomDAO = new RoomDAOImpl();

    private DAOFactory() {}

    public RoomDAO getRoomDAO() {
        return roomDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}
