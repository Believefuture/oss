package com.shinow.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TAu_RoleInfo")
public class TAuRoleInfoEntity {
    private Integer id;
    private String roleid;
    private String rolename;
    private Integer sortid;
    private Boolean state;
//    private Collection<TAuAuthorizationEntity> tAuAuthorizationsByRoleId;
//    private Collection<TAuOperInfoEntity> tAuOperInfosByRoleId;

    @Basic
    @Column(name = "ID",updatable = false,insertable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "RoleID")
    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleId) {
        this.roleid = roleId;
    }

    @Basic
    @Column(name = "RoleName")
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
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

        TAuRoleInfoEntity that = (TAuRoleInfoEntity) o;

        if (id != that.id) return false;
        if (roleid != null ? !roleid.equals(that.roleid) : that.roleid != null) return false;
        if (rolename != null ? !rolename.equals(that.rolename) : that.rolename != null) return false;
        if (sortid != null ? !sortid.equals(that.sortid) : that.sortid != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleid != null ? roleid.hashCode() : 0);
        result = 31 * result + (rolename != null ? rolename.hashCode() : 0);
        result = 31 * result + (sortid != null ? sortid.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "tAuRoleInfoByRoleId")
//    public Collection<TAuAuthorizationEntity> gettAuAuthorizationsByRoleId() {
//        return tAuAuthorizationsByRoleId;
//    }
//
//    public void settAuAuthorizationsByRoleId(Collection<TAuAuthorizationEntity> tAuAuthorizationsByRoleId) {
//        this.tAuAuthorizationsByRoleId = tAuAuthorizationsByRoleId;
//    }
//
//    @OneToMany(mappedBy = "tAuRoleInfoByRoleId")
//    public Collection<TAuOperInfoEntity> gettAuOperInfosByRoleId() {
//        return tAuOperInfosByRoleId;
//    }
//
//    public void settAuOperInfosByRoleId(Collection<TAuOperInfoEntity> tAuOperInfosByRoleId) {
//        this.tAuOperInfosByRoleId = tAuOperInfosByRoleId;
//    }
}
