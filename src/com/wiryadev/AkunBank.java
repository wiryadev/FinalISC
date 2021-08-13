package com.wiryadev;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static com.wiryadev.Main.*;

public class AkunBank implements FinancialContract {

    final String menuName = "Akun Bank";

    private final ArrayList<AkunBankData> akunBankDataList = new ArrayList<>();

    @Override
    public void tambah() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("(Input) Nama Nasabah: ");
            String nama = scanner.nextLine();
            System.out.print("(Input) PIN (minimal 6 karakter): ");
            String PIN = scanner.nextLine();

            if ((nama) != null && (PIN != null) && (PIN.length() >= 6)) {
                String norek = getDateTimeAsString();
                akunBankDataList.add(new AkunBankData(norek, nama, PIN));
                printAddSuccess(menuName);
            } else {
                printAllDataMustBeFilled();
            }
        } catch (Exception e) {
            tambah();
        }
    }

    @Override
    public void edit() {
        tampilkan();
        Scanner scanner = new Scanner(System.in);
        printEditCommand();
        int itemIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (itemIndex >= 0) {
            AkunBankData data = akunBankDataList.get(itemIndex);

            System.out.println("Masukkan PIN untuk melakukan penyuntingan: ");
            String validationPIN = scanner.nextLine();

            if (validationPIN.trim().equals(data.getPIN())) {
                try {
                    printEditSkip("Nama " + menuName);
                    String nama = scanner.nextLine();
                    System.out.print("(Minimal 6 karakter)");
                    printEditSkip("PIN " + menuName);
                    String newPIN = scanner.nextLine();
                    boolean namaIsEmpty = nama.trim().isEmpty();
                    boolean pinIsEmpty = newPIN.trim().isEmpty();

                    if (!namaIsEmpty) {
                        data.setNama(nama);
                    }

                    if (!pinIsEmpty && (newPIN.length() >= 6)) {
                        data.setPIN(newPIN);
                    }

                    if (namaIsEmpty && pinIsEmpty) {
                        printNoDataChanged();
                    } else {
                        printEditSuccess(menuName);
                    }
                } catch (Exception e) {
                    edit();
                }
            }
        } else {
            showMenu(menuName);
        }
    }

    @Override
    public void hapus() {
        tampilkan();
        if (akunBankDataList.size() > 0) {
            Scanner scanner = new Scanner(System.in);
            printDeleteCommand();
            int itemIndex = Integer.parseInt(scanner.nextLine()) - 1;
            AkunBankData data = akunBankDataList.get(itemIndex);

            try {
                System.out.println("Masukkan PIN untuk konfirmasi penghapusan: ");
                if (scanner.nextLine().trim().equals(data.getPIN())) {
                    akunBankDataList.remove(data);
                    printDeleteSuccess(menuName);
                } else {
                    printDeleteCanceled(menuName);
                    showMenu(menuName);
                }
            } catch (Exception e) {
                hapus();
            }
        }
    }

    @Override
    public void tampilkan() {
        showDivider();
        System.out.println("Daftar " + menuName);
        if (akunBankDataList.size() > 0) {
            for (int i = 0; i < akunBankDataList.size(); i++) {
                AkunBankData akunBankData = akunBankDataList.get(i);
                System.out.printf(
                        "%3d" + ". %-30s" + "%-30s \n",
                        i + 1, akunBankData.getNoRek(), akunBankData.getNama()
                );
            }
        } else {
            printNoDataExist();
        }
        showDivider();
    }

    private String getDateTimeAsString() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
                .replace("-", "")
                .replace(" ", "")
                .replace(":", "")
                .replace(".", "");
    }
}

class AkunBankData {
    private final String noRek;
    private String nama;
    private String PIN;

    public AkunBankData(String noRek, String nama, String PIN) {
        this.noRek = noRek;
        this.nama = nama;
        this.PIN = PIN;
    }

    public String getNoRek() {
        return noRek;
    }

    public String getNama() {
        return nama;
    }

    public String getPIN() {
        return PIN;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
}