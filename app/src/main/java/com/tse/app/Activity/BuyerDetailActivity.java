package com.tse.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tse.app.R;

public class BuyerDetailActivity extends AppCompatActivity {

    private BottomSheetBehavior sheetBehavior;
    LinearLayout linearLayout;
    ImageView close;
    TextView edExpectedQty, edExpectedRate, edRemark;

    Button btnBuyerOffer;
    private  Intent intent;
    TextView TxtOrderDeatailCode, TxtOrderDetailProduct, TxtOrderDetailCategory, TxtOrderDetailSubCategory, TxtOrderDetailCity,
            TxtOrderDetailArea, TxtOrderDetailDiemension, TxtOrderDetailGSM, TxtOrderDetailColour, TxtOrderDetailRemark,
            TxtOrderDetailQuality, TxtOrderDetailPaymentTerms ,TxtOrderDetailStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_detail);

        findViewBy_Id();
        intent = getIntent();
        TxtOrderDeatailCode.setText(intent.getStringExtra("ordercode"));
        TxtOrderDetailProduct.setText(intent.getStringExtra("product"));
        TxtOrderDetailCategory.setText(intent.getStringExtra("category"));
        TxtOrderDetailSubCategory.setText(intent.getStringExtra("subcategory"));

        TxtOrderDetailArea.setText(intent.getStringExtra("area1"));
        TxtOrderDetailDiemension.setText(intent.getStringExtra("Dimension"));
        TxtOrderDetailGSM.setText(intent.getStringExtra("Gsm"));
        TxtOrderDetailColour.setText(intent.getStringExtra("Colour"));
        TxtOrderDetailRemark.setText(intent.getStringExtra("Remark"));
        TxtOrderDetailQuality.setText(intent.getStringExtra("Qualitiy"));
        TxtOrderDetailPaymentTerms.setText(intent.getStringExtra("Paymentterms"));

        sheetBehavior = BottomSheetBehavior.from(linearLayout);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                close = bottomSheet.findViewById(R.id.img_close);
                edExpectedQty = bottomSheet.findViewById(R.id.edExpectedQty);
                edExpectedRate = bottomSheet.findViewById(R.id.edExpectedRate);
                edRemark = bottomSheet.findViewById(R.id.edRemark);

                edRemark.setText(intent.getStringExtra("Remark"));
                edExpectedQty.setText(intent.getStringExtra("Qualitiy"));
                edExpectedRate.setText(intent.getStringExtra("rate"));
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                        } else {

                        }
                    }
                });


                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        btnBuyerOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                } else {

                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
    }


    private void findViewBy_Id() {


        linearLayout = (LinearLayout) findViewById(R.id.bottom_sheet);
        btnBuyerOffer = (Button) findViewById(R.id.btnBuyerOffer);

        TxtOrderDeatailCode = (TextView) findViewById(R.id.TxtOrderDeatailCode);
        TxtOrderDetailProduct = (TextView) findViewById(R.id.TxtOrderDetailProduct);
        TxtOrderDetailCategory = (TextView) findViewById(R.id.TxtOrderDetailCategory);
        TxtOrderDetailSubCategory = (TextView) findViewById(R.id.TxtOrderDetailSubCategory);
        TxtOrderDetailCity = (TextView) findViewById(R.id.TxtOrderDetailCity);
        TxtOrderDetailArea = (TextView) findViewById(R.id.TxtOrderDetailArea);
        TxtOrderDetailDiemension = (TextView) findViewById(R.id.TxtOrderDetailDiemension);
        TxtOrderDetailGSM = (TextView) findViewById(R.id.TxtOrderDetailGSM);
        TxtOrderDetailColour = (TextView) findViewById(R.id.TxtOrderDetailColour);
        TxtOrderDetailRemark = (TextView) findViewById(R.id.TxtOrderDetailRemark);
        TxtOrderDeatailCode = (TextView) findViewById(R.id.TxtOrderDeatailCode);
        TxtOrderDetailQuality = (TextView) findViewById(R.id.TxtOrderDetailQuality);
        TxtOrderDetailPaymentTerms = (TextView) findViewById(R.id.TxtOrderDetailPaymentTerms);
        TxtOrderDetailStatus = (TextView) findViewById(R.id.TxtOrderDetailStatus);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add) {

        }
        return super.onOptionsItemSelected(item);
    }

}
