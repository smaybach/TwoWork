package com.baway.liuheng.dialog.greendao;

import com.baway.liuheng.dialog.bean.ImgBean;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig imgBeanDaoConfig;

    private final ImgBeanDao imgBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        imgBeanDaoConfig = daoConfigMap.get(ImgBeanDao.class).clone();
        imgBeanDaoConfig.initIdentityScope(type);

        imgBeanDao = new ImgBeanDao(imgBeanDaoConfig, this);

        registerDao(ImgBean.class, imgBeanDao);
    }
    
    public void clear() {
        imgBeanDaoConfig.clearIdentityScope();
    }

    public ImgBeanDao getImgBeanDao() {
        return imgBeanDao;
    }

}
