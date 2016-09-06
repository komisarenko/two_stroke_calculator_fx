package main.doughnutChart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;

/**
 * Created by sanya on 12.08.16.
 */
public class DoughnutChart extends PieChart {

    private final Circle innerCircle;
    private Text text = new Text("");

    public DoughnutChart() {
        this(FXCollections.observableArrayList());
    }

    public DoughnutChart(ObservableList<Data> pieData) {
        super(pieData);
        innerCircle = new Circle();
        innerCircle.getStyleClass().add("circle");
        text.getStyleClass().add("chartLabel");
    }


    @Override
    protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
        super.layoutChartChildren(top, left, contentWidth, contentHeight);
        addInnerCircleIfNorPresent();
        updateInnerCircleLayout();
    }

    private void addInnerCircleIfNorPresent() {
        if (getData().size() > 0) {
            Node pie = getData().get(0).getNode();
            if (pie.getParent() instanceof Pane) {
                Pane parent = (Pane) pie.getParent();
                if (parent.getChildren().contains(innerCircle)) {
                    parent.getChildren().removeAll(innerCircle, text);
                }
                if (!parent.getChildren().contains(innerCircle)) {
                    parent.getChildren().addAll(innerCircle, text);
                }
            }
        }
    }

    private void updateInnerCircleLayout() {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        double textOffsetX;
        double textOffsetY;

        for (PieChart.Data data : getData()) {
            Node node = data.getNode();
            Bounds bounds = node.getBoundsInParent();
            if (bounds.getMinX() < minX) {
                minX = bounds.getMinX();
            }
            if (bounds.getMinY() < minY) {
                minY = bounds.getMinY();
            }
            if (bounds.getMaxX() > maxX) {
                maxX = bounds.getMaxX();
            }
            if (bounds.getMaxY() > maxY) {
                maxY = bounds.getMaxY();
            }
        }
        innerCircle.setCenterX(minX + (maxX - minX) / 2);
        innerCircle.setCenterY(minY + (maxY - minY) / 2);
        innerCircle.setRadius((maxX - minX) / 3);
        if (getData().size() > 0) {
            text.setText(String.format("%.1f\u00b0", getData().get(0).getPieValue()));
        }
        textOffsetX = text.getBoundsInParent().getWidth() / 2;
        textOffsetY = text.getBoundsInParent().getHeight() / 2;
        text.relocate((minX + (maxX - minX) / 2) - textOffsetX, (minY + (maxY - minY) / 2) - textOffsetY);
        text.setBoundsType(TextBoundsType.VISUAL);
    }
}
