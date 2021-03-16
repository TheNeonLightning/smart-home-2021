package ru.sbt.mipt.oop;

public class SmartHomeUtility {
    public static Room findRoom(SmartHome home, String doorId) {
        for (Room room : home.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    return room;
                }
            }
        }
        throw new RuntimeException("Door ID not found");
    }

    public static Light findLight(SmartHome home, String lightId) {
        for (Room room : home.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)) {
                    return light;
                }
            }
        }
        throw new RuntimeException("Light ID not found");
    }

    public static Door findDoor(SmartHome home, String doorId) {
        for (Room room : home.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    return door;
                }
            }
        }
        throw new RuntimeException("Door ID not found");
    }
}
