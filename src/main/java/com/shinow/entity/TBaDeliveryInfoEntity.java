package com.shinow.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TBa_DeliveryInfo")
public class TBaDeliveryInfoEntity {
    private Integer id;
    private String deliveryid;
    private String deliveryname;
    private String address;
    private String linkname;
    private String linktel;
    private String qq;
    private String email;
    private Byte sortid;
    private Boolean state;
//    private Collection<TMeOrderInfoEntity> MeOrderInfosByDeliveryId;

    @Basic
    @Column(name = "ID",insertable = false,updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "DeliveryID")
    public String getDeliveryid() {
        return deliveryid;
    }

    public void setDeliveryid(String deliveryid) {
        this.deliveryid = deliveryid;
    }

    @Basic
    @Column(name = "DeliveryName")
    public String getDeliveryname() {
        return deliveryname;
    }

    public void setDeliveryname(String deliveryname) {
        this.deliveryname = deliveryname;
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
    @Column(name = "LinkName")
    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
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
    @Column(name = "SortID")
    public Byte getSortid() {
        return sortid;
    }

    public void setSortid(Byte sortId) {
        this.sortid = sortId;
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

        TBaDeliveryInfoEntity that = (TBaDeliveryInfoEntity) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (deliveryid != null ? !deliveryid.equals(that.deliveryid) : that.deliveryid != null) return false;
        if (deliveryname != null ? !deliveryname.equals(that.deliveryname) : that.deliveryname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (linkname != null ? !linkname.equals(that.linkname) : that.linkname != null) return false;
        if (linktel != null ? !linktel.equals(that.linktel) : that.linktel != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (sortid != null ? !sortid.equals(that.sortid) : that.sortid != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (deliveryid != null ? deliveryid.hashCode() : 0);
        result = 31 * result + (deliveryname != null ? deliveryname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (linkname != null ? linkname.hashCode() : 0);
        result = 31 * result + (linktel != null ? linktel.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (sortid != null ? sortid.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "tBaDeliveryInfoByDeliveryId")
//    public Collection<TMeOrderInfoEntity> getMeOrderInfosByDeliveryId() {
//        return MeOrderInfosByDeliveryId;
//    }
//
//    public void setMeOrderInfosByDeliveryId(Collection<TMeOrderInfoEntity> MeOrderInfosByDeliveryId) {
//        this.MeOrderInfosByDeliveryId = MeOrderInfosByDeliveryId;
//    }
}
