package club.luosuo.community.mapper;

import club.luosuo.community.dto.QuessionDTO;
import club.luosuo.community.model.Quession;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuessionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag)  values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Quession quession);

    @Select("select * from question")
    List<QuessionDTO> list();
}
