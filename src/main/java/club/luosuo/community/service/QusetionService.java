package club.luosuo.community.service;

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

    public List<QuessionDTO> list() {
        List<QuessionDTO> quessions = quessionMapper.list();
        List<Quession> quessionlist = new ArrayList<>();
        for (QuessionDTO quession : quessions) {
            User user = userMapper.findByID(quession.getCreator());
            QuessionDTO quessionDTO = new QuessionDTO();
            BeanUtils.copyProperties(quession, quessionDTO);
            quessionDTO.setUser(user);

        }

        return null;
    }
}
