package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login_page extends JFrame {
    JFrame frame = new JFrame();
    private JLabel usernamelabal = new JLabel();
    private JLabel passwordlabal = new JLabel();
    private JTextField usertext = new JTextField();
    private JTextField showpasswordtext = new JTextField();
    private JPasswordField passwordtext = new JPasswordField();
    private JButton reset = new JButton("Reset");
    private JButton login = new JButton("Login");
    private JCheckBox showpassword = new JCheckBox("Show Password");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu themeMenu = new JMenu("Themes");
    private JMenu subThemeD = new JMenu("Dark Themes");
    private JMenu subThemeL = new JMenu("Light Themes");
    private JMenuItem darkTheme1 = new JMenuItem("Theme_One");
    private JMenuItem darkTheme2 = new JMenuItem("Theme_Two");
    private JMenuItem darkTheme3 = new JMenuItem("Theme_Three");
    private JMenuItem darkTheme4 = new JMenuItem("Theme_Four");
    private JMenuItem lightTheme1 = new JMenuItem("Theme_One");
    private JMenuItem lightTheme2 = new JMenuItem("Theme_Two");

    void BackGround(Color b, Color f){
        frame.getContentPane().setBackground(b);
        usertext.setForeground(f);
        usernamelabal.setForeground(f);
        passwordtext.setForeground(f);
        passwordlabal.setForeground(f);
        frame.getContentPane().setForeground(f);
        login.setBackground(b);
        login.setForeground(f);
        reset.setBackground(b);
        reset.setForeground(f);
        showpasswordtext.setForeground(f);
        showpasswordtext.setBackground(b);
        showpassword.setBackground(b);
        showpassword.setForeground(f);
    }
    void formation(){

        BackGround( new Color(255,255,255) ,new Color(0,0,0));

        themeMenu.add(subThemeD);
        themeMenu.add(subThemeL);

        subThemeD.add(darkTheme1);
        subThemeL.add(lightTheme1);
        subThemeD.add(darkTheme2);
        subThemeL.add(lightTheme2);
        subThemeD.add(darkTheme3);
        subThemeD.add(darkTheme4);
        menuBar.add(themeMenu);

        darkTheme1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Color b = new Color(16, 24, 32);
                    Color f = new Color(0, 107, 56);
                    BackGround(b,f);
                    SwingUtilities.updateComponentTreeUI(Login_page.this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        darkTheme2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Color b = new Color(0, 26, 51);
                    Color f = new Color(170,238,207);
                    BackGround(b,f);
                    SwingUtilities.updateComponentTreeUI(Login_page.this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        darkTheme3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Color b = new Color(45, 41, 38);
                    Color f = new Color(233, 75, 60);
                    BackGround(b,f);
                    SwingUtilities.updateComponentTreeUI(Login_page.this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        darkTheme4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Color b = new Color(0, 128, 128);
                    Color f = new Color(127, 255, 0);
                    BackGround(b,f);
                    SwingUtilities.updateComponentTreeUI(Login_page.this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        lightTheme1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Color b = new Color(249, 237, 235);
                    Color f = new Color(153, 0, 17);
                    BackGround(b,f);
                    SwingUtilities.updateComponentTreeUI(Login_page.this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        lightTheme2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Color b = new Color(100, 100, 100);
                    Color f = new Color(209, 174, 46);
                    BackGround(b,f);
                    SwingUtilities.updateComponentTreeUI(Login_page.this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        usernamelabal.setText("UserName: ");
        usernamelabal.setBounds(150,100,200,50);
        usernamelabal.setForeground(new Color(0, 26, 51));
        usernamelabal.setFont(new Font(Font.DIALOG,Font.BOLD,16));

        passwordlabal.setBounds(150 , 200 , 200 , 50);
        passwordlabal.setForeground(new Color(0, 26, 51));
        passwordlabal.setText("PassWord: ");
        passwordlabal.setFont(new Font(Font.DIALOG,Font.BOLD,16));

        usertext.setBounds(260,105,250,40);
        usertext.setFont(new Font(Font.SERIF,Font.BOLD,18));
        usertext.setOpaque(false);

        passwordtext.setBounds(260,205,250,40);
        passwordtext.setFont(new Font(Font.SERIF,Font.BOLD,18));
        passwordtext.setOpaque(false);

        showpasswordtext.setVisible(false);
        showpasswordtext.setBounds(260,205,250,40);

        showpassword.setBounds(200,300,120, 20);

        reset.setBounds(150 , 400 , 140 , 40);
        reset.setFont(new Font(Font.DIALOG,Font.BOLD,16));
        reset.setFocusable(false);

        showpassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showpassword.isSelected()) {
                    showpasswordtext.setVisible(true);
                    passwordtext.setVisible(false);
                    showpasswordtext.setText(new String(passwordtext.getPassword()));
                    passwordtext.setText("");
                    showpasswordtext.requestFocus();
                } else {
                    showpasswordtext.setVisible(false);
                    passwordtext.setVisible(true);
                    passwordtext.setText(new String(showpasswordtext.getText()));
                    showpasswordtext.setText("");
                    passwordtext.requestFocus();
                }
            }
        });
        showpasswordtext.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(showpasswordtext.getText().length()>=15){
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null,"Input of password is tooo Long","Lenght Issue",JOptionPane.INFORMATION_MESSAGE);
                    showpasswordtext.setText("");
                }
            }
        });
        passwordtext.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(passwordtext.getText().length()>=15){
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null,"Input of password is tooo Long","Lenght Issue",JOptionPane.INFORMATION_MESSAGE);
                    passwordtext.setText("");
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER){
                    checker();
                }

            }
        });
        usertext.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_DOWN )
                {
                    passwordtext.requestFocus();
                }

            }
        });
        passwordtext.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_UP )
                {
                    usertext.requestFocus();
                }

            }
        });
        usertext.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(usertext.getText().length()>=15){
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null,"Input of user is tooo Long","Lenght Issue",JOptionPane.INFORMATION_MESSAGE);
                    usertext.setText("");
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER){
                    checker();
                }
            }
        });

        login.setBounds(400 , 400 , 140 , 40);
        login.setFont(new Font(Font.DIALOG,Font.BOLD,16));
//    login.setBorderPainted(false);
        login.setFocusable(false);

        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usertext.setText("");
                passwordtext.setText("");
                showpasswordtext.setText("");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                reset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checker();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
    }
    void checker(){
        if(!(usertext.getText().equals("s")) && !(passwordtext.getText().equals("s"))){
            JOptionPane.showMessageDialog(null , "INCORRECT INFORMATION","Error" , JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(usertext.getText().equals("s") && passwordtext.getText().equals("s") || usertext.getText().equals("s") && showpasswordtext.getText().equals("s")){
            frame.dispose();
            try {
                new WebEngine(frame.getContentPane().getBackground(),frame.getContentPane().getForeground());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return;
        }
        if(!(usertext.getText().equals("s"))){
            JOptionPane.showMessageDialog(null , "INCORRECT UserName" , "Error" , JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(!(passwordtext.getText().equals("s"))){
            JOptionPane.showMessageDialog(null , "INCORRECT Password","Error" , JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public Login_page() {
        formation();
        frame.add(usernamelabal);
        frame.add(usertext);
        frame.add(passwordlabal);
        frame.add(passwordtext);
        frame.add(reset);
        frame.add(login);
        frame.add(showpassword);
        frame.add(showpasswordtext);
        frame.setJMenuBar(menuBar);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(720,540);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Log_in_Page");
        Image icon = Toolkit.getDefaultToolkit().getImage(WebEngine.mainPath + "\\images\\crawlericon.png");
        frame.setIconImage(icon);
        frame.setLayout(null);
        frame.getContentPane();
        frame.setVisible(true);
    }
}