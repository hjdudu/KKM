package com.kekemei.kekemei.utils;

/**
 * Created by peiyangfan on 2018/10/9.
 */

public class URLs {

    public static final String BASE_URL = "http://39.106.100.26";
    //    public static final String BASE_URL = "http://kekemei.ecooth.com";

    /**
     * 支付
     */
    //支付宝支付
    public static final String ORDER_ALI_PAY = BASE_URL + "/api/order/zfbapppays";
    //微信支付
    public static final String ORDER_WX_PAY = BASE_URL + "/api/order/wxappzf";
    //检查所有支付是否成功，并修改订单状态
    public static final String ORDER_REFUND = BASE_URL + "/api/order/apprefund";
    //生成订单
    public static final String ORDER_GENERATING = BASE_URL + "/api/order/generatingOrder";
    //订单搜索
    public static final String ORDER_SEARCH = BASE_URL + "/api/order/orderSearch";
    //订单详情
    public static final String ORDER_DETAILS = BASE_URL + "/api/order/details";
    //根据id查订单
    public static final String ORDER_BY_ID = BASE_URL + "/api/order/orderbyid";
    //购买会员卡
    public static final String BUY_MEMBER_CAR = BASE_URL +"/api/order/buyMemberCar";
    /**
     * 评论
     */
    //评论发布
    public static final String ADD_COMMENT = BASE_URL + "/api/comment/addcomment";
    //评论标签
    public static final String COMMENT_TAG = BASE_URL + "/api/comment/commenttag";
    //图片上传
    public static final String UPLOAD_IMAGE = BASE_URL + "/api/common/upload";
    //评论列表
    public static final String COMMENT_LIST = BASE_URL + "/api/comment/commentlist";


    /**
     * 我的
     */
    //确认订单
    public static final String ORDER_CONFIRM = BASE_URL + "/api/order/confirm";
    //会员卡
    public static final String MEMBER_CAR = BASE_URL + "/api/user/membercar";
    //删除订单
    public static final String DEL_ORDER = BASE_URL + "/api/order/delorder";
    //完成订单
    public static final String OVER_ORDER = BASE_URL + "/api/order/over";
    //接受订单
    public static final String ACCEPC_ORDER = BASE_URL + "/api/order/accepc";
    //取消订单
    public static final String CANCEL_ORDER = BASE_URL + "/api/order/cancelOrder";
    //领取新人卡
    public static final String ADD_NEW_PEOPLE = BASE_URL + "/api/user/addNewpople";
    //重置密码
    public static final String RESET_PASSWORD = BASE_URL + "/api/user/resetpwd";
    //我的首页
    public static final String MY_INFO = BASE_URL + "/api/user/myinfo";
    //我的红包
    public static final String MY_RED_ENVELOPES = BASE_URL + "/api/user/myRedenvelopes";
    //我的订单
    public static final String MY_ORDER = BASE_URL + "/api/order/myorder";
    //服务订单
    public static final String SERVICE_ORDER = BASE_URL + "/api/order/serviceOrder";
    //我的优惠券
    public static final String MY_COUPON = BASE_URL + "/api/user/mycoupon";
    //添加收藏
    public static final String ADD_COLLECTION = BASE_URL + "/api/user/addColleciton";
    //领取优惠券
    public static final String COUPON_RECEIVE = BASE_URL + "/api/coupon/receive";
    //领红包
    public static final String RED_ENVELOPES_RECEIVE = BASE_URL + "/api/Redenvelopes/robred";
    //为你推荐
    public static final String FOR_YOU = BASE_URL + "/api/project/foryou";
    //我关注的美容师
    public static final String MY_BEAUTICIAN = BASE_URL + "/api/user/myBeautician";
    //我的收藏
    public static final String MY_COLLECTION = BASE_URL + "/api/user/mycollection";
    //发布投诉
    public static final String ADD_COMPLAINT = BASE_URL + "/api/complaint/addcomplaint";
    //关于我们
    public static final String ABOUT_LIST = BASE_URL + "/api/abous/abousList";
    //修改个人资料
    public static final String EDIT_USER_INFO = BASE_URL + "/api/user/editUserinfo";
    //爱好列表
    public static final String HOBBY_LIST = BASE_URL + "/api/user/hobby";
    //皮肤列表
    public static final String SKIN_LIST = BASE_URL + "/api/user/skin";
    /**
     * 首页
     */

    //消息列表
    public static final String NOTICE_LIST = BASE_URL + "/api/notice/noticelist";
    //首页
    public static final String INDEX = BASE_URL + "/api/index";
    //发现
    public static final String DISCOVE = BASE_URL + "/api/shop/discove";
    //分类列表
    public static final String PROJECT_CATEGORY = BASE_URL + "/api/project/projectcategory";
    //新人专区，新人体验
    public static final String PROJECT_NEW_PEOPLE = BASE_URL + "/api/project/newpeople";
    //会员专区
    public static final String NEW_MEMBER_PEOPLE = BASE_URL + "/api/project/memberpeople";
    //红包一健领取
    public static final String COUPON_ONE_RECEIVE = BASE_URL + "/api/coupon/onereceive";

    /**
     * 美容师
     */
    //美容师主页
    public static final String BEAUTICIAN_DETAILS = BASE_URL + "/api/beautician/details";
    //关注
    public static final String FOLLOW_BEAUTICIAN = BASE_URL + "/api/beautician/follow";
    //附近的美容师
    public static final String BEAUTICIAN_NEAR = BASE_URL + "/api/beautician/nearbyBeautician";
    //预约美容师店铺
    public static final String ADD_APPOINTMENT = BASE_URL + "/api/appointment/addAppointment";
    //美容师搜索
    public static final String BEAUTICIAN_SEARCH = BASE_URL + "/api/beautician/search";
    //预约时间
    public static final String APPOINTMENT_TIME_DATA = BASE_URL + "/api/appointment/timedata";
    //通过店铺查找美容师
    public static final String SHOP_BEAUTICIAN = BASE_URL + "/api/shop/shopbeautician";
    /**
     * 搜索
     */
    //热搜&amp;搜索历史
    public static final String HOT_SEARCH = BASE_URL + "/api/index/hotsearch";
    //首页搜索
    public static final String INDEX_SEARCH = BASE_URL + "/api/index/search";

    /**
     * 店铺
     */
    //附近的店铺
    public static final String SHOP_NEAR = BASE_URL + "/api/shop/nearbyShop";
    //店铺主页
    public static final String SHOP_DETAILS = BASE_URL + "/api/shop/details";
    //座标搜索店铺并按距离排序
    public static final String COORDINATE_SHOP = BASE_URL + "/api/shop/coordinateshop";
    //通过美容师查店铺
    public static final String BEAUTICIAN_SHOP = BASE_URL + "/api/beautician/beauticianshop";


    /**
     * 登录注册
     */

    //第三方绑定用户
    public static final String USER_BINDING = BASE_URL + "/api/user/binding";
    //第三方登陆
    public static final String APP_THIRD = BASE_URL + "/api/user/appThird";
    //检测验证码
    public static final String SMS_CHECK = BASE_URL + "/api/sms/check";
    //手机验证码登陆
    public static final String MOBILE_LOGIN = BASE_URL + "/api/user/mobilelogin";
    //手机号注册
    public static final String USER_REGISTER = BASE_URL + "/api/user/register";
    //退出登陆
    public static final String USER_LOGIN_OUT = BASE_URL + "/api/user/loginout";
    //发送验证码
    public static final String SEND = BASE_URL + "/api/sms/send";
    //修改个人信息
    public static final String USER_PROFILE = BASE_URL + "/api/user/profile";


    /**
     * 项目
     */

    //项目详情
    public static final String PROJECT_DETAILS = BASE_URL + "/api/project/details";
    //秒杀列表
    public static final String SECOND_LIST = BASE_URL + "/api/project/secondlist";
    //秒杀
    public static final String SECOND = BASE_URL + "/api/project/second";
    //项目列表
    public static final String PROJECT_LIST = BASE_URL + "/api/project/projectList";
    //分类搜索
    public static final String PROJECT_SEATCH = BASE_URL + "/api/project/search";
    //按收藏数排序
    public static final String PROJECT_SORT_COLLECTION = BASE_URL + "/api/project/sortCollection";
    //按评论数排序
    public static final String PROJECT_SORT = BASE_URL + "/api/project/sortproject";
    //秒杀加抢购接口状态数据
    public static final String PROJECT_SECOND = BASE_URL + "/api/project/second";
    //按订单数排序
    public static final String PROJECT_SORT_ORDER = BASE_URL + "/api/project/sortorder";


    //分享url
    //项目
    public static final String SHARE_PROJECT_URL = BASE_URL + "/mob/project/details?id=";
    //美容师
    public static final String SHARE_BEA_URL = BASE_URL + "/mob/beautician/details?id=";
    //店铺
    public static final String SHARE_SHOP_URL = BASE_URL + "/mob/shop/details?id=";
}
