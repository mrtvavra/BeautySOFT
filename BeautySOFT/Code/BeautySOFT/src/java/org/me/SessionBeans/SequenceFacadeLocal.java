/**
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.SessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.me.EntityBeans.Sequence;


@Local
public interface SequenceFacadeLocal {

    void create(Sequence sequence);

    void edit(Sequence sequence);

    void remove(Sequence sequence);

    Sequence find(Object id);

    List<Sequence> findAll();

    List<Sequence> findRange(int[] range);

    int count();
    
}
