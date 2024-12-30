package com.bui.listening_party.model;

import jakarta.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isUpvote;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isUpvote() {
        return isUpvote;
    }

    public void setUpvote(boolean upvote) {
        isUpvote = upvote;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
