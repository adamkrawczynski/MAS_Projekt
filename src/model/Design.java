package model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Design {

    public Design(PaintType paintType, String paintColor, RimType rimType, String rimSize, String interiorColor, InteriorMaterial interiorMaterial) {
        this.paintType = paintType;
        this.paintColor = paintColor;
        this.rimType = rimType;
        this.rimSize = rimSize;
        this.interiorColor = interiorColor;
        this.interiorMaterial = interiorMaterial;
    }

    public Design() {

    }

    @Id
    @GeneratedValue(generator = "increment")
    @Column(unique = true)
    private int designId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaintType paintType;

    @NotNull
    @Column(length = 10)
    private String paintColor;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private RimType rimType;

    @NotNull
    @Column(length = 20)
    private String rimSize;

    @NotNull
    @Column(length = 10)
    private String interiorColor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private InteriorMaterial interiorMaterial;

    public int getDesignId() {
        return designId;
    }

    public PaintType getPaintType() {
        return paintType;
    }

    public void setPaintType(PaintType paintType) {
        this.paintType = paintType;
    }

    public String getPaintColor() {
        return paintColor;
    }

    public void setPaintColor(String paintColor) {
        this.paintColor = paintColor;
    }

    public RimType getRimType() {
        return rimType;
    }

    public void setRimType(RimType rimType) {
        this.rimType = rimType;
    }

    public String getRimSize() {
        return rimSize;
    }

    public void setRimSize(String rimSize) {
        this.rimSize = rimSize;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public InteriorMaterial getInteriorMaterial() {
        return interiorMaterial;
    }

    public void setInteriorMaterial(InteriorMaterial interiorMaterial) {
        this.interiorMaterial = interiorMaterial;
    }
}