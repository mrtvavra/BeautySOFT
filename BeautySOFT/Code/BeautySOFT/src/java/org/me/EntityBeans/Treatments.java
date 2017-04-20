/**
 *
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.EntityBeans;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "TREATMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Treatments.findAll", query = "SELECT t FROM Treatments t"),
    @NamedQuery(name = "Treatments.findByTreatId", query = "SELECT t FROM Treatments t WHERE t.treatId = :treatId"),
    @NamedQuery(name = "Treatments.findByTreatDes", query = "SELECT t FROM Treatments t WHERE t.treatDes = :treatDes"),
    @NamedQuery(name = "Treatments.findByPrice", query = "SELECT t FROM Treatments t WHERE t.price = :price")})
public class Treatments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TREAT_ID")
    private Integer treatId;
    @Size(max = 100)
    @Column(name = "TREAT_DES")
    private String treatDes;
    @Column(name = "PRICE")
    private Integer price;

    public Treatments() {
    }

    public Treatments(Integer treatId) {
        this.treatId = treatId;
    }

    public Integer getTreatId() {
        return treatId;
    }

    public void setTreatId(Integer treatId) {
        this.treatId = treatId;
    }

    public String getTreatDes() {
        return treatDes;
    }

    public void setTreatDes(String treatDes) {
        this.treatDes = treatDes;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (treatId != null ? treatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treatments)) {
            return false;
        }
        Treatments other = (Treatments) object;
        if ((this.treatId == null && other.treatId != null) || (this.treatId != null && !this.treatId.equals(other.treatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.me.EntityBeans.Treatments[ treatId=" + treatId + " ]";
    }
    
}
