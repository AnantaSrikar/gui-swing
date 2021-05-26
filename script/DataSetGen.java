import org.jfree.data.time.Second;  
import org.jfree.data.time.TimeSeries;  
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataSetGen
{
    public static XYDataset createDataSet(String s , String json)//data set generator
    {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries(s);
        JSONObject data = new JSONObject(json);
        JSONObject coin = data.getJSONObject(s);
        JSONArray time = coin.getJSONArray("time");
        JSONArray value = coin.getJSONArray("value");

        for(int i = 0 ; i < time.length() ; i++)
        {
            TimeFormat timef = TimeFormat.timeFormatter(time.get(i).toString());//returns time object for convenience
            series.addOrUpdate(new Second(timef.second,timef.minute,timef.hour,timef.day,timef.month,timef.year), Double.parseDouble(value.get(i).toString()));
            //addOrUpdate function used due to the case of duplicate time entries
        }

        dataset.addSeries(series);

        return dataset;
    }
}