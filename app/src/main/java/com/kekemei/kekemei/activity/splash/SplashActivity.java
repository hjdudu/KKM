package com.kekemei.kekemei.activity.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.activity.MainActivity;
import com.kekemei.kekemei.manager.PrefManager;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.SPUtils;
import com.kekemei.kekemei.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            佛祖保佑       永无BUG
*/

/**
 * 闪屏页
 */
public class SplashActivity extends Activity implements AMapLocationListener, Handler.Callback {
    private TextView txtSeconds;
    private Handler jumpHandler = new Handler(this);
    private static final int TIME_SPLASH = 3000;
    private static final int MSG_TIME_TICK = 1;
    private static final int MSG_JUMP_TASK = 2;
    private int timeTickCount = 0;
    boolean needShowGuide;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 处理安装后点击打开之后再按HOME的问题
        // http://stackoverflow.com/a/7748416
        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(intent.getAction())) {
                finish();
                return;
            }
        }
        setContentView(R.layout.activity_splash);
        normalStart();
    }

    public void initData() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            AppUtil.getUserPoint(this, this);
            Toast.makeText(this, "已开启定位权限", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean handleMessage(Message message) {
        if (message.what == MSG_TIME_TICK) {
            int last = TIME_SPLASH - timeTickCount * 1000;
            txtSeconds.setText(StringUtils.format("%d S", last / 1000));
            if (last <= 0) {
                jumpHandler.sendEmptyMessage(MSG_JUMP_TASK);
            } else {
                jumpHandler.sendEmptyMessageDelayed(MSG_TIME_TICK, 1000);
                timeTickCount++;
            }
        }
        if (message.what == MSG_JUMP_TASK) {
            jump();
        }
        return true;
    }

    private void loadAndShowImage() {
        View layoutSkip = findViewById(R.id.layoutSkip);
        layoutSkip.setVisibility(View.VISIBLE);
        txtSeconds = findViewById(R.id.txtSeconds);

        layoutSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                jumpHandler.removeMessages(MSG_TIME_TICK);
                finish();
            }
        });
    }

    private void jump() {
        Intent intent = new Intent(SplashActivity.this, needShowGuide ? GuideActivity.class : MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                double latitude = aMapLocation.getLatitude();//获取纬度
                double longitude = aMapLocation.getLongitude();//获取经度
                String city = aMapLocation.getCity();
                SPUtils.putString(getBaseContext(), "latitude", latitude + "");
                SPUtils.putString(getBaseContext(), "longitude", longitude + "");
                SPUtils.putString(getBaseContext(), "city", city);
                //                mPoint = new DPoint(latitude,longitude);
                //                loadData();//后续操作
                HttpParams commonParams = new HttpParams();
                commonParams.put("latitude", latitude);
                commonParams.put("longitude", longitude);
                OkGo.getInstance().init(getApplication()).addCommonParams(commonParams);
                LogUtil.d("APPLOCALTION  HomeFragment", "LATITUDE : " + latitude + " --  LONGITUDE : " + longitude);
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                LogUtil.d("APPLOCALTION  HomeFragment", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    //正常启动
    private void normalStart() {
        needShowGuide = PrefManager.newInstance(SplashActivity.this, PrefManager.FILE_SETTINGS).getBoolean(PrefManager.KEY_NEED_SHOW_GUIDE, true);
        if (needShowGuide) {
            jumpHandler.sendEmptyMessageDelayed(MSG_JUMP_TASK, 1000);
        } else {
            loadAndShowImage();
            jumpHandler.sendEmptyMessageDelayed(MSG_TIME_TICK, 500);
        }
    }
}
