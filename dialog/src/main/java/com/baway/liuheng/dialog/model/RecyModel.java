package com.baway.liuheng.dialog.model;

import android.util.Log;
import com.baway.liuheng.dialog.bean.UserBean;
import com.baway.liuheng.dialog.inter.APIservice;
import com.baway.liuheng.dialog.inter.APi;
import com.baway.liuheng.dialog.presenter.IRecyPresenter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import org.reactivestreams.Publisher;

import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/11/11.
 */

public class RecyModel implements IRecyModel {
    @Override
    public void recy(int type, final IRecyPresenter rp) {
        Retrofit build = new Retrofit.Builder().baseUrl(APi.PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        APIservice apIservice = build.create(APIservice.class);
        Flowable<List<UserBean>> data = apIservice.getData(type);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //主线程
                .flatMap(new Function<List<UserBean>, Publisher<UserBean>>() {
                    @Override
                    public Publisher<UserBean> apply(List<UserBean> userBeen) throws Exception {
                        return Flowable.fromIterable(userBeen);
                    }
                })
                .subscribeWith(new DisposableSubscriber<UserBean>() {
            @Override
            public void onNext(UserBean userBean) {
                List<UserBean.DataBean> been = userBean.getData();
                Log.i("====-=-=-=-=-",been.toString());
                rp.show(been);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });


    }
}
