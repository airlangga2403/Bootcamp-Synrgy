package com.example.synrgy6.view;

import com.example.synrgy6.utils.Utils;
import org.springframework.stereotype.Component;

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

    }



//    public void printMenu(List<Menu> menu) {
//        System.out.println("Silahkan pilih makanan :");
//        for (int i = 0; i < menu.size(); i++) {
//            System.out.println((i + 1) + ". " + menu.get(i).getNama() + Utils.TABLE + menu.get(i).getHarga());
//        }
//        System.out.println("99. Pesan dan Bayar");
//        System.out.println("0. Keluar Aplikasi");
//        System.out.print("=> ");
//    }
//
//    public void orderSummary(List<Menu> order) {
//        System.out.println(Utils.SEPARATOR);
//        System.out.println("Konfirmasi & Pembayaran");
//        System.out.println(Utils.SEPARATOR);
//
//        for (Menu menu : order) {
//            System.out.println(menu.getNama() + Utils.TABLE + menu.getJumlah() + Utils.TABLE + menu.getHarga() + Utils.TABLE + menu.getNote());
//        }
//    }

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

//    public void hitungMakanView(Menu makanan) {
//        System.out.println(Utils.SEPARATOR);
//        System.out.println("Berapa Pesanan anda");
//        System.out.println(Utils.SEPARATOR);
//
//        System.out.println(makanan.getNama() + "\t|\t" + makanan.getHarga());
//        System.out.println("(input 0 untuk kembali)");
//        System.out.print("qty => ");
//    }

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
