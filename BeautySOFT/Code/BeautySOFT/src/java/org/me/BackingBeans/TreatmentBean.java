/**
 *
 * @author Martin Vavra
 * @version 1.0
 */
package org.me.BackingBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class TreatmentBean {

    private String treatId;
    private String treatDes;
    private Integer price;
   

    public TreatmentBean() {
    }

    public TreatmentBean(String ID, String description, int price) {
        this.treatId = ID;
        this.treatDes = description;
        this.price = price;
    }


    public String getTreatId() {
        return treatId;
    }

    public void setTreatId(String treatId) {
        this.treatId = treatId;
    }


    public String getTreatDes() {
        return treatDes;
    }

    public void setTreatDes(String treatDes) {
        this.treatDes = treatDes;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
