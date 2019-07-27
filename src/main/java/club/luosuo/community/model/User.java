package club.luosuo.community.model;

public class User {
    private Integer id;
    private String name;
    private String account_id;
    private String token;
    private String gmt_create;
    private String gmt_modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return account_id;
    }

    public void setAccountId(String accountId) {
        this.account_id = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGmt_Creat() {
        return gmt_create;
    }

    public void setGmt_Creat(String gmt_Creat) {
        this.gmt_create = gmt_Creat;
    }

    public String getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modifiy(String gmt_modifiy) {
        this.gmt_modified= gmt_modifiy;
    }
}
