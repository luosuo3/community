package club.luosuo.community.controller;

import club.luosuo.community.dto.AcesstTokenDTO;
import club.luosuo.community.dto.GithubUser;
import club.luosuo.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {

        AcesstTokenDTO acesstTokenDTO = new AcesstTokenDTO();
        acesstTokenDTO.setClient_id(clientId);
        acesstTokenDTO.setClient_secret(clientSecret);
        acesstTokenDTO.setCode(code);
        acesstTokenDTO.setRedirect_uri(redirectUri);
        acesstTokenDTO.setState(state);
        String acessToken = githubProvider.getAcessToken(acesstTokenDTO);
        GithubUser user = githubProvider.getUser(acessToken);
        System.out.println(user.getName());

        return "index";
    }
}
