import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Test extends JSONStringConversion
{
    public static void main(String args[])
    {
        Test obj = new Test();
        obj.run();
    }
    public void run()
    {
        String s = getJSONString("../data/data.json");
        JSONObject data = (JSONObject)JSONValue.parse(s);
        JSONArray coin = (JSONArray)data.get("btc");
        JSONArray coinx = (JSONArray)coin.get(0);
        JSONArray coiny = (JSONArray)coin.get(1);
        System.out.println(coinx.get(0) + "\n" + coiny.get(0));
    }
}
