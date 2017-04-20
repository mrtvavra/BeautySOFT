/**
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.SessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.me.EntityBeans.Employees;


@Local
public interface EmployeesFacadeLocal {

    void create(Employees employees);

    void edit(Employees employees);

    void remove(Employees employees);

    Employees find(Object id);

    List<Employees> findAll();

    List<Employees> findRange(int[] range);

    int count();
    
}
