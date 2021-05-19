import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringBufferInputStream;
import java.util.Scanner;

public class HTMLStringConversion
{
    public String getHTMLString(int fontsize, String colorhex,String addr)
    {
        try
        {
            File text = new File(addr);
            Scanner sc = new Scanner(text);
            String htmltext = "";
            while(sc.hasNextLine())
                htmltext = htmltext + htmlFormat(sc.nextLine()) + "<br>";
            htmltext = "<html><p style=\"font-size:"+fontsize+";color:"+colorhex+";\">"+htmltext.toString()+"</p></html>";
            sc.close();
            return htmltext.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }
    public String htmlFormat(String s)
    {
        String res = "";
        for(int i = 0 ; i < s.length() ; i++)
        {
           switch(s.charAt(i))
           {            
                case '\t' :res = res + " ";
                    break;
                default :res = res + s.charAt(i);
           }
        }
        return res;
    }
}