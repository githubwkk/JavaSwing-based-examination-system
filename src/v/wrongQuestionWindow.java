package v;

import c.DBUtils;
import m.questions;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class wrongQuestionWindow extends JFrame {
    wrongQuestionWindow wrongQuestionWindow;

    public wrongQuestionWindow(int[] wrongQuestions) {

        this.wrongQuestionWindow = this;
        this.setLayout(new BorderLayout());
        int width = 1200, height = 800;
        this.setTitle("错题预览");
        int pX = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2;
        int pY = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2;
        this.setBounds(pX, pY, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new TitledBorder(null, "错题预览", TitledBorder.LEFT, TitledBorder.TOP, new Font("", 0, 25), Color.red));
        JTextArea textArea = new JTextArea();//错题预览文本区
        textArea.setFont(new Font("微软雅黑", 0, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        scrollPane.getViewport().add(textArea);
        this.add(scrollPane, BorderLayout.CENTER);
        //返回按钮
        JButton button = new JButton("关闭");
        button.setFont(new Font("微软雅黑", 0, 16));
        button.setBackground(Color.pink);
        this.add(button, BorderLayout.SOUTH);
        DBUtils dbUtils = new DBUtils();
        StringBuilder s = new StringBuilder();
        List<questions> list = dbUtils.executeQueryBeans("select * from questions", questions.class);
        for (int i = 0; i < wrongQuestions.length; i++) {
            if (wrongQuestions[i] == 1) {
                s.append("第" + (i + 1) + "题：" + list.get(i).toString());
            }
        }
        textArea.setText(s.toString());
        //返回主界面按钮监听
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
