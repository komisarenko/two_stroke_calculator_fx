package main.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import main.HibernateUtil;
import main.Main;
import main.model.Calc;
import main.model.Converter;
import main.model.Engine;
//import org.hibernate.Session;

public class MainController {

    @FXML
    TextField stroke;
    @FXML
    TextField conRodLength;
    @FXML
    TextField deck;
    @FXML
    TextField exhaust;
    @FXML
    TextField inlet;
    @FXML
    TextField transfer;
    @FXML
    TextField pistonHeight;
    @FXML
    TextField exhaustWidth;
    @FXML
    TextField transferWidth;
    @FXML
    TextField inletWidth;
    @FXML
    TextField inletHeight;
    @FXML
    TextField bore;
    @FXML
    TextField id;
    @FXML
    LineChart<Double, Double> portMapChart;


    private Engine engine = new Engine();




//    private XYChart.Series<Double, Double> portMapExhaust = new XYChart.Series<>();
//    private Main main;

    @FXML
    protected void handleExitButtonAction() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void handleCalcButtonAction() {
        readFromFields();
        calculate();
    }

    @FXML
    protected void initialize() {
    }

    private void readFromFields() {
//        engine.setConRodLength(Double.parseDouble(conRodLength.getText()));
//        engine.setStroke(Double.parseDouble(stroke.getText()));
//        engine.setDeck(Double.parseDouble(deck.getText()));
//        engine.setExhaustHeight(Double.parseDouble(exhaust.getText()));
//        engine.setInletFloor(Double.parseDouble(inlet.getText()));
//        engine.setBore(Double.parseDouble(bore.getText()));
//        engine.setTransferHeight(Double.parseDouble(transfer.getText()));
//        engine.setPistonHeight(Double.parseDouble(pistonHeight.getText()));
//        engine.setExhaustWidth(Double.parseDouble(exhaustWidth.getText()));
//        engine.setTransferWidth(Double.parseDouble(transferWidth.getText()));
//        engine.setInletHeight(Double.parseDouble(inletHeight.getText()));
//        engine.setInletWidth(Double.parseDouble(inletWidth.getText()));
//        engine.setTransferCount(2);
//        engine.setExhaustCount(2);
    }

    private void calculate() {
//        engine.setExhaustDuration(Calc.exTrDuration(engine.getStroke(), engine.getConRodLength(),
//                engine.getDeck(), engine.getExhaustHeight()));
//        engine.setInletDuration(Calc.inletDuration(engine.getStroke(), engine.getConRodLength(),
//                engine.getDeck(), engine.getPistonHeight(), engine.getInletFloor()));
//        engine.setTransferDuration(Calc.exTrDuration(engine.getStroke(), engine.getConRodLength(),
//                engine.getDeck(), engine.getTransferHeight()));
//        engine.setExhaustTime(Calc.openTime(engine.getExhaustDuration(), (int) (rpmSlider.getValue() * 1000)));
//        engine.setTransferTime(Calc.openTime(engine.getTransferDuration(), (int) (rpmSlider.getValue() * 1000)));
//        engine.setInletTime(Calc.openTime(engine.getInletDuration(), (int) (rpmSlider.getValue() * 1000)));
//        engine.setVolume(Converter.volumeMmToCm(Calc.volume(engine.getExhaustHeight(), engine.getBore())));
//        engine.setFullVolume(Converter.volumeMmToCm(Calc.volume(engine.getStroke(), engine.getBore())));
//        engine.setExhaustArea(Converter.sqrMmToCm(Calc.squareRect(engine.getExhaustWidth(), engine.getStroke() -
//                engine.getExhaustHeight())) * engine.getExhaustCount());
//        engine.setTransferArea(Converter.sqrMmToCm(Calc.squareRect(engine.getTransferWidth(), engine.getStroke() -
//                engine.getTransferHeight())) * engine.getTransferCount());
//        engine.setInletArea(Converter.sqrMmToCm(Calc.squareRect(engine.getInletWidth(), engine.getInletHeight())));
//        engine.setExhaustTimeArea(Calc.timeArea(engine.getFullVolume(), engine.getExhaustArea(), engine.getExhaustTime()));
//        engine.setTransferTimeArea(Calc.timeArea(engine.getFullVolume(), engine.getTransferArea(), engine.getTransferTime()));
//        engine.setInletTimeArea(Calc.timeArea(engine.getFullVolume(), engine.getInletArea(), engine.getInletTime()));
//        engine.setExhaustAngleArea(Calc.angleArea(engine.getExhaustDuration(), engine.getExhaustArea(), engine.getFullVolume()));
//        engine.setTransferAngleArea(Calc.angleArea(engine.getTransferDuration(), engine.getTransferArea(), engine.getFullVolume()));
//        engine.setInletAngleArea(Calc.angleArea(engine.getInletDuration(), engine.getInletArea(), engine.getFullVolume()));
    }

//
//        portMapExhaust.getData().addAll(new XYChart.Data<>(10d, 10d), new XYChart.Data<>(30d, 10d),
//                new XYChart.Data<>(30d, 0d), new XYChart.Data<>(10d, 0d),
//                new XYChart.Data<>(10d, 10d));
//        portMapChart.getData().add(portMapExhaust);
//


}
