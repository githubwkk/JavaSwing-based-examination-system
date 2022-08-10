package v;

import m.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mainWindow extends JFrame {
    int sumScore = 0;//考生总得分
    int fullScore = 0;//试题满分
    m.user user;
    int[] wrongQuestions;//记录用户错误的题目

    private boolean finish = false;//finish为判断考试是否结束的标志

    public void setWrongQuestions(int[] wrongQuestions) {
        this.wrongQuestions = wrongQuestions;
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }

    public void setSumScore(int sumScore) {
        this.sumScore = sumScore;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    //让考试窗口examWindow 获取到考生信息
    public user getRegister() {
        return user;
    }

    public mainWindow(user user) {
        this.user = user;
        init();
    }

    private void init() {
        this.setTitle("已成功登陆考试系统");
        int width = 1300, height = 500;
        int pX = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2;
        int pY = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2;
        this.setBounds(pX, pY, width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(236, 247, 241));
//设置欢迎语
        Date date = new Date();
        String hello = null;
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String str = df.format(date);
        int a = Integer.parseInt(str);
        if (a >= 0 && a <= 6) {
            hello = "凌晨好，时候不早了";
        }
        if (a > 6 && a <= 11) {
            hello = "上午好";
        }
        if (a > 11 && a <= 13) {
            hello = "中午好";
        }
        if (a > 13 && a <= 18) {
            hello = "下午好";
        }
        if (a > 18 && a <= 24) {
            hello = "晚上好";
        }
        JLabel welcome = new JLabel(hello + "，" + user.getName() + "，欢迎您使用考试系统！", SwingConstants.LEFT);
        welcome.setFont(new Font("宋体", Font.PLAIN, 30));

        welcome.setBorder(BorderFactory.createEmptyBorder(6, 6, 0, 0));
        panel.add(welcome, BorderLayout.NORTH);
        panel.add(createBtnPanel(), BorderLayout.CENTER);
        this.add(panel);
    }

    //创建中间菜单的按钮：开始考试、查看成绩、错题合集、退出登陆、退出系统
    private JPanel createBtnPanel() {
        mainWindow mainWindow = this;
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 100));
        panel.setBackground(new Color(236, 247, 241));
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();
        JButton b4 = new JButton();
        JButton b5 = new JButton();
        JLabel label = new JLabel("您还没有完成考试，请先完成考试！");
        Font font = new Font("微软雅黑", Font.PLAIN, 18);
        label.setFont(font);

        //111111111.“开始考试”按钮监听器的添加
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (finish) {
                    JLabel label = new JLabel("考试已经结束，无法再次考试！");
                    label.setFont(font);
                    JOptionPane.showMessageDialog(null, label, "提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    examWindow examWindow = new examWindow(mainWindow);
                    mainWindow.setVisible(false);
                    examWindow.setVisible(true);

                }
            }
        });

        //2222222222.“查看成绩”按钮监听器的添加
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!finish) {
                    JOptionPane.showMessageDialog(null, label, "提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    JLabel label = new JLabel("您的成绩是：" + sumScore + " 分,本次考试满分" + fullScore + "分。");
                    label.setFont(font);
                    JOptionPane.showMessageDialog(null, label);
                }
            }
        });

        //3333333“错题本”按钮监听器的添加
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (finish) {
                    wrongQuestionWindow wrongQuestionsWindow = new wrongQuestionWindow(mainWindow.wrongQuestions);
                    wrongQuestionsWindow.setVisible(true);
//                    mainWindow.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, label, "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        //44444444“退出登陆”按钮监听器的添加
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel("您确定要退出登陆吗？");
                label.setFont(font);
                int i = JOptionPane.showConfirmDialog(null, label, "提示", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    mainWindow.setVisible(false);
                    loginWindow loginWindow = new loginWindow();
                    loginWindow.setVisible(true);
                }
            }
        });


        //5555555555“退出系统”按钮监听器的添加
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(menuButton(b1, "开始考试", new ImageIcon("image/exam.png")));
        panel.add(menuButton(b2, "查看成绩", new ImageIcon("image/grade.png")));
        panel.add(menuButton(b3, "错题合集", new ImageIcon("image/wqb.png")));
        panel.add(menuButton(b4, "退出登陆", new ImageIcon("image/loginOut.png")));
        panel.add(menuButton(b5, "退出系统", new ImageIcon("image/close.png")));

        return panel;
    }

    //四个按钮样式的设置
    private JButton menuButton(JButton b, String name, ImageIcon icon) {
        b.setFont(new Font("黑体", Font.PLAIN, 22));
        b.setText(name);
        b.setIcon(icon);
        b.setVerticalTextPosition(b.BOTTOM);
        b.setHorizontalTextPosition(b.CENTER);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        return b;
    }
}
