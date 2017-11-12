package com.baway.liuheng.dialog.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2017/11/11.
 */
@Entity
public class ImgBean {
    @Id(autoincrement = true)
    Long Img;
    String Url;
    public String getUrl() {
        return this.Url;
    }
    public void setUrl(String Url) {
        this.Url = Url;
    }
    public Long getImg() {
        return this.Img;
    }
    public void setImg(Long Img) {
        this.Img = Img;
    }
    @Generated(hash = 1351764128)
    public ImgBean(Long Img, String Url) {
        this.Img = Img;
        this.Url = Url;
    }
    @Generated(hash = 2027780091)
    public ImgBean() {
    }
    

}
