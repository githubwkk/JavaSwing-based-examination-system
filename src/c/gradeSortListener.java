package c;

import v.adminWindow;
import m.grades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class gradeSortListener implements ActionListener {
    adminWindow AW;
    public gradeSortListener(adminWindow adminWindow) {
        this.AW=adminWindow;
    }

    public void actionPerformed(ActionEvent e) {
        DBUtils dbUtils=new DBUtils();
        if (AW.gradeSort.getSelectedIndex() == 0) {
            if (!Objects.equals(AW.date.getSelectedItem().toString(), "")) {
                String sql = "select * from grades where date='" + (Objects.requireNonNull(AW.date.getSelectedItem())) + "'ORDER BY grade DESC";
                List<grades> newData2 = dbUtils.executeQueryBeans(sql, grades.class);
                Object[][] tableData2 = AW.updateTable(newData2);
                AW.model2.setDataVector(tableData2, AW.col2);//更新表格数据模型
            } else {
                String sql = "select * from grades ORDER BY grade DESC";
                List<grades> newData3 = dbUtils.executeQueryBeans(sql, grades.class);
                Object[][] tableData3 = AW.updateTable(newData3);
                AW.model2.setDataVector(tableData3, AW.col2);//更新表格数据模型
            }
        }
        if (AW.gradeSort.getSelectedIndex() == 1) {
            if (!Objects.equals(AW.date.getSelectedItem().toString(), "")) {
                String sql = "select * from grades where date='" + (Objects.requireNonNull(AW.date.getSelectedItem())) + "'ORDER BY grade ASC";
                List<grades> newData4 = dbUtils.executeQueryBeans(sql, grades.class);
                Object[][] tableData4 = AW.updateTable(newData4);
                AW.model2.setDataVector(tableData4, AW.col2);//更新表格数据模型
            } else {
                String sql = "select * from grades ORDER BY grade ASC";
                List<grades> newData5 = dbUtils.executeQueryBeans(sql, grades.class);
                Object[][] tableData5 = AW.updateTable(newData5);
                AW.model2.setDataVector(tableData5, AW.col2);//更新表格数据模型
            }
        }
    }
}
