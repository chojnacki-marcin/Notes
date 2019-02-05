package com.Notes.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Item {

    @Id
    private long itemId;

    @NotNull
    private String itemContent;

    private boolean checked;

}
