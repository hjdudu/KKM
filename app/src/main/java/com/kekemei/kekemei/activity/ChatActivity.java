package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.bean.WaiterBean;

public class ChatActivity extends EaseBaseActivity {
    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    private static final String CHAT_PARAM = "param";
    private WaiterBean waiterBean;

    public static void start(Context context, WaiterBean waiterBean) {
        Intent intent = new Intent(context, ChatActivity.class);
        // EaseUI封装的聊天界面需要这两个参数，聊天者的username，以及聊天类型，单聊还是群聊
        intent.putExtra(CHAT_PARAM, waiterBean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        activityInstance = this;
        //user or group id
        waiterBean = (WaiterBean) getIntent().getSerializableExtra(CHAT_PARAM);
        chatFragment = new EaseChatFragment();
        //set arguments
        Bundle bundle = new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID, waiterBean.getId());
        bundle.putString(EaseConstant.EXTRA_NICK_NAME, waiterBean.getNickname());
        chatFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.ec_layout_container, chatFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // enter to chat activity when click notification bar, here make sure only one chat activiy
        WaiterBean chatBean = (WaiterBean) intent.getSerializableExtra(CHAT_PARAM);
        if (waiterBean == chatBean)
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }
}
