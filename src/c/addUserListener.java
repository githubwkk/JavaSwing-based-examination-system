package c;

import v.adminWindow;
import m.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addUserListener implements ActionListener {
    adminWindow AW;

    public addUserListener(adminWindow adminWindow) {
        this.AW = adminWindow;
    }

    public void actionPerformed(ActionEvent e) {
        DBUtils dbUtils = new DBUtils();
        String id = AW.inputID.getText().trim();
        String name = AW.inputName.getText().trim();
        String password = AW.inputPassWord.getText().trim();
        if (id.length() != 0 && name.length() != 0 && password.length() != 0) {
            String sql = "insert into user(id,name,password)values('" + id + "','" + name + "','" + password + "')";
            dbUtils.executeUpdate(sql);
            user user = dbUtils.executeQueryBean("select * from user where id='" + id + "'", user.class);
            //查询数据在数据库中是否存在
            if (user != null) {
                JLabel label = new JLabel("添加成功");
                label.setFont(AW.font16);
                JOptionPane.showMessageDialog(AW, label, "提示", JOptionPane.WARNING_MESSAGE, AW.tick);
                AW.inputName.setText("");
                AW.inputPassWord.setText("");
                AW.inputName.setText("");
                AW.model1.setDataVector(AW.renewTable(), AW.col1);
            } else {
                JLabel label = new JLabel("添加失败");
                label.setFont(AW.font16);
                JOptionPane.showMessageDialog(AW, label, "提示", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JLabel label = new JLabel("姓名或学号或密码为空！");
            label.setFont(AW.font16);
            JOptionPane.showMessageDialog(AW, label, "提示", JOptionPane.WARNING_MESSAGE);
        }
    }
}
