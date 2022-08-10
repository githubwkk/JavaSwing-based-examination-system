package c;

import v.adminWindow;
import m.grades;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Objects;

public class dateListener implements ItemListener {
    adminWindow AW;

    public dateListener(adminWindow adminWindow) {
        this.AW=adminWindow;
    }
    public void itemStateChanged(ItemEvent e) {
        DBUtils dbUtils=new DBUtils();
        if (!Objects.equals(AW.date.getSelectedItem().toString(), "")) {
            String sql = "select * from grades where date='" + (Objects.requireNonNull(AW.date.getSelectedItem())) + "'ORDER BY grade DESC";
            List<grades> newData1 = dbUtils.executeQueryBeans(sql, grades.class);
            Object[][] tabledata2 = AW.updateTable(newData1);
            AW.model2.setDataVector(tabledata2, AW.col2);//更新表格数据模型
        } else {
            AW.model2.setDataVector(AW.tableData2, AW.col2);//更新表格数据模型为默认
        }
    }
}
