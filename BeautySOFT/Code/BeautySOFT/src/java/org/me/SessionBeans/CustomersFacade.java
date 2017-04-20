/**
 *
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.SessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.me.EntityBeans.Customers;


@Stateless
public class CustomersFacade extends AbstractFacade<Customers> implements CustomersFacadeLocal {
    @PersistenceContext(unitName = "BeautySOFTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomersFacade() {
        super(Customers.class);
    }
    
}
