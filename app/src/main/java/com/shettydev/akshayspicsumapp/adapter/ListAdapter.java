package com.shettydev.akshayspicsumapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.shettydev.akshayspicsumapp.R;
import com.shettydev.akshayspicsumapp.activity.ListDetail;
import com.shettydev.akshayspicsumapp.activity.MainActivity;
import com.shettydev.akshayspicsumapp.model.ListResponse;

import java.util.List;

public class ListAdapter  extends BaseAdapter {
    Context mContext;
    private List<ListResponse> listDataArray;

    public ListAdapter(Context mContext, List<ListResponse> listDataArray) {
        this.mContext=mContext;
        this.listDataArray=listDataArray;
    }
    @Override
    public int getCount()
    {
        return listDataArray.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }
       LinearLayout ll_list_item=view.findViewById(R.id.ll_list_item);
        ll_list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, ListDetail.class);
                i.putExtra("author",listDataArray.get(position).getAuthor());
                i.putExtra("filename",listDataArray.get(position).getFilename());
                i.putExtra("id",listDataArray.get(position).getId());


                mContext.startActivity(i);

            }
        });
        ImageView iv_image = view.findViewById(R.id.iv_image);
        TextView tv_author=view.findViewById(R.id.tv_author);
        Picasso.with(mContext)
                .load("https://picsum.photos/300/300?image="+listDataArray.get(position).getId())
                .error(R.mipmap.ic_launcher)
                .into(iv_image);
        tv_author.setText(listDataArray.get(position).getAuthor());
        return view;
    }
    }





