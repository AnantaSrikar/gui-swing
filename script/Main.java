import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.net.URL;


public class Main
{
    public static void main(String args[])
    {
        HomePage homepage = new HomePage();
    }
}

class HomePage implements ActionListener//home page frame generator class
{
    HomePage()
    {
        JFrame homepage = new JFrame("Pop and Block");

        ImageIcon frameIcon = new ImageIcon("../assets/icon.png");
        
        JButton readme = new JButton("README");//respective buttons with the titles
        JButton source = new JButton("Source\ncode");
        JButton market = new JButton("Market");

        JLabel header = new JLabel("Pop and Block");
        Image scaledimage = (frameIcon.getImage()).getScaledInstance(200 , 200, Image.SCALE_FAST);//scaling frameicon image
        frameIcon = new ImageIcon(scaledimage);
        header.setIcon(frameIcon);
        header.setBounds(590,90,250,250);//boundaries
        header.setFont(header.getFont().deriveFont(30.0f));
        header.setHorizontalTextPosition(JLabel.CENTER);
        header.setVerticalTextPosition(JLabel.BOTTOM);//aligning text to center-bottom of jlabel.
        
        readme.setBounds(325,540,120,40);//orientation divided according to third of frame height,
        source.setBounds(670,540,120,40);//and quarters of frame length
        market.setBounds(1015,540,120,40);

        source.addActionListener(this);//action listener for source button

        homepage.add(readme);//adding buttons
        homepage.add(source);
        homepage.add(market);

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
        openWebpage("https://github.com/pop-and-block");
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