package com.example.springvue.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Description: 发送短信的工具类
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 20:20
 */
public class SmsUtil {
    /**
     * 短信签名名称
     */
    private final static String SIGNNAME = "lucky商城";
    /**
     * 短信模板ID
     */
    private final static String TEMPLATEMODE = "SMS_174029278";
    private final static String ACCESSKEYID = "LTAI4FhohufGvsPcYERYemor";
    private final static String ACCESSSECRET = "JckyqLkFAEPnb5demQFdcaAdMcYynO";

    /**
     * @param verificationCode
     * @param phone
     * @Description: 发送短信
     */
    public static void sendSms(String verificationCode, String phone) {

        DefaultProfile profile = DefaultProfile.getProfile("default", ACCESSKEYID, ACCESSSECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "default");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", SIGNNAME);
        request.putQueryParameter("TemplateCode", TEMPLATEMODE);
        request.putQueryParameter("TemplateParam", "{\"code\":" + "\"" + verificationCode + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 生成验证码
     * @return: 返回验证码
     */
    public static String setVerificationCode() {
        String code = "";
        int len = 6;
        for (int i = 0; i < len; i++) {
            code += (int) (Math.random() * (10));
        }
        return code;
    }
}
