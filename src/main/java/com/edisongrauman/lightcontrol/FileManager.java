/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Edison Grauman
 */
public class FileManager {

    private GsonBuilder builder;
    private Gson gson;

    private File targetFile;

    private FileChooser fc;
    private FileChooser.ExtensionFilter ef;

    private final String defaultFileName = "Test.lc";

    public FileManager() {
        builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.serializeNulls();
        gson = builder.create();

        targetFile = null;

        fc = new FileChooser();
        ef = new FileChooser.ExtensionFilter("LIGHT CONTROL files (*.lc)", "*.lc");
        fc.getExtensionFilters().add(ef);

        fc.setInitialFileName(defaultFileName);
    }

    public ModuleContainer loadFile(Stage stage) throws IOException {

        targetFile = fc.showOpenDialog(stage);
//            System.out.println(targetFile.getPath());
        return readFile();

    }

    public void saveFile(Stage stage, ModuleContainer mc) {
        if (targetFile == null) {
            System.out.println("Target File is null");
            saveAsFile(stage, mc);
        } else {
            try {
                writeFile(mc);
            } catch (IOException e) {
            }
        }
    }

    public void saveAsFile(Stage stage, ModuleContainer mc) {
        targetFile = fc.showSaveDialog(stage);
        if (!targetFile.getName().endsWith(".lc")) {
            targetFile = new File(targetFile.getAbsolutePath() + ".lc");
        }
        try {
            writeFile(mc);
        } catch (IOException e) {
        }

    }

    private void writeFile(ModuleContainer mc) throws IOException {

        try (FileWriter fw = new FileWriter(targetFile)) {
            fw.write(gson.toJson(mc));
            fw.close();
        }
    }

    private ModuleContainer readFile() throws IOException {

        FileReader fr = new FileReader(targetFile);

        ModuleContainer mc = gson.fromJson(fr, ModuleContainer.class);
        fr.close();
        return mc;

    }

    public String getFileName() {
        if (targetFile != null) {
            return targetFile.getName();
        } else {
            return "(Unsaved Project)";
        }
    }
}
