import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringBufferInputStream;
import java.util.Scanner;

public class JSONStringConversion 
{
   public String getJSONString(String addr)
   {
      try
      {
         File json = new File(addr);
         Scanner sc = new Scanner(json);
         StringBuilder datajson = new StringBuilder();
         while(sc.hasNextLine())
            datajson.append(unEscape(sc.nextLine()));
         sc.close();
         return datajson.toString();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         return "";
      }
   }
   public String unEscape(String s)
   {
      StringBuilder res = new StringBuilder();
      for(int i = 0 ; i < s.length() ; i++)
      {
         switch(s.charAt(i))
         {            
            case '\t' :res.append("\\t");
               break;
            case '\b' :res.append("\\b");
               break;
            case '\n' :res.append("\\n");
               break;
            case '\r' :res.append("\\r");
               break;
            case '\f' :res.append("\\f");
               break;
            case ' ' :break;
            default :res.append(s.charAt(i));
         }
      }
      return res.toString();
   }   
}
