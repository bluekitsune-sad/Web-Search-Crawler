package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class credit extends JFrame {
    private JFrame frame = new JFrame();
    private JPanel pan = new JPanel();
    private JButton log = new JButton("Login page");
//    private JButton engine = new JButton("Engine page");
    private JLabel pic = new JLabel();
    private JLabel tylabel = new JLabel("~ Thankyou ~");
    private JLabel saad = new JLabel("Saad yousuf - webengine, crawler;");
    private JLabel abdullah = new JLabel("Abdullah Wasim - Data sorting, Ranking, Searching;");
    private JLabel farjad = new JLabel("Farjad-ur-Rehman - Trie, Spell Checker;");

        public credit() {
            pan.add(pic);
            pic.add(log);
//            pic.add(engine);
            pic.add(tylabel);
            pic.add(saad);
            pic.add(abdullah);
            pic.add(farjad);
            Image icon = Toolkit.getDefaultToolkit().getImage(WebEngine.mainPath + "\\images\\crediticon.png");
            frame.setIconImage(icon);
            pic.setIcon(new ImageIcon(WebEngine.mainPath + "\\images\\cradad.png"));

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image curoor = toolkit.getImage(WebEngine.mainPath + "\\images\\troll.png");
            Image imageclick = toolkit.getImage(WebEngine.mainPath + "\\images\\angry.png");

            Cursor cursor = toolkit.createCustomCursor(curoor, new Point(0, 0), "Custom Cursor");
            pic.setCursor(cursor);

            frame.setContentPane(pic);
            pan.setSize(720, 540);

            log.setBounds(580, 450, 100, 27);
            log.setFocusable(false);

            tylabel.setBounds(230, 100, 300, 100);
            tylabel.setFont(new Font(Font.DIALOG, Font.BOLD, 32));
            saad.setBounds(230, 100, 200, 200);
            saad.setForeground(new Color(255, 255, 255));
            abdullah.setBounds(180, 120, 290, 200);
            abdullah.setForeground(new Color(255, 255, 255));
            farjad.setBounds(220, 140, 250, 200);
            farjad.setForeground(new Color(255, 255, 255));


            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setSize(720, 540);
            frame.setLocationRelativeTo(null);
            frame.setTitle("Cradit");
            frame.setLayout(null);
            frame.setVisible(true);
            frame.getContentPane();

            log.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                    new Login_page();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    Cursor cursor = toolkit.createCustomCursor(imageclick, new Point(0, 0), "Custom Cursor");
                    log.setCursor(cursor);
                }
            });
        }
}