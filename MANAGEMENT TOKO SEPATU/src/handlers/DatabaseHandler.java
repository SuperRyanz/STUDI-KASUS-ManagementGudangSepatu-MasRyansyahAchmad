package handlers;

import entity.SepatuEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String DATABASE_PATH = "data/sepatu_database.json";

    public static void simpanData(List<SepatuEntity> listSepatu) {
        try {
            StringBuilder jsonString = new StringBuilder("[");
            for (int i = 0; i < listSepatu.size(); i++) {
                SepatuEntity sepatu = listSepatu.get(i);
                jsonString.append("{")
                    .append("\"nama\":\"").append(escapeJson(sepatu.getNama())).append("\",")
                    .append("\"merk\":\"").append(escapeJson(sepatu.getMerk())).append("\",")
                    .append("\"warna\":\"").append(escapeJson(sepatu.getWarna())).append("\",")
                    .append("\"stok\":").append(sepatu.getStok())
                    .append("}");
                if (i < listSepatu.size() - 1) {
                    jsonString.append(",");
                }
            }
            jsonString.append("]");
            
            Files.write(Paths.get(DATABASE_PATH), jsonString.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<SepatuEntity> bacaData() {
        List<SepatuEntity> listSepatu = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(DATABASE_PATH)), StandardCharsets.UTF_8);
            
            // Parse JSON array manually
            content = content.trim();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);
                
                // Split objects
                String[] objects = splitJsonObjects(content);
                for (String obj : objects) {
                    obj = obj.trim();
                    if (!obj.isEmpty()) {
                        SepatuEntity sepatu = parseJsonObject(obj);
                        if (sepatu != null) {
                            listSepatu.add(sepatu);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listSepatu;
    }

    private static String[] splitJsonObjects(String content) {
        List<String> objects = new ArrayList<>();
        int braceCount = 0;
        int lastStart = 0;
        boolean inQuotes = false;
        boolean escapeNext = false;

        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            
            if (escapeNext) {
                escapeNext = false;
                continue;
            }

            if (c == '\\') {
                escapeNext = true;
                continue;
            }

            if (c == '"') {
                inQuotes = !inQuotes;
            }

            if (!inQuotes) {
                if (c == '{') {
                    braceCount++;
                } else if (c == '}') {
                    braceCount--;
                    if (braceCount == 0) {
                        objects.add(content.substring(lastStart, i + 1));
                        lastStart = i + 1;
                        // Skip comma if exists
                        if (lastStart < content.length() && content.charAt(lastStart) == ',') {
                            lastStart++;
                        }
                    }
                }
            }
        }

        return objects.toArray(new String[0]);
    }

    private static SepatuEntity parseJsonObject(String jsonObject) {
        try {
            String nama = extractJsonValue(jsonObject, "nama");
            String merk = extractJsonValue(jsonObject, "merk");
            String warna = extractJsonValue(jsonObject, "warna");
            int stok = Integer.parseInt(extractJsonValue(jsonObject, "stok"));
            
            return new SepatuEntity(nama, merk, warna, stok);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String extractJsonValue(String jsonObject, String key) {
        String searchKey = "\"" + key + "\":";
        int startIndex = jsonObject.indexOf(searchKey);
        if (startIndex == -1) {
            return "";
        }
        
        startIndex += searchKey.length();
        char firstChar = jsonObject.charAt(startIndex);
        
        if (firstChar == '"') {
            // String value
            startIndex++;
            int endIndex = startIndex;
            boolean escapeNext = false;
            while (endIndex < jsonObject.length()) {
                if (escapeNext) {
                    escapeNext = false;
                    endIndex++;
                    continue;
                }
                if (jsonObject.charAt(endIndex) == '\\') {
                    escapeNext = true;
                    endIndex++;
                    continue;
                }
                if (jsonObject.charAt(endIndex) == '"') {
                    return jsonObject.substring(startIndex, endIndex);
                }
                endIndex++;
            }
        } else {
            // Number value
            int endIndex = startIndex;
            while (endIndex < jsonObject.length() && 
                   (Character.isDigit(jsonObject.charAt(endIndex)) || jsonObject.charAt(endIndex) == '-')) {
                endIndex++;
            }
            return jsonObject.substring(startIndex, endIndex);
        }
        return "";
    }

    private static String escapeJson(String value) {
        return value.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}