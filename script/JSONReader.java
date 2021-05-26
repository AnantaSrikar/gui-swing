import org.json.JSONObject;
import org.json.JSONArray;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class JSONReader
{
    public static JSONArray getJSONarray(String addr)
    {
        try
        {
            InputStream stream = Files.newInputStream(Paths.get(addr));
            String json = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject resjson = new JSONObject(json);
            JSONArray markets = resjson.getJSONArray("markets");
            return markets;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}