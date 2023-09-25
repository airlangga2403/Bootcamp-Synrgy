package challange1;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private boolean finishOrder = false;
    ArrayList<Menu> order = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.startOrder();
    }

    private void startOrder() {
        System.out.println("==========================");
        System.out.println("Selamat Datang di BinarFud");
        System.out.println("==========================");

        while (!finishOrder) {
            pilihMakanan();
        }
    }

    private void pilihMakanan() {
        System.out.println("Silahkan pilih makanan :");

        System.out.println("1. Nasi Goreng\t|\t15.000");
        System.out.println("2. Mie Goreng\t|\t13.000");
        System.out.println("3. Nasi + Ayam\t|\t18.000");
        System.out.println("4. Es Teh Manis\t|\t3.000");
        System.out.println("5. Es Jeruk \t|\t5.000");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi");

        //  Input User
        System.out.print("=> ");
        Scanner inpScanner = new Scanner(System.in);
        int inputUser = inpScanner.nextInt();

        switch (inputUser) {
            case 1 -> hitungMakanan("Nasi Goreng", 15000);
            case 2 -> hitungMakanan("Mie Goreng", 13000);
            case 3 -> hitungMakanan("Nasi + Ayam", 18000);
            case 4 -> hitungMakanan("Es Teh Manis", 3000);
            case 5 -> hitungMakanan("Es Jeruk", 5000);
            case 99 -> {
                konfirmasiPembayaran();
                finishOrder = true;
            }
            case 0 -> finishOrder = true;
            default -> System.out.println("=== Pilihan Anda Salah ! ===\n");
        }
    }

    private void hitungMakanan(String makanan, int harga) {
        System.out.println("==========================");
        System.out.println("Berapa Pesanan anda");
        System.out.println("==========================");

        System.out.println(makanan + "\t|\t" + harga);
        System.out.println("(input 0 untuk kembali)");
        System.out.print("qty =>");
        Scanner inpScanner = new Scanner(System.in);
        int qtyInputUser = inpScanner.nextInt();
        int price = qtyInputUser * harga;

        boolean found = false;

        for (Menu menu : order) {
            if (menu.getNama().equals(makanan)) {
                menu.setJumlah(menu.getJumlah() + qtyInputUser);
                menu.setHarga(menu.getHarga() + price);
                found = true;
                break;
            }
        }
        if (!found) {
            order.add(new Menu(makanan, qtyInputUser, price));
        }
        System.out.println();
    }

    private void konfirmasiPembayaran() {

        System.out.println("==========================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("==========================");

        printTotal();

        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");

        System.out.print("=>");
        Scanner inpScanner = new Scanner(System.in);
        int inputUser = inpScanner.nextInt();

        switch (inputUser) {
            case 1 -> invoicePayment();
            case 2 -> {
                finishOrder = true;
                while (finishOrder) {
                    pilihMakanan();
                }
            }
            case 0 -> System.exit(0);
            default -> System.out.println("=== Pilihan Anda Salah ! ===\n");
        }
    }

    private void invoicePayment() {
        System.out.println();
        System.out.println("==========================");
        System.out.println("BinarFud");
        System.out.println("==========================");

        System.out.println("Terima kasih sudah memesan\ndi BinarFud");
        System.out.println();
        System.out.println("Dibawah ini adalah pesanan anda");
        System.out.println();

        printTotal();

        System.out.println("Pembayaran : BinarCash");
        System.out.println("==========================");
        System.out.println("Simpan struk ini sebagai\nbukti pembayaran");
        System.out.println("==========================");

        saveOrderToFile();

        System.exit(0);
    }

    private void saveOrderToFile() {
        String randomString = generateRandomString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("ss-hh-mm-yyyy");
        String dateTime = dateFormat.format(new Date());

        String fileName = randomString + "-" + dateTime + "-" + "Invoice.txt";

        try {
            FileWriter writer = new FileWriter(fileName);

            writer.write("==========================\n");
            writer.write("BinarFud\n");
            writer.write("==========================\n");
            writer.write("Terima kasih sudah memesan\ndi BinarFud\n\n");
            writer.write("Dibawah ini adalah pesanan anda:\n\n");

            for (Menu menu : order) {
                writer.write(menu.getNama() + "\t" + menu.getJumlah() + "\t" + menu.getHarga() + "\n");
            }

            int totalQty = 0;
            int totalPrice = 0;
            for (Menu menu : order) {
                totalQty += menu.getJumlah();
                totalPrice += menu.getHarga();
            }
            writer.write("-----------------------------+\n");
            writer.write("Total \t" + totalQty + "\t" + totalPrice + "\n");

            writer.write("Pembayaran : BinarCash\n");
            writer.write("==========================\n");
            writer.write("Simpan struk ini sebagai\nbukti pembayaran\n");
            writer.write("==========================\n");

            writer.close();
            System.out.println("Pesanan telah disimpan ke dalam file pesanan.txt");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan pesanan ke dalam file.");
            e.printStackTrace();
        }
    }

    private String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    private void printTotal() {
        int jumlahOrder = 0;
        int jumlahPrice = 0;
        for (Menu menu : order) {
            jumlahOrder += menu.getJumlah();
            jumlahPrice += menu.getHarga();
            System.out.println(menu.getNama() + "\t" + menu.getJumlah() + "\t" + menu.getHarga());
        }
        System.out.println("-----------------------------+");
        System.out.println("Total   \t" + jumlahOrder + "\t" + jumlahPrice);
    }
}
