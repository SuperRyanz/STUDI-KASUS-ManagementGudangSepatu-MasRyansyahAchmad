package controllers;

import models.SepatuModel;
import entity.SepatuEntity;
import handlers.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class SepatuController {
    private SepatuModel sepatuModel;

    // Constructor
    public SepatuController() {
        this.sepatuModel = new SepatuModel();

        // Baca data dari database dan setel ke model
        List<SepatuEntity> dataFromDatabase = DatabaseHandler.bacaData();
        if (dataFromDatabase != null) {
            sepatuModel.setListSepatu(dataFromDatabase);
        }
    }

    // Metode lainnya

    public void tambahSepatu(String merk, String nama, String warna, int stok) {
        SepatuEntity sepatuBaru = new SepatuEntity(nama, merk, warna, stok);
        sepatuModel.tambahSepatu(sepatuBaru);

        // Simpan ke database
        DatabaseHandler.simpanData(sepatuModel.getListSepatu());
    }

    public void hapusSepatu(String nama) {
        sepatuModel.hapusSepatu(nama);

        // Menyimpan data setelah perubahan
        DatabaseHandler.simpanData(sepatuModel.getListSepatu());
    }

    public void editSepatu(String nama, SepatuEntity sepatuEntity) {
        sepatuModel.editSepatu(nama, sepatuEntity);

        // Menyimpan data setelah perubahan
        DatabaseHandler.simpanData(sepatuModel.getListSepatu());
    }

    public List<SepatuEntity> getListSepatu() {
        return sepatuModel.getListSepatu();
    }

public List<SepatuEntity> searchSepatu(String keyword) {
    List<SepatuEntity> sepatuList = sepatuModel.getListSepatu();
    List<SepatuEntity> searchResult = new ArrayList<>();

    for (SepatuEntity sepatu : sepatuList) {
        if (sepatu.getMerk().toLowerCase().contains(keyword.toLowerCase()) ||
            sepatu.getNama().toLowerCase().contains(keyword.toLowerCase()) ||
            sepatu.getWarna().toLowerCase().contains(keyword.toLowerCase())) {
            searchResult.add(sepatu);
        }
    }

    return searchResult;
}
}
