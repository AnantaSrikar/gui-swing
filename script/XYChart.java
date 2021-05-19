import java.awt.Color;  
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot; 
import org.jfree.data.time.Day;  
import org.jfree.data.time.TimeSeries;  
import org.jfree.data.time.TimeSeriesCollection;  
import org.jfree.data.xy.XYDataset;  

public class XYChart
{
    JFrame frame;
    JFreeChart chart;
    JPanel panel;
    ChartPanel chpanel;
    XYDataset data;
    XYPlot plot;

    XYChart()
    {
        frame = new JFrame();
        
        data = createDataSet();

        chart = ChartFactory.createTimeSeriesChart("sus","sus","sus",data);
        plot = (XYPlot)chart.getPlot();  
        plot.setBackgroundPaint(new Color(255,228,196));  
        
        panel = new JPanel();
        panel.setBounds(40,40,350,350);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        chpanel = new ChartPanel(chart);
        chpanel.setBounds(50,50,300,300);
        panel.add(chpanel);

        frame.setSize(1920,1080);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    XYDataset createDataSet()
    {
        TimeSeriesCollection dataset = new TimeSeriesCollection();  
  
        TimeSeries series1 = new TimeSeries("Series1");  
        series1.add(new Day(1, 1, 2017), 50);  
        series1.add(new Day(2, 1, 2017), 40);  
        series1.add(new Day(3, 1, 2017), 45);  
        series1.add(new Day(4, 1, 2017), 30);  
        series1.add(new Day(5, 1, 2017), 50);  
        series1.add(new Day(6, 1, 2017), 45);  
        series1.add(new Day(7, 1, 2017), 60);  
        series1.add(new Day(8, 1, 2017), 45);  
        series1.add(new Day(9, 1, 2017), 55);  
        series1.add(new Day(10, 1, 2017), 48);  
        series1.add(new Day(11, 1, 2017), 60);  
        series1.add(new Day(12, 1, 2017), 45);  
        series1.add(new Day(13, 1, 2017), 65);  
        series1.add(new Day(14, 1, 2017), 45);  
        series1.add(new Day(15, 1, 2017), 55);  
        dataset.addSeries(series1);  
        
        return dataset;
    }
    public static void main(String args[])
    {
        XYChart frame = new XYChart();
    }
}