package v;

import c.DBUtils;
import c.adminLoginListener;
import c.userLoginListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginWindow extends JFrame {
    loginWindow loginWindow = this;
    public JButton buttonLogin = new JButton("登录");
    public JTextField idFidld;
    public JPasswordField passwordField;
    DBUtils dbUtils = new DBUtils();
    public Font font18 = new Font("微软雅黑", Font.PLAIN, 18);
    Font font13 = new Font("微软雅黑", Font.PLAIN, 13);
    Color bg = new Color(236, 247, 241);
    Color btColor = new Color(18, 183, 245);//按钮颜色
    userLoginListener ULL = new userLoginListener(this);//用户登录按钮监听器的定义
    adminLoginListener ALL = new adminLoginListener(loginWindow);//管理员登录按钮监听器的定义

    public loginWindow() {
        init();
    }

    private void init() {
        int width = 400, height = 320;
        this.setTitle("JAVA考试系统");
        int pX = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2;
        int pY = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2;
        this.setBounds(pX, pY, width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createLoginPanel(), BorderLayout.CENTER);
        this.add(btnPanel(), BorderLayout.SOUTH);
    }

    //切换用户单选框
    private JPanel userSwitch() {
        JPanel panel = new JPanel();
        panel.setBackground(bg);
        Font font = new Font("微软雅黑", Font.PLAIN, 17);
        ImageIcon tick = new ImageIcon("image/tick.png");
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JRadioButton userButton = new JRadioButton("用户", true);
        userButton.setBackground(bg);
        JRadioButton adminButton = new JRadioButton("管理员");
        adminButton.setBackground(bg);
        ButtonGroup buttonGroup = new ButtonGroup();
        userButton.setFont(font13);
        adminButton.setFont(font13);
        buttonGroup.add(userButton);
        buttonGroup.add(adminButton);
        panel.add(userButton);
        panel.add(adminButton);
        userButton.addActionListener(e -> {
            if (buttonLogin.getActionListeners().length != 0) {
                buttonLogin.removeActionListener(buttonLogin.getActionListeners()[0]);//移除之前登录按钮的监听器
            }
            buttonLogin.addActionListener(ULL);//添加监听
            JLabel label = new JLabel("成功切换到考生登录");
            label.setFont(font);
            JOptionPane.showMessageDialog(loginWindow, label, "切换成功", JOptionPane.WARNING_MESSAGE, tick);
        });
        adminButton.addActionListener(e -> {
            if (buttonLogin.getActionListeners().length != 0) {
                buttonLogin.removeActionListener(buttonLogin.getActionListeners()[0]);//移除之前登录按钮的监听器
            }
            buttonLogin.addActionListener(ALL);//添加监听
            JLabel label = new JLabel("成功切换到管理员登录");
            label.setFont(font);
            JOptionPane.showMessageDialog(loginWindow, label, "切换成功", JOptionPane.WARNING_MESSAGE, tick);
        });
        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bg);
        JPanel info = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        info.setBackground(bg);
        JLabel label = new JLabel("登录", SwingConstants.CENTER);
        label.setForeground(new Color(0x7A000040, true));
        label.setFont(new Font("微软雅黑", Font.PLAIN, 33));
        info.add(label);
        panel.add(info, BorderLayout.NORTH);
        panel.add(idAndPwdPanel(), BorderLayout.CENTER);
        panel.add(userSwitch(), BorderLayout.SOUTH);
        return panel;
    }

    //账号和密码面板
    private JPanel idAndPwdPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panel.setBackground(bg);
        panel.add(idPane());
        panel.add(pwdPane());
        return panel;
    }

    //密码面板
    private JPanel pwdPane() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bg);
        JLabel label = new JLabel("密码：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        panel.add(label, BorderLayout.WEST);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(220, 30));
        panel.add(passwordField, BorderLayout.CENTER);
        return panel;
    }

    //账号面板
    private JPanel idPane() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("账号：");
        panel.setBackground(bg);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        panel.add(label, BorderLayout.WEST);
        idFidld = new JTextField();
        idFidld.setPreferredSize(new Dimension(220, 30));
        panel.add(idFidld, BorderLayout.CENTER);
        return panel;
    }

    //按钮面板
    private JPanel btnPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBorder(new EmptyBorder(0, 0, 10, 0));
        panel.setBackground(bg);
        JButton buttonregister = new JButton("注册");
        buttonLogin.addActionListener(ULL);
        buttonLogin.setBackground(btColor);
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setFont(font13);
        buttonregister.setBackground(btColor);
        buttonregister.setForeground(Color.WHITE);
        buttonregister.setFont(font13);
        //注册按钮监听器的添加
        buttonregister.addActionListener(e -> {
            dispose();
            registerWindow registerWindow = new registerWindow();
            registerWindow.setVisible(true);
        });
        panel.add(buttonLogin);
        panel.add(buttonregister);
        return panel;
    }

    public static void main(String[] args) {
        loginWindow loginWindow = new loginWindow();
        loginWindow.setVisible(true);
    }
}
