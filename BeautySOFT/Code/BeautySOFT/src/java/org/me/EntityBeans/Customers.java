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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "CUSTOMERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c"),
    @NamedQuery(name = "Customers.findByCustId", query = "SELECT c FROM Customers c WHERE c.custId = :custId"),
    @NamedQuery(name = "Customers.findByCustEmail", query = "SELECT c FROM Customers c WHERE c.custEmail = :custEmail"),
    @NamedQuery(name = "Customers.findByCustName", query = "SELECT c FROM Customers c WHERE c.custName = :custName"),
    @NamedQuery(name = "Customers.findByCustPass", query = "SELECT c FROM Customers c WHERE c.custPass = :custPass")})
public class Customers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CUST_ID")
    private Integer custId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "CUST_EMAIL")
    private String custEmail;
    @Size(max = 40)
    @Column(name = "CUST_NAME")
    private String custName;
    @Size(max = 40)
    @Column(name = "CUST_PASS")
    private String custPass;

    public Customers() {
    }
     public Customers(String email, String name, String pass) {
         this.custEmail = email;
         this.custName = name;
         this.custPass = pass;
    }

    public Customers(Integer custId) {
        this.custId = custId;
    }

    public Customers(Integer custId, String custEmail) {
        this.custId = custId;
        this.custEmail = custEmail;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPass() {
        return custPass;
    }

    public void setCustPass(String custPass) {
        this.custPass = custPass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custId != null ? custId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.me.EntityBeans.Customers[ custId=" + custId + " ]";
    }
    
    
    
}
