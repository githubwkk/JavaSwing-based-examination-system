package c;

import v.adminWindow;
import v.examWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class examSetListener implements ActionListener {
    adminWindow AW;
    public examSetListener(adminWindow adminWindow) {
        this.AW = adminWindow;
    }

    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(AW.newTime.getText(), "") && Objects.equals(AW.newNum.getText(), "")) {
            JLabel label = new JLabel("请输入分数或题数后再点击“更新”");
            label.setFont(AW.font16);
            JOptionPane.showMessageDialog(AW, label, "消息框", JOptionPane.WARNING_MESSAGE);
        } else {
            DBUtils dbUtils = new DBUtils();
            String sql = "update questions set time=" + AW.newTime.getText().trim() + " where time=" + AW.oldTime.getText().trim();
            dbUtils.executeUpdate(sql);
            AW.oldTime.setText(AW.renewTime());
            AW.newTime.setText("");
            int tiShu=(Integer.parseInt(AW.newNum.getText()));//传题数的值到考试类中
            examWindow.tiShu=tiShu;
            AW.oldNum.setText(AW.newNum.getText());
            AW.newNum.setText("");
        }
    }
}
