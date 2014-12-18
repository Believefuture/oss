package com.shinow.entity;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TMe_OutStockInfo")
public class TMeOutStockInfoEntity {
    private Integer id;
    private String outbillcode;
    private Timestamp outtime;
    private String handler;
    private Byte outtype;
    private BigDecimal totalmoney;
    private String remark;
//    private Collection<TMeOrderInfoEntity> tMeOrderInfosByOutBillCode;
//    private Collection<TMeOutStockDetailsInfoEntity> tMeOutStockDetailsInfosByOutBillCode;
    private TAuOperInfoEntity operid;

    @Basic
    @Column(name = "ID",insertable = false,updatable= false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "OutBillCode")
    public String getOutbillcode() {
        return outbillcode;
    }

    public void setOutbillcode(String outbillcode) {
        this.outbillcode = outbillcode;
    }

    @Basic
    @Column(name = "OutTime")
    @JSON(format = "yyyy-MM-dd")
    public Timestamp getOuttime() {
        return outtime;
    }

    @JSON(format = "yyyy-MM-dd")
    public void setOuttime(Timestamp outtime) {
        this.outtime = outtime;
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
    @Column(name = "OutType")
    public Byte getOuttype() {
        return outtype;
    }

    public void setOuttype(Byte outtype) {
        this.outtype = outtype;
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

        TMeOutStockInfoEntity that = (TMeOutStockInfoEntity) o;

        if (id != that.id) return false;
        if (handler != null ? !handler.equals(that.handler) : that.handler != null) return false;
        if (outbillcode != null ? !outbillcode.equals(that.outbillcode) : that.outbillcode != null) return false;
        if (outtime != null ? !outtime.equals(that.outtime) : that.outtime != null) return false;
        if (outtype != null ? !outtype.equals(that.outtype) : that.outtype != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (totalmoney != null ? !totalmoney.equals(that.totalmoney) : that.totalmoney != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (outbillcode != null ? outbillcode.hashCode() : 0);
        result = 31 * result + (outtime != null ? outtime.hashCode() : 0);
        result = 31 * result + (handler != null ? handler.hashCode() : 0);
        result = 31 * result + (outtype != null ? outtype.hashCode() : 0);
        result = 31 * result + (totalmoney != null ? totalmoney.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "tMeOutStockInfoByOutBillCode")
//    public Collection<TMeOrderInfoEntity> gettMeOrderInfosByOutBillCode() {
//        return tMeOrderInfosByOutBillCode;
//    }
//
//    public void settMeOrderInfosByOutBillCode(Collection<TMeOrderInfoEntity> tMeOrderInfosByOutBillCode) {
//        this.tMeOrderInfosByOutBillCode = tMeOrderInfosByOutBillCode;
//    }
//
//    @OneToMany(mappedBy = "tMeOutStockInfoByOutBillCode")
//    public Collection<TMeOutStockDetailsInfoEntity> gettMeOutStockDetailsInfosByOutBillCode() {
//        return tMeOutStockDetailsInfosByOutBillCode;
//    }
//
//    public void settMeOutStockDetailsInfosByOutBillCode(Collection<TMeOutStockDetailsInfoEntity> tMeOutStockDetailsInfosByOutBillCode) {
//        this.tMeOutStockDetailsInfosByOutBillCode = tMeOutStockDetailsInfosByOutBillCode;
//    }

    @ManyToOne
    @JoinColumn(name = "OperID", referencedColumnName = "OperID")
    public TAuOperInfoEntity getOperid() {
        return operid;
    }

    public void setOperid(TAuOperInfoEntity operid) {
        this.operid = operid;
    }
}
