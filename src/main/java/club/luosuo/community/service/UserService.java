package club.luosuo.community.service;

import club.luosuo.community.mapper.UserMapper;
import club.luosuo.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbuser = userMapper.findByAccountId(user.getAccount_id());
        if (dbuser == null) {
//            插入
            user.setGmt_create(String.valueOf(new Date()));
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);


        } else {
          dbuser.setGmt_modified(String.valueOf(new Date()));
            dbuser.setAvatar_url(user.getAvatar_url());
            dbuser.setToken(user.getToken());
            dbuser.setName(user.getName());
            userMapper.update(user);
        }
    }


}
