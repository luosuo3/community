package club.luosuo.community.service;

import club.luosuo.community.dto.PaginationDTO;
import club.luosuo.community.dto.QuestionDTO;
import club.luosuo.community.mapper.QuestionMapper;
import club.luosuo.community.mapper.UserMapper;
import club.luosuo.community.model.Question;
import club.luosuo.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
//        size*(page-1)
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> quessionDTOlist = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO quessionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, quessionDTO);
            quessionDTO.setUser(user);
            quessionDTOlist.add(quessionDTO);


        }
         paginationDTO.setQuestions(quessionDTOlist );
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
    }

    public PaginationDTO list(Integer Id, Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(Id,offset, size);
        List<QuestionDTO> quessionDTOlist = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO quessionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, quessionDTO);
            quessionDTO.setUser(user);
            quessionDTOlist.add(quessionDTO);


        }
        paginationDTO.setQuestions(quessionDTOlist );
        Integer totalCount = questionMapper.countByUserId(Id);
        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
    }

    public QuestionDTO getById( Integer id) {
        Question question = questionMapper.getById(id);
        User user = userMapper.findByID(question.getCreator());
        QuestionDTO quessionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, quessionDTO);
        quessionDTO.setUser(user);
        return quessionDTO;

    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            questionMapper.create(question);
        } else {
            question.setGmt_create(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
