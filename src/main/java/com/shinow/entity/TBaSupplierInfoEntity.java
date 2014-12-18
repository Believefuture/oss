package com.shinow.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TBa_SupplierInfo")
public class TBaSupplierInfoEntity {
    private Integer id;
    private String supplierid;
    private String suppliername;
    private String supplierab;
    private String address;
    private String linkname;
    private String linktel;
    private String qq;
    private String email;
    private Integer sortid;
    private Boolean state;
//    private Collection<TMeInStockInfoEntity> tMeInStockInfosBySupplierId;

    @Basic
    @Column(name = "ID",insertable=false,updatable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "SupplierID")
    public String getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(String supplierId) {
        this.supplierid = supplierId;
    }

    @Basic
    @Column(name = "SupplierName")
    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String supplierName) {
        this.suppliername = supplierName;
    }

    @Basic
    @Column(name = "SupplierAB")
    public String getSupplierab() {
        return supplierab;
    }

    public void setSupplierab(String supplierAb) {
        this.supplierab = supplierAb;
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

    public void setLinkname(String linkName) {
        this.linkname = linkName;
    }

    @Basic
    @Column(name = "LinkTel")
    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linkTel) {
        this.linktel = linkTel;
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
    public Integer getSortid() {
        return sortid;
    }

    public void setSortid(Integer sortId) {
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

        TBaSupplierInfoEntity that = (TBaSupplierInfoEntity) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (linkname != null ? !linkname.equals(that.linkname) : that.linkname != null) return false;
        if (linktel != null ? !linktel.equals(that.linktel) : that.linktel != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (sortid != null ? !sortid.equals(that.sortid) : that.sortid != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (supplierab != null ? !supplierab.equals(that.supplierab) : that.supplierab != null) return false;
        if (supplierid != null ? !supplierid.equals(that.supplierid) : that.supplierid != null) return false;
        if (suppliername != null ? !suppliername.equals(that.suppliername) : that.suppliername != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (supplierid != null ? supplierid.hashCode() : 0);
        result = 31 * result + (suppliername != null ? suppliername.hashCode() : 0);
        result = 31 * result + (supplierab != null ? supplierab.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (linkname != null ? linkname.hashCode() : 0);
        result = 31 * result + (linktel != null ? linktel.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (sortid != null ? sortid.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "tBaSupplierInfoBySupplierId")
//    public Collection<TMeInStockInfoEntity> gettMeInStockInfosBySupplierId() {
//        return tMeInStockInfosBySupplierId;
//    }
//
//    public void settMeInStockInfosBySupplierId(Collection<TMeInStockInfoEntity> tMeInStockInfosBySupplierId) {
//        this.tMeInStockInfosBySupplierId = tMeInStockInfosBySupplierId;
//    }
}
