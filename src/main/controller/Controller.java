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
import org.hibernate.Session;

public class Controller {

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
    PieChart exhaustDuration;
    @FXML
    PieChart transferDuration;
    @FXML
    PieChart inletDuration;
    @FXML
    LineChart<Double, Double> timeAngleChart;
    @FXML
    Slider rpmSlider;
    @FXML
    NumberAxis xAxis;
    @FXML
    NumberAxis yAxis;
    @FXML
    Label rpmLabel;

    private Engine engine = new Engine();
    private ObservableList<PieChart.Data> exhaustData = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> transferData = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> inletData = FXCollections.observableArrayList();
    private final XYChart.Series<Double, Double> exhaustHigh = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> transferHigh = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> inletHigh = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> exhaustInlet = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> transferLow = new XYChart.Series<>();
    private XYChart.Series<Double, Double> pointExhaust = new XYChart.Series<>();
    private XYChart.Series<Double, Double> pointInlet = new XYChart.Series<>();
    private XYChart.Series<Double, Double> pointTransfer = new XYChart.Series<>();
    private main.model.entity.Engine entityEngine = new main.model.entity.Engine();
    private Session session;
//    private Main main;

    @FXML
    protected void handleExitButtonAction() {
        HibernateUtil.shutdown();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void getFromDataBase() {
        session = Main.getSession();
        session.beginTransaction();
        entityEngine = session.get(main.model.entity.Engine.class, Integer.parseInt(id.getText()));
        System.out.println(entityEngine.getBore());
        session.getTransaction().commit();
        pasteToFields();
    }

    @FXML
    protected void handleCalcButtonAction() {
        readFromFields();
        calculate();
        pieChart();
        addPointToChart();

    }

    @FXML
    protected void initialize() {
        listeners();
        lineChartInit();
        pieChartInit();
// /
        exhaustHigh.setName("Exhaust Hi");
        transferHigh.setName("Transfer Hi");
        transferLow.setName("Transfer Low");
        inletHigh.setName("Inlet Hi");
        exhaustInlet.setName("Exhaust-Inlet");
        pointExhaust.setName("Exhaust Point");
        pointInlet.setName("Inlet Point");
        pointTransfer.setName("Transfer Point");
//
    }

    private void pasteToFields(){
        conRodLength.setText(Double.toString(entityEngine.getConRodLength()));
        stroke.setText(Double.toString(entityEngine.getStroke()));
        deck.setText(Double.toString(entityEngine.getDeck()));
        exhaust.setText(Double.toString(entityEngine.getExhaustHeight()));
        inlet.setText(Double.toString(entityEngine.getInletFloor()));
        bore.setText(Double.toString(entityEngine.getBore()));
        transfer.setText(Double.toString(entityEngine.getTransferHeight()));
        pistonHeight.setText(Double.toString(entityEngine.getPistonHeight()));
        exhaustWidth.setText(Double.toString(entityEngine.getExhaustWidth()));
        transferWidth.setText(Double.toString(entityEngine.getTransferWidth()));
        inletHeight.setText(Double.toString(entityEngine.getInletHeight()));
        inletWidth.setText(Double.toString(entityEngine.getInletWidth()));
    }

    private void readFromFields() {
        engine.setConRodLength(Double.parseDouble(conRodLength.getText()));
        engine.setStroke(Double.parseDouble(stroke.getText()));
        engine.setDeck(Double.parseDouble(deck.getText()));
        engine.setExhaustHeight(Double.parseDouble(exhaust.getText()));
        engine.setInletFloor(Double.parseDouble(inlet.getText()));
        engine.setBore(Double.parseDouble(bore.getText()));
        engine.setTransferHeight(Double.parseDouble(transfer.getText()));
        engine.setPistonHeight(Double.parseDouble(pistonHeight.getText()));
        engine.setExhaustWidth(Double.parseDouble(exhaustWidth.getText()));
        engine.setTransferWidth(Double.parseDouble(transferWidth.getText()));
        engine.setInletHeight(Double.parseDouble(inletHeight.getText()));
        engine.setInletWidth(Double.parseDouble(inletWidth.getText()));
        //
        engine.setTransferCount(2);
        engine.setExhaustCount(2);
        //
    }

    private void calculate() {
        engine.setExhaustDuration(Calc.exTrDuration(engine.getStroke(), engine.getConRodLength(),
                engine.getDeck(), engine.getExhaustHeight()));
        engine.setInletDuration(Calc.inletDuration(engine.getStroke(), engine.getConRodLength(),
                engine.getDeck(), engine.getPistonHeight(), engine.getInletFloor()));
        engine.setTransferDuration(Calc.exTrDuration(engine.getStroke(), engine.getConRodLength(),
                engine.getDeck(), engine.getTransferHeight()));
        engine.setExhaustTime(Calc.openTime(engine.getExhaustDuration(), (int) (rpmSlider.getValue() * 1000)));
        engine.setTransferTime(Calc.openTime(engine.getTransferDuration(), (int) (rpmSlider.getValue() * 1000)));
        engine.setInletTime(Calc.openTime(engine.getInletDuration(), (int) (rpmSlider.getValue() * 1000)));
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

    private void pieChart() {
        if (!exhaustData.isEmpty()) {
            exhaustDuration.setStartAngle((-180 + engine.getExhaustDuration()) / 2);
            exhaustData.get(0).setPieValue(engine.getExhaustDuration());
            exhaustData.get(1).setPieValue(360 - engine.getExhaustDuration());
        } else {
            exhaustData.addAll(new PieChart.Data("BDС", engine.getExhaustDuration()),
                    new PieChart.Data("TDС", 360 - engine.getExhaustDuration()));
            exhaustDuration.setStartAngle((-180 + engine.getExhaustDuration()) / 2);
            exhaustDuration.setData(exhaustData);
        }

        if (!transferData.isEmpty()) {
            transferDuration.setStartAngle((-180 + engine.getTransferDuration()) / 2);
            transferData.get(0).setPieValue(engine.getTransferDuration());
            transferData.get(1).setPieValue(360 - engine.getTransferDuration());
        } else {
            transferData.addAll(new PieChart.Data("BDС", engine.getTransferDuration()),
                    new PieChart.Data("TDС", 360 - engine.getTransferDuration()));
            transferDuration.setStartAngle((-180 + engine.getTransferDuration()) / 2);
            transferDuration.setData(transferData);
        }

        if (!inletData.isEmpty()) {
            inletDuration.setStartAngle((-180 + engine.getInletDuration()) / 2);
            inletData.get(0).setPieValue(engine.getInletDuration());
            inletData.get(1).setPieValue(360 - engine.getInletDuration());
        } else {
            inletData.addAll(new PieChart.Data("BDС", engine.getInletDuration()),
                    new PieChart.Data("TDС", 360 - engine.getInletDuration()));
            inletDuration.setStartAngle((-180 + engine.getInletDuration()) / 2);
            inletDuration.setData(inletData);
        }
    }

    private void pieChartInit() {
        exhaustData.add(new PieChart.Data("", 0));
        exhaustData.add(new PieChart.Data("", 1));
        exhaustDuration.setData(exhaustData);
        inletData.add(new PieChart.Data("", 0));
        inletData.add(new PieChart.Data("", 1));
        inletDuration.setData(inletData);
        transferData.add(new PieChart.Data("", 0));
        transferData.add(new PieChart.Data("", 1));
        transferDuration.setData(transferData);
    }

    private void lineChartInit() {
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
        xAxis.setLowerBound(3);
        xAxis.setTickUnit(1);
        xAxis.setUpperBound(14);
        yAxis.setLowerBound(1.7);
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
    }

    private void addPointToChart() {
        if (pointExhaust.getData().isEmpty()) {
            pointExhaust.getData().add(new XYChart.Data<>(rpmSlider.getValue(), engine.getExhaustAngleArea()));
            pointTransfer.getData().add(new XYChart.Data<>(rpmSlider.getValue(), engine.getTransferAngleArea()));
            pointInlet.getData().add(new XYChart.Data<>(rpmSlider.getValue(), engine.getInletAngleArea()));
            timeAngleChart.getData().add(pointExhaust);
            timeAngleChart.getData().add(pointTransfer);
            timeAngleChart.getData().add(pointInlet);
        } else {
            pointExhaust.getData().get(0).setXValue(rpmSlider.getValue());
            pointExhaust.getData().get(0).setYValue(engine.getExhaustAngleArea());
            pointTransfer.getData().get(0).setXValue(rpmSlider.getValue());
            pointTransfer.getData().get(0).setYValue(engine.getTransferAngleArea());
            pointInlet.getData().get(0).setXValue(rpmSlider.getValue());
            pointInlet.getData().get(0).setYValue(engine.getInletAngleArea());
        }
    }

    private void listeners() {
        rpmSlider.valueProperty().addListener(observable -> {
            rpmLabel.setText(Integer.toString((int) (rpmSlider.getValue() * 1000)) + " RPM");
        });
    }
}
