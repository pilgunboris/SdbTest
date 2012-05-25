/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester.gui;

import java.util.ArrayList;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author boris
 */
public class DiagramViewer {

    public static final byte DIAGRAM_TYPE_TIME = 0;
    public static final byte DIAGRAM_TYPE_CPU = 1;
    public static final byte DIAGRAM_TYPE_RAM = 2;
    // class fields
    private byte diagramType = -1;
    private JFreeChart chart = null;

    public DiagramViewer(ArrayList data1, ArrayList data2, String name1, String name2, byte diagramType) {
        this.diagramType = diagramType;
        XYSeriesCollection dataset1 = createDataset(data1, name1);
        XYSeriesCollection dataset2 = createDataset(data2, name2);
        this.chart = createChart(getAxisLabels()[2], getAxisLabels()[0], getAxisLabels()[1], dataset1, dataset2);
    }

    /*
     * result[0] -> Y result[1] -> X result[2] -> title
     */
    private String[] getAxisLabels() {
        String[] labels = new String[3];
        switch (this.diagramType) {
            case DIAGRAM_TYPE_TIME: {
                labels[0] = "millisec";
                labels[2] = "Response time";
                break;
            }
            case DIAGRAM_TYPE_CPU: {
                labels[0] = "%";
                labels[2] = "CPU load";
                break;
            }
            case DIAGRAM_TYPE_RAM: {
                labels[0] = "%";
                labels[2] = "RAM load";
                break;
            }
        }
        labels[1] = "iterat.";
        return labels;
    }

    private XYSeriesCollection createDataset(ArrayList<Object> data, String name) {
        XYSeries ser = new XYSeries(name);
        for (int i = 0; i < data.size(); ++i) {
            double level = Double.parseDouble(data.get(i).toString());
            ser.add(i, level);
        }
        XYSeriesCollection dataset1 = new XYSeriesCollection(ser);
        return dataset1;
    }

    private JFreeChart createChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset1, XYDataset dataset2) {
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        XYItemRenderer renderer1 = new XYLineAndShapeRenderer(true, false);
        XYPlot plot = new XYPlot(dataset2, yAxis, xAxis, renderer);
        plot.setDataset(1, dataset1);
        plot.setRenderer(1, renderer1);
        plot.setOrientation(PlotOrientation.VERTICAL);
        JFreeChart chart1 = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);

        return chart1;
    }

    public byte getDiagramType() {
        return diagramType;
    }

    public void setDiagramType(byte diagramType) {
        this.diagramType = diagramType;
    }

    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }
}
