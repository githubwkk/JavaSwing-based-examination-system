package v;

import c.DBUtils;
import c.registerUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerWindow extends JFrame {
    Font font18 = new Font("微软雅黑", Font.PLAIN, 18);
    public Font font16 = new Font("微软雅黑", Font.PLAIN, 16);
    public JTextField idField;
    public JTextField nameField;
    public JTextField passwordField;
    public JTextField passwordField2;

    registerWindow() {
        init();
    }

    private void init() {
        int width = 340, height = 400;
        this.setTitle("JAVA考试系统");
        int pX = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2;
        int pY = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2;
        this.setBounds(pX, pY, width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(main());
    }

    private JPanel main() {
        JPanel panel = new JPanel(new BorderLayout(10, 25));
        JLabel label = new JLabel("请输入您的信息:");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 26));
        label.setForeground(new Color(0x7A000040, true));
        panel.setBackground(new Color(236, 247, 241));
        panel.add(label, BorderLayout.NORTH);
        JPanel idAndName = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        idAndName.setBackground(new Color(236, 247, 241));
        idAndName.add(id());
        idAndName.add(name());
        idAndName.add(password());
        idAndName.add(password2());
        panel.add(idAndName, BorderLayout.CENTER);
        panel.add(button(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel id() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBackground(new Color(236, 247, 241));
        JLabel label = new JLabel("学号:");
        label.setFont(font18);
        idField = new JTextField();
        idField.setFont(font18);
        idField.setPreferredSize(new Dimension(220, 34));
        panel.add(label);
        panel.add(idField);
        return panel;
    }

    private JPanel name() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBackground(new Color(236, 247, 241));
        JLabel label = new JLabel("姓名:");
        label.setFont(font18);
        nameField = new JTextField();
        nameField.setFont(font18);
        nameField.setPreferredSize(new Dimension(220, 34));
        panel.add(label);
        panel.add(nameField);
        return panel;
    }

    private JPanel password() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBackground(new Color(236, 247, 241));
        JLabel label = new JLabel("密码:");
        label.setFont(font18);
        passwordField = new JTextField();
        passwordField.setFont(font18);
        passwordField.setPreferredSize(new Dimension(220, 34));
        panel.add(label);
        panel.add(passwordField);
        return panel;
    }

    private JPanel password2() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBackground(new Color(236, 247, 241));
        JLabel label = new JLabel("确认密码:");
        label.setFont(font18);
        passwordField2 = new JTextField();
        passwordField2 = new JTextField();
        passwordField2.setFont(font18);
        passwordField2.setPreferredSize(new Dimension(185, 34));
        panel.add(label);
        panel.add(passwordField2);
        return panel;
    }

    private JPanel button() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel.setBackground(new Color(236, 247, 241));
        JButton button = new JButton("注册");
        button.setBackground(Color.cyan);
        button.setFont(font18);
        button.setPreferredSize(new Dimension(80, 30));
        JButton button2 = new JButton("返回");
        button2.setBackground(new Color(234, 247, 241));
        button2.setFont(font18);
        button2.setPreferredSize(new Dimension(80, 30));
        panel.add(button);
        panel.add(button2);
        registerUser RU=new registerUser(this);//添加注册监听
        button.addActionListener(RU);
        button2.addActionListener(e -> {
            registerWindow.this.dispose();
            loginWindow loginWindow = new loginWindow();
            loginWindow.setVisible(true);
        });
        return panel;
    }
}
