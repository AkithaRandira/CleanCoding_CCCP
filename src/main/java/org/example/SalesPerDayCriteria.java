package org.example;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesPerDayCriteria implements FilterSalesDataPerDay{
    @Override
    public List<Bill> FilterBillsByDay(List<Bill> bills) {

        List<Bill> filteredBills=new ArrayList<>();

        Date currentDate=Date.valueOf(LocalDate.now());
        for(Bill bill: bills){
            if(bill.getDateOfBill().equals(currentDate)){
                filteredBills.add(bill);
            }
        }
        return filteredBills;
    }
}
