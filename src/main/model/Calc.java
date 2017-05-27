package main.model;

import java.lang.Math;

public class Calc {

    public static double exTrDuration(double stroke, double conRodLength,
                                      double deck, double exTrHeight) {
        double result;
        double T;
        double num;
        double den;
        double R;
        R = stroke / 2;
        T = R + conRodLength + deck - exTrHeight;
        num = Math.pow(T, 2) + Math.pow(R, 2) - Math.pow(conRodLength, 2);
        den = 2 * R * T;
        result = (180 - Math.acos(num / den) * 180 / Math.PI) * 2;
        return result;
    }

    public static double inletDuration(double stroke, double conRodLength,
                                       double deck, double pistonHeight,
                                       double inletFloor) {
        double result;
        double P;
        double R;
        double num;
        double den;
        R = stroke / 2;
        P = R + conRodLength + pistonHeight + deck - inletFloor;
        num = Math.pow(P, 2) + Math.pow(R, 2) - Math.pow(conRodLength, 2);
        den = 2 * P * R;
        result = (Math.acos(num / den) * 180 / Math.PI) * 2;
        return result;
    }

    public static double pistonSpeed(double stroke, int rpm) {

        return 0.166 * Converter.mmToInches(stroke) * rpm;
    }

    public static double pistonAcceleration(double stroke, double conRodLength,
                                            int rpm) {
        return ((Math.pow(rpm, 2) * Converter.mmToInches(stroke) / 2189) * (1 + (1 / (2 *
                (Converter.mmToInches(conRodLength) / Converter.mmToInches(stroke))))));
    }

    public static double openTime(double duration, int rpm) {
        return duration / (rpm * 6);
    }

//    public static double exhaustLength(double stroke, double conRodLength,
//                                       double deck, double duration) {
//        double R;
//        double sqrt;
//        double result;
//        double x;
//        x = Math.cos((180 - (duration / 2) * Math.PI / 180));
//        R = stroke / 2;
//        sqrt = Math.sqrt(Math.pow(duration, 2) * Math.pow(R, 2) - Math.pow(R, 2) +
//                Math.pow(conRodLength, 2));
//        result = R + conRodLength + deck + (x * R) + sqrt;
//        return result;
//    }

    public static double volume(double height, double diameter) {
        return squareCircle(diameter) * height;
    }

    public static double compressionRatio(double volume, double chamber) {
        return (volume + chamber) / chamber;
    }

    public static double squareCircle(double d) {
        return Math.pow(d / 2, 2) * Math.PI;
    }

    public static double squareRect(double w, double h) {
        return w * h;
    }

    public static double circleLength(double diameter){return diameter * Math.PI;}

    public static double bmep(double p) {
        return p * 1.1;
    }


    public static double bhp(double pressure, double stroke, double bore,
                             int rpm) {
        double L;
        double A;
        L = Converter.mmToFeet(stroke);
        A = squareCircle(Converter.mmToInches(bore));
        return (pressure * L * A * rpm) / 33000;
    }

    public static double tunedLength(double openPeriod, int rpm) {
        return Converter.inchesToMm((openPeriod * 1700) / rpm);
    }

    public static double timeArea(double volume, double area, double openTime) {
        return (area / volume) * openTime;
    }

    public static double angleArea(double angle, double area, double volume){
        return (area/volume) * angle;
    }

    public static double throttleBore(double volume, int rpm) {
        return 0.85 * Math.sqrt((volume / 1000) * rpm);
    }

    public static double cot(double deg) {
        return 1.0 / Math.tan(Math.toRadians(deg));
    }

    public static double L1(double Lt, double L2) {
        return Lt - (L2 / 2);
    }

    public static double L2(double D2, double A2) {
        return (D2 / 2) * cot(A2);
    }

    public static double L3(double D1, double k) {
        return D1 * k;
    }

    public static double L4(double D2, double D1, double A1) {
        return ((D2 - D1) / 2) * cot(A1);
    }

    public static double L5(double L1, double L3, double L4) {
        return L1 - (L3 + L4);
    }

    public static double L6(double D2, double D3, double A2) {
        return ((D2 - D3) / 2) * cot(A2);
    }

    public static double L7(double D3) {
        return D3 * 12;
    }

    public static double D1(double fullVolume, int rpm) {
        return Math.sqrt(0.00085 * fullVolume * rpm / Math.PI) * 2;
    }

    public static double D2(double D1) {
        return Math.sqrt(6.25 * Math.pow(D1, 2));
    }

    public static double D3(double D1, double k) {
        return D1 * k;
    }
}
