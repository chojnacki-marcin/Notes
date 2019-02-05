package com.Notes.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Account {

    @Id
    private long accountId;

    @NotNull
    private String email;

    @NotNull
    private String hashedPassword;

    @OneToMany
    private List<Note> notes;
}
