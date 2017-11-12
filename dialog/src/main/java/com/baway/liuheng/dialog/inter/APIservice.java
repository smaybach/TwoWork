package com.baway.liuheng.dialog.inter;

import com.baway.liuheng.dialog.bean.UserBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lenovo on 2017/11/11.
 */

public interface APIservice {
    @GET("page_{type}.json")
    Flowable<List<UserBean>> getData(@Path("type") int a);

}
