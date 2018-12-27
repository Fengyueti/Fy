package com.example.lenovo.yk.ui.madapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.yk.R;
import com.example.lenovo.yk.ui.model.UserBean1;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MAdapter2 extends BaseAdapter {
    private Context context;
    private List<UserBean1.ResultBean> list;

    public MAdapter2(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<UserBean1.ResultBean> list) {

            this.list = list;

        notifyDataSetChanged();
    }
    public void setadd(List<UserBean1.ResultBean> list1) {
        list.addAll(list1);
        notifyDataSetChanged();
    }
    //个数
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public UserBean1.ResultBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
       // convertView=为空
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.linear,parent,false);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.bindData(getItem(position));
        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView summary;
        ImageView img;

        public ViewHolder(View item) {
            this.name = item.findViewById(R.id.name);
            this.summary=item.findViewById(R.id.summary);
            this.img=item.findViewById(R.id.img);
            item.setTag(this);
        }

        public void bindData(UserBean1.ResultBean item) {
            name.setText(item.getName());//放入
            summary.setText(item.getSummary());
            DisplayImageOptions options=new DisplayImageOptions.Builder()
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .build();
            ImageLoader.getInstance().displayImage(item.getImageUrl(),this.img,options);
        }
    }
}
