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
    private boolean finishInput = false;
    private static final Scanner inpScanner = new Scanner(System.in);

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
            int inputUser = inpScanner.nextInt();

            if (inputUser > 0 && inputUser <= menu.size()) {
                hitungMakanan(menu.get(inputUser - 1));
            } else {
                if (inputUser == 99) {
                    if (order.getItems().isEmpty()) {
                        view.errorInputZero();
                    } else {
                        konfirmasiPembayaran();
                    }
                } else if (inputUser == 0) {
                    finishOrder = true;
                }
            }
        } catch (InputMismatchException e) {
            finishInput = false;
            salahInput();
        }
    }

    private void salahInput() {
        if (finishInput) {
            finishInput = false;
        } else {
            while (true) {
                view.errorInputMessage();
                String inputUser = inpScanner.next();
                if (inputUser.equals("Y")) {
                    finishInput = true;
                    break;
                } else if (inputUser.equals("N")) {
                    System.exit(0);
                }
            }
        }
    }

    private void hitungMakanan(Menu makanan) {
        try {
            view.hitungMakanView(makanan);
            int qtyInputUser = inpScanner.nextInt();
            view.catatanOrder();
            String noteInputUser= inpScanner.next();
            if (qtyInputUser > 0) {
                boolean found = false;
                for (Menu order : order.getItems()) {
                    if (order.getNama().equals(makanan.getNama())) {
                        order.setJumlah(order.getJumlah() + qtyInputUser);
                        order.setHarga(order.getHarga() + (qtyInputUser * makanan.getHarga()));
                        order.setNote(noteInputUser);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    order.addItem(new Menu(makanan.getNama(), qtyInputUser, qtyInputUser * makanan.getHarga(), noteInputUser));
                }
            }
        } catch (InputMismatchException e) {
            finishInput = false;
            salahInput();
        }
    }

    // Penerapan Strea
    private void konfirmasiPembayaran() {
        view.orderSummary(order.getItems());

        // Menggunakan Java Stream untuk menghitung total jumlah
        int totalQty = order.getItems().stream()
                .mapToInt(Menu::getJumlah)
                .sum();

        // Menggunakan Java Stream untuk menghitung total harga
        double total = order.getItems().stream()
                .mapToDouble(menu -> menu.getJumlah() * menu.getHarga())
                .sum();

        view.totalAmount(totalQty, total);
        view.paymentOptions();
        view.choice();

        try {
            int inputUser = inpScanner.nextInt();
            if (inputUser == 1) {
                invoicePayment();
            } else if (inputUser == 2) {
                finishOrder = false;
                displayMenu();
                pilihMakanan();
            } else if (inputUser == 0) {
                System.exit(0);
            }
        } catch (InputMismatchException e) {
            finishInput = false;
            salahInput();
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
