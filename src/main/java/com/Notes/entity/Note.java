package com.Notes.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Note {

    @Id
    private long noteId;

    private String noteTitle;

    @NotNull
    private String noteContent;


    @OneToMany
    private List<Image> images;

    @OneToMany
    private List<Item> items;

}
