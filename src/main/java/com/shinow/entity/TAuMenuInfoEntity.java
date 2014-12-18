package com.shinow.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TAu_MenuInfo")
public class TAuMenuInfoEntity {
    private Integer id;
    private Integer menuid;
    private String menuname;
    private String url;
    private Integer sortId;
    private Boolean state;
    private String src;
    private String modelid;
//    private Collection<TAuAuthorizationEntity> tAuAuthorizationsByMenuId;
//    private Collection<TBaLogInfoEntity> tBaLogInfosByMenuId;
    @Basic
    @Column(name = "ID",insertable = false,updatable =false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "MenuID")
    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    @Basic
    @Column(name = "MenuName")
    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    @Basic
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "SortID")
    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    @Basic
    @Column(name = "State")
    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Basic
    @Column(name = "src")
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    @Basic
    @Column(name = "modelid")
    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAuMenuInfoEntity that = (TAuMenuInfoEntity) o;

        if (id != that.id) return false;
        if (menuid != null ? !menuid.equals(that.menuid) : that.menuid != null) return false;
        if (menuname != null ? !menuname.equals(that.menuname) : that.menuname != null) return false;
        if (sortId != null ? !sortId.equals(that.sortId) : that.sortId != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (menuid != null ? menuid.hashCode() : 0);
        result = 31 * result + (menuname != null ? menuname.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (sortId != null ? sortId.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "tAuMenuInfoByMenuId")
//    public Collection<TAuAuthorizationEntity> gettAuAuthorizationsByMenuId() {
//        return tAuAuthorizationsByMenuId;
//    }
//
//    public void settAuAuthorizationsByMenuId(Collection<TAuAuthorizationEntity> tAuAuthorizationsByMenuId) {
//        this.tAuAuthorizationsByMenuId = tAuAuthorizationsByMenuId;
//    }
//
//    @OneToMany(mappedBy = "tAuMenuInfoByMenuId")
//    public Collection<TBaLogInfoEntity> gettBaLogInfosByMenuId() {
//        return tBaLogInfosByMenuId;
//    }
//
//    public void settBaLogInfosByMenuId(Collection<TBaLogInfoEntity> tBaLogInfosByMenuId) {
//        this.tBaLogInfosByMenuId = tBaLogInfosByMenuId;
//    }
}
