package m;

import c.DBUtils;

import java.util.List;

public class questions {
    String question;
    String answer;
    int score;
    long time;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String toString() {
        return "(" + score + "分) " + question + "\n答案：" + answer + '\n' + '\n' + '\n';
    }

    public static void main(String[] args) {
        String sql = "select * from questions";
        DBUtils dbUtils = new DBUtils();
        List<questions> list = dbUtils.executeQueryBeans(sql, questions.class);
        list.forEach(questions -> System.out.println(questions.toString()));
        System.out.println("\n考试时间" + list.get(0).getTime() + "分钟。");
        System.out.println("题数：" + list.toArray().length);

    }
}
