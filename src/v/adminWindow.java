package v;

import c.*;
import m.grades;
import m.questions;
import m.user;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class adminWindow extends JFrame {
    //题库的三个输入框
    public JTextField SCORE_TEXT;
    public JTextArea QUESTION_TEXT;
    public JTextField ANSWER_TEXT;
    public JTextArea QBArea;
    //创建学生界面三个输入框
    public JTextField inputPassWord;
    public JTextField inputID;
    public JTextField inputName;
    DBUtils dbUtils = new DBUtils();
    public JTable table1;//学生信息表
    public String[] col1 = {"账号", "密码", "姓名"};
    public Object[][] tableData1;
    public DefaultTableModel model1;
    public JTable table2;//成绩信息表
    public String[] col2 = {"日期", "姓名", "分数"};
    public DefaultTableModel model2;
    public Object[][] tableData2;
    public JTextField newTime;
    public JTextField newNum;
    public JLabel oldTime;
    public JLabel oldNum;
    public JComboBox gradeSort;
    public JComboBox date;
    public ImageIcon tick = new ImageIcon("image/tick.png");
    public Font font16 = new Font("微软雅黑", Font.PLAIN, 16);
    JTabbedPane jTabbedPane;
    int width = 1200, height = 800;
    public adminWindow() {
        init();
    }

    private void init() {

        int pX = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2;
        int pY = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2;
        this.setBounds(pX, pY, width, height);
        this.setVisible(true);
        //设置窗口监听器：关闭窗口，打开登陆界面
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                loginWindow loginWindow = new loginWindow();
                loginWindow.setVisible(true);
            }
        });
        jTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        jTabbedPane.setFont(new Font("宋体", Font.PLAIN, 20));
        jTabbedPane.add("添加题目", add());
        jTabbedPane.add("查看题库", view());
        jTabbedPane.add("学生管理", studentManagement());
        jTabbedPane.add("成绩管理", scoreQuery());
        jTabbedPane.add("考试管理", examSet());
        this.add(jTabbedPane);
        this.validate();
    }

    //添加题目选项卡
    public JPanel add() {
        JPanel Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        Font font20 = new Font("微软雅黑", Font.PLAIN, 20);
        JLabel title = new JLabel("添加题目：");
        title.setFont(font20);
        QUESTION_TEXT = new JTextArea();
        QUESTION_TEXT.setBackground(new Color(236, 247, 241));
        QUESTION_TEXT.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        QUESTION_TEXT.setLineWrap(true);
        QUESTION_TEXT.setColumns(50);
        QUESTION_TEXT.setRows(10);
        QUESTION_TEXT.setEditable(true);
        JPanel down = new JPanel(new GridLayout(1, 5, 15, 20));
        JLabel answer = new JLabel("问题答案：");
        answer.setFont(font20);
        ANSWER_TEXT = new JTextField(5);
        ANSWER_TEXT.setFont(font20);
        JLabel score = new JLabel("题目分数：");
        score.setFont(font20);
        SCORE_TEXT = new JTextField(6);
        SCORE_TEXT.setFont(font20);
        JButton submit = new JButton("添加");
        submit.setFont(font20);
        submit.setBackground(new Color(190,229,252));
        JButton clear = new JButton("清空");
        clear.setFont(font20);
        clear.setBackground(Color.LIGHT_GRAY);
        adminSubmitListen ASL = new adminSubmitListen(this);//提交按钮监听器的添加
        submit.addActionListener(ASL);
        adminClearAllTextListener ACAL = new adminClearAllTextListener(this);//清空按钮监听器的添加
        clear.addActionListener(ACAL);
        Panel.add(title);
        Panel.add(QUESTION_TEXT);
        down.add(answer);
        down.add(ANSWER_TEXT);
        down.add(score);
        down.add(SCORE_TEXT);
        down.add(submit);
        down.add(clear);
        Panel.add(down);
        return Panel;
    }

    //查看题库选项卡
    private JScrollPane view() {
        JScrollPane scrollPane = new JScrollPane();
        QBArea = new JTextArea();
        QBArea.setMargin(new Insets(5, 15, 5, 0));
        QBArea.setFont(new Font("微软雅黑", 0, 18));
        QBArea.setLineWrap(true);
        QBArea.setWrapStyleWord(true);
        QBArea.setEditable(false);
        scrollPane.getViewport().add(QBArea);
        viewQuestionListener VQL = new viewQuestionListener(this); //为查看题库选项卡添加监听
        jTabbedPane.addChangeListener(VQL);
        return scrollPane;
    }

    //学生管理选项卡
    private JPanel studentManagement() {
        DBUtils dbUtils = new DBUtils();
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new TitledBorder(null, "学生管理", TitledBorder.LEFT, TitledBorder.TOP, new Font("", 0, 22), Color.blue));
        JScrollPane scrollpane = new JScrollPane();//数据显示区域
        panel.add(scrollpane, BorderLayout.CENTER);

        JPanel tool = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        JButton bAdd = new JButton("添加");
        JButton bDelete = new JButton("删除");
        JButton bModify = new JButton("修改");
        bAdd.setFont(font16);
        bDelete.setFont(font16);
        bModify.setFont(font16);
        bAdd.setBackground(new Color(190,229,252));
        bDelete.setBackground(new Color(255,214,225));
        bModify.setBackground(new Color(139,165,131));
        JLabel l1 = new JLabel("账号");
        JLabel l2 = new JLabel("用户名：");
        JLabel l3 = new JLabel("密码：");
        l1.setFont(font16);
        l2.setFont(font16);
        l3.setFont(font16);
        inputPassWord = new JTextField(12);//创建三个输入框
        inputID = new JTextField(10);
        inputName = new JTextField(10);
        inputName.setPreferredSize(new Dimension(100, 30));
        inputPassWord.setPreferredSize(new Dimension(100, 30));
        inputID.setPreferredSize(new Dimension(100, 30));
        model1 = new DefaultTableModel();//创建表格模型
        model1.setDataVector(renewTable(), col1);//调用更新表格方法
        table1 = new JTable(model1) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollpane.setViewportView(table1);
        table1.setFont(font16);
        table1.setRowHeight(30);
        table1.setBackground(Color.white);
        tool.add(l1);
        tool.add(inputID);
        tool.add(l2);
        tool.add(inputName);
        tool.add(l3);
        tool.add(inputPassWord);
        tool.add(bAdd);
        tool.add(bDelete);
        tool.add(bModify);
        //“添加”按钮监听器的添加
        addUserListener AUL = new addUserListener(this);
        bAdd.addActionListener(AUL);
        //“删除”按钮监听器的添加
        deleteUserListener DUL = new deleteUserListener(this);
        bDelete.addActionListener(DUL);
        //“修改”按钮监听器的添加
        modifyUser MU = new modifyUser(this);
        bModify.addActionListener(MU);

        panel.add(tool, BorderLayout.SOUTH);//下面面板
        return panel;
    }

    //更新表数据的方法
    public Object[][] renewTable() {
        List<user> list = dbUtils.executeQueryBeans("select * from user order by id asc", user.class);
        tableData1 = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            tableData1[i][0] = list.get(i).getId();
            tableData1[i][1] = list.get(i).getPassword();
            tableData1[i][2] = list.get(i).getName();
        }
        return tableData1;
    }

    //分数查询选项卡
    private JPanel scoreQuery() {
        JPanel panel = new JPanel();//主面板
        JScrollPane scrollpane = new JScrollPane();//中间面板
        panel.setLayout(new BorderLayout());
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));//上面面板
        panel1.setLayout(new FlowLayout());
        Font font = new Font("微软雅黑", Font.PLAIN, 18);//设置字体
        JLabel label1 = new JLabel("日期：");
        label1.setFont(font);
        JLabel label2 = new JLabel("查询排序：");
        label2.setFont(font);
        date = new JComboBox();    //创建JComboBox
        date.addItem("");
        DBUtils dbUtils = new DBUtils();
        List<grades> data = dbUtils.executeQueryBeans("select distinct date from grades ORDER BY date DESC", grades.class);//查询数据库去除重复的日期降序添加到下拉列表组件里
        for (grades da : data) {
            date.addItem(da.getDate());
        }
        //初始化表格————所有数据(按照日期降序排列)
        List<grades> grades = dbUtils.executeQueryBeans("select * from grades ORDER BY date DESC", grades.class);
        tableData2 = updateTable(grades);
        model2 = new DefaultTableModel(tableData2, col2);//创建表格模型
        table2 = new JTable(model2) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollpane.setViewportView(table2);

        table2.setFont(font16);
        table2.setRowHeight(30);
        table2.setBackground(Color.white);
        gradeSort = new JComboBox();
        gradeSort.addItem("按分数降序");
        gradeSort.addItem("按分数升序");
        date.setFont(font16);
        gradeSort.setFont(font16);
        panel1.add(label1);
        panel1.add(date);
        panel1.add(label2);
        panel1.add(gradeSort);

        //"日期"下拉组件监听器的添加
        panel.add(scrollpane, BorderLayout.CENTER);
        dateListener DL = new dateListener(this);
        date.addItemListener(DL);
        //"排序"下拉组件监听器的添加
        gradeSortListener GSL = new gradeSortListener(this);
        gradeSort.addActionListener(GSL);
        panel.add(panel1, BorderLayout.NORTH);
        return panel;

    }

    //更新表格数据方法，返回表格数据
    public Object[][] updateTable(List<grades> list) {
        Object[][] tableData = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            tableData[i][0] = list.get(i).getDate();
            tableData[i][1] = list.get(i).getName();
            tableData[i][2] = list.get(i).getGrade();
        }
        return tableData;
    }


    //考试管理选项卡
    private JPanel examSet() {
        JPanel panel = new JPanel(new BorderLayout());
        Font font30 = new Font("微软雅黑", Font.PLAIN, 25);
        JPanel panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCenter.setBorder(new TitledBorder(null, "当前考试信息", TitledBorder.LEFT, TitledBorder.TOP, new Font("", 0, 25),new Color(204, 151, 240)));
        JPanel panelNorth = new JPanel(new BorderLayout(5,15));
        panelNorth.setBorder(new EmptyBorder(5, 0, 20, 0));
        JLabel label1 = new JLabel("考试时间：");
        label1.setFont(font30);
        JLabel label2 = new JLabel("分       ");
        label2.setFont(font30);
        JLabel label3 = new JLabel("分钟          ");
        label3.setFont(font30);
        JLabel label4=new JLabel("考试题数：");
        label4.setFont(font30);
        JLabel label5=new JLabel("道题");
        label5.setFont(font30);
        newTime = new JTextField();
        newTime.setFont(font30);
        newTime.setPreferredSize(new Dimension(220, 45));
        newNum=new JTextField();
        newNum.setFont(font30);
        newNum.setPreferredSize(new Dimension(220, 45));

        JButton set = new JButton("更新");
        set.setBackground(new Color(23, 107, 169));
        set.setForeground(new Color(255, 255, 255));
        set.setPreferredSize(new Dimension(90, 40));
        set.setFont(new Font("微软雅黑", Font.PLAIN, 23));

        //当前考试时间标签
        oldTime = new JLabel();
        oldTime.setText(renewTime());
        oldTime.setFont(new Font("Times New Roman", Font.PLAIN, 150));
        oldNum=new JLabel();
        oldNum.setText(String.valueOf(examWindow.tiShu));
        oldNum.setFont(new Font("Times New Roman", Font.PLAIN, 150));


        examSetListener ES = new examSetListener(this);
        set.addActionListener(ES);
        panelCenter.add(oldTime);
        panelCenter.add(label3);
        panelCenter.add(oldNum);
        panelCenter.add(label5);
        JPanel n1=new JPanel(new FlowLayout(FlowLayout.LEFT));
        n1.add(label1);
        n1.add(newTime);
        n1.add(label2);
        n1.add(set);
        JPanel n2=new JPanel(new FlowLayout(FlowLayout.LEFT));
        n2.add(label4);
        n2.add(newNum);
        panelNorth.add(n1,BorderLayout.NORTH);
        panelNorth.add(n2,BorderLayout.CENTER);
        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(panelCenter, BorderLayout.CENTER);
        return panel;
    }

    //获取考试时间的方法
    public String renewTime() {
        DBUtils dbUtils = new DBUtils();
        questions questions = dbUtils.executeQueryBean("select * from questions", m.questions.class);
        return String.valueOf(questions.getTime());
    }

    public static void main(String[] args) {
        adminWindow adminWindow=new adminWindow();
        adminWindow.setVisible(true);
    }
}
