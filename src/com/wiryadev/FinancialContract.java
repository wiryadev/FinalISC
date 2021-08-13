package com.wiryadev;

import java.util.Scanner;

interface FinancialContract {
    default void showMenu(String menu) {
        System.out.println();
        Main.showDivider();
        System.out.println(menu + " Menu");
        System.out.println("1. Tambah");
        System.out.println("2. Edit");
        System.out.println("3. Hapus");
        System.out.println("4. Tampilkan " + menu);
        System.out.println("5. Kembali ke menu awal");
        System.out.print("(Input) Pilih aksi: ");

        Scanner pilihMenu = new Scanner(System.in);
        int pilihan = 0;

        try {
            pilihan = pilihMenu.nextInt();
        } catch (Exception e) {
            showMenu(menu);
        }

        if (pilihan != 0) {
            showAction(pilihan, menu);
        }
    }

    default void showAction(int pilihan, String menuName) {
        switch (pilihan) {
            case 1:
                tambah();
                showMenu(menuName);
                break;
            case 2:
                edit();
                showMenu(menuName);
            case 3:
                hapus();
                showMenu(menuName);
            case 4:
                tampilkan();
                showMenu(menuName);
                break;
            case 5:
                Main.showMainMenu();
                break;
            default:
                Main.exitApp();
        }
    }

    void tambah();
    void edit();
    void hapus();
    void tampilkan();
}
