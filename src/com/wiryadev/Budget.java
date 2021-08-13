package com.wiryadev;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static com.wiryadev.Main.*;

public class Budget implements FinancialContract {

    final String menuName = "Budget";

    private final ArrayList<BudgetData> budgetDataList = new ArrayList<>();

    @Override
    public void tambah() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("(Input) Nama Budget: ");
            String nama = scanner.nextLine();
            System.out.print("(Input) Budget: ");
            int nominal = Integer.parseInt(scanner.nextLine());

            if ((nama) != null && (nominal > 0)) {
                budgetDataList.add(new BudgetData(nama, nominal));
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
            try {
                printEditSkip("Nama " + menuName);
                String nama = scanner.nextLine();
                printEditSkip("Nominal " + menuName);
                String nominal = scanner.nextLine();
                boolean namaIsEmpty = nama.trim().isEmpty();
                boolean nominalIsEmpty = nominal.trim().isEmpty();

                if (!namaIsEmpty) {
                    budgetDataList.get(itemIndex).setNama(nama);
                }

                if (!nominalIsEmpty) {
                    budgetDataList.get(itemIndex).setNominal(Integer.parseInt(nominal));
                }

                if (namaIsEmpty && nominalIsEmpty) {
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
        if (budgetDataList.size() > 0) {
            Scanner scanner = new Scanner(System.in);
            printDeleteCommand();
            int itemIndex = Integer.parseInt(scanner.nextLine()) - 1;

            try {
                printDeleteConfirmation(budgetDataList.get(itemIndex).getNama());
                if (scanner.nextLine().trim().toLowerCase(Locale.ROOT).equals("y")) {
                    budgetDataList.remove(itemIndex);
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
        if (budgetDataList.size() > 0) {
            for (int i = 0; i < budgetDataList.size(); i++) {
                BudgetData budgetData = budgetDataList.get(i);
                System.out.printf(
                        "%3d" + ". %-30s" + ":%-9d \n",
                        i + 1, budgetData.getNama(), budgetData.getNominal()
                );
            }
        } else {
            printNoDataExist();
        }
        showDivider();
    }

}

class BudgetData {
    private String nama;
    private int nominal;

    public BudgetData(String nama, int nominal) {
        this.nama = nama;
        this.nominal = nominal;
    }

    public String getNama() {
        return nama;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
}