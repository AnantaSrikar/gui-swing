import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;  
import org.jfree.data.time.TimeSeries;  
import org.jfree.data.time.TimeSeriesCollection;  
import org.jfree.data.xy.XYDataset; 
import org.json.JSONArray;
import org.json.JSONObject;
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

        header = new JLabel("<html><h1 style=\"font-family:verdana;color:#F0F3F5\">Pop-And-Block</h1></html>");
        scaledimage = (frameIcon.getImage()).getScaledInstance(200,200 , Image.SCALE_FAST);//scaling frameicon image
        frameIcon = new ImageIcon(scaledimage);


        homepage.setSize(800,600);
        homepage.setIconImage(frameIcon.getImage());
        homepage.getContentPane().setBackground(Color.decode("#122932"));
        homepage.setLayout(null);//no layout manager
        homepage.setLocationRelativeTo(null);//center the jframe wrt screen
        homepage.setVisible(true);
        homepage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //close the page on pressing the close button terminates the program if its the only window

        Rectangle frameDim = homepage.getBounds();

        header.setIcon(frameIcon);
        header.setBounds(frameDim.width/2-100,frameDim.height/4-100,200,250);//boundaries
        header.setHorizontalTextPosition(JLabel.CENTER);
        header.setVerticalTextPosition(JLabel.BOTTOM);//aligning text to center-bottom of jlabel.
        
        readme.setBounds(frameDim.width/4 - 70,3*frameDim.height/4 - 20,140,40);//orientation divided according to third of frame height,
        market.setBounds(frameDim.width/2 - 70,3*frameDim.height/4 - 20,140,40);//and quarters of frame length
        source.setBounds(3*frameDim.width/4-70,3*frameDim.height/4 - 20,140,40);

        readme.setBackground(Color.decode("#F85E00"));
        market.setBackground(Color.decode("#F85E00"));
        source.setBackground(Color.decode("#F85E00"));

        readme.addActionListener(this);
        market.addActionListener(this);
        source.addActionListener(this);

        homepage.add(readme);//adding buttons
        homepage.add(market);
        homepage.add(source);

        homepage.add(header);
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

class Readme extends HTMLStringConversion implements ActionListener
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

        readme.setSize(800,600);
        readme.setIconImage(frameIcon.getImage());
        readme.getContentPane().setBackground(Color.decode("#122932"));//color set
        readme.setLayout(null);//no layout manager
        readme.setLocationRelativeTo(null);//center the jframe wrt screen
        readme.setVisible(true);
        readme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //close the page on pressing the close button terminates the program if its the only window
        Rectangle readmeDim = readme.getBounds();

        back.setBounds(10,10,120,40);//top left button
        back.setBackground(Color.decode("#F85E00"));
        back.addActionListener(this);
        
        Border readmebox = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(/*Color.decode("#7C7C7C"),3*/),new EmptyBorder(10,10,10,10));//padded border
        readmetext.setText(getHTMLString(15,"#F0F3F5","../assets/README.txt"));//html formatted text
        readmetext.setBounds(25,70,readmeDim.width-50,readmeDim.height-140);//left half of screen
        readmetext.setHorizontalAlignment(JLabel.LEFT);
        readmetext.setVerticalAlignment(JLabel.TOP);
        readmetext.setBorder(readmebox);
        
        readme.add(back);//adding buttons

        readme.add(readmetext);
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
        doge = new JPanel();
        eth = new JPanel();

        market.setSize(1280,1024);
        market.setIconImage(frameIcon.getImage());
        market.getContentPane().setBackground(Color.decode("#122932"));
        market.setLayout(null);//no layout manager
        market.setLocationRelativeTo(null);//center the jframe wrt screen
        market.setVisible(true);
        market.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //close the page on pressing the close button terminates the program if its the only window

        Rectangle marketDim = market.getBounds();

        tab.add("btc",btc);
        tab.add("doge",doge);
        tab.add("eth",eth);
        tab.setBounds(35,70,marketDim.width - 70,marketDim.height - 140);
        Rectangle tabbedpaneDim = tab.getBounds();
        
        btc.setBounds(0,0,tabbedpaneDim.width,tabbedpaneDim.height);
        btc.setLayout(null);
        databtc = DataSetGen.createDataSet("btc");
        chartbtc = ChartFactory.createTimeSeriesChart("Bitcoin","Timeline","Value",databtc);
        chpanelbtc = new ChartPanel(chartbtc);
        XYPlot plotbtc = (XYPlot)chartbtc.getPlot();
        plotbtc.setBackgroundPaint(Color.decode("#F2F5A3"));
        chpanelbtc.setBounds(0,0,tabbedpaneDim.width,tabbedpaneDim.height);
        btc.add(chpanelbtc);

        doge.setBounds(0,0,tabbedpaneDim.width,tabbedpaneDim.height);
        doge.setLayout(null);
        datadoge = DataSetGen.createDataSet("doge");
        chartdoge = ChartFactory.createTimeSeriesChart("Dogecoin","Timeline","Value",datadoge);
        chpaneldoge = new ChartPanel(chartdoge);
        XYPlot plotdoge = (XYPlot)chartdoge.getPlot();
        plotdoge.setBackgroundPaint(Color.decode("#F2F5A3"));
        chpaneldoge.setBounds(0,0,tabbedpaneDim.width,tabbedpaneDim.height);
        doge.add(chpaneldoge);

        eth.setBounds(0,0,tabbedpaneDim.width,tabbedpaneDim.height);
        eth.setLayout(null);
        dataeth = DataSetGen.createDataSet("eth");
        charteth = ChartFactory.createTimeSeriesChart("Ethereum","Timeline","Value",dataeth);
        chpaneleth = new ChartPanel(charteth);
        XYPlot ploteth = (XYPlot)charteth.getPlot();
        ploteth.setBackgroundPaint(Color.decode("#F2F5A3"));
        chpaneleth.setBounds(0,0,tabbedpaneDim.width,tabbedpaneDim.height);
        eth.add(chpaneleth);        
        
        tab.setBackground(Color.decode("#FFCDAD"));

        back.setBounds(10,10,120,40);//top left button
        back.setBackground(Color.decode("#F85E00"));
        back.addActionListener(this);
                
        market.add(back);//adding buttons
        market.add(tab);//adding tabs    
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