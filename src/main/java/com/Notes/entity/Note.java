package com.Notes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long noteId;

    private String noteTitle;

    @NotNull
    private String noteContent;

    @OneToMany
    private List<Image> images;

    @OneToMany
    private List<Item> items;


    private long accountId;

}
