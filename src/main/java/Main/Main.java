package Main;

import HTTPSolicitations.Solicitations;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private String baseCurrency;
    private String targetCurrency;

    public void MakeCurrency(String baseCurrency, String targetCurrency) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public Main() {
    }

    public static void main(String[] args) {
        Solicitations solicitations = new Solicitations();
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);


        int userChoice = -1; // Inicializar com um valor diferente de 9 para entrar no loop
        while (userChoice != 9) {
            String menu = """
                 *****************************************************
                  - Hi. Welcome to Currency Converter!
                  ...Select an Option to Perform the Conversion :]
                  1) Dólar > Real
                  2) Real > Dólar
                  3) Dólar > Peso Argentino
                  4) Peso Argentino > Dólar
                  5) Real > Peso Argentino
                  6) Peso Argentino > Real
                  7) Euro > Dólar
                  8) Dólar > Euro
                  9) SAIR
                  *****************************************************
                 """;
            System.out.println(menu);
            userChoice = scanner.nextInt();

            if (userChoice == 9) {
                System.out.println("Thanks...");
                break;
            }

            switch (userChoice) {
                case 1:
                    main.DolarFromReal();
                    break;
                case 2:
                    main.RealFromDolar();
                    break;
                case 3:
                    main.DolarFromPesoArg();
                    break;
                case 4:
                    main.PesoArgFromDolar();
                    break;
                case 5:
                    main.RealFromPesoArg();
                    break;
                case 6:
                    main.PesoArgFromReal();
                    break;
                case 7:
                    main.EuroFromDolar();
                    break;
                case 8:
                    main.DolarFromEuro();
                    break;
                default:
                    System.out.println("Choose a Valid Number.");
                    continue;
            }

            //iteração com o cliente
            System.out.println("Enter the value for conversion from " + main.baseCurrency + " to " + main.targetCurrency + ":");
            double value = scanner.nextDouble();

            solicitations.setCurrencies(main.baseCurrency, main.targetCurrency);
            //tentando fazer a conversão - caso contrário, lança exceção
            try {
                double rate = solicitations.requestRate();
                double convertedAmount = value * rate;
                System.out.println("Result: " + value + " " + main.baseCurrency + " = " + convertedAmount + " " + main.targetCurrency);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void DolarFromReal() {
        baseCurrency = "USD";
        targetCurrency = "BRL";
    }

    private void RealFromDolar() {
        baseCurrency = "BRL";
        targetCurrency = "USD";
    }

    private void DolarFromPesoArg() {
        baseCurrency = "USD";
        targetCurrency = "ARS";
    }

    private void PesoArgFromDolar() {
        baseCurrency = "ARS";
        targetCurrency = "USD";
    }

    private void RealFromPesoArg() {
        baseCurrency = "BRL";
        targetCurrency = "ARS";
    }

    private void PesoArgFromReal() {
        baseCurrency = "ARS";
        targetCurrency = "BRL";
    }

    private void EuroFromDolar() {
        baseCurrency = "EUR";
        targetCurrency = "USD";
    }

    private void DolarFromEuro() {
        baseCurrency = "USD";
        targetCurrency = "EUR";
    }
}
