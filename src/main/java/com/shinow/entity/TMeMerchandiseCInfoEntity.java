package com.shinow.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Administrator on 2014-11-12.
 */
@Entity
@Table(name = "TMe_MerchandiseCInfo")
public class TMeMerchandiseCInfoEntity {
    private Integer id;
    private String merchandisecid;
    private String merchandisecname;
    private Integer sortid;
    private Boolean state;
//    private Collection<TMeMerchandiseInfoEntity> tMeMerchandiseInfosByMerchandiseCid;

    @Basic
    @Column(name = "ID",insertable = false,updatable= false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "MerchandiseCID")
    public String getMerchandisecid() {
        return merchandisecid;
    }

    public void setMerchandisecid(String merchandisecid) {
        this.merchandisecid = merchandisecid;
    }

    @Basic
    @Column(name = "MerchandiseCName")
    public String getMerchandisecname() {
        return merchandisecname;
    }

    public void setMerchandisecname(String merchandisecname) {
        this.merchandisecname = merchandisecname;
    }

    @Basic
    @Column(name = "SortID")
    public Integer getSortid() {
        return sortid;
    }

    public void setSortid(Integer sortid) {
        this.sortid = sortid;
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

        TMeMerchandiseCInfoEntity that = (TMeMerchandiseCInfoEntity) o;

        if (id != that.id) return false;
        if (merchandisecname != null ? !merchandisecname.equals(that.merchandisecname) : that.merchandisecname != null)
            return false;
        if (merchandisecid != null ? !merchandisecid.equals(that.merchandisecid) : that.merchandisecid != null)
            return false;
        if (sortid != null ? !sortid.equals(that.sortid) : that.sortid != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (merchandisecid != null ? merchandisecid.hashCode() : 0);
        result = 31 * result + (merchandisecname != null ? merchandisecname.hashCode() : 0);
        result = 31 * result + (sortid != null ? sortid.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "tMeMerchandiseCInfoByMerchandiseCid")
//    public Collection<TMeMerchandiseInfoEntity> gettMeMerchandiseInfosByMerchandiseCid() {
//        return tMeMerchandiseInfosByMerchandiseCid;
//    }
//
//    public void settMeMerchandiseInfosByMerchandiseCid(Collection<TMeMerchandiseInfoEntity> tMeMerchandiseInfosByMerchandiseCid) {
//        this.tMeMerchandiseInfosByMerchandiseCid = tMeMerchandiseInfosByMerchandiseCid;
//    }
}
