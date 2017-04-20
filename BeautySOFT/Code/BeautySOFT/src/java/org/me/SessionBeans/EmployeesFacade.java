/**
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.SessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.me.EntityBeans.Employees;


@Stateless
public class EmployeesFacade extends AbstractFacade<Employees> implements EmployeesFacadeLocal {
    @PersistenceContext(unitName = "BeautySOFTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeesFacade() {
        super(Employees.class);
    }
    
}
