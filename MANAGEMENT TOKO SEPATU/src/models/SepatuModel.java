package models;

import entity.SepatuEntity;

import java.util.ArrayList;
import java.util.List;

public class SepatuModel {
    private List<SepatuEntity> listSepatu;

    public void setListSepatu(List<SepatuEntity> listSepatu) {
        this.listSepatu = listSepatu;
    }

    // Metode lainnya

    public List<SepatuEntity> getListSepatu() {
        return listSepatu;
    }

    public void tambahSepatu(SepatuEntity sepatu) {
        // Implementasi penambahan sepatu ke dalam list
        listSepatu.add(sepatu);
    }

    public void hapusSepatu(String nama) {
        // Implementasi penghapusan sepatu dari list
        listSepatu.removeIf(sepatu -> sepatu.getNama().equals(nama));
    }

    public void editSepatu(String nama, SepatuEntity sepatuBaru) {
        // Implementasi pengeditan sepatu dalam list
        for (int i = 0; i < listSepatu.size(); i++) {
            SepatuEntity sepatu = listSepatu.get(i);
            if (sepatu.getNama().equals(nama)) {
                listSepatu.set(i, sepatuBaru);
                break;
            }
        }
    }

    public List<SepatuEntity> searchSepatu(String keyword) {
        // Implementasi pencarian sepatu dalam list
        List<SepatuEntity> searchResult = new ArrayList<>();
        for (SepatuEntity sepatu : listSepatu) {
            if (sepatu.getNama().toLowerCase().contains(keyword.toLowerCase())) {
                searchResult.add(sepatu);
            }
        }
        return searchResult;
    }
}
