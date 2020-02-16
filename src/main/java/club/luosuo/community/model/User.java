package club.luosuo.community.model;

import lombok.Data;

/**
 * @author wzlx7
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String account_id;
    private String token;
    private String gmt_create;
    private String gmt_modified;
    private String avatar_url;
}


