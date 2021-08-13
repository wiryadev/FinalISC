package com.wiryadev;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static com.wiryadev.Main.*;

public class Transaksi implements FinancialContract {

    final String menuName = "Transaksi";

    private final ArrayList<TransaksiData> transaksiDataList = new ArrayList<>();

    @Override
    public void tambah() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("(Input) Nama transaksi: ");
            String nama = scanner.nextLine();
            System.out.print("(Input) Nominal: ");
            int nominal = Integer.parseInt(scanner.nextLine());
            System.out.println("(Input) Jenis pengeluaran: ");
            System.out.print("(Input) 1 - Pemasukan / 2 - Pengeluaran: ");
            int jenis = Integer.parseInt(scanner.nextLine());

            if ((nama) != null && (nominal > 0)) {
                if (jenis == 1) {
                    transaksiDataList.add(new TransaksiData(nama, nominal, TransaksiData.JenisTransaksi.PEMASUKAN));
                    printAddSuccess(menuName);
                } else if (jenis == 2) {
                    transaksiDataList.add(new TransaksiData(nama, nominal, TransaksiData.JenisTransaksi.PENGELUARAN));
                    printAddSuccess(menuName);
                } else {
                    System.out.println("Jenis transaksi tidak valid");
                    tambah();
                }
            } else {
                printAllDataMustBeFilled();
                tambah();
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
            TransaksiData data = transaksiDataList.get(itemIndex);

            try {
                printEditSkip("Nama " + menuName);
                String nama = scanner.nextLine();
                printEditSkip("Nominal " + menuName);
                String nominal = scanner.nextLine();
                System.out.println("(Input) Jenis pengeluaran: ");
                System.out.print("(Input) 1 - Pengeluaran / 2 - pemasukan: ");
                String jenis = scanner.nextLine();

                boolean namaIsEmpty = nama.trim().isEmpty();
                boolean nominalIsEmpty = nominal.trim().isEmpty();
                boolean jenisIsEmpty = jenis.trim().isEmpty();

                if (!namaIsEmpty) {
                    data.setNama(nama);
                }

                if (!nominalIsEmpty) {
                    data.setNominal(Integer.parseInt(nominal));
                }

                if (!jenisIsEmpty) {
                    int jenisType = Integer.parseInt(jenis);
                    if (jenisType == 1) {
                        data.setJenisTransaksi(TransaksiData.JenisTransaksi.PEMASUKAN);
                    } else if (jenisType == 2) {
                        data.setJenisTransaksi(TransaksiData.JenisTransaksi.PENGELUARAN);
                    } else {
                        System.out.println("Jenis transaksi tidak valid");
                        edit();
                    }
                }

                if (namaIsEmpty && nominalIsEmpty && jenisIsEmpty) {
                    printNoDataChanged();
                } else {
                    printEditSuccess(menuName);
                }
            } catch (Exception e) {
                edit();
            }
        } else {
            showMenu(menuName);
        }
    }

    @Override
    public void hapus() {
        tampilkan();
        if (transaksiDataList.size() > 0) {
            Scanner scanner = new Scanner(System.in);
            printDeleteCommand();
            int itemIndex = Integer.parseInt(scanner.nextLine()) - 1;

            try {
                printDeleteConfirmation(transaksiDataList.get(itemIndex).getNama());
                if (scanner.nextLine().trim().toLowerCase(Locale.ROOT).equals("y")) {
                    transaksiDataList.remove(itemIndex);
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
        System.out.println("Daftar Transaksi");
        if (transaksiDataList.size() > 0) {
            System.out.printf("No " + "%-30s" + "%-15s" + "%-9s \n", "Nama", "Jenis", "Harga");
            for (int i = 0; i < transaksiDataList.size(); i++) {
                TransaksiData transaksiData = transaksiDataList.get(i);
                System.out.printf(
                        "%-3d" + "%-30s" + "%-15s" + "%-9d \n",
                        i+1, transaksiData.getNama(), transaksiData.getJenisTransaksi(), transaksiData.getNominal()
                );
            }
        } else {
            printNoDataExist();
        }
        showDivider();
    }
    
}

class TransaksiData extends BudgetData {
    private JenisTransaksi jenisTransaksi;

    public TransaksiData(String nama, int nominal, JenisTransaksi jenisTransaksi) {
        super(nama, nominal);
        this.jenisTransaksi = jenisTransaksi;
    }

    public void setJenisTransaksi(JenisTransaksi jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public JenisTransaksi getJenisTransaksi() {
        return jenisTransaksi;
    }

    enum JenisTransaksi {
        PEMASUKAN {
            @Override
            public String toString() {
                return "Pemasukan";
            }
        },
        PENGELUARAN {
            @Override
            public String toString() {
                return "Pengeluaran";
            }
        }
    }
}

