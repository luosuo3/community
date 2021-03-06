package club.luosuo.community.controller;

import club.luosuo.community.dto.AcesstTokenDTO;
import club.luosuo.community.dto.GithubUser;
import club.luosuo.community.model.User;
import club.luosuo.community.provider.GithubProvider;
import club.luosuo.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * @author wzlx7
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Autowired
    UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        AcesstTokenDTO acesstTokenDTO = new AcesstTokenDTO();
        acesstTokenDTO.setClient_id(clientId);
        acesstTokenDTO.setClient_secret(clientSecret);
        acesstTokenDTO.setCode(code);
        acesstTokenDTO.setRedirect_uri(redirectUri);
        acesstTokenDTO.setState(state);
        String acessToken = githubProvider.getAcessToken(acesstTokenDTO);
        GithubUser githubUser = githubProvider.getUser(acessToken);

        if (githubUser != null && githubUser.getId() != null) {
//            登陆成功
            User user = new User();
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmt_create(String.valueOf(new Date()));
            user.setGmt_modified(user.getGmt_create());
            user.setAvatar_url(githubUser.getAvatar_url());
            userService.createOrUpdate(user);
            request.getSession().setAttribute("user", user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            //登录失败重新登录
            return "redirect:/";
        }

    }
@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
