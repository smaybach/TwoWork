package com.baway.liuheng.dialog.presenter;

import com.baway.liuheng.dialog.bean.UserBean;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */

public interface IRecyPresenter {
    void show(List<UserBean.DataBean> ub);
    void showModel(int type);
}
