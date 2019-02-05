package com.Notes.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Image {

    @Id
    private long imageId;

    private String imageTitle;

    @NotNull
    private byte[] imageContent;
}
