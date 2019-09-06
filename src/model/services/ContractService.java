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

        for(int i = 1; i <= months; i++){
            double value = 0;
            value += onlinePaymentService.interest(contract.getTotalValue()/months, i);
            value = onlinePaymentService.paymentFee(value);
            cal.setTime(contract.getDate());
            cal.add(Calendar.MONTH, i);
            Date date = cal.getTime();
            contract.getInstalments().add(new Installment(date, value));
        }
    }
}
