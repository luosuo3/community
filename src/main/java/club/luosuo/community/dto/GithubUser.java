package club.luosuo.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String dio;
    private String avatar_url;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", dio='" + dio + '\'' +
                '}';
    }


}
