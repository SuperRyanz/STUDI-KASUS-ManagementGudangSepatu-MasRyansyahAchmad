package handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.SepatuEntity;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class DatabaseHandler {
    private static final String DATABASE_PATH = "C:\\Kuliah Semester 3\\PBO\\Praktikum PBO 2023\\Studi Kasus\\MANAGEMENT TOKO SEPATU\\data\\sepatu_database.json";


    public static void simpanData(List<SepatuEntity> listSepatu) {
        try (Writer writer = new FileWriter(DATABASE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(listSepatu, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<SepatuEntity> bacaData() {
        try (Reader reader = new FileReader(DATABASE_PATH)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<SepatuEntity>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}