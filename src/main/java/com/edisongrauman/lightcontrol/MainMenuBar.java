/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edisongrauman.lightcontrol;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Edison Grauman
 */
public class MainMenuBar extends MenuBar {

    private final Menu fileMenu;
    private final MenuItem fileExit;
    private final MenuItem fileSave;
    private final MenuItem fileSaveAs;
    private final MenuItem fileLoad;
    private final MenuItem fileNew;

    private SimpleBooleanProperty savePressed;
    private SimpleBooleanProperty saveAsPressed;
    private SimpleBooleanProperty loadPressed;
    private SimpleBooleanProperty newPressed;

    public MainMenuBar() {

        savePressed = new SimpleBooleanProperty(false);
        saveAsPressed = new SimpleBooleanProperty(false);
        loadPressed = new SimpleBooleanProperty(false);
        newPressed = new SimpleBooleanProperty(false);

        fileExit = new MenuItem("Exit");
        fileExit.setOnAction((e) -> {
            System.out.println("Exit Pressed");
        });

        fileSave = new MenuItem("Save");
        fileSave.setOnAction((e) -> {
            System.out.println("Save Pressed");
            savePressed.set(!savePressed.get());

        });
        
        fileNew = new MenuItem("New");
        fileNew.setOnAction((e) -> {
            System.out.println("New Pressed");
           newPressed.set(!newPressed.get());

        });

        fileSaveAs = new MenuItem("Save As");
        fileSaveAs.setOnAction((e) -> {
            System.out.println("SaveAs Pressed");
            saveAsPressed.set(!saveAsPressed.get());

        });

        fileLoad = new MenuItem("Load");
        fileLoad.setOnAction((e) -> {
            System.out.println("Load Pressed");
            loadPressed.set(!loadPressed.get());
        });

        fileExit.setOnAction((e) -> {
            System.out.println("Exit Pressed");
            System.exit(0);
        });

        fileMenu = new Menu("File");
        fileMenu.getItems().addAll(fileExit,fileNew, fileSave, fileSaveAs, fileLoad);

        this.getMenus().addAll(fileMenu);
    }

    SimpleBooleanProperty getSavePressed() {
        return savePressed;
    }

    SimpleBooleanProperty getSaveAsPressed() {
        return saveAsPressed;
    }

    SimpleBooleanProperty getLoadPressed() {
        return loadPressed;
    }
    
    SimpleBooleanProperty getNewPressed() {
        return loadPressed;
    }
}
