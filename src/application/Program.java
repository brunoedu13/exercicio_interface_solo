package application;

import jdk.swing.interop.SwingInterOpUtils;
import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.OnlinePaymentService;
import model.services.PaypalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {



    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        //CRIANDO FORMULARIO PARA DADOS DO CONTRATO
        System.out.println("Enter contract data ");
        System.out.print("Number: ");
        int numberContract = sc.nextInt();
        System.out.print("Date (dd/MM/yyyy): ");
        Date date = sdf.parse(sc.next());
        System.out.print("Contract value: ");
        double contractValue = sc.nextDouble();

        //INSTANCIANDO UM CONTRATO

        Contract c1 = new Contract(numberContract,date,contractValue);

        //INFORMANDO QUANTIDADE DE PARCELAS

        System.out.printf("Enter number of installments: ");
        int installments = sc.nextInt();

        //INSTACIANDO UM PROCESSO SERVICE PARA PROCESSAR O CONTRATO

        ContractService s1 = new ContractService(new PaypalService());
        s1.processContract(c1,installments);

        //IMPRESSAO DO RESULTADO DOS VALORES

        System.out.println("Installments: ");
        for(Installment ins: c1.getInstallments()){
            System.out.println(ins);
        }






        sc.close();






      /*  Contract c1 = new Contract(1001,new Date(),600.00);
        ContractService s1 = new ContractService(new PaypalService());
        s1.processContract(c1,3);

        for(Installment l: c1.getInstallments()) {
            System.out.println(l);
        }

       */












    }





}
