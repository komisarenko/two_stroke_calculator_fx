package main.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.model.Calc;
import main.model.Converter;
import main.model.Engine;
//import org.hibernate.Session;

public class MainController {

    final String EXHAUST = "exhaust";
    final String TRANSFER = "transfer";
    final String INLET = "inlet";

    @FXML TextField stroke;
    @FXML TextField deck;
    @FXML TextField pistonHeight;
    @FXML TextField bore;
    @FXML TextField conRodLength;
    @FXML Spinner exhaustCount;
    @FXML Spinner inletCount;
    @FXML Spinner transferCount;
    @FXML VBox root;
    @FXML TabPane tabPane;
    @FXML Label header;
    @FXML Spinner exhaustWidth;
    @FXML Spinner inletWidth;
    @FXML Spinner transferWidth;
    @FXML Spinner exhaustHeight;
    @FXML Spinner inletHeight;
    @FXML Spinner transferHeight;
    @FXML Button calcButton;
    @FXML Slider rpmSlider;

    @FXML private PhaseDurationController phaseDurationController;
    @FXML private TimeAngleAreaController timeAngleAreaController;
    @FXML private ExhaustPipeController exhaustPipeController;

    private Engine engine = new Engine();

    @FXML
    protected void handleExit() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void handleSaveButtonAction() {
        readFromFieldsMainParameters();
        calcButton.setDisable(false);
    }

    @FXML
    protected void handleCalcButtonAction(){
        readFromFields();
        calculate();
        render();
    }

    @FXML
    protected void handleMinimize(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    protected void initialize() {
        header.setText(tabPane.getSelectionModel().getSelectedItem().getText().toUpperCase());
        tabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        header.setText(tabPane.getSelectionModel().getSelectedItem().getText().toUpperCase());
                    }
                }
        );
        System.out.println("'main.fxml' was loaded. Initialize successful");
    }

    private void readFromFieldsMainParameters() {
        engine.setBore(Double.parseDouble(bore.getText()));
        engine.setPistonHeight(Double.parseDouble(pistonHeight.getText()));
        engine.setDeck(Double.parseDouble(deck.getText()));
        engine.setStroke(Double.parseDouble(stroke.getText()));
        engine.setConRodLength(Double.parseDouble(conRodLength.getText()));
        engine.setExhaustCount(Integer.parseInt(exhaustCount.getValue().toString()));
        engine.setTransferCount(Integer.parseInt(transferCount.getValue().toString()));
        engine.setInletCount(Integer.parseInt(inletCount.getValue().toString()));
    }

    private void readFromFields(){
        engine.setExhaustHeight(Double.parseDouble(exhaustHeight.getValue().toString()));
        engine.setInletFloor(Double.parseDouble(inletHeight.getValue().toString()));
        engine.setTransferHeight(Double.parseDouble(transferHeight.getValue().toString()));
        engine.setExhaustWidth(Double.parseDouble(exhaustWidth.getValue().toString()));
        engine.setInletWidth(Double.parseDouble(inletWidth.getValue().toString()));
        engine.setTransferWidth(Double.parseDouble(transferWidth.getValue().toString()));
        engine.setRpm((int) (rpmSlider.getValue() * 1000));
        System.out.println(engine.getRpm());
    }

    private void calculate() {
        engine.setExhaustDuration(Calc.exTrDuration(engine.getStroke(), engine.getConRodLength(),
                engine.getDeck(), engine.getExhaustHeight()));
        engine.setInletDuration(Calc.inletDuration(engine.getStroke(), engine.getConRodLength(),
                engine.getDeck(), engine.getPistonHeight(), engine.getInletFloor()));
        engine.setTransferDuration(Calc.exTrDuration(engine.getStroke(), engine.getConRodLength(),
                engine.getDeck(), engine.getTransferHeight()));
        engine.setExhaustTime(Calc.openTime(engine.getExhaustDuration(), engine.getRpm()));
        engine.setTransferTime(Calc.openTime(engine.getTransferDuration(), engine.getRpm()));
        engine.setInletTime(Calc.openTime(engine.getInletDuration(), engine.getRpm()));
        engine.setVolume(Converter.volumeMmToCm(Calc.volume(engine.getExhaustHeight(), engine.getBore())));
        engine.setFullVolume(Converter.volumeMmToCm(Calc.volume(engine.getStroke(), engine.getBore())));
        engine.setExhaustArea(Converter.sqrMmToCm(Calc.squareRect(engine.getExhaustWidth(), engine.getStroke() -
                engine.getExhaustHeight())) * engine.getExhaustCount());
        engine.setTransferArea(Converter.sqrMmToCm(Calc.squareRect(engine.getTransferWidth(), engine.getStroke() -
                engine.getTransferHeight())) * engine.getTransferCount());
        engine.setInletArea(Converter.sqrMmToCm(Calc.squareRect(engine.getInletWidth(), engine.getInletHeight())));
        engine.setExhaustTimeArea(Calc.timeArea(engine.getFullVolume(), engine.getExhaustArea(), engine.getExhaustTime()));
        engine.setTransferTimeArea(Calc.timeArea(engine.getFullVolume(), engine.getTransferArea(), engine.getTransferTime()));
        engine.setInletTimeArea(Calc.timeArea(engine.getFullVolume(), engine.getInletArea(), engine.getInletTime()));
        engine.setExhaustAngleArea(Calc.angleArea(engine.getExhaustDuration(), engine.getExhaustArea(), engine.getFullVolume()));
        engine.setTransferAngleArea(Calc.angleArea(engine.getTransferDuration(), engine.getTransferArea(), engine.getFullVolume()));
        engine.setInletAngleArea(Calc.angleArea(engine.getInletDuration(), engine.getInletArea(), engine.getFullVolume()));
    }

    private void render(){
        phaseDurationController.pieChart(EXHAUST, engine.getExhaustDuration());
        phaseDurationController.pieChart(TRANSFER, engine.getTransferDuration());
        phaseDurationController.pieChart(INLET, engine.getInletDuration());
        timeAngleAreaController.addPointToChart(EXHAUST, engine.getRpm(), engine.getExhaustAngleArea());
        timeAngleAreaController.addPointToChart(TRANSFER, engine.getRpm(), engine.getTransferAngleArea());
        timeAngleAreaController.addPointToChart(INLET, engine.getRpm(), engine.getInletAngleArea());
        System.out.println(engine.getTransferAngleArea());
    }
}
