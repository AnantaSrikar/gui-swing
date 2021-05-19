import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Day;  
import org.jfree.data.time.TimeSeries;  
import org.jfree.data.time.TimeSeriesCollection;  
import org.jfree.data.xy.XYDataset;  
import java.net.URL;

public class Main
{
    public static void main(String args[])
    {
        new HomePage();
    }
}

class HomePage implements ActionListener//home page frame generator class
{
    JFrame homepage;
    ImageIcon frameIcon;
    JButton readme; 
    JButton market; 
    JButton source;
    JLabel header;
    Image scaledimage;

    HomePage()
    {
        homepage = new JFrame("Pop and Block");

        frameIcon = new ImageIcon("../assets/icon.png");
        
        readme = new JButton("README");//respective buttons with the titles
        market = new JButton("Market");
        source = new JButton("Source code");

        header = new JLabel("Pop and Block");
        scaledimage = (frameIcon.getImage()).getScaledInstance(200 , 200, Image.SCALE_FAST);//scaling frameicon image
        frameIcon = new ImageIcon(scaledimage);
        header.setIcon(frameIcon);
        header.setBounds(590,90,250,250);//boundaries
        header.setFont(header.getFont().deriveFont(30.0f));
        header.setHorizontalTextPosition(JLabel.CENTER);
        header.setVerticalTextPosition(JLabel.BOTTOM);//aligning text to center-bottom of jlabel.
        
        readme.setBounds(325,540,120,40);//orientation divided according to third of frame height,
        market.setBounds(670,540,120,40);//and quarters of frame length
        source.setBounds(1015,540,120,40);

        readme.addActionListener(this);
        market.addActionListener(this);
        source.addActionListener(this);

        homepage.add(readme);//adding buttons
        homepage.add(market);
        homepage.add(source);

        homepage.add(header);

        homepage.setSize(690*2,420*2);
        homepage.setIconImage(frameIcon.getImage());
        homepage.setLayout(null);//no layout manager
        homepage.setVisible(true);
        homepage.setLocationRelativeTo(null);//center the jframe wrt screen
        homepage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //close the page on pressing the close button terminates the program if its the only window
    }

    public void actionPerformed(ActionEvent ae)//overriden actionperformed
    {
        try
        {
            if(ae.getSource() == market)
            {
                System.out.println("market button");
                homepage.setVisible(false);//hiding homepage
                new Market(homepage);//passing homepage object to Readme class to let it return back to the homepage
            }
            else if(ae.getSource() == readme)
            {
                System.out.println("readme button");
                homepage.setVisible(false);
                new Readme(homepage);
            }
            else if(ae.getSource() == source)
            {
                System.out.println("source code button");
                openWebpage("https://github.com/pop-and-block");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void openWebpage(String urlString) 
    {
        try
        {
            Desktop.getDesktop().browse(new URL(urlString).toURI());//opens urlString in computer's default browser
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

class Readme implements ActionListener
{
    JFrame homepage_r;
    JFrame readme;
    ImageIcon frameIcon;
    JButton back;
    JLabel readmetext;

    Readme(JFrame homepage)
    {
        homepage_r = homepage;//original homepage object(JFrame)
        readme = new JFrame("Pop and Block");
        frameIcon = new ImageIcon("../assets/icon.png");        
        back = new JButton("back");//respective buttons with the titles
        readmetext = new JLabel();

        back.setBounds(10,10,120,40);//top left button
        back.addActionListener(this);
        
        readmetext.setText("<html><font size = 5>So hewe's how it wowks.The wowwd has a shit ton of cabwes. They'we aww undew the ocean ow on wand ow undew wand. Those cabwes cawwy peta-fucking-bytes of infowmation evewy singwe second. That's being shawed by you, the giww you have a cwush on but don't have the bawws to say you wike hew and hew cwush. When you say you'we paying fow a connection, you'we paying fow a tiny tap into the wesouwce that 10s of thousands of ewectwicaw, ewectwonics, computew science engineews and evewyone ewse have wowked on theiw entiwe wives. They devewoped awgowithms which you won't be capabwe of even undewstanding unwess you sit down fow houws. They evowved softwawe and hawdwawe and pushed technowogy to the wimits which you won't even know about. They wowked theiw asses off so you couwd type desi pown on pownhub and nut in 12 seconds. That wesouwce is shawed. Just because you pay fow it, doesn't mean it's youws to abuse. Keep the gwobaw twaffic wow.</font></html>");
        //html formatted text
        readmetext.setBounds(0,70,690,790);//left half of screen
        readmetext.setHorizontalAlignment(JLabel.LEFT);
        readmetext.setVerticalAlignment(JLabel.TOP);
        
        readme.add(back);//adding buttons

        readme.add(readmetext);

        readme.setSize(690*2,420*2);
        readme.setIconImage(frameIcon.getImage());
        readme.setLayout(null);//no layout manager
        readme.setVisible(true);
        readme.setLocationRelativeTo(null);//center the jframe wrt screen
        readme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //close the page on pressing the close button terminates the program if its the only window
    }

    public void actionPerformed(ActionEvent ae)
    {
        try
        {
            if(ae.getSource() == back)
            {
                homepage_r.setVisible(true);//passed homepage object set back to visible
                readme.dispose();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

class Market implements ActionListener
{
    JFrame homepage_m;
    JFrame market;
    JTabbedPane tab;
    JPanel btc;XYDataset databtc;JFreeChart chartbtc;ChartPanel chpanelbtc;//panel elements for each coin
    JPanel doge;XYDataset datadoge;JFreeChart chartdoge;ChartPanel chpaneldoge;
    JPanel eth;XYDataset dataeth;JFreeChart charteth;ChartPanel chpaneleth;
    ImageIcon frameIcon;
    JButton back;

    Market(JFrame homepage)
    {
        homepage_m = homepage;//original homepage object(JFrame)
        market = new JFrame("Pop and Block");
        frameIcon = new ImageIcon("../assets/icon.png");        
        back = new JButton("back");//respective buttons with the titles

        tab = new JTabbedPane();
        
        btc = new JPanel();
        btc.setBounds(50,50,1280,740);
        btc.setLayout(null);
        databtc = createDataSet("btc");
        chartbtc = ChartFactory.createTimeSeriesChart("Bitcoin","Timeline","Value",databtc);
        chpanelbtc = new ChartPanel(chartbtc);
        chpanelbtc.setBounds(0,0,1280,740);
        btc.add(chpanelbtc);

        doge = new JPanel();
        doge.setBounds(0,0,1280,740);
        doge.setLayout(null);
        datadoge = createDataSet("doge");
        chartdoge = ChartFactory.createTimeSeriesChart("Dogecoin","Timeline","Value",datadoge);
        chpaneldoge = new ChartPanel(chartdoge);
        chpaneldoge.setBounds(0,0,1280,740);
        doge.add(chpaneldoge);

        eth = new JPanel();
        eth.setBounds(0,0,1280,740);
        eth.setLayout(null);
        dataeth = createDataSet("eth");
        charteth = ChartFactory.createTimeSeriesChart("Ethereum","Timeline","Value",dataeth);
        chpaneleth = new ChartPanel(charteth);
        chpaneleth.setBounds(0,0,1280,740);
        eth.add(chpaneleth);        
        
        tab.add("btc",btc);
        tab.add("doge",doge);
        tab.add("eth",eth);
        tab.setBounds(50,50,1280,740);

        back.setBounds(10,10,120,40);//top left button
        back.addActionListener(this);
                
        market.add(back);//adding buttons
        market.add(tab);//adding tabs

        market.setSize(690*2,420*2);
        market.setIconImage(frameIcon.getImage());
        market.setLayout(null);//no layout manager
        market.setVisible(true);
        market.setLocationRelativeTo(null);//center the jframe wrt screen
        market.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //close the page on pressing the close button terminates the program if its the only window        
    }

    public XYDataset createDataSet(String s)
    {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries(s);

        if(s.equals("btc"))
        {
            series.add(new Day(1, 1, 2017), 50);  
            series.add(new Day(2, 1, 2017), 40);  
            series.add(new Day(3, 1, 2017), 45);  
            series.add(new Day(4, 1, 2017), 30);  
            series.add(new Day(5, 1, 2017), 50);  
            series.add(new Day(6, 1, 2017), 45);  
            series.add(new Day(7, 1, 2017), 60);  
            series.add(new Day(8, 1, 2017), 45);  
            series.add(new Day(9, 1, 2017), 55);  
            series.add(new Day(10, 1, 2017), 48);  
            series.add(new Day(11, 1, 2017), 60);  
            series.add(new Day(12, 1, 2017), 45);  
            series.add(new Day(13, 1, 2017), 65);  
            series.add(new Day(14, 1, 2017), 45);  
            series.add(new Day(15, 1, 2017), 55);
        }
        else if(s.equals("doge"))
        {
            series.add(new Day(1, 1, 2017), 50);  
            series.add(new Day(2, 1, 2017), 40);  
            series.add(new Day(3, 1, 2017), 45);  
            series.add(new Day(4, 1, 2017), 30);  
            series.add(new Day(5, 1, 2017), 50);  
            series.add(new Day(6, 1, 2017), 45);  
            series.add(new Day(7, 1, 2017), 60);  
            series.add(new Day(8, 1, 2017), 45);  
            series.add(new Day(9, 1, 2017), 55);  
            series.add(new Day(10, 1, 2017), 48);  
            series.add(new Day(11, 1, 2017), 60);  
            series.add(new Day(12, 1, 2017), 45);  
            series.add(new Day(13, 1, 2017), 65);  
            series.add(new Day(14, 1, 2017), 45);  
            series.add(new Day(15, 1, 2017), 55);
        }
        else if(s.equals("eth"))
        {
            series.add(new Day(1, 1, 2017), 50);  
            series.add(new Day(2, 1, 2017), 40);  
            series.add(new Day(3, 1, 2017), 45);  
            series.add(new Day(4, 1, 2017), 30);  
            series.add(new Day(5, 1, 2017), 50);  
            series.add(new Day(6, 1, 2017), 45);  
            series.add(new Day(7, 1, 2017), 60);  
            series.add(new Day(8, 1, 2017), 45);  
            series.add(new Day(9, 1, 2017), 55);  
            series.add(new Day(10, 1, 2017), 48);  
            series.add(new Day(11, 1, 2017), 60);  
            series.add(new Day(12, 1, 2017), 45);  
            series.add(new Day(13, 1, 2017), 65);  
            series.add(new Day(14, 1, 2017), 45);  
            series.add(new Day(15, 1, 2017), 55);
        }

        dataset.addSeries(series);

        return dataset;
    }

    public void actionPerformed(ActionEvent ae)
    {
        try
        {
            if(ae.getSource() == back)
            {
                homepage_m.setVisible(true);//passed homepage object set back to visible
                market.dispose();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}