package c;

import v.adminWindow;
import m.questions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminSubmitListen implements ActionListener {
    adminWindow AW;

    public adminSubmitListen(adminWindow adminWindow) {
        this.AW = adminWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DBUtils dbUtils = new DBUtils();
        String question = AW.QUESTION_TEXT.getText();
        String answer = AW.ANSWER_TEXT.getText().trim();
        String score = AW.SCORE_TEXT.getText().trim();
        //如果没有空，则添加题目
        if (!question.equals("") && !answer.equals("") && !score.equals("")) {
            String sql = "insert into questions(question,answer,score) values('" + question + "','" + answer + "'," + score + ")";
            dbUtils.executeUpdate(sql);
            questions questions = dbUtils.executeQueryBean("select * from questions where question='" + question + "'", questions.class);
            //查询数据在数据库中是否存在(已经添加)
            if (questions != null) {
                JLabel label = new JLabel("添加成功!");
                label.setFont(AW.font16);
                JOptionPane.showMessageDialog(AW, label, "消息", JOptionPane.WARNING_MESSAGE, AW.tick);
                AW.QUESTION_TEXT.setText("");//添加成功后将“问题”和“答案”文本框置为空白
                AW.ANSWER_TEXT.setText("");
            } else {
                JLabel label = new JLabel("数据库中没有查到要添加的数据，添加失败!");
                label.setFont(AW.font16);
                JOptionPane.showMessageDialog(AW, label, "消息", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入完整信息！");
        }
    }
}