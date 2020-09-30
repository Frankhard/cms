package com.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cms.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;


    /**
     *  @Fields Id : id
     *
     * */
    private Integer id;
    /**
     *  @Fields Title : 标题
     *
     * */
    private String title;
    /**
     *  @Fields Img : img
     *
     * */
    private String img;
    /**
     *  @Fields Content : content
     *
     * */
    private String content;
    /**
     *  @Fields CreateTime : 添加时间
     *
     * */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmt_create;

    private String gmt_createStr;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public News() {

    }

    public News(Integer id ,String title ,String img ,String content){
        super();
        this.id=id;
        this.title=title;
        this.img=img;
        this.content=content;
    }

    @Override
    public String toString() {
        return "News [id="+ id + ",title="+ title + ",img="+ img + ",content="+ content + ",gmt_create="+ gmt_create +  "]";
    }

    public String getGmt_createStr() {
        return DateUtil.format(this.gmt_create);
    }

    public void setGmt_createStr(String gmt_createStr) {
        this.gmt_createStr = gmt_createStr;
    }

}
