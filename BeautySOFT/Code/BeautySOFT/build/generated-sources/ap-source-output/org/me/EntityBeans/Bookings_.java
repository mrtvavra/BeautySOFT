package org.me.EntityBeans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-19T22:52:30")
@StaticMetamodel(Bookings.class)
public class Bookings_ { 

    public static volatile SingularAttribute<Bookings, Date> date;
    public static volatile SingularAttribute<Bookings, Integer> custId;
    public static volatile SingularAttribute<Bookings, String> time;
    public static volatile SingularAttribute<Bookings, Integer> treatId;
    public static volatile SingularAttribute<Bookings, Integer> bookingId;

}