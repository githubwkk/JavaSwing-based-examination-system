package c;

import v.adminWindow;
import m.questions;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;

public class viewQuestionListener implements ChangeListener {
    adminWindow AW;

    public viewQuestionListener(adminWindow adminWindow) {
        this.AW = adminWindow;
    }

    public void stateChanged(ChangeEvent e) {
        DBUtils dbUtils = new DBUtils();
        List<questions> list = dbUtils.executeQueryBeans("select * from questions", questions.class);

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String a = i + 1 + ". ";
            s.append(a).append(list.get(i).toString());
        }
        AW.QBArea.setText(s.toString());
    }
}

