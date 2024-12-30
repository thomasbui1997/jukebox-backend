package com.bui.listening_party.repository;

import com.bui.listening_party.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByTitle(String title);
}