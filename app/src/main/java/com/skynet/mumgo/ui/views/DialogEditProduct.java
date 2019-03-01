package com.skynet.mumgo.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Product;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by thaopt on 8/28/17.
 */

public class DialogEditProduct extends Dialog {
    @BindView(R.id.imageView16)
    ImageView imageView16;
    @BindView(R.id.imgAvt)
    ImageView imgAvt;
    @BindView(R.id.tvNameProduct)
    TextView tvNameProduct;
    @BindView(R.id.textView21)
    TextView textView21;
    @BindView(R.id.btnDown)
    ImageView btnDown;
    @BindView(R.id.tvQuantity)
    TextView tvQuantity;
    @BindView(R.id.btnUp)
    ImageView btnUp;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.textView22)
    TextView textView22;
    @BindView(R.id.edtNote2)
    EditText edtNote2;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.textView24)
    TextView textView24;
    @BindView(R.id.layoutcontent)
    ConstraintLayout layoutcontent;
    @BindView(R.id.cardView2)
    CardView cardView2;
    @BindView(R.id.btnClear)
    ImageView btnClear;
    private int mType = 0;
    private int quatity = 0;
    private Context mContext;
    private DialogOneButtonClickListener mListener;
    private Product product;

    public DialogEditProduct(@NonNull Context context, Product product, DialogOneButtonClickListener listener) {
        super(context);
        this.product = product;
        this.mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_edit_product);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        this.mListener = listener;
        ButterKnife.bind(this);
        if (product.getImg() != null && !product.getImg().isEmpty()) {
            Picasso.with(context).load(product.getImg()).into(imgAvt);
        }
        tvNameProduct.setText(product.getName());
        quatity = product.getQuatity();
        tvQuantity.setText(quatity + "");
        edtNote2.setText(product.getNote());
    }


    public void setType(int type) {
        this.mType = type;
    }

    private void exceute(int i) {
        quatity += i;
        if (quatity < 0) quatity = 0;
        product.setQuatity(quatity);
        tvQuantity.setText(quatity + "");
    }

    @OnClick({R.id.btnDown, R.id.btnUp, R.id.btnUpdate, R.id.btnClear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDown:
                exceute(-1);
                break;
            case R.id.btnUp:
                exceute(1);
                break;
            case R.id.btnUpdate:
                product.setNote(edtNote2.getText().toString());
                mListener.okClick(product);
                dismiss();
                break;
            case R.id.btnClear:
                dismiss();
                break;
        }
    }

    //callback

    public interface DialogOneButtonClickListener {
        void okClick(Product product);
    }


    public void setDialogOneButtonClick(DialogOneButtonClickListener listener) {
        this.mListener = listener;
    }
}
