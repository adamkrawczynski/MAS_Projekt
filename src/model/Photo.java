package model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Photo {

    public Photo(Car car, String url) {
        this.car = car;
        this.url = url;
    }

    public Photo() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int photoId;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carId")
    private Car car;

    @NotNull
    private String url;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
