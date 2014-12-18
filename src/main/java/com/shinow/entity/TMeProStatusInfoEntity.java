package com.shinow.entity;

import com.alibaba.druid.sql.dialect.sqlserver.ast.SQLServerColumnDefinition;
import com.sun.scenario.effect.Identity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TMe_ProStatusInfo")
public class TMeProStatusInfoEntity {
    private Integer prostatusid;
    private String prostatusname;
    private Boolean status;
    private String remark;
//    private Collection<TMeMerchandiseInfoEntity> tMeMerchandiseInfosByProStatusId;

    @Id
    @Column(name = "ProStatusID" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getProstatusid() {
        return prostatusid;
    }

    public void setProstatusid(Integer prostatusid) {
        this.prostatusid = prostatusid;
    }

    @Basic
    @Column(name = "ProStatusName")
    public String getProstatusname() {
        return prostatusname;
    }

    public void setProstatusname(String prostatusname) {
        this.prostatusname = prostatusname;
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

        TMeProStatusInfoEntity that = (TMeProStatusInfoEntity) o;

        if (prostatusid != that.prostatusid) return false;
        if (prostatusname != null ? !prostatusname.equals(that.prostatusname) : that.prostatusname != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) prostatusid;
        result = 31 * result + (prostatusname != null ? prostatusname.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "tMeProStatusInfoByProStatusId")
//    public Collection<TMeMerchandiseInfoEntity> gettMeMerchandiseInfosByProStatusId() {
//        return tMeMerchandiseInfosByProStatusId;
//    }
//
//    public void settMeMerchandiseInfosByProStatusId(Collection<TMeMerchandiseInfoEntity> tMeMerchandiseInfosByProStatusId) {
//        this.tMeMerchandiseInfosByProStatusId = tMeMerchandiseInfosByProStatusId;
//    }
}
