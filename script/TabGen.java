import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;

public class TabGen
{
    JPanel coin;
    XYDataset data;
    JFreeChart chart;
    ChartPanel chpanel;
    XYPlot plot;

    public JPanel tabGenerator(String coinname , Rectangle tabbedpaneDim , String json)
    {
        coin = new JPanel();//panel to hold the charts
        coin.setBounds(0,0,tabbedpaneDim.width,tabbedpaneDim.height);
        coin.setLayout(null);
        data = DataSetGen.createDataSet(coinname , json);//data set generation from json data
        chart = ChartFactory.createTimeSeriesChart(coinname.substring(0,coinname.length()-3).toUpperCase(),"Timeline","Value",data);
        chpanel = new ChartPanel(chart);
        plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(Color.decode("#F2F5A3"));
        chpanel.setBounds(0,0,tabbedpaneDim.width,tabbedpaneDim.height);
        coin.add(chpanel);
        
        return coin;
    }
}