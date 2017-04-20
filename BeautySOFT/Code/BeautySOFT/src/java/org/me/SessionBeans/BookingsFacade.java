/**
 *
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.SessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.me.EntityBeans.Bookings;


@Stateless
public class BookingsFacade extends AbstractFacade<Bookings> implements BookingsFacadeLocal {
    @PersistenceContext(unitName = "BeautySOFTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookingsFacade() {
        super(Bookings.class);
    }
    
}
