package com.bui.listening_party.controller;

import com.bui.listening_party.model.Room;
import com.bui.listening_party.model.Song;
import com.bui.listening_party.repository.SongRepository;
import com.bui.listening_party.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;
    private final SongRepository songRepository;

    public RoomController(RoomService roomService, SongRepository songRepository) {
        this.roomService = roomService;
        this.songRepository = songRepository;
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

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    // Add a song to the room
    @PostMapping("/{roomId}/songs")
    public Room addSongToRoom(@PathVariable Long roomId, @RequestBody Song song) {
        Room room = roomService.getRoomById(roomId);

        // Check if song already exists in database
        Song exisitingSong = songRepository.findByTitle(song.getTitle())
                .orElseGet(() -> songRepository.save(song));

        // Add song to room's song list
        room.getSongs().add(exisitingSong);

        // Save the updated room
        return roomService.saveRoom(room);
    }

    // Get songs for a room, sorted by votes
    @GetMapping("/{roomId}/songs")
    public List<Song> getSongs(@PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        return room.getSongs();
    }


    // Up or Down vote a song
    @PostMapping("/songs/{songId}/vote")
    public Song voteSong(@PathVariable Long songId, @RequestParam boolean upvote) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));
        song.setVotes(song.getVotes() + (upvote ? 1 : -1));
        return songRepository.save(song);
    }
}
