package main.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * Created by 26san on 12.05.2017.
 */
public class TimeAngleAreaController {

    @FXML LineChart<Double, Double> timeAngleChart;
    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;

    private final XYChart.Series<Double, Double> exhaustHigh = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> transferHigh = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> inletHigh = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> exhaustInlet = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> transferLow = new XYChart.Series<>();
    private XYChart.Series<Double, Double> pointExhaust = new XYChart.Series<>();
    private XYChart.Series<Double, Double> pointInlet = new XYChart.Series<>();
    private XYChart.Series<Double, Double> pointTransfer = new XYChart.Series<>();

    public void addPointToChart(String pointType, double rpm, double areaAngle) {
        XYChart.Series<Double, Double> point = pointInlet;
        switch (pointType){
            case "exhaust": point = pointExhaust;
                break;
            case "transfer": point = pointTransfer;
                break;
        }
        rpm /= 1000;
        if (point.getData().isEmpty()) {
            point.getData().add(new XYChart.Data<>(rpm, areaAngle));
            timeAngleChart.getData().add(point);
        } else {
            point.getData().get(0).setXValue(rpm);
            point.getData().get(0).setYValue(areaAngle);
        }
    }

    @FXML
    protected void initialize(){
        exhaustHigh.setName("Exhaust Hi");
        transferHigh.setName("Transfer Hi");
        transferLow.setName("Transfer Low");
        inletHigh.setName("Inlet Hi");
        exhaustInlet.setName("Exhaust-Inlet");
        pointExhaust.setName("Exhaust Point");
        pointInlet.setName("Inlet Point");
        pointTransfer.setName("Transfer Point");
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
        xAxis.setLowerBound(3);
        xAxis.setTickUnit(1);
        xAxis.setUpperBound(14);
        yAxis.setLowerBound(1.7d);
        yAxis.setTickUnit(1);
        yAxis.setUpperBound(13);
        timeAngleChart.getXAxis().setLabel("RPMx1000");
        timeAngleChart.getYAxis().setLabel("deg-cm^2/cm^3");
        exhaustHigh.getData().addAll(new XYChart.Data<>(4.0, 3.7647), new XYChart.Data<>(13.0, 11.7059));
        transferHigh.getData().addAll(new XYChart.Data<>(4.0, 2.5), new XYChart.Data<>(13.0, 7.8235));
        transferLow.getData().addAll(new XYChart.Data<>(4.0, 1.88), new XYChart.Data<>(13.0, 6.177));
        inletHigh.getData().addAll(new XYChart.Data<>(4.0, 4.0), new XYChart.Data<>(13.0, 12.7059));
        exhaustInlet.getData().addAll(new XYChart.Data<>(4.0, 3.5), new XYChart.Data<>(13.0, 10.8824));
        timeAngleChart.getData().addAll(exhaustHigh, transferHigh, transferLow, inletHigh, exhaustInlet);
        System.out.println("'time_angle_area.fxml' was loaded. Initialize successful");
    }
}
