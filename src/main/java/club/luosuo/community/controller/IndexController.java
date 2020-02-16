package club.luosuo.community.controller;

import club.luosuo.community.dto.QuessionDTO;
import club.luosuo.community.mapper.QuessionMapper;
import club.luosuo.community.mapper.UserMapper;
import club.luosuo.community.model.Quession;
import club.luosuo.community.model.User;
import club.luosuo.community.service.QusetionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wzlx7
 */
@Controller
public class IndexController
{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    QusetionService qusetionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user =userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        List<QuessionDTO> quessions=qusetionService.list();
        model.addAttribute("questions", quessions);
        return "index";
    }
}
