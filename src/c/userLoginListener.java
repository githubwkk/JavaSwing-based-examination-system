package c;

import v.loginWindow;
import v.mainWindow;
import m.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userLoginListener implements ActionListener {
    loginWindow LW;
    public userLoginListener(loginWindow loginWindow) {
        this.LW = loginWindow;
    }
    public void actionPerformed(ActionEvent e) {
        DBUtils dbUtils = new DBUtils();
        String ID = LW.idFidld.getText().trim();
        String PassWord = LW.passwordField.getText().trim();
        String sql = "select * from user where  id='" + ID + "'  and password='" + PassWord + "'";
        user user = dbUtils.executeQueryBean(sql, user.class);
        if (e.getSource() == LW.buttonLogin) {
            if (ID.length() == 0 || PassWord.length() == 0) {
                JLabel label = new JLabel("请输入用户名和密码");
                label.setFont(LW.font18);
                JOptionPane.showMessageDialog(LW, label, "消息框", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (user != null) {
                JLabel label1 = new JLabel("欢迎您：" + user.getName());
                label1.setFont(LW.font18);
                JOptionPane.showMessageDialog(LW, label1, "消息框", JOptionPane.WARNING_MESSAGE);
                mainWindow mainWindow = new mainWindow(user);
                mainWindow.setVisible(true);
                LW.dispose();//关闭登录窗口
            } else {
                JLabel label2 = new JLabel("您输入的账户或密码有误请重新输入！");
                label2.setFont(LW.font18);
                JOptionPane.showMessageDialog(LW, label2, "登录失败", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
