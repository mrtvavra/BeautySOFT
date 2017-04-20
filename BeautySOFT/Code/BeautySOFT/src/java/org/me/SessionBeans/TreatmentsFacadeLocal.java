/**
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.SessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.me.EntityBeans.Treatments;


@Local
public interface TreatmentsFacadeLocal {

    void create(Treatments treatments);

    void edit(Treatments treatments);

    void remove(Treatments treatments);

    Treatments find(Object id);

    List<Treatments> findAll();

    List<Treatments> findRange(int[] range);

    int count();
    
}
