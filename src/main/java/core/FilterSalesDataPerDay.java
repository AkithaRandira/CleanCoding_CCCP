package core;

import java.util.List;

public interface FilterSalesDataPerDay {
    List<Bill> FilterBillsByDay(List<Bill> bills);

}