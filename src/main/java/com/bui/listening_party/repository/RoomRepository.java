package com.bui.listening_party.repository;
import com.bui.listening_party.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByInvitationCode(String invitationCode);
}
