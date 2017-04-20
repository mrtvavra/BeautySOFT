/**
 *
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.BackingBeans;

import java.beans.Statement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.me.EntityBeans.Customers;
import org.me.EntityBeans.Bookings;
import org.me.EntityBeans.Employees;
import org.me.SessionBeans.BookingsFacadeLocal;
import org.me.SessionBeans.CustomersFacadeLocal;
import org.me.SessionBeans.EmployeesFacadeLocal;


@ManagedBean
@SessionScoped
public class SalonCoordination {

    @EJB
    private CustomersFacadeLocal cfl;
    @EJB
    private BookingsFacadeLocal bfl;
    @EJB
    private EmployeesFacadeLocal efl;
    @PersistenceContext
    private EntityManager em;

    public SalonCoordination() {
    }

    public UserBean getUserBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserBean userBean = (UserBean) context.getApplication().evaluateExpressionGet(context, "#{userBean}", UserBean.class);
        return userBean;
    }

    public BookingBean getBookingBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        BookingBean bookingBean = (BookingBean) context.getApplication().evaluateExpressionGet(context, "#{bookingBean}", BookingBean.class);
        return bookingBean;
    }

    public BookingsDataTable getBookingsDataTable() {
        FacesContext context = FacesContext.getCurrentInstance();
        BookingsDataTable dbBookingsDataTable = (BookingsDataTable) context.getApplication().evaluateExpressionGet(context, "#{dbBookingsDataTable}", BookingsDataTable.class);
        return dbBookingsDataTable;
    }

    public TreatmentBean getTreatmentBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        TreatmentBean treatmentBean = (TreatmentBean) context.getApplication().evaluateExpressionGet(context, "#{treatmentBean}", TreatmentBean.class);
        return treatmentBean;
    }

    public void logIn() {

        UserBean userBean = this.getUserBean();

        if (userBean.getTypeOfUser().equals("customer")) {
            Customers c = this.getCustomerByEmail(userBean.getUserEmail());
            if (c != null) {
                if (userBean.getUserEmail().equals(c.getCustEmail())) {
                    if (c.getCustPass().equals(userBean.getUserPassword())) {
                        String uri = "customerAccountInfo.xhtml";
                        try {
                            FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
                        } catch (IOException ex) {
                            Logger.getLogger(SalonCoordination.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }

        if (userBean.getTypeOfUser().equals("employee")) {
            Employees e = this.getEmployeeByEmail(userBean.getUserEmail());
            if (e != null) {
                if (userBean.getUserEmail().equals(e.getEmpEmail())) {
                    if (e.getEmpPass().equals(userBean.getUserPassword())) {
                        String uri = "employeeAccountInfo.xhtml";
                        try {
                            FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
                        } catch (IOException ex) {
                            Logger.getLogger(SalonCoordination.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }
        }
        this.emptyText();
        userBean.setReturnMsg("Invalid user, please enter correct login name and password!");

    }

    public void logOff() throws IOException {
        this.emptyText();
        String uri = "welcomePage.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
        this.getUserBean().setUserEmail(null);
        this.getUserBean().setUserPassword(null);
    }

    public void createAccount() throws IOException {
        UserBean userBean = this.getUserBean();

        Customers c = new Customers(userBean.getUserEmail(), userBean.getUserName(), userBean.getUserPassword());
        cfl.create(c);
        BookingBean bb = this.getBookingBean();
        bb.setSuccessfulBooking("");
        String uri = "customerAccountInfo.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);

    }

    public void makeBooking() throws IOException {
        this.getBookingBean().makeNewBooking();
    }

    public void deleteBooking(int ID) throws IOException {
        this.getBookingBean().deleteExistingBooking(ID);
    }

    public void deleteAccount() throws IOException {
        UserBean userBean = this.getUserBean();
        Customers c = this.getCustomerByEmail(userBean.getUserEmail());
        cfl.remove(c);
        this.emptyText();
        String uri = "welcomePage.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
    }

    /* This method will be used for changing account information function
     and ensure that the user is not redirected to welcomePage.xhtml
     */
    public void deleteAccountOnly() throws IOException {
        UserBean userBean = this.getUserBean();
        Customers c = this.getCustomerByEmail(userBean.getUserEmail());
        cfl.remove(c);
    }

    public void deleteEmpAccount() throws IOException {
        UserBean userBean = this.getUserBean();
        Employees e = this.getEmployeeByEmail(userBean.getUserEmail());
        efl.remove(e);
        this.emptyText();
        String uri = "welcomePage.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
    }

    public void emptyText() {
        UserBean userBean = this.getUserBean();
        userBean.setUserEmail("");
        userBean.setUserPassword("");
        userBean.setReturnMsg("");
        userBean.setUserName("");

    }

    public void changeAccount() throws IOException {
        
        Customers c = this.getCustomerByEmail(this.getUserBean().getUserEmail());
        c.setCustName(this.getUserBean().getUserNameChange());
        c.setCustEmail(this.getUserBean().getUserEmailChange());
        c.setCustPass(this.getUserBean().getUserPasswordChange());
        cfl.edit(c);
        this.getUserBean().setUserEmail(this.getUserBean().getUserEmailChange());
        this.getUserBean().setUserPassword(this.getUserBean().getUserPasswordChange());
        this.getUserBean().setUserName(this.getUserBean().getUserNameChange());
        this.backToHomePage();

    }


    /*
     This method will check how many treatments are booked for selected date and time
     */
    public Boolean checkNumberOfBookings(Date date, String time) {
        int limitBookingsPerHour = 3;//this number represents how many treatments can be perfomed in one hour!
        Boolean check = true;
        List<Bookings> result = new ArrayList<>();
        List<Bookings> finalResult = new ArrayList<>();
        result = this.getBookingsDataTable().findByDate(date);
        if (result.isEmpty()) {
            return check;
        } else {
            for (Bookings eachBooking : result) {
                if (eachBooking.getTime().equals(time)) {
                    finalResult.add(eachBooking);
                }

            }
            if (finalResult.size() >= limitBookingsPerHour) {
                check = false;
            }
        }
        return check;

    }

    public void backToHomePage() throws IOException {
        BookingBean bb = this.getBookingBean();
        bb.setSuccessfulBooking("");
        String uri = "customerAccountInfo.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
    }

    public Customers getCustomerByEmail(String custEmail) {
        Query query = em.createNamedQuery("Customers.findByCustEmail");
        query.setParameter("custEmail", custEmail);
        List<Customers> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }

    }

    public Employees getEmployeeByEmail(String empEmail) {
        Query query = em.createNamedQuery("Employees.findByEmpEmail");
        query.setParameter("empEmail", empEmail);
        List<Employees> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }

    }

}
