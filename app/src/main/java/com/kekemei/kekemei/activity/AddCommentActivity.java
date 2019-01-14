package com.kekemei.kekemei.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kekemei.kekemei.R;
import com.kekemei.kekemei.adapter.GridImageAdapter;
import com.kekemei.kekemei.bean.CommentTagsBean;
import com.kekemei.kekemei.manager.AppFolderManager;
import com.kekemei.kekemei.utils.AppUtil;
import com.kekemei.kekemei.utils.CollectionUtils;
import com.kekemei.kekemei.utils.ImageCompressUtil;
import com.kekemei.kekemei.utils.LogUtil;
import com.kekemei.kekemei.utils.StringUtils;
import com.kekemei.kekemei.utils.ToastUtil;
import com.kekemei.kekemei.utils.URLs;
import com.kekemei.kekemei.view.NoScrollGridView;
import com.kekemei.kekemei.view.StarBar;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 评价
 */
public class AddCommentActivity extends BaseActivity {
    private static final String TAG = AddCommentActivity.class.getSimpleName();
    private static final String EXTRA_KEY_ENUM_ID = "type";
    private static final String EXTRA_KEY_ORDER_ID = "orderId";
    private static final String COMMENT_TYPE_ONE = "1";
    private static final String COMMENT_TYPE_TWO = "2";
    private static final String COMMENT_TYPE_THREE = "3";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.submitComment)
    TextView submitComment;

    @BindView(R.id.etCommentContent)
    EditText etCommentContent;

    @BindView(R.id.tvVerySatisfied)
    TextView tvVerySatisfied;
    @BindView(R.id.tvSatisfied)
    TextView tvSatisfied;
    @BindView(R.id.tvCommonly)
    TextView tvCommonly;

    @BindView(R.id.commentTagFlowLayout)
    FlexboxLayout commentTagFlowLayout;

    @BindView(R.id.commentImages)
    NoScrollGridView commentImages;

    @BindView(R.id.starTitle)
    TextView starTitle;
    @BindView(R.id.starSubTitle)
    TextView starSubTitle;
    @BindView(R.id.star_bar)
    StarBar starBar;

    private GridImageAdapter adapter;
    public static final int MAX_PIC = 4;
    public static final int REQUEST_ALBUM = 10;
    private static final int UPLOAD_IMAGE_COMPRESS_FINISH = 1000;
    private Thread compressThread;

    private List<String> commentTags;
    private String satisfaction;

    @SuppressLint("HandlerLeak")
    private Handler mHandlers = new Handler() {
        @SuppressWarnings("unchecked")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPLOAD_IMAGE_COMPRESS_FINISH:
                    ArrayList<String> imageUrls = (ArrayList<String>) msg.obj;
                    for (String imageUrl : imageUrls) {
                        uploadImage(imageUrl);//上传图片
                    }
                    break;
            }
        }
    };

    private String commonType;
    private String orderId;
    private StringBuilder tags_ids = new StringBuilder();
    private StringBuilder images = new StringBuilder();

    public static void start(Context context, String commonType, String orderId) {
        Intent intent = new Intent(context, AddCommentActivity.class);
        intent.putExtra(EXTRA_KEY_ENUM_ID, commonType);
        intent.putExtra(EXTRA_KEY_ORDER_ID, orderId);
        context.startActivity(intent);
    }

    @Override
    protected View setTitleBar() {
        return toolbar;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_comment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        commonType = super.getStringExtraSecure(EXTRA_KEY_ENUM_ID);
        orderId = super.getStringExtraSecure(EXTRA_KEY_ORDER_ID);
        toolbar.setNavigationIcon(R.mipmap.back);
        tv_title.setText("评价");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (commonType.equals(COMMENT_TYPE_ONE)) {
            starTitle.setText("店铺");
            starSubTitle.setText("服务评价");
        } else if (commonType.equals(COMMENT_TYPE_TWO)) {
            starTitle.setText("美容师");
            starSubTitle.setText("服务技术");
        } else if (commonType.equals(COMMENT_TYPE_THREE)) {
            starTitle.setText("项目");
            starSubTitle.setText("体验评价");
        }

        commentTags = new ArrayList<>();
        adapter = new GridImageAdapter(this);
        commentImages.setAdapter(adapter);
        commentImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == adapter.infos.size()) {
                    openPhotoPicker();
                } else {
                    List<Object> imgUrls = adapter.getItems();
                    openLargeImage(imgUrls, position);
                }
            }
        });
    }

    /**
     * 打开大图
     *
     * @param imgUrls
     * @param position
     */
    private void openLargeImage(List<Object> imgUrls, int position) {
        if (CollectionUtils.isNotEmpty(imgUrls)) {
            ArrayList<String> imageList = new ArrayList<>();
            for (Object imgUrl : imgUrls) {
                imageList.add((String) imgUrl);
            }
            LargeImageActivity.start(AddCommentActivity.this, imageList, position);
        }
    }

    /**
     * 打开图片选择器
     */
    private void openPhotoPicker() {
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, REQUEST_ALBUM);
    }

    @Override
    protected void initData() {
        super.initData();
        OkGo.<String>post(URLs.COMMENT_TAG)
                .tag(this)
                .params("type", commonType)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("comment", "body:" + response.body());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            Object msg = jsonObject.opt("msg");
                            String data = jsonObject.optString("data");
                            if (msg.equals("暂无数据") || StringUtils.isEmpty(data)) {
                                fillTags(null);
                                return;
                            }
                            Gson gson = new Gson();
                            List<CommentTagsBean> commentTagsBean = gson.fromJson(data, new TypeToken<List<CommentTagsBean>>() {
                            }.getType());
                            fillTags(commentTagsBean);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        LogUtil.e("TAG", response.message());
                        fillTags(null);
                    }
                });
    }

    /**
     * 填充评价标识
     *
     * @param result
     */
    private void fillTags(final List<CommentTagsBean> result) {
        if (CollectionUtils.isEmpty(result)) {
            return;
        }
        commentTagFlowLayout.removeAllViews();
        for (final CommentTagsBean resultBean : result) {
            TextView txt = (TextView) LayoutInflater.from(this).inflate(R.layout.item_comment_tag_layout, commentTagFlowLayout, false);
            if (!AppUtil.isEmptyString(resultBean.getName())) {
                txt.setText(resultBean.getName());
                txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tagId = resultBean.getId() + "";
                        if (!commentTags.contains(tagId)) {
                            commentTags.add(tagId);
                        } else {
                            commentTags.remove(tagId);
                        }
                        if (v.isSelected()) {
                            v.setSelected(false);
                        } else {
                            v.setSelected(true);
                        }
                    }
                });
                commentTagFlowLayout.addView(txt);
            }
        }
    }

    /**
     * 提交评价
     */
    private void submitComment() {
        String content = etCommentContent.getText().toString();
        if (StringUtils.isEmpty(content)) {
            content = "该用户暂无评价";
        }
        if (CollectionUtils.isEmpty(adapter.getItems())) {
            ToastUtil.showToastMsg(this, "请上传图片！");
            return;
        }
        for (int i = 0; i < commentTags.size(); i++) {
            if (i != commentTags.size() - 1)
                tags_ids = tags_ids.append(commentTags.get(i) + ",");
            else
                tags_ids = tags_ids.append(commentTags.get(i));
        }
        for (int i = 0; i < adapter.getItems().size(); i++) {
            if (i != adapter.getItems().size() - 1)
                images = images.append(adapter.getItems().get(i) + ",");
            else
                images = images.append(adapter.getItems().get(i));
        }
        OkGo.<String>post(URLs.ADD_COMMENT).tag(this).params("comment_type", commonType)
                .params("order_id", orderId).params("tags_ids", tags_ids.toString())
                .params("content", content).params("start", (int) starBar.getStarMark())
                .params("satisfaction", satisfaction).params("images", images.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("comment", "body:" + response.body());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            Object msg = jsonObject.opt("msg");
                            if (msg.equals("暂无数据")) {
                                ToastUtil.showToastMsg(AddCommentActivity.this, "评论失败");
                                return;
                            }
                            ToastUtil.showToastMsg(AddCommentActivity.this, msg.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        LogUtil.e("TAG", response.message());
                        ToastUtil.showToastMsg(AddCommentActivity.this, "评论失败");
                    }
                });
    }

    @OnClick({R.id.submitComment, R.id.tvVerySatisfied, R.id.tvSatisfied, R.id.tvCommonly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitComment:
                submitComment();
                break;
            case R.id.tvVerySatisfied:
                if (tvVerySatisfied.isSelected()) {
                    tvVerySatisfied.setSelected(false);
                    satisfaction = "";
                } else {
                    tvVerySatisfied.setSelected(true);
                    tvSatisfied.setSelected(false);
                    tvCommonly.setSelected(false);
//                    satisfaction = tvVerySatisfied.getText().toString();
                    satisfaction = "3";
                }
                break;
            case R.id.tvSatisfied:
                if (tvSatisfied.isSelected()) {
                    tvSatisfied.setSelected(false);
                    satisfaction = "";
                } else {
                    tvSatisfied.setSelected(true);
                    tvVerySatisfied.setSelected(false);
                    tvCommonly.setSelected(false);
//                    satisfaction = tvSatisfied.getText().toString();
                    satisfaction = "2";
                }
                break;
            case R.id.tvCommonly:
                if (tvCommonly.isSelected()) {
                    tvCommonly.setSelected(false);
                    satisfaction = "";
                } else {
                    tvCommonly.setSelected(true);
                    tvSatisfied.setSelected(false);
                    tvVerySatisfied.setSelected(false);
//                    satisfaction = tvCommonly.getText().toString();
                    satisfaction = "1";
                }
                break;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == REQUEST_ALBUM) {
                ArrayList<ImageItem> images = data.getParcelableArrayListExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (CollectionUtils.isNotEmpty(images)) {
                    compressImage(images);
                }
            }
        }
    }

    /**
     * 压缩图片
     *
     * @param resultImagePath
     */
    private void compressImage(final ArrayList<ImageItem> resultImagePath) {
        compressThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ExecutorService taskExecutor = Executors.newFixedThreadPool(resultImagePath.size());
                Map<String, String> map = new HashMap<>();
                final Map<String, String> synchronizedMap = Collections.synchronizedMap(map);
                final long timeStamp = System.currentTimeMillis();
                for (final ImageItem originalImageClone : resultImagePath) {
                    taskExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.d(TAG, "url is " + originalImageClone.path + ", thread id is " + Thread.currentThread().getId() + ",mimeType:" + originalImageClone.mimeType);
                            String compressedFileDirectory = AppFolderManager.getInstance().getImageCompressFolder();
                            String[] split = originalImageClone.mimeType.split("/");
                            LogUtil.d(TAG, "split mimeType:" + split[1]);
                            //避免因文件名相同导致的图片重复的问题
                            String compressedFileName = timeStamp + "_" + resultImagePath.indexOf(originalImageClone) + "." + split[1];
                            File compressedFile = new File(compressedFileDirectory, compressedFileName);
                            boolean compressResult = ImageCompressUtil.handle(originalImageClone.path, compressedFile.getPath());
                            LogUtil.d(TAG, "compress image result is " + compressResult);
                            if (compressResult) {
                                synchronizedMap.put(originalImageClone.path, compressedFile.getPath());
                            }
                        }
                    });
                }
                taskExecutor.shutdown();
                LogUtil.d(TAG, "taskExecutor shutdown");
                try {
                    taskExecutor.awaitTermination(4, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    LogUtil.e(TAG, "interrupted", e);
                }
                LogUtil.d(TAG, "complete " + synchronizedMap.size());

                if (synchronizedMap.size() == 0) {
                    ToastUtil.showToastMsg(AddCommentActivity.this, "图片处理出现错误，请稍后再试");
                    return;
                }

                ArrayList<String> uploadImages = new ArrayList<>();
                for (ImageItem originalImageClone : resultImagePath) {
                    if (synchronizedMap.containsKey(originalImageClone.path)) {
                        uploadImages.add(synchronizedMap.get(originalImageClone.path));
                    }
                }
                Message message = new Message();
                message.obj = uploadImages;
                message.what = UPLOAD_IMAGE_COMPRESS_FINISH;
                mHandlers.sendMessage(message);
            }
        });
        compressThread.start();
    }

    /**
     * 上传图片
     *
     * @param uploadImagePath 选择的图片路径
     */
    public void uploadImage(String uploadImagePath) {
        ToastUtil.showToastMsg(this, "正在上传图片");
        File file = new File(uploadImagePath);
        OkGo.<String>post(URLs.UPLOAD_IMAGE).tag(this).params("file", file).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtil.e("comment", "body:" + response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Object msg = jsonObject.opt("msg");
                    if (msg.equals("暂无数据")) {
                        return;
                    }
                    JSONObject data = jsonObject.optJSONObject("data");
                    if (data != null) {
                        ToastUtil.showToastMsg(AddCommentActivity.this, "上传成功");
                        String url = data.optString("url");
                        adapter.addItem(url);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                LogUtil.e("TAG", response.message());
            }
        });
    }
}
