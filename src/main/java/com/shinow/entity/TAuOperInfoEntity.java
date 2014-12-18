package com.shinow.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TAu_OperInfo")
public class TAuOperInfoEntity {
    private Integer id;
    private String operid;
    private String opername;
    private String pwd;
    private String address;
    private String linktel;
    private String qq;
    private String email;
    private String mobile;
    private Short sortid;
    private Boolean state;
    private TAuRoleInfoEntity roleid;
//    private Collection<TBaLogInfoEntity> tBaLogInfosByOperId;
//    private Collection<TMeInStockInfoEntity> tMeInStockInfosByOperId;
//    private Collection<TMeOrderInfoEntity> tMeOrderInfosByOperId;
//    private Collection<TMeOutStockInfoEntity> tMeOutStockInfosByOperId;

    @Basic
    @Column(name = "ID" ,insertable = false,updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "OperID")
    public String getOperid() {
        return operid;
    }

    public void setOperid(String operid) {
        this.operid = operid;
    }

    @Basic
    @Column(name = "OperName")
    public String getOpername() {
        return opername;
    }

    public void setOpername(String opername) {
        this.opername = opername;
    }

    @Basic
    @Column(name = "Pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "LinkTel")
    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel;
    }

    @Basic
    @Column(name = "QQ")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "SortID")
    public Short getSortid() {
        return sortid;
    }

    public void setSortid(Short sortid) {
        this.sortid = sortid;
    }

    @Basic
    @Column(name = "State")
    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAuOperInfoEntity that = (TAuOperInfoEntity) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (linktel != null ? !linktel.equals(that.linktel) : that.linktel != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (operid != null ? !operid.equals(that.operid) : that.operid != null) return false;
        if (opername != null ? !opername.equals(that.opername) : that.opername != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (sortid != null ? !sortid.equals(that.sortid) : that.sortid != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (operid != null ? operid.hashCode() : 0);
        result = 31 * result + (opername != null ? opername.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (linktel != null ? linktel.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (sortid != null ? sortid.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID")
    public TAuRoleInfoEntity getRoleid() {
        return roleid;
    }

    public void setRoleid(TAuRoleInfoEntity roleid) {
        this.roleid = roleid;
    }

    //    @OneToMany(mappedBy = "tAuOperInfoByOperId")
//    public Collection<TBaLogInfoEntity> gettBaLogInfosByOperId() {
//        return tBaLogInfosByOperId;
//    }
//
//    public void settBaLogInfosByOperId(Collection<TBaLogInfoEntity> tBaLogInfosByOperId) {
//        this.tBaLogInfosByOperId = tBaLogInfosByOperId;
//    }
//
//    @OneToMany(mappedBy = "tAuOperInfoByOperId")
//    public Collection<TMeInStockInfoEntity> gettMeInStockInfosByOperId() {
//        return tMeInStockInfosByOperId;
//    }
//
//    public void settMeInStockInfosByOperId(Collection<TMeInStockInfoEntity> tMeInStockInfosByOperId) {
//        this.tMeInStockInfosByOperId = tMeInStockInfosByOperId;
//    }
//
//    @OneToMany(mappedBy = "tAuOperInfoByOperId")
//    public Collection<TMeOrderInfoEntity> gettMeOrderInfosByOperId() {
//        return tMeOrderInfosByOperId;
//    }
//
//    public void settMeOrderInfosByOperId(Collection<TMeOrderInfoEntity> tMeOrderInfosByOperId) {
//        this.tMeOrderInfosByOperId = tMeOrderInfosByOperId;
//    }
//
//    @OneToMany(mappedBy = "tAuOperInfoByOperId")
//    public Collection<TMeOutStockInfoEntity> gettMeOutStockInfosByOperId() {
//        return tMeOutStockInfosByOperId;
//    }
//
//    public void settMeOutStockInfosByOperId(Collection<TMeOutStockInfoEntity> tMeOutStockInfosByOperId) {
//        this.tMeOutStockInfosByOperId = tMeOutStockInfosByOperId;
//    }
}
