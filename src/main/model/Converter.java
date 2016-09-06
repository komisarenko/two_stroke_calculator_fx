package main.model;

public class Converter {

    public static double mmToInches(double mm) {
        return mm * 0.0393700787;
    }

    public static double atmToPsi(double p) {
        return p * 14.6959488;
    }

    public static double psiToAtm(double p) {
        return p * 0.068;
    }

    public static double mmToFeet(double mm) {
        return mm * 0.0032808;
    }

    public static double feetToMm(double feet) {
        return feet * 304.8;
    }

    public static double inchesToMm(double inches) {
        return inches * 25.4;
    }

    public static double sqrMmToCm(double value){
        return value/100;
    }

    public static double volumeMmToCm(double value){
        return value/1000;
    }
}
