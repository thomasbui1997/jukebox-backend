package com.bui.listening_party.service;

import com.bui.listening_party.model.Room;
import com.bui.listening_party.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomByInvitationCode(String code) {
        return roomRepository.findByInvitationCode(code)
                .orElseThrow(() -> new RuntimeException("Room not found with code: " + code));
    }
}
