package club.luosuo.community.controller;

import club.luosuo.community.mapper.QuessionMapper;
import club.luosuo.community.mapper.UserMapper;
import club.luosuo.community.model.Quession;
import club.luosuo.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    QuessionMapper quessionMapper;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model

    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title.equals("")) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if ((description == null) || (description == "")) {
            model.addAttribute("error", "问题不能为空");
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            model.addAttribute("error", "用户未登陆");
            return "publish";
        }
        Quession quession = new Quession();
        quession.setTitle(title);
        quession.setDescription(description);
        quession.setTag(tag);
        quession.setCreator(user.getId());
        quession.setGmt_create(System.currentTimeMillis());
        quession.setGmt_modified(quession.getGmt_create());
        quessionMapper.create(quession);
        return "redirect:/";
    }
}
