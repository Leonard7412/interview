package com.harper.interview.passwordTest;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.passwordTest
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-12-12  11:02
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
public class PasswordTest {
    @Resource
    private StringEncryptor encryptor;

    @Test
    void password() {
        // 你的邮箱密码
        String password = "uncxpndhlmygbddh";
        // 加密后的密码(注意：配置上去的时候需要加 ENC(加密密码))
        String encryptPassword = encryptor.encrypt(password);
        String decryptPassword = encryptor.decrypt(encryptPassword);

        System.out.println("password = " + password);
        System.out.println("encryptPassword = " + encryptPassword);
        System.out.println("decryptPassword = " + decryptPassword);
    }
}
