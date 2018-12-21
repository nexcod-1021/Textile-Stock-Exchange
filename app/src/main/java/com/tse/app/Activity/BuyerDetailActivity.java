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
import android.widget.Toast;

import com.tse.app.R;

public class BuyerDetailActivity extends AppCompatActivity {

    private BottomSheetBehavior sheetBehavior;
    LinearLayout linearLayout;
    Button btnBuyerOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_detail);

        findViewBy_Id();

        sheetBehavior = BottomSheetBehavior.from(linearLayout);


        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                ImageView close = bottomSheet.findViewById(R.id.img_close);
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
