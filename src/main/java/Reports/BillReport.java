package Reports;

import java.util.ArrayList;
import core.Report;
import core.Bill;
import core.BillIterator;
import core.BillRepository;

import java.util.List;

public class BillReport extends Report  {

    private List<Bill> bills=new ArrayList<>();


    @Override
    protected void getData() {
        System.out.println("Getting data for Bill Report");
        BillRepository billRepository=new BillRepository();
        BillIterator billIterator=new BillIterator(bills,billRepository);
        billIterator.loadBills();
    }

    @Override
    protected void createReport() {


        System.out.println("========Creating Bill Report========");
        for(Bill bill: bills){
            System.out.println("Bill Serial Number: "+bill.getBillSerialNumber());
            System.out.println("Net Total: "+bill.getNetTotal());
            System.out.println("Discount: "+bill.getDiscount());
            System.out.println("Cash Tendered: "+bill.getCashTendered());
            System.out.println("Change Amount: "+bill.getChangeAmount());
            System.out.println("Sub Total: "+bill.getSubTotal());
            System.out.println("Date of Bill: "+bill.getDateOfBill());
            System.out.println("Total Quantities Sold: "+bill.getTotalQuantitiesSold());
            System.out.println("Customer: "+bill.getCustomerName());
            System.out.println("Bill Item: ");
            for(int i=0;i<bill.getBillItems().size();i++){
                System.out.println("Item Code: "+bill.getBillItems().get(i).getItemCode());
                System.out.println("Item Name: "+bill.getBillItems().get(i).getItemName());
                System.out.println("Item Quantity: "+bill.getBillItems().get(i).getQuantity());
                System.out.println("Item Price: "+bill.getBillItems().get(i).getUnitPrice());
                System.out.println("Item Total: "+bill.getBillItems().get(i).getTotalPrice());
            }
            System.out.println("---------------------------------------------");
        }
    }



}
