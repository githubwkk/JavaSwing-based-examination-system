package c;

import v.adminWindow;
import m.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modifyUser implements ActionListener {
    adminWindow AW;

    public modifyUser(adminWindow adminWindow) {
        this.AW = adminWindow;
    }

    public void actionPerformed(ActionEvent e) {
        DBUtils dbUtils = new DBUtils();
        String name = AW.inputName.getText().trim();
        String password = AW.inputPassWord.getText().trim();

        if (!name.equals("")&&!password.equals("")) {
            int pos = AW.table1.getSelectedRow();
            if (pos == -1) {
                JLabel label1 = new JLabel("请选择要修改的学生信息");
                label1.setFont(AW.font16);
                JOptionPane.showMessageDialog(null, label1, "选择记录", JOptionPane.YES_OPTION);
                return;
            }
            JLabel label2 = new JLabel("是否将学号为 " + AW.tableData1[pos][0] + " 的信息修改为姓名：" + name + " 密码：" + password+"。");
            label2.setFont(AW.font16);
            int value = JOptionPane.showConfirmDialog(null, label2, "删除确认", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (value == JOptionPane.NO_OPTION) return;

            String sql = "update user set name='" + name + "',password='" + password + "' where id='" + AW.tableData1[pos][0] + "'";
            dbUtils.executeUpdate(sql);
            user user = dbUtils.executeQueryBean("SELECT * FROM user WHERE password='" + password + "' AND name='" + name + "'", user.class);
            //查询数据在数据库中是否存在
            if (user != null) {
                JLabel label = new JLabel("修改成功");
                label.setFont(AW.font16);
                JOptionPane.showMessageDialog(AW, label, "提示", JOptionPane.WARNING_MESSAGE, AW.tick);
                AW.model1.setDataVector(AW.renewTable(), AW.col1);
                AW.inputName.setText("");
                AW.inputPassWord.setText("");
            } else {
                JLabel label = new JLabel("修改失败");
                label.setFont(AW.font16);
                JOptionPane.showMessageDialog(AW, label, "提示", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JLabel label = new JLabel("密码或姓名不能为空！");
            label.setFont(AW.font16);
            JOptionPane.showMessageDialog(AW, label, "提示", JOptionPane.WARNING_MESSAGE);
        }
    }
}
