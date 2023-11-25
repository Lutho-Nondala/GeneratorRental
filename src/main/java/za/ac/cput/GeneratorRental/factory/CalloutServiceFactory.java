package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.domain.CalloutService;
import za.ac.cput.GeneratorRental.domain.Sales;

import java.util.Date;
import java.util.Set;

public class CalloutServiceFactory {
    public static CalloutService createCalloutService(Date date_of_callout, Date date_of_collection, Sales sales){
        if (date_of_callout == null){
            throw new IllegalArgumentException("Date of callout is required!");
        }
        if (date_of_collection == null){
            throw new IllegalArgumentException("Date of collection is required!");
        }
        if (sales == null){
            throw new IllegalArgumentException("Sales is required!");
        }

        return new CalloutService.Builder()
                .date_of_callout(date_of_callout)
                .date_of_collection(date_of_collection)
                .sales(sales).
                build();
    }
}
