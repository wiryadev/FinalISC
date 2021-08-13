package com.wiryadev;

import java.util.Scanner;

public class Main {

    static Budget budget = new Budget();
    static AkunBank akunBank = new AkunBank();
    static Transaksi transaksi = new Transaksi();

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        showDivider();
        System.out.println("Main Menu");
        System.out.println("1. Budget");
        System.out.println("2. Akun Bank");
        System.out.println("3. Transaksi");
        System.out.println("4. Tampilkan Report");
        System.out.println("Masukkan angka selain 1-4 untuk keluar");
        System.out.print("(Input) Masukkan menu: ");

        Scanner pilihMenu = new Scanner(System.in);
        int pilihan = 0;
        try {
            pilihan = pilihMenu.nextInt();
        } catch (Exception e) {
            showMainMenu();
        }

        if (pilihan != 0) {
            chooseMenu(pilihan);
        }
    }

    private static void chooseMenu(int i) {

        switch (i) {
            case 1:
                budget.showMenu(budget.menuName);
                break;
            case 2:
                akunBank.showMenu(akunBank.menuName);
                break;
            case 3:
                transaksi.showMenu(transaksi.menuName);
                break;
            case 4:
                generateReport();
                break;
            default:
                exitApp();
        }
    }

    public static void generateReport() {
        budget.tampilkan();
        akunBank.tampilkan();
        transaksi.tampilkan();

        System.out.println("Tekan enter untuk kembali ke menu awal");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        showMainMenu();
    }

    public static void printAddSuccess(String menuName) {
        System.out.println("[INFO] " + menuName + " berhasil ditambahkan");
    }

    public static void printEditSuccess(String menuName) {
        System.out.println("[INFO] " + menuName + " berhasil diubah");
    }

    public static void printEditCommand() {
        System.out.print("(Input) Pilih nomor yang akan diupdate, masukkan angka negatif untuk membatalkan: ");
    }

    public static void printEditSkip(String itemName) {
        System.out.print("(Input) " + itemName + ", lewati jika tidak ingin diubah: ");
    }

    public static void printDeleteSuccess(String menuName) {
        System.out.println("[INFO] " + menuName + " berhasil dihapus");
    }

    public static void printDeleteCanceled(String menuName) {
        System.out.println("[INFO] penghapusan" + menuName + " dibatalkan");
    }

    public static void printDeleteCommand() {
        System.out.print("(Input) Pilih nomor yang akan dihapus: ");
    }

    public static void printDeleteConfirmation(String itemName) {
        System.out.println("Anda yakin ingin menghapus " + itemName + "? (y/n)");
    }

    public static void printAllDataMustBeFilled() {
        System.out.println("[INFO] Semua data harus diisi");
    }

    public static void printNoDataExist() {
        System.out.println("Belum ada data yang ditambahkan");
    }

    public static void printNoDataChanged() {
        System.out.println("[INFO] Tidak ada data yang diubah");
    }

    public static void exitApp() {
        System.out.println("==================================================");
        System.out.println(" Terimakasih Telah Menggunakan Program ini");
        System.out.println("==================================================");
        System.exit(0);
    }

    public static void showDivider() {
        System.out.println("=========================================================================================");
    }

}
