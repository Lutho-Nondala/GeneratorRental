package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.Util.Helper;
import za.ac.cput.GeneratorRental.domain.Insurance;
import za.ac.cput.GeneratorRental.domain.Sales;

import java.util.Set;

public class InsuranceFactory {
    public static Insurance createInsurance(String type, double cost_per_day, Set<Sales> sales){
        if (Helper.isNullorEmpty(type)){
            throw new IllegalArgumentException("Insurance type is required!");
        }

        if (!(cost_per_day >= 0) || !(cost_per_day <= 0)){
            throw new IllegalArgumentException("Cost per day is required!");
        }

        if (sales == null){
            throw new IllegalArgumentException("Sale are required!");
        }

        return new Insurance.Builder()
                .type(type)
                .cost_per_day(cost_per_day)
                .sales(sales)
                .build();
    }
}
