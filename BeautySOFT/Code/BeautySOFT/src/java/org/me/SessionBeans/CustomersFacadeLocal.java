/**
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.SessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.me.EntityBeans.Customers;


@Local
public interface CustomersFacadeLocal {

    void create(Customers customers);

    void edit(Customers customers);

    void remove(Customers customers);

    Customers find(Object id);

    List<Customers> findAll();

    List<Customers> findRange(int[] range);

    int count();
    
}
