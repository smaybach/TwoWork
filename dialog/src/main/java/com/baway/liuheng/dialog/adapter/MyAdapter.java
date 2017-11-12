package com.baway.liuheng.dialog.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.baway.liuheng.dialog.R;
import com.baway.liuheng.dialog.bean.UserBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private List<UserBean.DataBean> list;

    public interface OnLintener{
        void setOnLintener(int position, View view, View view1, View view2);
    }
    OnLintener onLintener;

    public void setOnLintener(OnLintener onLintener) {
        this.onLintener = onLintener;
    }

    public MyAdapter(Context context, List<UserBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String img = list.get(position).getImg();
        Uri uri = Uri.parse(img);
        holder.img.setImageURI(uri);
        holder.title.setText(list.get(position).getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLintener.setOnLintener(position,holder.ll,holder.sb,holder.tv);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final SimpleDraweeView img;
        private final LinearLayout ll;
        private final SeekBar sb;
        private final TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (SimpleDraweeView)itemView.findViewById(R.id.img);
            title = (TextView)itemView.findViewById(R.id.title);
            ll = (LinearLayout)itemView.findViewById(R.id.ll);
            sb = (SeekBar)itemView.findViewById(R.id.sb);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }
}