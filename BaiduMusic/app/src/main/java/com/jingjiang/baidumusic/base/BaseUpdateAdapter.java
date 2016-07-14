package com.jingjiang.baidumusic.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/13.
 */
public abstract class BaseUpdateAdapter<T> extends BaseAdapter {
    private List<T> list;
    private LayoutInflater inflater;

    public BaseUpdateAdapter(Context context) {
        init(context, new ArrayList<T>());
    }

    public BaseUpdateAdapter(Context context, List<T> list) {
        init(context, list);
    }

    private void init(Context context, List<T> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    private void clearList() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        if (null != list) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        if (null != list) {
            this.list.remove(position);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflate(setLayout());
        }

        get(convertView, position);
        return convertView;
    }

    /**
     * 加载布局
     *
     * @param layoutResId 布局layout
     * @return
     */
    private View inflate(int layoutResId) {
        View view = inflater.inflate(layoutResId, null);
        return view;
    }


    /**
     * 布局资源
     *
     * @return
     */
    protected abstract int setLayout();


    /**
     * 重写getView方法
     *
     * @param position
     * @param convertView
     * @return
     */
    protected abstract View getView(View convertView, int position);


    /**
     * @param view
     * @param id   控件的id
     * @param <E>  返回<T extends View>
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <E extends View> E get(View view, int id) {
        SparseArray<View> holder = (SparseArray<View>) view.getTag();
        if (null == holder) {
            holder = new SparseArray<View>();
            view.setTag(holder);
        }
        View childView = holder.get(id);
        if (null == childView) {
            childView = view.findViewById(id);
            holder.put(id, childView);
        }
        return (E) childView;
    }


}
