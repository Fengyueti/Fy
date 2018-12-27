package com.example.rk2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MAdapter extends BaseAdapter {
    private Context context;
    private List<UserBean.DataBean> list;
    private int one_xml=0;
    private int two_xml=1;
    public MAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<UserBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void setadd(List<UserBean.DataBean> list1) {
        list.addAll(list1);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public UserBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return one_xml;
        }else {
            return two_xml;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ViewHolder2 viewHolder2;
        if(getItemViewType(position)==one_xml){
            if (convertView==null){
                convertView=LayoutInflater.from(context).inflate(R.layout.linearone,parent,false);
                viewHolder= new ViewHolder(convertView);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.bindData(getItem(position));
        }else{
            if (convertView==null){
                convertView=LayoutInflater.from(context).inflate(R.layout.lineartwo,parent,false);
                viewHolder2= new ViewHolder2(convertView);
            }else{
                viewHolder2 = (ViewHolder2) convertView.getTag();
            }
            viewHolder2.bindData1(getItem(position));
        }
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView title;

        public ViewHolder(View item) {
            this.img = item.findViewById(R.id.img);
            this.title=item.findViewById(R.id.title);
            item.setTag(this);
        }

        public void bindData(UserBean.DataBean item) {
            title.setText(item.getTitle());
            DisplayImageOptions options=new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheInMemory(true)
                    .build();
            ImageLoader.getInstance().displayImage(item.getDetailUrl(),this.img,options);
        }
    }
    class ViewHolder2{
        TextView title;

        public ViewHolder2(View item) {
            this.title = item.findViewById(R.id.tv_title);
            item.setTag(this);
        }

        public void bindData1(UserBean.DataBean item) {
            title.setText(item.getTitle());
        }
    }
}
