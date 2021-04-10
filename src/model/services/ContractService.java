package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ContractService {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months) {

        double instalments = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++) {
            Date date = workDate(contract.getDate(), i);
            double quotaInstalments = instalments + onlinePaymentService.interest(instalments, i);
            double totalInstalments = quotaInstalments + onlinePaymentService.paymentFee(quotaInstalments);

            contract.getInstallments().add(new Installment(date, totalInstalments));

        }

    }

    private Date workDate(Date date, int p) {
        Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, p);
            return cal.getTime();

    }


}
