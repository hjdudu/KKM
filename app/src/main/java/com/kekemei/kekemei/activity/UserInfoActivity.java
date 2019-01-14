package com.kekemei.kekemei.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcloud.image_loader_module.ImageLoaderUtil;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.UserPropertyBean;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.CustomDatePicker;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.utils.UserHelp;
import com.kekemei.kekemei.view.CommonDialog;
import com.kekemei.kekemei.view.ImagePickerLoader;
import com.kekemei.kekemei.view.SectionRowView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户中心
 */
public class UserInfoActivity extends BaseActivity {
    private static final String EXTRA_KEY_USER_ID = "userId";
    public static final int REQUEST_ALBUM = 10;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.icon)
    ImageView icon;

    @BindView(R.id.txtNick)
    SectionRowView txtNick;
    @BindView(R.id.txtSex)
    SectionRowView txtSex;
    @BindView(R.id.txtBirth)
    SectionRowView txtBirth;
    @BindView(R.id.txtSkin)
    SectionRowView txtSkin;
    @BindView(R.id.txtHobby)
    SectionRowView txtHobby;

    private String userId;

    private CustomDatePicker startTimePicker;
    private List<UserPropertyBean.DataBean> sexList, hobbyList, skinList;
    private int sexId, skinId, hobbyId = -1;

    public static void start(Context context, String userId) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra(EXTRA_KEY_USER_ID, userId);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        userId = super.getStringExtraSecure(EXTRA_KEY_USER_ID);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setBackgroundColor(Color.parseColor("#00000000"));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("个人资料修改");
        tv_submit.setVisibility(View.VISIBLE);
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImagePickerLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素

        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + UserHelp.getAvatar(this), icon);
        if (StringUtils.isNotEmpty(UserHelp.getNickName(this))) {
            txtNick.setContentTxt(UserHelp.getNickName(this));
        } else {
            txtNick.setContentTxt("未知");
        }
        txtSex.setContentTxt("未知");
        txtBirth.setContentTxt("未知");
        txtSkin.setContentTxt("未知");
        txtHobby.setContentTxt("未知");

        sexList = new ArrayList<>();
        UserPropertyBean.DataBean womanBean = new UserPropertyBean.DataBean();
        womanBean.setId(0);
        womanBean.setName("女");
        UserPropertyBean.DataBean manBean = new UserPropertyBean.DataBean();
        manBean.setId(1);
        manBean.setName("男");
        sexList.add(womanBean);
        sexList.add(manBean);
        initDatePicker();
    }

    /*
     *初始化时间选择器
     */
    protected void initDatePicker() {
        startTimePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                String[] split = time.split("-");
                String[] split1 = split[2].split(" ");
                String birthDay = split[0] + "年" + split[1] + "月" + split1[0] + "日";
                txtBirth.setContentTxt(birthDay);
                ToastUtil.showToastMsg(UserInfoActivity.this, birthDay);
            }
        }, "1950-01-01 00:00", "2050-01-01 00:00", "请设置开始时间"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        startTimePicker.showSpecificTime(false); // 显示时和分
        startTimePicker.setIsLoop(true); // 允许循环滚动
    }

    @Override
    protected void initData() {
        super.initData();
        OkGo.<String>post(URLs.HOBBY_LIST).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                UserPropertyBean userPropertyBean = gson.fromJson(response.body(), UserPropertyBean.class);
                hobbyList = userPropertyBean.getData();
            }
        });
        OkGo.<String>post(URLs.SKIN_LIST).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                UserPropertyBean userPropertyBean = gson.fromJson(response.body(), UserPropertyBean.class);
                skinList = userPropertyBean.getData();
            }
        });
    }

    @OnClick({R.id.txtSex, R.id.txtBirth, R.id.txtSkin, R.id.txtHobby, R.id.icon, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtSex:
                CommonDialog commonSexDialog = new CommonDialog(this, sexList);
                commonSexDialog.setOnItemClick(new CommonDialog.CommonItemClickListener() {
                    @Override
                    public void onClick(UserPropertyBean.DataBean item) {
                        sexId = item.getId();
                        txtSex.setContentTxt(item.getName());
                    }
                });
                commonSexDialog.show();
                break;
            case R.id.txtBirth:
                startTimePicker.show(AppUtil.getFormatTime(System.currentTimeMillis()));
                break;
            case R.id.txtSkin:
                CommonDialog commonSkinDialog = new CommonDialog(this, skinList);
                commonSkinDialog.setOnItemClick(new CommonDialog.CommonItemClickListener() {
                    @Override
                    public void onClick(UserPropertyBean.DataBean item) {
                        skinId = item.getId();
                        txtSkin.setContentTxt(item.getName());
                    }
                });
                commonSkinDialog.show();
                break;
            case R.id.txtHobby:
                CommonDialog commonHobbyDialog = new CommonDialog(this, hobbyList);
                commonHobbyDialog.setOnItemClick(new CommonDialog.CommonItemClickListener() {
                    @Override
                    public void onClick(UserPropertyBean.DataBean item) {
                        hobbyId = item.getId();
                        txtHobby.setContentTxt(item.getName());
                    }
                });
                commonHobbyDialog.show();
                break;
            case R.id.icon:
                openPhotoPicker();
                break;
            case R.id.tv_submit:
                saveUserInfo();
                break;
        }
    }

    /**
     * 打开图片选择器
     */
    private void openPhotoPicker() {
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, REQUEST_ALBUM);
    }

    private void saveUserInfo() {
        if (sexId == -1) {
            ToastUtil.showToastMsg(UserInfoActivity.this, "请选择性别！");
            return;
        }
        if (StringUtils.isBlank(txtBirth.getContentTxt().toString())) {
            ToastUtil.showToastMsg(UserInfoActivity.this, "请选择生日！");
            return;
        }
        if (skinId == -1) {
            ToastUtil.showToastMsg(UserInfoActivity.this, "请选择皮肤！");
            return;
        }
        if (hobbyId == -1) {
            ToastUtil.showToastMsg(UserInfoActivity.this, "请选择爱好！");
            return;
        }
        OkGo.<String>post(URLs.EDIT_USER_INFO).params("user_id", userId).params("gender", sexId)
                .params("birthday", txtBirth.getContentTxt().toString()).params("hobby_ids", hobbyId)
                .params("skin_ids", skinId).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    if (msg.equals("暂无数据")) {
                        ToastUtil.showToastMsg(UserInfoActivity.this, "提交失败");
                        return;
                    }
                    ToastUtil.showToastMsg(UserInfoActivity.this, msg.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == REQUEST_ALBUM) {
                ArrayList<ImageItem> images = data.getParcelableArrayListExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (CollectionUtils.isNotEmpty(images)) {
                    uploadImage(images);
                }
            }
        }
    }

    public void uploadImage(ArrayList<ImageItem> uploadImagePath) {
        ToastUtil.showToastMsg(this, "正在上传图片");
        File file = new File(uploadImagePath.get(0).path);
        OkGo.<String>post(URLs.UPLOAD_IMAGE).tag(this).params("file", file).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    if (msg.equals("暂无数据")) {
                        ToastUtil.showToastMsg(UserInfoActivity.this, "提交失败");
                        return;
                    }
                    JSONObject data = jsonObject.optJSONObject("data");
                    if (data != null) {
                        String url = data.optString("url");
                        ImageLoaderUtil.getInstance().loadImage(URLs.BASE_URL + url, icon);
                        UserHelp.setAvatar(getBaseContext(),url);
                    }
                    ToastUtil.showToastMsg(UserInfoActivity.this, msg.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                LogUtil.e("TAG", response.message());
                ToastUtil.showToastMsg(UserInfoActivity.this, "提交失败");
            }
        });
    }
}
