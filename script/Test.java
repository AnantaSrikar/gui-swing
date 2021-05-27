import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.Math;

public class Test
{
    public static void main(String args[])
    {
        Test obj = new Test();
        obj.run();
    }
    public void run()
    {
        coinname = "btcinr";
        String live_market_data_string = APIw.getJSONEndPoint("tickers", "null");
        JSONObject live_market_data = new JSONObject(live_market_data_string);
        JSONObject coin_data = new JSONObject(live_market_data.get(coinname).toString());
        
        if(Math.random() > 0.5)
            dl_prediction = "Buy";
        else
            dl_prediction = "Sell";
    }
}
