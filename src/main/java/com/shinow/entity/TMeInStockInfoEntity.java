package com.shinow.entity;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TMe_InStockInfo")
public class TMeInStockInfoEntity {
    private Integer id;
    private String billcode;
    private Byte intype;
    private Timestamp intime;
    private String handler;
    private BigDecimal totalmoney;
    private String remark;
    private List<TMeInStockDetailsInfoEntity> instockdetailsinfoslist;
    private TAuOperInfoEntity operid;
    private TBaSupplierInfoEntity supplierid;

    @Basic
    @Column(name = "ID" ,insertable=false,updatable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "BillCode")
    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

    @Basic
    @Column(name = "InType")
    public Byte getIntype() {
        return intype;
    }

    public void setIntype(Byte intype) {
        this.intype = intype;
    }

    @Basic
    @Column(name = "InTime")
    @JSON(format = "yyyy-MM-dd")
    public Timestamp getIntime() {
        return intime;
    }

    @JSON(format = "yyyy-MM-dd")
    public void setIntime(Timestamp intime) {
        this.intime = intime;
    }

    @Basic
    @Column(name = "Handler")
    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    @Basic
    @Column(name = "TotalMoney")
    public BigDecimal getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(BigDecimal totalmoney) {
        this.totalmoney = totalmoney;
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

        TMeInStockInfoEntity that = (TMeInStockInfoEntity) o;

        if (id != that.id) return false;
        if (billcode != null ? !billcode.equals(that.billcode) : that.billcode != null) return false;
        if (handler != null ? !handler.equals(that.handler) : that.handler != null) return false;
        if (intime != null ? !intime.equals(that.intime) : that.intime != null) return false;
        if (intype != null ? !intype.equals(that.intype) : that.intype != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (totalmoney != null ? !totalmoney.equals(that.totalmoney) : that.totalmoney != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (billcode != null ? billcode.hashCode() : 0);
        result = 31 * result + (intype != null ? intype.hashCode() : 0);
        result = 31 * result + (intime != null ? intime.hashCode() : 0);
        result = 31 * result + (handler != null ? handler.hashCode() : 0);
        result = 31 * result + (totalmoney != null ? totalmoney.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "billcode")
    public List<TMeInStockDetailsInfoEntity> getInstockdetailsinfoslist() {
        return instockdetailsinfoslist;
    }

    public void setInstockdetailsinfoslist(List<TMeInStockDetailsInfoEntity> instockdetailsinfoslist) {
        this.instockdetailsinfoslist = instockdetailsinfoslist;
    }
    @ManyToOne
    @JoinColumn(name = "OperID", referencedColumnName = "OperID")
    public TAuOperInfoEntity getOperid() {
        return operid;
    }

    public void setOperid(TAuOperInfoEntity operid) {
        this.operid = operid;
    }

    @ManyToOne
    @JoinColumn(name = "SupplierID", referencedColumnName = "SupplierID")
    public TBaSupplierInfoEntity getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(TBaSupplierInfoEntity supplierid) {
        this.supplierid = supplierid;
    }

}
