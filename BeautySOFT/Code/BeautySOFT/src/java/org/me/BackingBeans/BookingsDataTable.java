/**
 *
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.BackingBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.me.EntityBeans.Bookings;
import org.me.EntityBeans.Customers;
import org.me.EntityBeans.Treatments;
import org.me.SessionBeans.BookingsFacadeLocal;
import org.me.SessionBeans.CustomersFacadeLocal;
import org.me.SessionBeans.TreatmentsFacadeLocal;


@ManagedBean(name = "dbBookingsDataTable")
@SessionScoped
public class BookingsDataTable implements Serializable {

    private List<Bookings> bookings;
    private List<Bookings> bookingsByDate;
    @PersistenceContext
    public EntityManager em;
    @EJB
    private BookingsFacadeLocal bfl;
    @EJB
    private CustomersFacadeLocal cfl;
    @EJB
    private TreatmentsFacadeLocal tfl;
    private Bookings selectedBooking;

    private UserBean getUserBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserBean userBean = (UserBean) context.getApplication().evaluateExpressionGet(context, "#{userBean}", UserBean.class);
        return userBean;
    }

    private TreatmentBean getTreatmentBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        TreatmentBean treatmenBean = (TreatmentBean) context.getApplication().evaluateExpressionGet(context, "#{treatmentBean}", TreatmentBean.class);
        return treatmenBean;
    }
     
    private SalonCoordination getSalonCoordinationtBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        SalonCoordination salonCoordination = (SalonCoordination) context.getApplication().evaluateExpressionGet(context, "#{salonCoordination}", SalonCoordination.class);
        return salonCoordination;
    }

    private BookingBean getBookingBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        BookingBean bookingBean = (BookingBean) context.getApplication().evaluateExpressionGet(context, "#{bookingBean}", BookingBean.class);
        return bookingBean;
    }

    public List<Bookings> getBookingsForCustomer() {
        String email  = this.getUserBean().getUserEmail();
        Customers c = this.getSalonCoordinationtBean().getCustomerByEmail(email);
        return bookings = this.getBookingsForCustEmail(c.getCustId());
        
        //return bookings = this.getBookingsForCustEmail(((this.getSalonCoordinationtBean().getCustomerByEmail(getUserBean().getUserEmail())).getCustId()));
    }

    public List<Bookings> getBookingsForDate() {
        return bookingsByDate = this.findByDate(this.getBookingBean().getDate());
    }

    public void setBookings(List<Bookings> bookings) {
        this.bookings = bookings;
    }

    public List<Bookings> getBookingsForCustEmail(int custID) {

        Query query = em.createNamedQuery("Bookings.findByCustId");
        query.setParameter("custId", custID);
        List<Bookings> result = query.getResultList();
        return result;
    }

    public List<Bookings> findByDate(Date date) {

        Query query = em.createNamedQuery("Bookings.findByDate");
        query.setParameter("date", date);
        List<Bookings> result = query.getResultList();
        return result;
    }

    public String getNameForBooking(String email) {
        
        Customers c = this.getSalonCoordinationtBean().getCustomerByEmail(email);
        return c.getCustName();

    }
    
    
    public String getEmailForCust(int ID){
        Customers c = cfl.find(ID);
        return c.getCustEmail();
    }
    
    public String getNameForBookingByID(int ID){
        Customers c = cfl.find(ID);
        return c.getCustName();
    }

    public String getTreatmentDes(int ID) {

        Treatments t = tfl.find(ID);
        return t.getTreatDes();

    }
    public int getTreatmentPrice(int ID) {

        Treatments t = tfl.find(ID);
        return t.getPrice();

    }

    public Bookings getSelectedBooking() {
        return selectedBooking;
    }

    public void setSelectedBooking(Bookings selectedBooking) {
        this.selectedBooking = selectedBooking;
    }
    

}
