package com.shinow.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TMe_OutStockDetailsInfo")
public class TMeOutStockDetailsInfoEntity {
    private Integer id;
    private Integer num;
    private BigDecimal price;
    private BigDecimal stockprice;
    private TMeMerchandiseInfoEntity merchandiseid;
    private TMeOutStockInfoEntity outbillcode;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
    @Column(name = "stock_price")
    public BigDecimal getStockprice() {
        return stockprice;
    }

    public void setStockprice(BigDecimal stockprice) {
        this.stockprice = stockprice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TMeOutStockDetailsInfoEntity that = (TMeOutStockDetailsInfoEntity) o;

        if (id != that.id) return false;
        if (num != null ? !num.equals(that.num) : that.num != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (stockprice != null ? !stockprice.equals(that.stockprice) : that.stockprice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (stockprice != null ? stockprice.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "MerchandiseID", referencedColumnName = "MerchandiseID")
    public TMeMerchandiseInfoEntity getMerchandiseid() {
        return merchandiseid;
    }

    public void setMerchandiseid(TMeMerchandiseInfoEntity merchandiseid) {
        this.merchandiseid = merchandiseid;
    }

    @ManyToOne
    @JoinColumn(name = "OutBillCode", referencedColumnName = "OutBillCode")
    public TMeOutStockInfoEntity getOutbillcode() {
        return outbillcode;
    }

    public void setOutbillcode(TMeOutStockInfoEntity outbillcode) {
        this.outbillcode = outbillcode;
    }
}
