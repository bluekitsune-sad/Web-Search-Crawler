package Controller;

import Crawler.WebCrawler;
import Search.SearchTextFiles;
import SpellChecker.Spell_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

//XMLDSig
//https://cr.openjdk.java.net/~ebaron/jdk8u/JDK-8219013/webrev.00/src/share/classes/org/jcp/xml/dsig/internal/dom/XMLDSigRI.java.sdiff.html

public class WebEngine extends JFrame  {
    private JFrame frame = new JFrame();
    private JLabel designStar = new JLabel();
    private JLabel toptitle = new JLabel("SEARCH ENGINE");
    private JLabel urllabel = new JLabel("Input URL:");
    private JLabel searchlabel = new JLabel("Input Query:");
    private JTextField urlinput = new JTextField();
    private JTextField searchQuery = new JTextField();
    private JButton searchButton = new JButton("Search");
    private JButton crawlButton = new JButton("Crawl");
    private  JButton creditButton = new JButton("Credits");
    private JButton darkBUTON = new JButton("Dark");
    private JButton lightBUTON = new JButton("Light");
    private JToggleButton backButoon = new JToggleButton("ᕙ(⇀‸↼‶)ᕗ");
    private JPanel panel = new JPanel();
    private JPanel topStrip = new JPanel();
    private JPanel areatext = new JPanel ();
    static JTextArea output = new JTextArea("",11,30);
    static JScrollPane scroll = new JScrollPane(output,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    public static String mainPath = "C:\\Users\\teacher.GC\\Desktop\\DSA-ProJect\\DSA-ProJect";
    public static boolean ifValidURL(String URL){   //checking validity of URL
        try {
            System.out.println("Validating URL...");
            URL obj = new URL(URL);
            HttpURLConnection CON = (HttpURLConnection) obj.openConnection();
            CON.setRequestMethod("GET");
            int response = CON.getResponseCode();
            if(response==200) {
                return true;
            }else {
                return false;
            }

        }
        catch (Exception e) {
            return false;
        }
    }
    private static boolean checkURL(String URL) {

        if(ifValidURL(URL)) {
            setOutputln("Enterd URL " + URL + " is valid");
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null,"Please try again . . .","check result",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
    void Crael(){
        try {
            String URL = urlinput.getText();
            if (!URL.startsWith("http://") && !URL.startsWith("https://"))
            {
                URL= "https://" + URL;
            }

            boolean afterCheck = checkURL(URL);
            if(afterCheck)
            {
                System.out.println("Crawling . . . ");
                setOutputln("Now, we can start crawling \n");
                WebCrawler.crawlerStart(URL, 0);
                setOutputln("Crawling done...");
                setOutputln("\n*************************************************************************");
                setOutputln("Thank you");
                setOutputln("************************************************************************* \n");
            }
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    void sorach(){
        try {
            boolean searchAgain = false;
            while(searchAgain == false)
            {
                System.out.println("Searching . . .");
                //searching and returning result in hashmap with its values
                HashMap<String, Integer> result = new HashMap();
                boolean checkResultIsNotEmpty = false;
                boolean haveResults = false;
                double start, end;

                String query = searchQuery.getText();
                if(query.equals("")){   //if the query box is empty
                    searchQuery.setText(JOptionPane.showInputDialog("The Search Option was empty",JOptionPane.MESSAGE_TYPE_PROPERTY));
                    return;
                }
                SearchTextFiles search = new SearchTextFiles();
                start = System.currentTimeMillis();
                result = search.searchString(query);
                end = System.currentTimeMillis();

                //iterating in hashmap
                setOutputln(" ");
                setOutputln("*************************************************************************\n");
                setOutputln("Search Result of Query "+query+" is as follows:\n");
                setOutputln("*************************************************************************");
                setOutputln(" ");

                int i = 0;
                for (HashMap.Entry<String, Integer> entry : result.entrySet())
                {
                    if(i < 10)
                    {
                        if(entry.getValue()!= 0)
                        {
                            setOutputln("url:"+entry.getKey());
                            setOutputln("frequency:"+entry.getValue());
                            checkResultIsNotEmpty = false;
                            haveResults = true;
                            i++;
                        }
                        else
                        {
                            checkResultIsNotEmpty = true;
                            break;
                        }
                    }
                }
                setOutputln("Total time taken to search:"+(end-start)+"ms");
                setOutputln(" ");

                if (checkResultIsNotEmpty == true && haveResults == false)
                {
                    //Attention
                    setOutput("Output:");
                    setOutputln("No Result Found");
                    setOutputln(" ");
                    setOutputln("Running Spell Correction...");

                    String dictionaryFileName = WebEngine.mainPath + ("/src/Files/dictionary.txt");

                    Spell_1 corrector = new Spell_1();

                    corrector.dict_file(dictionaryFileName);

                    String suggestion = corrector.suggestSimilarWord(query);

                    if (suggestion == null) {
                        JOptionPane.showMessageDialog(null,"No similar word found !!","check result",JOptionPane.INFORMATION_MESSAGE);
                        suggestion = "No similar word found";
                        searchQuery.setText("");
                        sorach();
                        return;

                    }
//
                    int yes = JOptionPane.showConfirmDialog(null, "similar word found !!","check result", JOptionPane.YES_NO_OPTION);
                    searchQuery.setText("");
                    setOutputln("Did you mean: " + suggestion);

                    setOutputln("Type Yes to proceed with the suggestion");
                    setOutput("Input:");

                    if(yes==JOptionPane.YES_OPTION) {

                        result = search.searchString(suggestion);

                        //iterating in hashmap
                        setOutputln(" ");
                        setOutputln("*************************************************************************\n");
                        setOutputln("Search Result of Query "+suggestion+" is as follows:\n");
                        setOutputln("*************************************************************************");
                        setOutputln(" ");
                        int j = 0;
                        for (HashMap.Entry<String, Integer> entry : result.entrySet())
                        {
                            if(j < 10)
                            {
                                if(entry.getValue()!= 0)
                                {
                                    setOutputln("url:"+entry.getKey());
                                    setOutputln("frequency:"+entry.getValue());
                                    checkResultIsNotEmpty = false;
                                    haveResults = true;
                                    j++;
                                }

                                else
                                {
                                    checkResultIsNotEmpty = true;
                                    break;
                                }
                            }
                        }

                    }

                    else {
                        searchQuery.setText("");
                        JOptionPane.showMessageDialog(null,"Now reseting search","lal",JOptionPane.INFORMATION_MESSAGE);
                        setOutputln("User did not agree with the suggestion");
                        sorach();
                        return;


                    }
                }
                setOutputln(" ");
                setOutputln("Total time taken to search:"+(end-start)+"ms");
                setOutputln(" ");
                setOutputln("*************************************************************************");
                setOutputln("Do you want to search again?");
                setOutputln("type y for yes and n for no");
                //Attention
                setOutput("Input:");
                int yes = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirmation", JOptionPane.YES_NO_OPTION);
                searchQuery.setText("");
                if (yes==JOptionPane.NO_OPTION)
                {
                    searchAgain = true;
                    setOutputln(" ");
                    setOutputln("Exiting Search Engine...");
                }

                else if(yes == JOptionPane.YES_OPTION)
                {
                    searchQuery.setText(JOptionPane.showInputDialog("The Search Option was empty",JOptionPane.MESSAGE_TYPE_PROPERTY));
                    searchAgain = false;
                }

                else
                {
                    JOptionPane.showConfirmDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                    setOutputln("Wrong Input");
                }
            }
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void setOutputln(String x) {
        output.append(x);
        output.append("\n");
    }
    public static void setOutput(String x) {
        output.append(x);
    }
    void formationFrame(Color b ,Color f){


        urllabel.setBounds(50, 150, 200, 35);
        urllabel.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        searchlabel.setBounds(50, 250, 200, 35);
        searchlabel.setFont(new Font(Font.SERIF, Font.BOLD, 16));

        areatext.setBounds(640, 130, 350, 200);

        output.setBounds(650, 130, 250, 200);

        output.setEditable(false);

        output.setWrapStyleWord(true);
        scroll.setOpaque(false);

        urlinput.setBounds(150, 150, 200, 35);
        urlinput.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        urlinput.setOpaque(false);

        searchQuery.setBounds(150, 250, 200, 35);
        searchQuery.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        searchQuery.setOpaque(false);

        topStrip.setBounds(0, 0, 1005, 55);
        toptitle.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        toptitle.setForeground(new Color(170, 238, 207));
        toptitle.setBounds(5, 50, 150, 100);

        crawlButton.setBounds(250, 350, 100, 30);
        crawlButton.setFocusable(false);
        searchButton.setBounds(450, 350, 100, 30);
        searchButton.setFocusable(false);
        creditButton.setBounds(850, 400, 90, 30);
        creditButton.setFocusable(false);
        darkBUTON.setBounds(750, 400, 50, 30);
        lightBUTON.setBounds(820, 400, 50, 30);
        Color compaGR = new Color(16, 24, 32);
        Color compaR = new Color(249, 237, 235);
        Color compaW = new Color(255,255,255);
        Color compaG = new Color(100, 100, 100);
        if (b.getRGB() == compaGR.getRGB()) {
            topStrip.setBackground(new Color(0, 77, 40));
        }else if (b.getRGB() == compaR.getRGB()||b.getRGB()==compaG.getRGB()) {
            topStrip.setBackground(new Color(29, 26, 5));
        }else if (b.getRGB() == compaW.getRGB()) {
            topStrip.setBackground(new Color(0, 32, 63));
        }else {
            topStrip.setBackground(new Color(114, 116, 116));
        }
        panel.setBackground(b);
        frame.setBackground(b);
        areatext.setBackground(b);
        output.setBackground(b);
        scroll.setBackground(b);
        urllabel.setForeground(f);
        searchlabel.setForeground(f);
        searchQuery.setForeground(f);
        urlinput.setForeground(f);
        output.setForeground(f);
        crawlButton.setBackground(b);
        crawlButton.setForeground(f);
        searchButton.setBackground(b);
        searchButton.setForeground(f);
        creditButton.setBackground(b);
        creditButton.setForeground(f);
        setOutputln(" ");
        creditButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new credit();
            }
            public void mouseEntered(MouseEvent e) {
                creditButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        crawlButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Crael();
            }
            public void mouseEntered(MouseEvent e) {
                crawlButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sorach();
            }
            public void mouseEntered(MouseEvent e) {
                searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
//        crawlButton.setMnemonic(KeyEvent.VK_C);
//        searchButton.setMnemonic(KeyEvent.VK_S);
        urlinput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER){
                    Crael();
                }
            }
        });searchQuery.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER){
                    sorach();
                }
            }
        });
        urlinput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_DOWN )
                {
                    searchQuery.requestFocus();
                }

            }
        });searchQuery.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_UP )
                {
                    urlinput.requestFocus();
                }

            }
        });
    }
    public WebEngine(Color b ,Color f) {

        formationFrame(b, f);

        panel.setBounds(0, 0, 1020, 500);
        panel.add(backButoon);
        panel.add(designStar);
        panel.add(urlinput);
        panel.add(urllabel);
        panel.add(crawlButton);
        panel.add(searchQuery);
        panel.add(searchButton);
        panel.add(searchlabel);
        panel.add(creditButton);
        panel.add(areatext);

        topStrip.add(toptitle);
        panel.add(topStrip);
        areatext.add(scroll);
        panel.setLayout(new BorderLayout());


        frame.add(panel);

        frame.setTitle("Web Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage(mainPath + "\\images\\crawlericon.png");
        frame.setSize(1020, 500);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(icon);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane();
        frame.setVisible(true);
    }
}