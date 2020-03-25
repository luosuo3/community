package club.luosuo.community.mapper;

import club.luosuo.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag)  values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{Id} limit #{offset},#{size}")
    List<Question> listByUserId(Integer Id, Integer offset, Integer size);

    @Select("select count(1)  from question where creator = #{Id}")
    Integer countByUserId(@Param("Id")  Integer Id);

    @Select("select * from question where id = #{Id}")
    Question getById(@Param("Id") Integer id);

    @Update("update question set title = #{title} , description = #{description} , gmt_modified = #{gmt_modified}, tag=#{tag} where id = #{id}")
    void update(Question question);
}
