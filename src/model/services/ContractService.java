package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months){
        Calendar cal = Calendar.getInstance();

        double basicQuota = contract.getTotalValue() / months;

        for(int i = 1; i <= months; i++){
            double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
            double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
            cal.setTime(contract.getDate());
            cal.add(Calendar.MONTH, i);
            Date date = cal.getTime();
            contract.getInstalments().add(new Installment(date, fullQuota));
        }
    }
}
