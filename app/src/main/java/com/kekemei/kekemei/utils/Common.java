package com.kekemei.kekemei.utils;

/**
 * Created by peiyangfan on 2018/10/16.
 */

public class Common {


    /***
     * 曾雪峰:
     微信支付帐号信息
     APPID     = 'wxe4c320e2f157904d';
     const MCHID     = '1507961931';
     const KEY       = 'Wjxscc4qgtrP7Kl1Fea0nrCghGX6fv9G';
     const APPSECRET = 'be658f877a34d543d51a612daf2aead8';
     const APPID                 //公众号中“开发者中心”看到的AppID
     const MCHID                     //微信支付商户资料审核成功邮件中的商户号
     const KEY                   //你在商户平台中设置的支付key
     const APPSECRET             //公众号中“开发者中心”看到的AppSecret
     ---------------------
     曾雪峰:
     支付宝帐号 appid = '2018061860415526';

     曾雪峰:
     支付宝帐号 appid = '2018060860352001';

     */
    public static final int ACTIVITY_REQUEST_CODE_ALI_PAY = 1024 + 1;
    public static final int ACTIVITY_REQUEST_CODE_WX_PAY = ACTIVITY_REQUEST_CODE_ALI_PAY + 1;


    public static final String ALI_APP_ID = "2018060860352001";
    public static final String RSA_PRIVATE ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxK2gaWPIxD0ljXfwnJEHl2qMHocTnogew7Tbeiu70ENlfnQyoySYfugPmDSzyTWAF1GAvg8YOqNs6z2Ww8iZ3zI5AxtgX0zX9YIoZP098qhuKmKjTjkb1EAXgl4bds7cqGiZcsm7br8L4CgPgeVaSDK/WIWDE4wTCcOPG96HG4rVNAvdB6FqO0p0Qz7ZpzapEmmNUPyRbKG3XCCLtJAjAC7lH6a4Mhl8in2BChN8OnRa87OaFP/N0KSOZylGuurf/ZaszyXR47ILNfEyCyX6xFlTgyIus5CdR0uTnKmcrCmF3t468sa16HmJct+Xt1Ump2S3db14T8xVwf1npvymIQIDAQAB";
    //五金商城测试号
//        public static final String WX_APP_ID = "wxaee1c5e6124fe6c1";
//        public static final String WX_APP_SECRET = "8378cf232253a7d612384a7b8bbfca0b";
//    public static final String WX_APP_ID = "wxd48a326938a576da";
//    public static final String WX_APP_SECRET = "2da388329e861b68405b622c494e5210";
    //微信平台的正式账号
//    public static final String WX_APP_ID =  "wx5c1c391ac956b658";
//    public static final String WX_APP_SECRET = "Wjxscc4qgtrP7Kl1Fea0nrCghGX6fv9G";
    public static final String WX_APP_ID =  "wx4181c60d8bf82265";
    public static final String WX_APP_SECRET = "83de27a184426812dc054fa7e1b486d0";
    //腾讯平台相关账号
    public static final String QQ_APP_ID = "1106153887";
    public static final String QQ_APP_SECRET = "eY3OUj77erW5r23J";

    public static final String ERROR_CODE = "errCode";

    public static final String WX_PAY_RESULT = "WX_PAY_RESULT";
}
