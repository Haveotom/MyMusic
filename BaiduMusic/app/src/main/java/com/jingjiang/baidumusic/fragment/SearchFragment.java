package com.jingjiang.baidumusic.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jingjiang.baidumusic.R;
import com.jingjiang.baidumusic.adapter.SearchAdapter;
import com.jingjiang.baidumusic.base.BaseFragment;
import com.jingjiang.baidumusic.bean.SearchBean;
import com.jingjiang.baidumusic.bean.SearchContentData;
import com.jingjiang.baidumusic.widget.eventbus.StringEvent;
import com.jingjiang.baidumusic.widget.myinterface.OnDrawerListener;
import com.jingjiang.baidumusic.widget.othertool.UrlTool;
import com.jingjiang.baidumusic.widget.single.VolleySingle;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dllo on 16/6/25.
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener {
    private ImageView returnIv, searchIv, delateIv;
    private EditText contentEt;
    private OnDrawerListener onDrawerListener;
    private SearchAdapter searchAdapter;
    private ListView listView;
    private GridView gridView;
    private LinearLayout beforeLl, afterLl;
    private SearchContentAdapter searchContentAdapter;
    private SearchContentData data;

    @Override
    protected int initLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        returnIv = bindView(R.id.fragment_main_fanhui_iv);
        searchIv = bindView(R.id.fragment_main_to_search_iv);
        contentEt = bindView(R.id.fragment_main_content_et);
        gridView = bindView(R.id.fragment_search_choice_gridview);
        delateIv = bindView(R.id.fragment_main_delate_iv);
        listView = bindView(R.id.fragment_main_to_search_listview);
        beforeLl = bindView(R.id.fragment_search_before_ll);
        afterLl = bindView(R.id.fragment_main_to_search_listview_ll);
        returnIv.setOnClickListener(this);
        contentEt.setOnClickListener(this);
        delateIv.setOnClickListener(this);
        searchAdapter = new SearchAdapter(getContext());
        searchContentAdapter = new SearchContentAdapter(getContext());
        searchIv.setOnClickListener(this);

    }


    public void setOnDrawerListener(OnDrawerListener onDrawerListener) {
        this.onDrawerListener = onDrawerListener;
    }

    @Override
    protected void initData() {
        VolleySingle.getInstance()._addRequest(UrlTool.SEARCH_WORD,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<SearchBean>() {
                    @Override
                    public void onResponse(final SearchBean response) {
                        searchAdapter.setBean(response);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                contentEt.setText(response.getResult().get(position).getWord());
                                searchIv.callOnClick();//让其点击
                            }
                        });

                    }
                }, SearchBean.class);
        gridView.setAdapter(searchAdapter);

    }

    private void initSearch() {
        //http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.merge&query=%id%&page_size=50&page_no=1&type=-1&format=json&from=ios&version=5.2.5&from=ios&channel=appstore
        String urlLeft = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.merge&query=";
        String rightUrl = "&page_size=50&page_no=1&type=-1&format=json&from=ios&version=5.2.5&from=ios&channel=appstore";
        String url = urlLeft + contentEt.getText() + rightUrl;
        VolleySingle.getInstance()._addRequest(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<SearchContentData>() {
                    @Override
                    public void onResponse(SearchContentData response) {
                        data = response;
                        searchContentAdapter.setData(data);
                    }
                }, SearchContentData.class);
        listView.setAdapter(searchContentAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(new StringEvent(data.getResult().getSong_info().getSong_list().get(position).getSong_id()));
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_main_fanhui_iv:
                onDrawerListener.closeDrawer();
                break;
            case R.id.fragment_main_content_et:
                if (contentEt.getText() != null) {
                    delateIv.setVisibility(View.VISIBLE);

                } else {
                    delateIv.setVisibility(View.GONE);
                }
                break;
            case R.id.fragment_main_delate_iv:
                contentEt.setText("");
                delateIv.setVisibility(View.GONE);
                afterLl.setVisibility(View.GONE);
                beforeLl.setVisibility(View.VISIBLE);
                break;
            case R.id.fragment_main_to_search_iv:
                beforeLl.setVisibility(View.GONE);
                afterLl.setVisibility(View.VISIBLE);
                initSearch();
                break;
        }

    }

    private class SearchContentAdapter extends BaseAdapter {
        private Context context;
        private SearchContentData data;

        public void setData(SearchContentData data) {
            this.data = data;
            notifyDataSetChanged();
        }

        public SearchContentAdapter(Context context) {
            this.context = context;
        }


        @Override
        public int getCount() {
            return data != null ? data.getResult().getSong_info().getSong_list().size() : 0;
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
            SearchContentViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_search_content, parent, false);
                holder = new SearchContentViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (SearchContentViewHolder) convertView.getTag();
            }
            holder.titleTv.setText(data.getResult().getSong_info().getSong_list().get(position).getTitle());
            holder.nameTv.setText(data.getResult().getSong_info().getSong_list().get(position).getAuthor());
            if (data.getResult().getSong_info().getSong_list().get(position).getAlbum_title() != "") {
                holder.albumTv.setText(" - " + data.getResult().getSong_info().getSong_list().get(position).getAlbum_title());
            }
            return convertView;
        }

        class SearchContentViewHolder {
            TextView titleTv, nameTv, albumTv;
            ImageView moreIv;

            public SearchContentViewHolder(View view) {
                titleTv = (TextView) view.findViewById(R.id.item_search_content_title_tv);
                nameTv = (TextView) view.findViewById(R.id.item_search_content_name_tv);
                albumTv = (TextView) view.findViewById(R.id.item_search_content_album_tv);
                moreIv = (ImageView) view.findViewById(R.id.item_search_content_more_iv);

            }
        }
    }
}
