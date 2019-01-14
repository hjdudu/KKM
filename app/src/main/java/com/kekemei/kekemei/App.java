package com.kekemei.kekemei;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.kekemei.kekemei.init.OkHttpInit;
import com.kekemei.kekemei.manager.AppFolderManager;
import com.kekemei.kekemei.utils.AppCompatUtils;
import com.kekemei.kekemei.utils.Common;
import com.kekemei.kekemei.view.ImagePickerLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.lang.reflect.Field;

/**
 * Created by peiyangfan on 2018/10/9.
 */

public class App extends MultiDexApplication {

    public Context mContext;
    private static App instance = null;
    // 记录是否已经初始化
    private boolean isInit = false;

    {
        PlatformConfig.setWeixin(Common.WX_APP_ID, Common.WX_APP_SECRET);
        PlatformConfig.setQQZone(Common.QQ_APP_ID, Common.QQ_APP_SECRET);
//        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        AppCompatUtils.init(this);
        init();

        initImagePicker();
        // 初始化环信SDK
        initEasemob();
        initUM();
    }

    public Context getContext(){
        mContext = this;
        return mContext;
    }

    private void initUM() {
        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        try {
            Class<?> aClass = Class.forName("com.umeng.commonsdk.UMConfigure");
            Field[] fs = aClass.getDeclaredFields();
            for (Field f:fs){
                Log.e("xxxxxx","ff="+f.getName()+"   "+f.getType().getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        UMConfigure.init(this,"5bd2d515b465f57fa40000dc","Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "");


    }

    private void initImagePicker() {
        AppFolderManager.getInstance().init(this);
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImagePickerLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    private void init() {
        OkHttpInit jOkHttpInit = new OkHttpInit();
        jOkHttpInit.initOkGo(this);

        AMapLocationClient.setApiKey("8a8682f033516bb9029983628bb51899");


//
//        /**
//         * 设置组件化的Log开关
//         * 参数: boolean 默认为false，如需查看LOG设置为true
//         */
        UMConfigure.setLogEnabled(true);
    }

    /**
     * 初始化环信SDK
     */
    private void initEasemob() {

        if (EaseUI.getInstance().init(getContext(), initOptions())) {

            // 设置开启debug模式
            EMClient.getInstance().setDebugMode(true);

            // 设置初始化已经完成
            isInit = true;
        }


    }

    /**
     * SDK初始化的一些配置
     * 关于 EMOptions 可以参考官方的 API 文档
     * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
     */
    private EMOptions initOptions() {

        EMOptions options = new EMOptions();
        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
        // options.setAppKey("lzan13#hxsdkdemo");
        // 设置自动登录
        options.setAutoLogin(true);
        // 设置是否需要发送已读回执
        options.setRequireAck(true);
        // 设置是否需要发送回执，
        options.setRequireDeliveryAck(true);
        // 设置是否需要服务器收到消息确认
//        options.setRequireServerAck(true);
        // 设置是否根据服务器时间排序，默认是true
        options.setSortMessageByServerTime(false);
        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
        options.setAcceptInvitationAlways(false);
        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
        options.setAutoAcceptGroupInvitation(false);
        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
        options.setDeleteMessagesAsExitGroup(false);
        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
        options.allowChatroomOwnerLeave(true);
        // 设置google GCM推送id，国内可以不用设置
        // options.setGCMNumber(MLConstants.ML_GCM_NUMBER);
        // 设置集成小米推送的appid和appkey
        // options.setMipushConfig(MLConstants.ML_MI_APP_ID, MLConstants.ML_MI_APP_KEY);

        return options;
    }
}

