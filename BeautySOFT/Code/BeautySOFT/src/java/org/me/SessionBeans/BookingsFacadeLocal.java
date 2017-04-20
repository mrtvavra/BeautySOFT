/**
 *
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.SessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.me.EntityBeans.Bookings;


@Local
public interface BookingsFacadeLocal {

    void create(Bookings bookings);

    void edit(Bookings bookings);

    void remove(Bookings bookings);

    Bookings find(Object id);

    List<Bookings> findAll();

    List<Bookings> findRange(int[] range);

    int count();
    
}
