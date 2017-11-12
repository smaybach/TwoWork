package com.baway.liuheng.dialog.activity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.liuheng.dialog.R;
import com.baway.liuheng.dialog.adapter.MyAdapter;
import com.baway.liuheng.dialog.bean.ImgBean;
import com.baway.liuheng.dialog.bean.UserBean;
import com.baway.liuheng.dialog.greendao.ImgBeanDao;
import com.baway.liuheng.dialog.myapp.MyApp;
import com.baway.liuheng.dialog.presenter.RecyPresenter;
import com.baway.liuheng.dialog.view.RecyView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadStatus;

public class MainActivity extends AppCompatActivity implements RecyView {

    private RecyPresenter presenter;
    private RecyclerView rlv;
    private SwipeRefreshLayout sfl;
    int a=1;
    private ImgBeanDao dao;
    private RxDownload rxDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new RecyPresenter(this);
        presenter.showModel(a);
        dao = MyApp.getApplication().getDaoSession().getImgBeanDao();

        //配置断点续传
        rxDownload = RxDownload.getInstance(this).defaultSavePath(Environment.getExternalStorageDirectory() + "/LH").maxThread(3);


    }
    private void initView() {
        rlv = (RecyclerView) findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        //刷新条目
        sfl = (SwipeRefreshLayout) findViewById(R.id.sfl);
        sfl.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimaryDark);
        sfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sfl.setRefreshing(false);
                        a++;
                        presenter.showModel(a);
                    }
                },2000);
            }
        });
    }
    //回传数据
    @Override
    public void showRecy(List<UserBean.DataBean> ub) {
        MyAdapter myAdapter = new MyAdapter(this, ub);
        rlv.setAdapter(myAdapter);
        for (int i = 0; i <ub.size(); i++) {
            add(ub.get(i).getImg());
        }
        myAdapter.setOnLintener(new MyAdapter.OnLintener() {
            @Override
            public void setOnLintener(int position, View view, View view1, View view2) {
                xiazai(query().get(position).getUrl(),(LinearLayout)view,(SeekBar)view1,(TextView)view2);
            }


        });
    }
    private void xiazai(String url, final LinearLayout view, final SeekBar view1, final TextView view2) {
        Disposable disposable = rxDownload
                .download(url)                       //只传url即可
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DownloadStatus>() {
                    @Override
                    public void accept(DownloadStatus status) throws Exception {
                        //DownloadStatus为下载进度
                        view.setVisibility(View.VISIBLE);
                        double l = status.getDownloadSize() * 100 / status.getTotalSize();
                        view1.setProgress((int) l);
                        view2.setText(l+"%");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //下载失败
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view.setVisibility(View.GONE);
                            }
                        },2000);
                        Toast.makeText(MainActivity.this,"下载失败",Toast.LENGTH_SHORT).show();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //下载成功
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view.setVisibility(View.GONE);
                            }
                        },2000);
                        Toast.makeText(MainActivity.this,"下载成功",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    //插入数据
    public void add(String Url){
        ImgBean imgBean = new ImgBean();
        imgBean.setUrl(Url);
        dao.insert(imgBean);
    }
    //查询数据
    public List<ImgBean> query(){
        List<ImgBean> img=dao.loadAll();
        return img;
    }



}
