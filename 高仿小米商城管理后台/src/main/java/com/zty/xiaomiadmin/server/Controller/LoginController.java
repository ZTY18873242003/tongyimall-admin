package com.zty.xiaomiadmin.server.Controller;

import com.zty.xiaomiadmin.server.Entity.User.User;
import com.zty.xiaomiadmin.server.Service.Login.LoginServiceImp;
import com.zty.xiaomiadmin.server.Vo.Login.LoginData;
import com.zty.xiaomiadmin.server.Vo.Login.LoginResult;
import com.zty.xiaomiadmin.server.utils.RSAUtils;
import com.zty.xiaomiadmin.server.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("Login")
public class LoginController {

    @Autowired
    private LoginServiceImp loginServiceImp;

    Map<String, String> keyMap = RSAUtils.createKeys(512);
//    String  publicKey = keyMap.get("publicKey");
//    String  privateKey = keyMap.get("privateKey");
//    private String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIS-9P6aiWK-mNIJxz0R_XXIbHM21pS_ZncpQcE8T5Mu6P6sJfoi5Cw6S4jcSqxNbRs5FSUc59GAkU1Ek9onrz8CAwEAAQ";
    private String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAhL70_pqJYr6Y0gnHPRH9dchsczbWlL9mdylBwTxPky7o_qwl-iLkLDpLiNxKrE1tGzkVJRzn0YCRTUST2ievPwIDAQABAkAviOHaOiRwQ6gdrnq2F7cXcknDiCOW6GMc0J1HQP2Nyn52HKtc9E9Ailm-eQesTw6P-qTsvHMyfuVYz8fLhfkhAiEAvhvoG2Dz6qSN6IHeuPboJP7YHK9zTt6M_NGCJKAenasCIQCywU7eStITcHFTRDjjShH0CfO-vR6q-zgWQPjtNI_YvQIgY8UNiODW4bu-ZdlSY8vghRnhnRxO7YNKhpwFKueFacUCIQCZ-P65ZVNs4ScMZTBnP48VWVk2S-rVto0KRi7lC6h4NQIgEa_GhBO6U2p6G5cGAR25ZiQVH9dFnrMVyPxY7P7-WsA";

    @PostMapping("/login")
    public LoginResult Login(@RequestBody User adminUser) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        User user = loginServiceImp.getUserByUserName(adminUser.getUsername());
        LoginResult result = new LoginResult();
        LoginData loginData = new LoginData();

        if (user == null) {
            result.setMsg("无此用户，请重新输入正确用户名");
            result.setStatus(2008);
        } else {
            if (RSAUtils.privateDecrypt(user.getPwd(), RSAUtils.getPrivateKey(privateKey)).equals(adminUser.getPwd())){
                result.setStatus(0);
                String token = TokenUtil.token(user.getId().trim());
                loginData.setToken(token);
                result.setMsg("登录成功");
                result.setStatus(1010);
                result.setData(loginData);
            }else{
                result.setStatus(2007);
                result.setMsg("密码错误");
            }
        }
        return result;
    }
}
