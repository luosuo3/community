package club.luosuo.community.mapper;

import club.luosuo.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author wzlx7
 */

public interface UserMapper {
    /**
     * @param user
     */
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insert(User user);

    /**
     * @param token
     * @return
     */
    @Select("select * from user where token =#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where token =#{id}")
    User findByID(@Param("id") Integer id);

}