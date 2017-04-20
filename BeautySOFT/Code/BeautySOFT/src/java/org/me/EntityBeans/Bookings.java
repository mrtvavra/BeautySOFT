/**
 *
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "BOOKINGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bookings.findAll", query = "SELECT b FROM Bookings b"),
    @NamedQuery(name = "Bookings.findByBookingId", query = "SELECT b FROM Bookings b WHERE b.bookingId = :bookingId"),
    @NamedQuery(name = "Bookings.findByDate", query = "SELECT b FROM Bookings b WHERE b.date = :date"),
    @NamedQuery(name = "Bookings.findByTime", query = "SELECT b FROM Bookings b WHERE b.time = :time"),
    @NamedQuery(name = "Bookings.findByCustId", query = "SELECT b FROM Bookings b WHERE b.custId = :custId"),
    @NamedQuery(name = "Bookings.findByTreatId", query = "SELECT b FROM Bookings b WHERE b.treatId = :treatId")})
public class Bookings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BOOKING_ID")
    private Integer bookingId;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 40)
    @Column(name = "TIME")
    private String time;
    @Column(name = "CUST_ID")
    private Integer custId;
    @Column(name = "TREAT_ID")
    private Integer treatId;

    public Bookings() {
    }
    
     public Bookings(Date date, String time, int cust_id, int treat_id) {
         this.custId = cust_id;
         this.date=date;
         this.time=time;
         this.treatId=treat_id;
    }

    public Bookings(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getTreatId() {
        return treatId;
    }

    public void setTreatId(Integer treatId) {
        this.treatId = treatId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingId != null ? bookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookings)) {
            return false;
        }
        Bookings other = (Bookings) object;
        if ((this.bookingId == null && other.bookingId != null) || (this.bookingId != null && !this.bookingId.equals(other.bookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.me.EntityBeans.Bookings[ bookingId=" + bookingId + " ]";
    }
    
}
