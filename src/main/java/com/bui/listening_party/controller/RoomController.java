package com.bui.listening_party.controller;

import com.bui.listening_party.model.Room;
import com.bui.listening_party.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/find")
    public Room getRoomByInvitationCode(@RequestParam String code) {
        return roomService.getRoomByInvitationCode(code);
    }
}
