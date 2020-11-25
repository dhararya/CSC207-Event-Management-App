package Schedule;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Stores all the rooms for the conference.
 */
public class RoomStorage {
    /**
     * A list of all the rooms.
     */
    ArrayList<Room> roomList;
    /**
     * A mapping of rooms to its corresponding RoomScheduleManager which checks for double booking.
     */
    //HashMap<Room, RoomScheduleManager> scheduleList;
    EventManager eventManager;

    HashMap<String, Room>roomNameMap;

    /**
     * Creates a room storage.
     */
    public RoomStorage(){
        this.roomList = new ArrayList<>();
        //this.scheduleList = new HashMap<Room, RoomScheduleManager>();
        this.eventManager = new EventManager();
        this.roomNameMap = new HashMap<String, Room>();
    }

    /**
     * Creates a room with the specified name. And adds the rooms to the room list  and schedule list.
     * @param roomName The name of the room.
     * @return A boolean notifying if the room was successfully created.
     */
    public boolean createRoom(String roomName) {
        boolean bool = true;
        for (Room r : roomList) {
            if (r.getRoomName().equals(roomName)) {
                bool = false;
            }
        }
        if (bool) {
            Room room = new Room(roomName);
            roomList.add(room);
//            RoomScheduleManager rScheduleManager = new RoomScheduleManager(room);
//            scheduleList.put(room, rScheduleManager);
            roomNameMap.put(roomName, room);
            return true;
        }
        return false;
    }

    /**
     * Creates a room with the specified name and capacity. And adds the rooms to the room list  and schedule list.
     * @param roomName The name of the room.
     * @param capacity The capacity of the room.
     */
    public void createRoom(String roomName, int capacity) {
        boolean bool = true;
        for (Room r : roomList) {
            if (r.getRoomName().equals(roomName)) {
                bool = false;
            }
        }
        if (bool) {
            Room room = new Room(roomName, capacity);
            roomList.add(room);
//            RoomScheduleManager rScheduleManager = new RoomScheduleManager(room);
//            scheduleList.put(room, rScheduleManager);
            roomNameMap.put(roomName, room);
        }
    }
    public void removeRoom(String roomName){
        for (Room r : roomList){
            if (r.getRoomName().equals(roomName)){
                roomList.remove(r);
            }
        }
    }
    /**
     * Gets the list of rooms.
     * @return An ArrayList representing the roomList of RoomStorage.
     */
    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    /**
     * Gets the schedule list.
     * @return A HashMap representing the scheduleList of RoomStorage.
     */
    public boolean addEvent(String roomName, String EventId, LocalDateTime start, LocalDateTime end){
        Room r = roomNameMap.get(roomName);
        boolean found = true;
        for (String id : r.getTalkList()){
            if (id.equals(EventId)){
                found = false;
            }
        }
        if (found){
            if (eventManager.checkDoubleBooking(start, end, r.getTalkList())){
                r.addTalk(EventId);
                return true;
            }
        }
        return false;
    }
    public void removeEvent(String roomName, String EventId){
        Room r = roomNameMap.get(roomName);
        for (String id : r.getTalkList()){
            if (id.equals(EventId)){
                r.getTalkList().remove(EventId);
            }
        }
    }
}



