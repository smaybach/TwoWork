package com.baway.liuheng.dialog.presenter;

import com.baway.liuheng.dialog.bean.UserBean;
import com.baway.liuheng.dialog.model.IRecyModel;
import com.baway.liuheng.dialog.model.RecyModel;
import com.baway.liuheng.dialog.view.RecyView;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */

public class RecyPresenter implements IRecyPresenter {
    RecyView view;
    IRecyModel model;

    public RecyPresenter(RecyView view) {
        this.view = view;
        model=new RecyModel();
    }

    @Override
    public void show(List<UserBean.DataBean> ub) {
        view.showRecy(ub);


    }

    @Override
    public void showModel(int type) {
        model.recy(type,this);

    }
}
