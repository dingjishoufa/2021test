public class Member {
    //姓名 卡号 密码 积分 开卡日期
    private String name;
    private int cardId;
    private String password;
    private int score;
    private String registDate;


    public Member(){

    }

    public Member(String name, int cardId, String password, int score, String registDate) {
        this.name = name;
        this.cardId = cardId;
        this.password = password;
        this.score = score;
        this.registDate = registDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }
}
