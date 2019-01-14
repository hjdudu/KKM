package com.kekemei.kekemei.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kekemei.kekemei.R;
import com.kekemei.kekemei.utils.StringUtils;

import butterknife.BindView;

/**
 * Created by peiyangfan on 2018/10/24.
 */

public class TextAndLineView extends LinearLayout {


    private CharSequence titleText;
    private int lineColor;
    private TypedArray ta;
    private float titleTxtSize;
    private float lineHeight;
    private boolean textAndLineViewSelect;
    private Button tv_title;
    private View vLine;

    public TextAndLineView(Context context) {
        this(context, null);
    }

    public TextAndLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextAndLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate = View.inflate(context, R.layout.text_line, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextAndLineView);
        titleText = ta.getString(R.styleable.TextAndLineView_TextAndLineTitleText);
        lineColor = ta.getColor(R.styleable.TextAndLineView_lineColor, 0xFF7AD2D2);
        titleTxtSize = ta.getDimension(R.styleable.TextAndLineView_TextAndLineTitleTextSize, context.getResources().getDimension(R.dimen.dimen_18sp));
        lineHeight = ta.getDimension(R.styleable.TextAndLineView_lineHeight, context.getResources().getDimension(R.dimen.dimen_16sp));
        textAndLineViewSelect = ta.getBoolean(R.styleable.TextAndLineView_TextAndLineViewSelect, false);
        ta.recycle();
        initView(context, attrs,inflate);
    }

    private void initView(Context context, AttributeSet attrs, View inflate) {
        tv_title = inflate.findViewById(R.id.tv_text);
        vLine = inflate.findViewById(R.id.v_line);
        setTitleText(titleText);
        setTitleTxtSize(titleTxtSize);
        setSelect(textAndLineViewSelect);
    }


    public void setSelect(boolean flag) {
        tv_title.setClickable(flag);
        vLine.setSelected(flag);
    }

    public CharSequence getTitleText() {
        return titleText;
    }

    public void setTitleText(CharSequence titleText) {
        this.titleText = titleText;
        tv_title.setText(titleText);
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public TypedArray getTa() {
        return ta;
    }

    public void setTa(TypedArray ta) {
        this.ta = ta;
    }

    public float getTitleTxtSize() {
        return titleTxtSize;
    }

    public void setTitleTxtSize(float titleTxtSize) {
        this.titleTxtSize = titleTxtSize;
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTxtSize);
    }

    public float getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(float lineHeight) {
        this.lineHeight = lineHeight;
    }

    public boolean isTextAndLineViewSelect() {
        return textAndLineViewSelect;
    }

    public void setTextAndLineViewSelect(boolean textAndLineViewSelect) {
        this.textAndLineViewSelect = textAndLineViewSelect;
    }

    private CharSequence checkStringAttr(CharSequence attr) {
        if (StringUtils.isNotBlank(attr)) {
            return attr;
        }
        return "";
    }
}
