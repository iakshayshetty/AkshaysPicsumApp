package com.shettydev.akshayspicsumapp.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.shettydev.akshayspicsumapp.R;

public class ListDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        String author  = getIntent().getExtras().getString("author");
        String id = getIntent().getExtras().getString("id");

        ImageView iv_image=findViewById(R.id.iv_image);
        TextView tv_author=findViewById(R.id.tv_author);
        tv_author.setText(author);
        Picasso.with(this)
                .load("https://picsum.photos/300/300?image="+id)
                .error(R.mipmap.ic_launcher)
                .into(iv_image);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}