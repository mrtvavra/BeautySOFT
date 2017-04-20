/**
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.UtilityClasses;

import java.util.Comparator;
import org.me.EntityBeans.Treatments;


public class TreatmentsComparator implements Comparator<Treatments> {
    
    
    @Override
    public int compare(Treatments o1, Treatments o2) {
        
        return o1.getTreatId().compareTo(o2.getTreatId());
    }
    
}
