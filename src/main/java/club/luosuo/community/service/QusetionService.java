package club.luosuo.community.service;

import club.luosuo.community.dto.PaginationDTO;
import club.luosuo.community.dto.QuessionDTO;
import club.luosuo.community.mapper.QuessionMapper;
import club.luosuo.community.mapper.UserMapper;
import club.luosuo.community.model.Quession;
import club.luosuo.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QusetionService {
    @Autowired
    QuessionMapper quessionMapper;
    @Autowired
    UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
//        size*(page-1)
        Integer offset = size * (page - 1);
        List<Quession> quessions = quessionMapper.list(offset, size);
        List<QuessionDTO> quessionDTOlist = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Quession quession : quessions) {
            User user = userMapper.findByID(quession.getCreator());
            QuessionDTO quessionDTO = new QuessionDTO();
            BeanUtils.copyProperties(quession, quessionDTO);
            quessionDTO.setUser(user);
            quessionDTOlist.add(quessionDTO);


        }
         paginationDTO.setQuestions(quessionDTOlist );
        Integer totalCount = quessionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
    }
}
