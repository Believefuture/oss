package com.shinow.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TMe_StockInfo")
public class TMeStockInfoEntity {
    private Integer id;
    private BigDecimal avgPrice;
    private Integer num;
    private TMeMerchandiseInfoEntity tMeMerchandiseInfoByMerchandiseId;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AvgPrice")
    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    @Basic
    @Column(name = "Num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TMeStockInfoEntity that = (TMeStockInfoEntity) o;

        if (id != that.id) return false;
        if (num != that.num) return false;
        if (avgPrice != null ? !avgPrice.equals(that.avgPrice) : that.avgPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (avgPrice != null ? avgPrice.hashCode() : 0);
        result = 31 * result + num;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "MerchandiseID", referencedColumnName = "MerchandiseID")
    public TMeMerchandiseInfoEntity gettMeMerchandiseInfoByMerchandiseId() {
        return tMeMerchandiseInfoByMerchandiseId;
    }

    public void settMeMerchandiseInfoByMerchandiseId(TMeMerchandiseInfoEntity tMeMerchandiseInfoByMerchandiseId) {
        this.tMeMerchandiseInfoByMerchandiseId = tMeMerchandiseInfoByMerchandiseId;
    }
}
