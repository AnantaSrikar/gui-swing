import javax.swing.JPanel;
import javax.swing.JLabel;
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
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.lang.Math;

public class TabGen
{
    JPanel coin;
    XYDataset data;
    JFreeChart chart;
    ChartPanel chpanel;
    XYPlot plot;
    JLabel last;
    JLabel high;
    JLabel low;
    JLabel buy;
    JLabel sell;
    JLabel volume;
    JLabel at;
    JLabel prediction;

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
        chpanel.setBounds(0,0,tabbedpaneDim.width,tabbedpaneDim.height-200);

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

        
        last = new JLabel("Last: "+coin_data.get("last"));
        high = new JLabel("High: "+coin_data.get("high"));
        low = new JLabel("Low: "+coin_data.get("low"));
        buy = new JLabel("Buy: "+coin_data.get("buy"));
        sell = new JLabel("Sell: "+coin_data.get("sell"));
        volume = new JLabel("Volume: "+coin_data.get("volume"));
        at = new JLabel("At time(UTC): "+formattedDate);
        prediction = new JLabel("Suggested course of action: "+dl_prediction);

        at.setBounds(30 , tabbedpaneDim.height - 190 , tabbedpaneDim.width/2 , 40);
        at.setHorizontalTextPosition(JLabel.CENTER);
        at.setVerticalTextPosition(JLabel.BOTTOM);

        last.setBounds(30 , tabbedpaneDim.height - 140 , 150 , 40);
        volume.setBounds(30 , tabbedpaneDim.height - 90, 150 , 40);
        high.setBounds(tabbedpaneDim.width/4  , tabbedpaneDim.height - 140, 150, 40);
        low.setBounds(tabbedpaneDim.width/4  , tabbedpaneDim.height - 90 , 150, 40);
        buy.setBounds(tabbedpaneDim.width/2 - 70 , tabbedpaneDim.height - 140 , 150 , 40);
        sell.setBounds(tabbedpaneDim.width/2 - 70, tabbedpaneDim.height - 90 , 150, 40);

        prediction.setBounds(3*tabbedpaneDim.width/4 - 125 , tabbedpaneDim.height- 120 , 250 , 40);
        prediction.setHorizontalTextPosition(JLabel.CENTER);
        coin.add(at);
        coin.add(last);
        coin.add(volume);
        coin.add(high);
        coin.add(low);
        coin.add(buy);
        coin.add(sell);
        coin.add(chpanel);
        coin.add(prediction);

        return coin;
    }
}