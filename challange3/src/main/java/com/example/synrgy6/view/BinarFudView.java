package com.example.synrgy6.view;

import com.example.synrgy6.model.OrderDetail;
import com.example.synrgy6.model.Products;
import com.example.synrgy6.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BinarFudView {


    public void welcomeMessage() {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Selamat Datang di BinarFud");
        System.out.println(Utils.SEPARATOR);
        System.out.println("Apakah Anda Sudah Register ? ");
        System.out.println("1. Sudah Register");
        System.out.println("2. Belum Register");
        System.out.print("=> ");

    }

    public void inputUsername() {
        System.out.println("Masukkan Username Anda : ");
        System.out.print("=> ");
    }

    public void inputEmail() {
        System.out.println("Masukkan Email Anda : ");
        System.out.print("=> ");
    }

    public void inputPassword() {
        System.out.println("Masukkan Password Anda : ");
        System.out.print("=> ");
    }

    public void loginUser() {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Silahkan Login di BinarFud");
        System.out.println(Utils.SEPARATOR);
    }


    public void userNotFound() {
        System.out.println("User not found");
    }

    public void passwordBenar() {
        System.out.println("Password Benar");
    }

    public void passwordSalah() {
        System.out.println("Password Salah ! ");
        System.out.println("99. Login Lagi");
        System.out.print("=> ");

    }


    public void printMenu(List<Products> menu) {
        System.out.println("Silahkan pilih makanan :");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i).getProductName() + Utils.TABLE + menu.get(i).getPrice());
        }
        System.out.print("=> ");
    }

    public void choiceMenu() {
        System.out.println("88. Order Lagi");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("=> ");
    }

    public void inputUserAddress() {
        System.out.println("Silahkan Masukkan Alamat Anda : ");
        System.out.print("=> ");
    }

    public void inputQuantity() {
        System.out.println("Ingin Pesan Berapa ? :");
        System.out.print("=>");
    }

    //
    public void orderSummary(List<OrderDetail> orderDetails, ArrayList<Products> productsList) {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Order Summary");
        System.out.println(Utils.SEPARATOR);

        System.out.println("Product Name" + Utils.TABLE + "Quantity");

        for (int i = 0; i < orderDetails.size(); i++) {
            OrderDetail orderDetail = orderDetails.get(i);
            Products product = productsList.get(i); // Get the product from the list

            if (product != null) {
                String productName = product.getProductName(); // Get the product name
                System.out.println(productName + Utils.TABLE +
                        orderDetail.getQuantity() + Utils.TABLE + Utils.TABLE);
            } else {
                System.out.println("Product Name Unavailable" + Utils.TABLE +
                        orderDetail.getQuantity() + Utils.TABLE + Utils.TABLE);
            }
        }
    }


    public void totalAmount(int totalJumlah, double totalHarga) {
        System.out.println("-----------------------------+");
        System.out.println("Total \t| " + totalJumlah + Utils.TABLE + totalHarga);
    }

    public void thankYouMessage() {
        System.out.println("Terima kasih sudah memesan\ndi BinarFud");
        System.out.println();
    }

    public void paymentOptions() {
        System.out.println("Pembayaran : BinarCash");
        System.out.println(Utils.SEPARATOR);
        System.out.println("Simpan struk ini sebagai\nbukti pembayaran");
        System.out.println(Utils.SEPARATOR);
    }

    public void choice() {
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");
        System.out.print("=> ");
    }

    public void invoicePayment() {
        System.out.println();
        System.out.println(Utils.SEPARATOR);
        System.out.println("BinarFud");
        System.out.println(Utils.SEPARATOR);
    }


    public void catatanOrder() {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Catatan Order");
        System.out.println(Utils.SEPARATOR);
        System.out.print("=> ");
    }


    public void errorInputMessage() {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Mohon masukkan input\nPilihan anda");
        System.out.println(Utils.SEPARATOR);
        System.out.println("(Y) untuk lanjut");
        System.out.println("(N) untuk keluar");
        System.out.print("=> ");
    }

    public void errorInputZero() {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Minimal 1 Jumlah\nPesanan!");
        System.out.println(Utils.SEPARATOR);
    }
}
