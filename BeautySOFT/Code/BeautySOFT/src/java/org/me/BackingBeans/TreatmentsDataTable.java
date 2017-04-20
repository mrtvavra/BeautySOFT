/**
 *
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.BackingBeans;

import org.me.UtilityClasses.TreatmentsComparator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.me.EntityBeans.Treatments;
import org.me.SessionBeans.TreatmentsFacadeLocal;



@ManagedBean(name="dbTreatmentsDataTable")
@SessionScoped
public class TreatmentsDataTable implements Serializable{
 
    private List<Treatments> treatments = new ArrayList<>();
    @EJB
    private TreatmentsFacadeLocal tfl;
   

    public List<Treatments> getTreatments() {
        treatments = tfl.findAll();
        Collections.sort(treatments, new TreatmentsComparator());
        return treatments;
    }

    public void setTreatments(List<Treatments> treatments) {
        this.treatments = treatments;
    }
    
    
}

   
    
  
    
    
            
    
    
    

