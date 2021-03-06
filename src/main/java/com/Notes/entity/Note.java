package com.Notes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@Column(name = "note_id")
    private long noteId;

    private String noteTitle;

    @NotNull
    private String noteContent;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Account owner;


    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }
}
