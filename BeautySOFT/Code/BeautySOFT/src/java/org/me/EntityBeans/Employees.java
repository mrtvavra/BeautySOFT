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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EMPLOYEES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e"),
    @NamedQuery(name = "Employees.findByEmpId", query = "SELECT e FROM Employees e WHERE e.empId = :empId"),
    @NamedQuery(name = "Employees.findByEmpEmail", query = "SELECT e FROM Employees e WHERE e.empEmail = :empEmail"),
    @NamedQuery(name = "Employees.findByEmpName", query = "SELECT e FROM Employees e WHERE e.empName = :empName"),
    @NamedQuery(name = "Employees.findByEmpPass", query = "SELECT e FROM Employees e WHERE e.empPass = :empPass")})
public class Employees implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EMP_ID")
    private Integer empId;
    @Size(max = 40)
    @Column(name = "EMP_EMAIL")
    private String empEmail;
    @Size(max = 40)
    @Column(name = "EMP_NAME")
    private String empName;
    @Size(max = 40)
    @Column(name = "EMP_PASS")
    private String empPass;

    public Employees() {
    }

    public Employees(Integer empId) {
        this.empId = empId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.me.EntityBeans.Employees[ empId=" + empId + " ]";
    }
    
}
