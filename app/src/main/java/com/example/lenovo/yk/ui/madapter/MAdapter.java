package com.example.lenovo.yk.ui.madapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.yk.R;
import com.example.lenovo.yk.ui.model.UserBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MAdapter extends BaseAdapter {
    private Context context;
    private List<UserBean.ResultBean> list;

    public MAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<UserBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void setadd(List<UserBean.ResultBean> list1) {
        list.addAll(list1);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public UserBean.ResultBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.linear,parent,false);
            viewHolder=new ViewHolder(convertView);
        }else{
          viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.bindData(getItem(position));
        return convertView;
    }
    //类
    class ViewHolder{
        ImageView img;
        TextView name,summary;//定义

        public ViewHolder(View item) {
            this.img = item.findViewById(R.id.img);
            this.name = item.findViewById(R.id.name);
            this.summary = item.findViewById(R.id.summary);
            item.setTag(this);
        }

        public void bindData(UserBean.ResultBean item) {
            name.setText(item.getName());
            summary.setText(item.getSummary());
            DisplayImageOptions options=new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
            ImageLoader.getInstance().displayImage(item.getImageUrl(),this.img,options);
        }
    }
}
