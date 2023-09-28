package challange2.controller;

import challange2.model.Menu;
import challange2.model.Order;
import challange2.service.SaveBilling;
import challange2.view.FoodOrderView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FoodOrderController {

    private final List<Menu> menu = new ArrayList<>();
    private final Order order = new Order();
    private final FoodOrderView view = new FoodOrderView();
    private boolean finishOrder = false;

    public void startOrder() {
        view.welcomeMessage();
        loadMenu();
        while (!finishOrder) {
            displayMenu();
            pilihMakanan();
        }
    }

    private void loadMenu() {
        menu.add(new Menu("Nasi Goreng", 15000));
        menu.add(new Menu("Mie Goreng", 13000));
        menu.add(new Menu("Nasi + Ayam", 18000));
        menu.add(new Menu("Es Teh Manis", 3000));
        menu.add(new Menu("Es Jeruk", 5000));
    }

    private void displayMenu() {
        view.printMenu(menu);
    }

    private void pilihMakanan() {
        try {
            Scanner inpScanner = new Scanner(System.in);
            int inputUser = inpScanner.nextInt();

            if (inputUser > 0 && inputUser <= menu.size()) {
                hitungMakanan(menu.get(inputUser - 1));
            } else {
                switch (inputUser) {
                    case 99 -> konfirmasiPembayaran();
                    case 0 -> finishOrder = true;
                    default -> view.errorMessage();
                }
            }
        } catch (InputMismatchException e) {
            view.errorInputMessage();
        }
    }

    private void hitungMakanan(Menu makanan) {
        view.hitungMakanView(makanan);
        try {
            Scanner inpScanner = new Scanner(System.in);
            int qtyInputUser = inpScanner.nextInt();

            if (qtyInputUser > 0) {
                boolean found = false;
                for (Menu order : order.getItems()) {
                    if (order.getNama().equals(makanan.getNama())) {
                        order.setJumlah(order.getJumlah() + qtyInputUser);
                        order.setHarga(order.getHarga() + (qtyInputUser * makanan.getHarga()));
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    order.addItem(new Menu(makanan.getNama(), qtyInputUser, qtyInputUser * makanan.getHarga()));
                }
            }
        } catch (InputMismatchException e) {
            view.errorInputMessage();
        }
    }

    private void konfirmasiPembayaran() {
        view.orderSummary(order.getItems());
        double total = order.calculateTotal();
        int totalQty = 0;
        for (Menu menu : order.getItems()) {
            totalQty += menu.getJumlah();
        }
        view.totalAmount(totalQty, total);
        view.paymentOptions();
        view.choice();
        try {
            Scanner inpScanner = new Scanner(System.in);
            int inputUser = inpScanner.nextInt();

            switch (inputUser) {
                case 1 -> invoicePayment();
                case 2 -> {
                    finishOrder = false;
                    displayMenu();
                    pilihMakanan();
                }
                case 0 -> System.exit(0);
                default -> view.errorMessage();
            }
        } catch (InputMismatchException e) {
            view.errorInputMessage();
        }

    }

    private void invoicePayment() {
        view.invoicePayment();

        view.thankYouMessage();

        view.orderSummary(order.getItems());

        view.paymentOptions();

        SaveBilling.saveOrderToFile(order.getItems());

        System.exit(0);
    }
}
