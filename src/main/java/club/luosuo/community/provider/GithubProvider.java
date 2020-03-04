package club.luosuo.community.provider;

import club.luosuo.community.dto.AcesstTokenDTO;
import club.luosuo.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wzlx7
 */
@Component
public class GithubProvider {
    public String getAcessToken(AcesstTokenDTO acesstTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, com.alibaba.fastjson.JSON.toJSONString(acesstTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {

            assert response.body() != null;
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String string = response.body().string();
            GithubUser githubUser = com.alibaba.fastjson.JSON.parseObject(string, GithubUser.class);

            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
