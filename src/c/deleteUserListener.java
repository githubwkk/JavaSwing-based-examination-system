package c;

import v.adminWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteUserListener implements ActionListener {
    adminWindow AW;

    public deleteUserListener(adminWindow adminWindow) {
        this.AW = adminWindow;
    }

    public void actionPerformed(ActionEvent e) {
        DBUtils dbUtils = new DBUtils();
        int pos = AW.table1.getSelectedRow();
        if (pos == -1) {
            JLabel label1 = new JLabel("请选择要删除的记录");
            label1.setFont(AW.font16);
            JOptionPane.showMessageDialog(null, label1, "选择记录", JOptionPane.YES_OPTION);
            return;
        }
        JLabel label2 = new JLabel("确定删除?");
        label2.setFont(AW.font16);
        int value = JOptionPane.showConfirmDialog(null, label2, "删除确认", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (value == JOptionPane.NO_OPTION) return;
        String sql = "delete from user where id='" +  AW.tableData1[pos][0]+"'";
        dbUtils.executeUpdate(sql);
        AW.model1.setDataVector(AW.renewTable(), AW.col1);
    }
}
