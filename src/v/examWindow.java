package v;

import c.DBUtils;
import m.questions;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class examWindow extends JFrame {

    mainWindow mainWindow;
    private JLabel examTime;
    private JTextArea examArea = new JTextArea();
    private JLabel questionCount;
    private JButton prev;
    private JButton next;
    private Option options[] = new Option[4];
    DBUtils dbUtils = new DBUtils();
    public static int tiShu = 20;//题数,默认为20道题
    List<questions> allList = dbUtils.executeQueryBeans("select * from questions", questions.class);//全部题目列表
    List<questions> list = createRandoms(allList, tiShu);//随机后题目列表
    int[][] saveOption = new int[list.toArray().length][4];//保存用户当前选择的选项的数组
    String[] allOption = new String[list.toArray().length];//保存用户提交的答案时选项的数组
    int[] wrongQuestions = new int[list.toArray().length];//保存用户错误的题目

    int sumScore = 0;//总分
    int index = 0;//记录当前题目的索引

    public examWindow(mainWindow mainWindow) {
        timeDown();//调用倒计时方法
        examArea.setText("(" + list.get(0).getScore() + "分)" + list.get(0).getQuestion());//设置第一道题目
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        int width = 900, height = 600;
        this.setTitle("正在考试");
        int pX = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2;
        int pY = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2;
        this.setBounds(pX, pY, width, height);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.add(mainPanel());
    }
    //生成随机题目的方法
    private List<questions> createRandoms(List<questions> list, int n) {
        Map<Integer, String> map = new HashMap();
        List<questions> news = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random = (int) (Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    news.add(list.get(random));
                }
            }
            return news;
        }
    }

    //主面板
    private JPanel mainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 10, 10, 10));
        panel.add(examInfoPanel(), BorderLayout.NORTH);
        panel.add(examAreaPanel(), BorderLayout.CENTER);
        panel.add(examBottom(), BorderLayout.SOUTH);
        return panel;
    }

    //窗口下部分面版，包含按钮，选项，倒计时，题数信息(索引)
    private JPanel examBottom() {
        JPanel panel = new JPanel(new BorderLayout());
        questionCount = new JLabel("题目：" + (index + 1) + "/" + list.toArray().length);
        questionCount.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        examTime = new JLabel("");
        examTime.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        panel.add(examBtnPanel(), BorderLayout.SOUTH);
        panel.add(examOptionsPanel(), BorderLayout.CENTER);
        panel.add(questionCount, BorderLayout.WEST);
        panel.add(examTime, BorderLayout.EAST);
        return panel;
    }

    //考试信息面板
    private JPanel examInfoPanel() {
        JPanel panel = new JPanel();
        JLabel examInfo = new JLabel("考生姓名：" + mainWindow.getRegister().getName() + "      考生账号：" + mainWindow.getRegister().getId() + "      考试科目：Java" + "      考试时间：" + list.get(0).getTime() + " 分钟", SwingConstants.LEFT);
        examInfo.setFont(new Font("黑体", Font.PLAIN, 17));
        panel.add(examInfo);
        return panel;
    }

    //考试题目面板
    private JScrollPane examAreaPanel() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new TitledBorder(null, "考试题目", TitledBorder.LEFT, TitledBorder.TOP, new Font("", Font.PLAIN, 22), Color.magenta));
        examArea.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        examArea.setLineWrap(true);
        examArea.setWrapStyleWord(true);
        examArea.setEditable(false);
        scrollPane.getViewport().add(examArea);
        return scrollPane;
    }

    //选项面板--多选框
    private JPanel examOptionsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        Option a = new Option("A", "A");
        Option b = new Option("B", "B");
        Option c = new Option("C", "C");
        Option d = new Option("D", "D");
        //以下皆为单选框勾选时更新用户的选项
        a.addActionListener(e -> {
            if (a.isSelected()) {
                updateAnswer();
                saveOption();
            }
        });
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateAnswer();
                saveOption();
            }
        });
        c.addActionListener(e -> {
            if (c.isSelected()) {
                updateAnswer();
                saveOption();
            }
        });
        d.addActionListener(e -> {
            if (d.isSelected()) {
                updateAnswer();
                saveOption();
            }
        });
        options[0] = a;
        options[1] = b;
        options[2] = c;
        options[3] = d;
        panel.add(a);
        panel.add(b);
        panel.add(c);
        panel.add(d);
        return panel;
    }

    //定义一个多选框的子类
    static class Option extends JCheckBox {
        String value;

        public Option(String value, String text) {
            super(text);
            this.value = value;
        }
    }

    //保存用户选项的数组，以便于用于点击上一题或下一题时记录用户的选项
    private void saveOption() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) {
                saveOption[index][i] = 1;
            } else {
                saveOption[index][i] = 0;
            }
        }
    }

    //更新用户 选项值 并存入数组allOption中
    public void updateAnswer() {
        allOption[index] = getOptionValues();
    }

    //获取-当前题目-用户选项的值
    public String getOptionValues() {
        StringBuilder answer = new StringBuilder();
        for (Option option : options) {
            if (option.isSelected()) {
                answer.append(option.value);
            }
        }
        return String.valueOf(answer);
    }

    //统计分数(将用户选项数组allOption和数据库中答案列，逐一比对）
    public int getScore() {
        int score = 0;
        for (int i = 0; i < list.toArray().length; i++) {
            if (Objects.equals(allOption[i], list.get(i).getAnswer())) {
                score = score + list.get(i).getScore();
            }
            //记录错题
            if (!Objects.equals(allOption[i], list.get(i).getAnswer())) {
                wrongQuestions[i] = 1;
            }
        }
        return score;
    }

    //按钮面板
    private JPanel examBtnPanel() {
        examWindow examwindow = this;
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        prev = new JButton("上一题");
        prev.setFont(new Font("", Font.BOLD, 16));
        next = new JButton("下一题");
        next.setFont(new Font("", Font.BOLD, 16));
        JButton submit = new JButton("交卷");
        submit.setFont(new Font("", Font.BOLD, 16));
        panel.add(prev);
        panel.add(next);
        panel.add(submit);

        updateBtn(index);

        //上一题按钮的监听器的添加
        prev.addActionListener(e -> {

            examArea.setText("(" + list.get(index - 1).getScore() + "分)" + list.get(--index).getQuestion());
            updateBtn(index);
            questionCount.setText("题目：" + (index + 1) + "/" + list.toArray().length);//设置题目索引
            //设置用户选项的数组，以便于用于点击上一题或下一题时记录用户的选项
            for (int i = 0; i < 4; i++) {
                options[i].setSelected(saveOption[index][i] == 1);
            }

        });

        //下一题按钮的监听器的添加
        next.addActionListener(e -> {
            examArea.setText("(" + list.get(index + 1).getScore() + "分)" + list.get(++index).getQuestion());
            updateBtn(index);
            questionCount.setText("题目：" + (index + 1) + "/" + list.toArray().length);
            //设置用户选项的数组，以便于用于点击上一题或下一题时记录用户的选项
            for (int i = 0; i < 4; i++) {
                options[i].setSelected(saveOption[index][i] == 1);
            }


        });
        //提交试卷按钮的监听器的添加
        submit.addActionListener(e -> {
            JLabel label = new JLabel("正在退出系统,请到查看分数界面查看您的考试得分。");
            label.setFont(new Font("", Font.PLAIN, 17));
            JOptionPane.showMessageDialog(examwindow, label, "提示", JOptionPane.INFORMATION_MESSAGE);
            submit();
            examwindow.dispose(); //关闭窗口
            mainWindow.setVisible(true);
        });
        return panel;
    }

    //更新按钮的状态的方法,在第一题时，上一题按钮不可用，在最后一题时，下一题按钮不可用
    private void updateBtn(int index) {
        prev.setEnabled(index != 0);
        next.setEnabled(index != (list.toArray().length - 1));
    }

    //考试倒计时功能
    private void timeDown() {
        new Thread(new Runnable() {
            // 考试倒计时需要从数据库的表（单位为分）中获取考试时间，在此类中单位为秒
            //设置时间（仅能在数据表中第一组数据中获取）
//            long time = 5;//测试用
            long time = (allList.get(0).getTime()) * 60;

            public void run() {
                while (true) {
                    long hour = time / 3600;
                    long minute = (time - hour * 3600) / 60;
                    long seconds = time - hour * 3600 - minute * 60;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    examTime.setText(hour + " 时 " + minute + " 分 " + seconds + " 秒 ");
                    if (time < 60) {
                        examTime.setForeground(Color.red);
                    }
                    if (time == 0) {
                        JLabel label = new JLabel("考试时间到，请按确认键结束考试！");
                        label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
                        JOptionPane.showMessageDialog(null, label, "提示", JOptionPane.WARNING_MESSAGE);
                        submit();
                        dispose();//交卷事件到，关闭窗口释放资源
                        break;
                    }
                    time--;
                }
            }
        }).start();
    }

    //提交试卷的方法
    private void submit() {
        sumScore = getScore();//考生总得分
        int fullScore = 0;
        for (m.questions questions : list) {
            fullScore = fullScore + questions.getScore();
        }
        mainWindow.setFullScore(fullScore);//设置全部题目的分数——满分
        mainWindow.setFinish(true);//设置主界面的finish为true，表示已经完成了考试
        mainWindow.setSumScore(sumScore);//设置主界面的sumScore为用户的得分
        mainWindow.setWrongQuestions(wrongQuestions);//设置主界面的wrongQuestions为用户的错题
        //将考生的考试成绩存入数据库
        try {
            dbUtils.executeUpdate("insert into grades(name,grade)values('" + mainWindow.getRegister().getName() + "','" + sumScore + "')");
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("提交数据库失败");
            JOptionPane.showMessageDialog(null, "数据库中已存在当前用户的数据", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        boolean finish = true;
        mainWindow.setFinish(finish);//设置主窗口的finish值为true，表示考试结束已提交
        mainWindow.setVisible(true);
    }
}
