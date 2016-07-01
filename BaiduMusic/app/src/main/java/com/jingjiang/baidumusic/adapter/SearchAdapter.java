package com.jingjiang.baidumusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.bean.SearchBean;

/**
 * Created by dllo on 16/6/25.
 */
public class SearchAdapter extends BaseAdapter {
    private SearchBean bean;
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setBean(SearchBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean != null ? bean.getResult().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_choice, parent, false);
            holder = new SearchViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SearchViewHolder) convertView.getTag();
        }
        holder.textView.setText(bean.getResult().get(position).getWord());
        if (position == 0) {
            holder.textView.setTextColor(context.getResources().getColor(R.color.blue));
        }
        return convertView;
    }

    class SearchViewHolder {
        TextView textView;

        public SearchViewHolder(View itemView) {
            textView = (TextView) itemView.findViewById(R.id.item_search_choice_tv);

        }
    }
}
