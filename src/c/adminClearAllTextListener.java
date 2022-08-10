package c;

import v.adminWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminClearAllTextListener implements ActionListener {
    adminWindow AW;
    public adminClearAllTextListener(adminWindow adminWindow) {
        this.AW=adminWindow;
    }

    public void actionPerformed(ActionEvent e) {
        AW.QUESTION_TEXT.setText("");
        AW.ANSWER_TEXT.setText("");
        AW.SCORE_TEXT.setText("");
    }
}
