package com.harper.interview.config;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.config
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-30  16:25
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class AliyunSmsClientFactory {
    @Value("${smsConf.smsId}")
    private String accessKeyId;

    @Value("${smsConf.smsSecret}")
    private String accessKeySecret;

    public  AsyncClient createSmsClient() {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(accessKeyId)
                .accessKeySecret(accessKeySecret)
                .build());
        return AsyncClient.builder()
                .region("cn-hangzhou")
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                ).build();
    }

    public SendSmsRequest getSendSmsRequest(String phone, String templateParam) {
        return SendSmsRequest.builder()
                // 签名
                .signName("HarperSend")
                // 手机号
                .phoneNumbers(phone)
                // 模板
                .templateCode("SMS_464060946")
                .templateParam(templateParam)
                .build();
    }
}
