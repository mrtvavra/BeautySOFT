/**
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.BackingBeans;

import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.me.EntityBeans.Bookings;
import org.me.EntityBeans.Customers;
import org.me.SessionBeans.BookingsFacadeLocal;
import org.me.SessionBeans.CustomersFacadeLocal;
import org.me.SessionBeans.TreatmentsFacadeLocal;


@ManagedBean
@SessionScoped
public class BookingBean {
    
 private int bookingId;
 private Date date;
 private String time;
 private String successfulBooking;
 
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

    public String getSuccessfulBooking() {
        return successfulBooking;
    }

    public void setSuccessfulBooking(String successfulBooking) {
        this.successfulBooking = successfulBooking;
    }
 
 public BookingBean() {
    }

   BookingBean(int ID, Date date, String time) {
        this.bookingId= ID;
        this.date = date;
        this.time = time;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
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
    protected void makeNewBooking() throws IOException {
        TreatmentBean tb = this.getTreatmentBean();
        UserBean ub = this.getUserBean();
        BookingBean bb = this.getBookingBean();
        SalonCoordination sc = this.getSalonCoordinationtBean();
        Customers c = sc.getCustomerByEmail(ub.getUserEmail());
        if (this.getSalonCoordinationtBean().checkNumberOfBookings(bb.getDate(), bb.getTime())) {
            Bookings b = new Bookings(bb.getDate(), bb.getTime(), c.getCustId(), Integer.parseInt(tb.getTreatId()));
            bfl.create(b);
            bb.setDate(null);
            bb.setSuccessfulBooking("Your booking was successfully created!");
            String uri = "customerAccountInfo.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
        }
        String uri = "error.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
    }
    
     protected void deleteExistingBooking(int ID) throws IOException {
        Bookings b = bfl.find(ID);
        bfl.remove(b);
        BookingBean bb = this.getBookingBean();
        bb.setSuccessfulBooking("Your booking was successfully deleted!");
        String uri = "customerAccountInfo.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);

    }

   
 
    
}
