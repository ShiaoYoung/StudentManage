public class User {
    private String userName;    //用户名
    private String password;    //密码
    private String phoneNum;    //手机号
    private String ID;          //身份证号

    public User() {
    }

    public User(String userName, String password, String phoneNum, String ID) {
        this.userName = userName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}