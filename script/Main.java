import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.awt.Dimension;
import java.net.URL;

public class Main
{
    public static void main(String args[])
    {
        new HomePage(true);
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

    }
    HomePage(boolean newFrame)
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
                System.out.println("market button");
            else if(ae.getSource() == readme)
            {
                System.out.println("readme button");
                homepage.setVisible(false);//hiding homepage
                new Readme(homepage);//passing homepage object to Readme class to let it return back to the homepage
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
        readme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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