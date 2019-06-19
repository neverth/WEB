package entity;

public class ShopUser {
    private int userid;
    private String username;
    private String password;

    public ShopUser() {

    }
    public ShopUser(int userId, String username) {
        this.userid = userId;
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }

}
