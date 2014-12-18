package com.shinow.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TAu_Authorization")
public class TAuAuthorizationEntity {
    private Integer id;
    private Boolean isenabled;
    private TAuMenuInfoEntity menuid;
    private TAuRoleInfoEntity roleid;

    @Id
    @Column(name = "ID",insertable = false,updatable =false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IsEnabled")
    public Boolean getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(Boolean isenabled) {
        this.isenabled = isenabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAuAuthorizationEntity that = (TAuAuthorizationEntity) o;

        if (id != that.id) return false;
        if (isenabled != null ? !isenabled.equals(that.isenabled) : that.isenabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isenabled != null ? isenabled.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "MenuID", referencedColumnName = "MenuID")
    public TAuMenuInfoEntity getMenuid() {
        return menuid;
    }

    public void setMenuid(TAuMenuInfoEntity menuid) {
        this.menuid = menuid;
    }

    @ManyToOne
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID")
    public TAuRoleInfoEntity getRoleid() {
        return roleid;
    }

    public void setRoleid(TAuRoleInfoEntity roleid) {
        this.roleid = roleid;
    }
}
