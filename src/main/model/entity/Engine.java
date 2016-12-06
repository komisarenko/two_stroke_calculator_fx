package main.model.entity;

import javax.persistence.*;

/**
 * Created by sanya on 06.12.16.
 */
@Entity
@Table(name = "main_table")
public class Engine {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "stroke")
    private double stroke;
    @Column(name = "conRodLength")
    private double conRodLength;
    @Column(name = "deck")
    private double deck;
    @Column(name = "pistonHeight")
    private double pistonHeight;
    @Column(name = "inletFloor")
    private double inletFloor;
    @Column(name = "exhaustHeight")
    private double exhaustHeight;
    @Column(name = "transferHeight")
    private double transferHeight;
    @Column(name = "exhaustWidth")
    private double exhaustWidth;
    @Column(name = "exhaustCount")
    private int exhaustCount;
    @Column(name = "transferWidth")
    private double transferWidth;
    @Column(name = "transferCount")
    private int transferCount;
    @Column(name = "inletWidth")
    private double inletWidth;
    @Column(name = "inletHeight")
    private double inletHeight;
    @Column(name = "bore")
    private double bore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStroke() {
        return stroke;
    }

    public void setStroke(double stroke) {
        this.stroke = stroke;
    }

    public double getConRodLength() {
        return conRodLength;
    }

    public void setConRodLength(double conRodLength) {
        this.conRodLength = conRodLength;
    }

    public double getDeck() {
        return deck;
    }

    public void setDeck(double deck) {
        this.deck = deck;
    }

    public double getPistonHeight() {
        return pistonHeight;
    }

    public void setPistonHeight(double pistonHeight) {
        this.pistonHeight = pistonHeight;
    }

    public double getInletFloor() {
        return inletFloor;
    }

    public void setInletFloor(double inletFloor) {
        this.inletFloor = inletFloor;
    }

    public double getExhaustHeight() {
        return exhaustHeight;
    }

    public void setExhaustHeight(double exhaustHeight) {
        this.exhaustHeight = exhaustHeight;
    }

    public double getTransferHeight() {
        return transferHeight;
    }

    public void setTransferHeight(double transferHeight) {
        this.transferHeight = transferHeight;
    }

    public double getExhaustWidth() {
        return exhaustWidth;
    }

    public void setExhaustWidth(double exhaustWidth) {
        this.exhaustWidth = exhaustWidth;
    }

    public int getExhaustCount() {
        return exhaustCount;
    }

    public void setExhaustCount(int exhaustCount) {
        this.exhaustCount = exhaustCount;
    }

    public double getTransferWidth() {
        return transferWidth;
    }

    public void setTransferWidth(double transferWidth) {
        this.transferWidth = transferWidth;
    }

    public int getTransferCount() {
        return transferCount;
    }

    public void setTransferCount(int transferCount) {
        this.transferCount = transferCount;
    }

    public double getInletWidth() {
        return inletWidth;
    }

    public void setInletWidth(double inletWidth) {
        this.inletWidth = inletWidth;
    }

    public double getInletHeight() {
        return inletHeight;
    }

    public void setInletHeight(double inletHeight) {
        this.inletHeight = inletHeight;
    }

    public double getBore() {
        return bore;
    }

    public void setBore(double bore) {
        this.bore = bore;
    }
}
