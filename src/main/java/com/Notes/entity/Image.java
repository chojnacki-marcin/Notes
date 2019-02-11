package com.Notes.entity;



import javax.persistence.*;


@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long imageId;

    private String imageTitle;
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_note_id", referencedColumnName = "noteId")
    private Note note;


    public Image(String imageTitle, String imagePath) {
        this.imageTitle = imageTitle;
        this.imagePath = imagePath;
    }

    public Image(){

    }




    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
