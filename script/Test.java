import org.json.JSONArray;
import org.json.JSONObject;

public class Test
{
    public static void main(String args[])
    {
        Test obj = new Test();
        obj.run();
    }
    public void run()
    {
        JSONObject data = new JSONObject(APIc.getMarketJSONData());
        JSONObject coin = data.getJSONObject("dogeinr");
        JSONArray time = coin.getJSONArray("time");
        JSONArray value = coin.getJSONArray("value");
        System.out.println(Double.parseDouble(value.get(1).toString()));
    }
}
