package c;

import v.adminWindow;
import v.loginWindow;
import m.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminLoginListener implements ActionListener {
    loginWindow LW;

    public adminLoginListener(loginWindow loginWindow) {
        this.LW = loginWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DBUtils dbUtils = new DBUtils();
        String ID = LW.idFidld.getText().trim();
        String PassWord = LW.passwordField.getText().trim();
        String sql = "select * from admin where  ID='" + ID + "'  and password='" + PassWord + "'";
        admin admin = dbUtils.executeQueryBean(sql, admin.class);
        if (e.getSource() == LW.buttonLogin) {
            if (ID.length() == 0 || PassWord.length() == 0) {
                JLabel label = new JLabel("请输入用户名和密码");
                label.setFont(LW.font18);
                JOptionPane.showMessageDialog(LW, label, "消息框", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (admin != null) {
                JLabel label1 = new JLabel("登录成功");
                label1.setFont(LW.font18);
                JOptionPane.showMessageDialog(LW, label1, "消息框", JOptionPane.WARNING_MESSAGE);
                new adminWindow();
                LW.dispose();//关闭登录窗口
            } else {
                JLabel label2 = new JLabel("您输入的账户或密码有误请重新输入！");
                label2.setFont(LW.font18);
                JOptionPane.showMessageDialog(LW, label2, "登录失败", JOptionPane.WARNING_MESSAGE);
            }
        }

    }
}
