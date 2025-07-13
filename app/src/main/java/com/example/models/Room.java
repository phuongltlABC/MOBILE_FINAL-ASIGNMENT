package com.example.models;

public class Room {
    private int roomId;
    private String roomName;
    private String description;

    public Room(int roomId, String roomName, String description) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.description = description;
    }

    public int getRoomId() { return roomId; }
    public String getRoomName() { return roomName; }
    public String getDescription() { return description; }

    public void setRoomId(int roomId) { this.roomId = roomId; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    public void setDescription(String description) { this.description = description; }
}