package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

/**
 * Created by sanya on 12.08.16.
 */
public class PhaseDurationController {

    @FXML
    PieChart exhaustDurationChart;
    @FXML
    PieChart transferDurationChart;
    @FXML
    PieChart inletDurationChart;

    private ObservableList<PieChart.Data> exhaustData = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> transferData = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> inletData = FXCollections.observableArrayList();

    public void pieChart(String chartType, double duration) {
        PieChart chart = inletDurationChart;
        ObservableList<PieChart.Data> data = inletData;
        switch (chartType){
            case "exhaust": chart = exhaustDurationChart;
                    data = exhaustData;
                    break;
            case "transfer": chart = transferDurationChart;
                    data = transferData;
            break;
        }
        if (!data.isEmpty()) {
            chart.setStartAngle((-180 + duration) / 2);
            data.get(0).setPieValue(duration);
            data.get(1).setPieValue(360 - duration);
        } else {
            data.addAll(new PieChart.Data("BDС", duration),
                    new PieChart.Data("TDС", 360 - duration));
            chart.setStartAngle((-180 + duration) / 2);
            chart.setData(data);
        }
    }

    @FXML
    protected void initialize() {
    }
}
