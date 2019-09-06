package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PayPalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Contract Data");
        System.out.print("Number: ");
        int number = sc.nextInt();
        sc.nextLine();
        System.out.print("Date (dd/MM/yyyy): ");
        Date date = sdf.parse(sc.nextLine());
        System.out.print("Contract Value: ");
        double value = sc.nextDouble();

        Contract contract = new Contract(number, date, value);

        System.out.print("Enter number of Instalments: ");
        int months = sc.nextInt();

        ContractService contractService = new ContractService(new PayPalService());
        contractService.processContract(contract, months);

        System.out.println();
        System.out.println("Installments for Contract #"+contract.getNumber()+":");
        for (Installment i : contract.getInstalments()){
            System.out.println(sdf.format(i.getDate())+" - "+ String.format("%.2f",i.getValue()));
        }





        sc.close();
    }
}
