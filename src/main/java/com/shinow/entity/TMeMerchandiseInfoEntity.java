package com.shinow.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TMe_MerchandiseInfo")
public class TMeMerchandiseInfoEntity {
    private Integer id;
    private String merchandiseid;
    private String merchandisename;
    private String merchandiseab;
    private BigDecimal price;
    private boolean salestatus;
    private String spec;
    private String describe;
    private String picpath;
    private Integer clickcount;
    private String remark;
//    private Collection<TMeInStockDetailsInfoEntity> tMeInStockDetailsInfosByMerchandiseId;
    private TMeMerchandiseCInfoEntity merchandisecid;
    private TMeProStatusInfoEntity prostatusid;
    private TMeUnitInfoEntity unitid;
//    private Collection<TMeOrderDetailsInfoEntity> tMeOrderDetailsInfosByMerchandiseId;
//    private Collection<TMeOutStockDetailsInfoEntity> tMeOutStockDetailsInfosByMerchandiseId;
//    private Collection<TMeStockInfoEntity> tMeStockInfosByMerchandiseId;

    @Basic
    @Column(name = "ID",insertable = false,updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "MerchandiseID")
    public String getMerchandiseid() {
        return merchandiseid;
    }

    public void setMerchandiseid(String merchandiseid) {
        this.merchandiseid = merchandiseid;
    }

    @Basic
    @Column(name = "MerchandiseName")
    public String getMerchandisename() {
        return merchandisename;
    }

    public void setMerchandisename(String merchandisename) {
        this.merchandisename = merchandisename;
    }

    @Basic
    @Column(name = "MerchandiseAB")
    public String getMerchandiseab() {
        return merchandiseab;
    }

    public void setMerchandiseab(String merchandiseab) {
        this.merchandiseab = merchandiseab;
    }

    @Basic
    @Column(name = "Price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "SaleStatus")
    public boolean isSalestatus() {
        return salestatus;
    }

    public void setSalestatus(boolean salestatus) {
        this.salestatus = salestatus;
    }

    @Basic
    @Column(name = "Spec")
    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Basic
    @Column(name = "Describe")
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Basic
    @Column(name = "PicPath")
    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    @Basic
    @Column(name = "ClickCount")
    public Integer getClickcount() {
        return clickcount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }

    @Basic
    @Column(name = "Remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TMeMerchandiseInfoEntity that = (TMeMerchandiseInfoEntity) o;

        if (id != that.id) return false;
        if (salestatus != that.salestatus) return false;
        if (clickcount != null ? !clickcount.equals(that.clickcount) : that.clickcount != null) return false;
        if (describe != null ? !describe.equals(that.describe) : that.describe != null) return false;
        if (merchandiseab != null ? !merchandiseab.equals(that.merchandiseab) : that.merchandiseab != null)
            return false;
        if (merchandiseid != null ? !merchandiseid.equals(that.merchandiseid) : that.merchandiseid != null)
            return false;
        if (merchandisename != null ? !merchandisename.equals(that.merchandisename) : that.merchandisename != null)
            return false;
        if (picpath != null ? !picpath.equals(that.picpath) : that.picpath != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (spec != null ? !spec.equals(that.spec) : that.spec != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (merchandiseid != null ? merchandiseid.hashCode() : 0);
        result = 31 * result + (merchandisename != null ? merchandisename.hashCode() : 0);
        result = 31 * result + (merchandiseab != null ? merchandiseab.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (salestatus ? 1 : 0);
        result = 31 * result + (spec != null ? spec.hashCode() : 0);
        result = 31 * result + (describe != null ? describe.hashCode() : 0);
        result = 31 * result + (picpath != null ? picpath.hashCode() : 0);
        result = 31 * result + (clickcount != null ? clickcount.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "tMeMerchandiseInfoByMerchandiseId")
//    public Collection<TMeInStockDetailsInfoEntity> gettMeInStockDetailsInfosByMerchandiseId() {
//        return tMeInStockDetailsInfosByMerchandiseId;
//    }
//
//    public void settMeInStockDetailsInfosByMerchandiseId(Collection<TMeInStockDetailsInfoEntity> tMeInStockDetailsInfosByMerchandiseId) {
//        this.tMeInStockDetailsInfosByMerchandiseId = tMeInStockDetailsInfosByMerchandiseId;
//    }

    @ManyToOne
    @JoinColumn(name = "MerchandiseCID", referencedColumnName = "MerchandiseCID")
    public TMeMerchandiseCInfoEntity getMerchandisecid() {
        return merchandisecid;
    }

    public void setMerchandisecid(TMeMerchandiseCInfoEntity merchandisecid) {
        this.merchandisecid = merchandisecid;
    }

    @ManyToOne
    @JoinColumn(name = "ProStatusID", referencedColumnName = "ProStatusID")
    public TMeProStatusInfoEntity getProstatusid() {
        return prostatusid;
    }

    public void setProstatusid(TMeProStatusInfoEntity prostatusid) {
        this.prostatusid = prostatusid;
    }

    @ManyToOne
    @JoinColumn(name = "UnitID", referencedColumnName = "UnitID")
    public TMeUnitInfoEntity getUnitid() {
        return unitid;
    }

    public void setUnitid(TMeUnitInfoEntity unitid) {
        this.unitid = unitid;
    }
    //    @OneToMany(mappedBy = "tMeMerchandiseInfoByMerchandiseId")
//    public Collection<TMeOrderDetailsInfoEntity> gettMeOrderDetailsInfosByMerchandiseId() {
//        return tMeOrderDetailsInfosByMerchandiseId;
//    }
//
//    public void settMeOrderDetailsInfosByMerchandiseId(Collection<TMeOrderDetailsInfoEntity> tMeOrderDetailsInfosByMerchandiseId) {
//        this.tMeOrderDetailsInfosByMerchandiseId = tMeOrderDetailsInfosByMerchandiseId;
//    }
//
//    @OneToMany(mappedBy = "tMeMerchandiseInfoByMerchandiseId")
//    public Collection<TMeOutStockDetailsInfoEntity> gettMeOutStockDetailsInfosByMerchandiseId() {
//        return tMeOutStockDetailsInfosByMerchandiseId;
//    }
//
//    public void settMeOutStockDetailsInfosByMerchandiseId(Collection<TMeOutStockDetailsInfoEntity> tMeOutStockDetailsInfosByMerchandiseId) {
//        this.tMeOutStockDetailsInfosByMerchandiseId = tMeOutStockDetailsInfosByMerchandiseId;
//    }
//
//    @OneToMany(mappedBy = "tMeMerchandiseInfoByMerchandiseId")
//    public Collection<TMeStockInfoEntity> gettMeStockInfosByMerchandiseId() {
//        return tMeStockInfosByMerchandiseId;
//    }
//
//    public void settMeStockInfosByMerchandiseId(Collection<TMeStockInfoEntity> tMeStockInfosByMerchandiseId) {
//        this.tMeStockInfosByMerchandiseId = tMeStockInfosByMerchandiseId;
//    }
}
