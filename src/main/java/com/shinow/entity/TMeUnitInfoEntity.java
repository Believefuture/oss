package com.shinow.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TMe_UnitInfo")
public class TMeUnitInfoEntity {
    private Integer unitid;
    private String name;
    private Boolean status;
    private String remark;
//    private Collection<TMeMerchandiseInfoEntity> tMeMerchandiseInfosByUnitId;
//    private Collection<TMeOrderDetailsInfoEntity> tMeOrderDetailsInfosByUnitId;

    @Id
    @Column(name = "UnitID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUnitid() {
        return unitid;
    }

    public void setUnitid(Integer unitid) {
        this.unitid = unitid;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

        TMeUnitInfoEntity that = (TMeUnitInfoEntity) o;

        if (unitid != that.unitid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) unitid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "tMeUnitInfoByUnitId")
//    public Collection<TMeMerchandiseInfoEntity> gettMeMerchandiseInfosByUnitId() {
//        return tMeMerchandiseInfosByUnitId;
//    }
//
//    public void settMeMerchandiseInfosByUnitId(Collection<TMeMerchandiseInfoEntity> tMeMerchandiseInfosByUnitId) {
//        this.tMeMerchandiseInfosByUnitId = tMeMerchandiseInfosByUnitId;
//    }
//
//    @OneToMany(mappedBy = "tMeUnitInfoByUnitId")
//    public Collection<TMeOrderDetailsInfoEntity> gettMeOrderDetailsInfosByUnitId() {
//        return tMeOrderDetailsInfosByUnitId;
//    }

//    public void settMeOrderDetailsInfosByUnitId(Collection<TMeOrderDetailsInfoEntity> tMeOrderDetailsInfosByUnitId) {
//        this.tMeOrderDetailsInfosByUnitId = tMeOrderDetailsInfosByUnitId;
//    }
}
