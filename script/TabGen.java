import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.LocalDateTime;
import java.util.Locale;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.lang.Math;

public class TabGen
{
    JPanel coin;
    XYDataset data;
    JFreeChart chart;
    ChartPanel chpanel;
    XYPlot plot;

    public JPanel tabGenerator(String coinname , Rectangle tabbedpaneDim , String json, String live_market_data_string)
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

        JSONObject live_market_data = new JSONObject(live_market_data_string);
        JSONObject coin_data = new JSONObject(live_market_data.get(coinname).toString());

        String dl_prediction;

        // WARNING! TO BE REPLACED WITH ACTUAL DEEP LEARNING ALGORITHM LATER
        if(Math.random() > 0.5)
            dl_prediction = "Buy";
        else
            dl_prediction = "Sell";

        /*
            Just do coin_data.get("index"); to get the data, eg, coin_data.get("volume") to get the volume

            List of indices we need to show for every coin:
                last
                high
                low
                buy
                sell
                volume
                at

            Also plox show the output on the GUI of my cool deep learning algorithm prediction which is stored dl_prediction
        */

            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(coin_data.getLong("at"), 0, ZoneOffset.UTC);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE,MMMM d,yyyy h:mm,a", Locale.ENGLISH);
            String formattedDate = dateTime.format(formatter);

            // You can take the date and time of the latest thing from formattedDate.
            // System.out.println(formattedDate);
        
        return coin;
    }
}