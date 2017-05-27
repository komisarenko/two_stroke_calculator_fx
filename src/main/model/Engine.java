package main.model;

/**
 * Created by sanya on 12.08.16.
 */
public class Engine {

    private double stroke;
    private double conRodLength;
    private double deck;
    private double pistonHeight;
    private double inletFloor;
    private double exhaustHeight;
    private double transferHeight;
    private double exhaustWidth;
    private int exhaustCount;
    private int inletCount;
    private double transferWidth;
    private int transferCount;
    private double inletWidth;
    private double inletHeight;
    private double bore;
    private double exhaustDuration;
    private double transferDuration;
    private double inletDuration;
    private double volume;
    private double fullVolume;
    private double exhaustAngleArea;
    private double transferAngleArea;
    private double inletAngleArea;
    private double exhaustTimeArea;
    private double transferTimeArea;
    private double inletTimeArea;
    private double exhaustTime;
    private double inletTime;
    private double transferTime;
    private double exhaustArea;
    private double transferArea;
    private double inletArea;
    private int rpm;

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public double getExhaustTimeArea() {
        return exhaustTimeArea;
    }

    public void setExhaustTimeArea(double exhaustTimeArea) {
        this.exhaustTimeArea = exhaustTimeArea;
    }

    public double getTransferTimeArea() {
        return transferTimeArea;
    }

    public void setTransferTimeArea(double transferTimeArea) {
        this.transferTimeArea = transferTimeArea;
    }

    public double getInletTimeArea() {
        return inletTimeArea;
    }

    public void setInletTimeArea(double inletTimeArea) {
        this.inletTimeArea = inletTimeArea;
    }

    public double getExhaustArea() {
        return exhaustArea;
    }

    public void setExhaustArea(double exhaustArea) {
        this.exhaustArea = exhaustArea;
    }

    public double getTransferArea() {
        return transferArea;
    }

    public void setTransferArea(double transferArea) {
        this.transferArea = transferArea;
    }

    public double getInletArea() {
        return inletArea;
    }

    public void setInletArea(double inletArea) {
        this.inletArea = inletArea;
    }

    public double getBore() {
        return bore;
    }

    public void setBore(double bore) {
        this.bore = bore;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getFullVolume() {
        return fullVolume;
    }

    public void setFullVolume(double fullVolume) {
        this.fullVolume = fullVolume;
    }

    public double getExhaustTime() {
        return exhaustTime;
    }

    public void setExhaustTime(double exhaustTime) {
        this.exhaustTime = exhaustTime;
    }

    public double getInletTime() {
        return inletTime;
    }

    public void setInletTime(double inletTime) {
        this.inletTime = inletTime;
    }

    public double getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(double transferTime) {
        this.transferTime = transferTime;
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

    public double getExhaustDuration() {
        return exhaustDuration;
    }

    public void setExhaustDuration(double exhaustDuration) {
        this.exhaustDuration = exhaustDuration;
    }

    public double getTransferDuration() {
        return transferDuration;
    }

    public void setTransferDuration(double transferDuration) {
        this.transferDuration = transferDuration;
    }

    public double getInletDuration() {
        return inletDuration;
    }

    public void setInletDuration(double inletDuration) {
        this.inletDuration = inletDuration;
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

    public double getExhaustAngleArea() {
        return exhaustAngleArea;
    }

    public void setExhaustAngleArea(double exhaustAngleArea) {
        this.exhaustAngleArea = exhaustAngleArea;
    }

    public double getTransferAngleArea() {
        return transferAngleArea;
    }

    public void setTransferAngleArea(double transferAngleArea) {
        this.transferAngleArea = transferAngleArea;
    }

    public double getInletAngleArea() {
        return inletAngleArea;
    }

    public void setInletAngleArea(double inletAngleArea) {
        this.inletAngleArea = inletAngleArea;
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

    public int getInletCount() {
        return inletCount;
    }

    public void setInletCount(int inletCount) {
        this.inletCount = inletCount;
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
}
