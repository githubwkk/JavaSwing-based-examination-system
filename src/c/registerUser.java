package c;

import v.registerWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerUser implements ActionListener {
    registerWindow RW;

    public registerUser(registerWindow registerWindow) {
        this.RW = registerWindow;
    }

    public void actionPerformed(ActionEvent e) {
        String id = RW.idField.getText();
        String name = RW.nameField.getText();
        String password = RW.passwordField.getText();
        String password2 = RW.passwordField2.getText();
        if (id.equals("") || name.equals("") || password.equals("") || password2.equals("")) {
            JLabel label0 = new JLabel("请输入完整信息!");
            label0.setFont(RW.font16);
            JOptionPane.showMessageDialog(null, label0);
        } else if (!password.equals(password2)) {
            JLabel label1 = new JLabel("两次密码不一致!");
            label1.setFont(RW.font16);
            JOptionPane.showMessageDialog(null, label1);
        } else {
            DBUtils dbUtils = new DBUtils();
            try {
                dbUtils.executeUpdate("insert into user values('" + id + "','" + name + "','" + password + "')");
                JLabel label2 = new JLabel("注册成功!");
                label2.setFont(RW.font16);
                JOptionPane.showMessageDialog(null, label2);
                RW.dispose();
            } catch (Exception e1) {
                JLabel label3 = new JLabel("注册失败!，请检查学号是否已存在!");
                label3.setFont(RW.font16);
                JOptionPane.showMessageDialog(null, label3);
            }
        }
    }
}
