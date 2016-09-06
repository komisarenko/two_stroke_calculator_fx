package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * Created by sanya on 12.08.16.
 */
public class ChartController {

    private ObservableList<PieChart.Data> exhaustData = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data>  transferData = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data>  inletData = FXCollections.observableArrayList();
}
