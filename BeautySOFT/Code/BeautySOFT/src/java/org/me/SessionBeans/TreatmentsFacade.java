/**
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.SessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.me.EntityBeans.Treatments;

@Stateless
public class TreatmentsFacade extends AbstractFacade<Treatments> implements TreatmentsFacadeLocal {
    @PersistenceContext(unitName = "BeautySOFTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TreatmentsFacade() {
        super(Treatments.class);
    }
    
}
