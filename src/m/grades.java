package m;

import c.DBUtils;

import java.util.List;

public class grades {
    String name;
    int grade;
    String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static void main(String[] args) {
        DBUtils dbUtils = new DBUtils();
        List<grades> list = dbUtils.executeQueryBeans("select * from grades where id =", grades.class);
        System.out.println(list.get(0).getDate().toString());
    }
}
