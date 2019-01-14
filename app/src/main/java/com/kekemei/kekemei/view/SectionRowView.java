package com.kekemei.kekemei.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kekemei.kekemei.R;
import com.kekemei.kekemei.utils.StringUtils;

/**
 * 自定义行view
 */
@SuppressWarnings("unused")
public class SectionRowView extends LinearLayout {
    private Context mContext;
    private ImageView rowIcon;
    private TextView tvTitle;
    private float titleTxtSize;
    private TextView tvSubTitle;
    private TextView tvContent;
//    private ImageView rightArrow;

    private CharSequence titleTxt;
    private CharSequence subTitleTxt;
    private CharSequence contentTxt;
    private int contentTxtColor;
    private float iconRightMargin;
    private boolean hideRightArrow;
    private int iconDrawable;

    public SectionRowView(Context context) {
        this(context, null);
    }

    public SectionRowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectionRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(context);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SectionRowView);
        titleTxt = ta.getString(R.styleable.SectionRowView_titleTxt);
        titleTxtSize = ta.getDimension(R.styleable.SectionRowView_titleTxtSize, mContext.getResources().getDimension(R.dimen.dimen_18sp));
        subTitleTxt = ta.getString(R.styleable.SectionRowView_subTitleTxt);
        contentTxt = ta.getString(R.styleable.SectionRowView_contentTxt);
        iconDrawable = ta.getResourceId(R.styleable.SectionRowView_iconDrawable, View.NO_ID);
        hideRightArrow = ta.getBoolean(R.styleable.SectionRowView_hideRightArrow, false);
        contentTxtColor = ta.getColor(R.styleable.SectionRowView_contentTxtColor, 0xFF848484);
        iconRightMargin = ta.getDimension(R.styleable.SectionRowView_iconRightMargin, dip2px(mContext, 5));
        ta.recycle();

        initView();
    }

    private void init(Context context) {
        View.inflate(context, R.layout.section_row_view, this);
        rowIcon = (ImageView) this.findViewById(R.id.rowIcon);
        tvTitle = (TextView) this.findViewById(R.id.tvTitle);
        tvSubTitle = (TextView) this.findViewById(R.id.tvSubTitle);
        tvContent = (TextView) this.findViewById(R.id.tvContent);
//        rightArrow = (ImageView) this.findViewById(R.id.rightArrow);
    }

    private void initView() {
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTxtSize);
        setTitleTxt(checkStringAttr(titleTxt));
        setSubTitleTxt(checkStringAttr(subTitleTxt));
        setContentTxt(checkStringAttr(contentTxt));
        if (iconDrawable != NO_ID) {
            rowIcon.setVisibility(View.VISIBLE);
            rowIcon.setImageResource(iconDrawable);
        } else {
            rowIcon.setVisibility(View.GONE);
        }
        LayoutParams layoutParams = (LayoutParams) rowIcon.getLayoutParams();
        if (layoutParams!=null){
            layoutParams.setMargins(0, 0, (int) iconRightMargin, 0);
            rowIcon.setLayoutParams(layoutParams);
        }
//        rightArrow.setVisibility(hideRightArrow ? View.GONE : View.VISIBLE);
    }

    private CharSequence checkStringAttr(CharSequence attr) {
        if (StringUtils.isNotBlank(attr)) {
            return attr;
        }
        return "";
    }

    public CharSequence getTitleTxt() {
        return titleTxt;
    }

    public void setTitleTxt(CharSequence titleTxt) {
        this.titleTxt = titleTxt;
        tvTitle.setText(titleTxt);
    }

    public CharSequence getSubTitleTxt() {
        return subTitleTxt;
    }

    public void setSubTitleTxt(CharSequence subTitleTxt) {
        this.subTitleTxt = subTitleTxt;
        tvSubTitle.setText(subTitleTxt);
    }

    public CharSequence getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(CharSequence contentTxt) {
        this.contentTxt = contentTxt;
        tvContent.setText(contentTxt);
        tvContent.setTextColor(contentTxtColor);
    }

    public void setPriceContent(CharSequence priceContent) {
        if (StringUtils.isNotBlank(priceContent)) {
            this.contentTxt = getResources().getString(R.string.price_rmb_space_format, priceContent);
            tvContent.setText(contentTxt);
        }
    }

    public void setTitleDrawableRight(int drawableId) {
        tvTitle.setCompoundDrawablePadding(dip2px(mContext, 4));
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableId, 0);
    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
