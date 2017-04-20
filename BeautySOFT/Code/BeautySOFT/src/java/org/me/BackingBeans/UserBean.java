/**
 *
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.BackingBeans;

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
import org.me.EntityBeans.Employees;
import org.me.SessionBeans.CustomersFacadeLocal;
import org.me.SessionBeans.EmployeesFacadeLocal;

@ManagedBean
@SessionScoped
public class UserBean {

    private String userEmail = "";
    private String userPassword = "";
    private String userName;
    private String returnMsg;
    private String custDatabaseName;
    private String empDatabaseName;
    private String typeOfUser;
    private String userEmailChange;
    private String userNameChange;
    private String userPasswordChange;
    
    @PersistenceContext
    public EntityManager em;
    @EJB
    private CustomersFacadeLocal cfl;
    @EJB
    private EmployeesFacadeLocal efl;
    
    private SalonCoordination getSalonCoordinationtBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        SalonCoordination salonCoordination = (SalonCoordination) context.getApplication().evaluateExpressionGet(context, "#{salonCoordination}", SalonCoordination.class);
        return salonCoordination;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCustDatabaseName() {
        
        
       Customers c = this.getSalonCoordinationtBean().getCustomerByEmail(this.getUserEmail());  
        return c.getCustName();
    }

    public String getEmpDatabaseName() {

        Employees e = this.getSalonCoordinationtBean().getEmployeeByEmail(this.getUserEmail());
        return e.getEmpName();
    }

    public String getUserEmailChange() {
        return userEmailChange;
    }

    public void setUserEmailChange(String userEmailChange) {
        this.userEmailChange = userEmailChange;
    }

    public String getUserNameChange() {
        return userNameChange;
    }

    public void setUserNameChange(String userNameChange) {
        this.userNameChange = userNameChange;
    }

    public String getUserPasswordChange() {
        return userPasswordChange;
    }

    public void setUserPasswordChange(String userPasswordChange) {
        this.userPasswordChange = userPasswordChange;
    }

   
}
