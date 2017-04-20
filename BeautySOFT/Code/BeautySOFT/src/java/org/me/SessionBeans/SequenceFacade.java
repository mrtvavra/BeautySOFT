/**
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.SessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.me.EntityBeans.Sequence;


@Stateless
public class SequenceFacade extends AbstractFacade<Sequence> implements SequenceFacadeLocal {
    @PersistenceContext(unitName = "BeautySOFTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SequenceFacade() {
        super(Sequence.class);
    }
    
}
