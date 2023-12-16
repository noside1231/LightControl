package com.edisongrauman.lightcontrol;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private InstanceController instanceController;
    private MainMenuBar mainMenu;
    private FileManager fileManager;
    private ModuleContainer moduleContainer;
    private ConfigView configView;

    private BorderPane root;
    private Scene scene;

    @Override
    public void start(Stage stage) {

        fileManager = new FileManager();
        stage.setTitle(fileManager.getFileName());

        moduleContainer = new ModuleContainer();

        instanceController = new InstanceController();

        mainMenu = new MainMenuBar();
        configView = new ConfigView();

        root = new BorderPane();
        root.setTop(mainMenu);
        root.setLeft(configView);

        //Configuration Listeners
        setupConfigurationViewListeners();

        //Menu Listeners
        setupMenuListeners(stage);

        ChannelSlider cs = new ChannelSlider();
        root.setRight(cs);

//        ArtNetInstance a = new ArtNetInstance("127.127.0.0", 8000,8000);
//        byte[] c = {1,2,3,4,5,6,7,8,9,10};
//        a.unicastDmx(c);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void newModule() {
        moduleContainer.addModule();
        configView.addModuleConfigView(moduleContainer.getLastModule());
    }

    private void deleteModule(int moduleIndex) {
        moduleContainer.removeModule(moduleIndex);
        configView.removeModuleConfigView(moduleIndex);
    }

    private void newFixture(int moduleIndex) {
        moduleContainer.addFixture(moduleIndex);
        configView.addFixture(moduleIndex, moduleContainer.getLastFixture(moduleIndex));
    }

    private void deleteFixture(int moduleIndex, int fixtureIndex) {
        moduleContainer.removeFixture(moduleIndex, fixtureIndex);
        configView.removeFixture(moduleIndex, fixtureIndex);
    }

    private void startStopModule(int moduleIndex) {
        
        if (instanceController.isInstanceRunning(moduleContainer.getModule(moduleIndex).getInstanceID())) {
            
        } else {
            
        }
        int instanceID = instanceController.newInstance(moduleContainer.getModule(moduleIndex));
        if (instanceID > 0) {
            System.out.println("Started");
            moduleContainer.getModule(moduleIndex).setInstanceID(instanceID);
            configView.setModuleEdit(moduleIndex, false);
        }
    }

    private void setupConfigurationViewListeners() {
        ConfigChangeEvent ev = configView.getChangeEvent();
        ev.isChanged().addListener(cl -> {
            System.out.println(ev.toString());
            int moduleIndex = ev.getModuleIndex();
            int fixtureIndex = ev.getFixtureIndex();
            String value = ev.getValue();
            switch (ev.getPropertyType()) {

                case NONE:
                    System.out.println("NONE");
                    return;
                case MODULE_NAME:
                    moduleContainer.setModuleName(moduleIndex, value);
                    return;
                case FIXTURE_NAME:
                    moduleContainer.setFixtureName(moduleIndex, fixtureIndex, value);
                    return;
                case NEW_MODULE:
                    newModule();
                    return;
                case DELETE_MODULE:
                    deleteModule(moduleIndex);
                    return;
                case NEW_FIXTURE:
                    newFixture(moduleIndex);
                    return;
                case DELETE_FIXTURE:
                    deleteFixture(moduleIndex, fixtureIndex);
                    return;
                case OUTPUT_OPTION:
                    moduleContainer.setOutputOption(moduleIndex, value);
                    return;
                case ARTNET_IP_ADDRESS:
                    moduleContainer.setArtnetIPAddress(moduleIndex, value);
                    return;
                case ARTNET_SOURCE_PORT:
                    moduleContainer.setArtnetSourcePort(moduleIndex, value);
                    return;
                case ARTNET_DESTINATION_PORT:
                    moduleContainer.setArtnetDestinationPort(moduleIndex, value);
                    return;
                case OSC_SOURCE_PORT:
                    moduleContainer.setOSCSourcePort(moduleIndex, value);
                    return;
                case OSC_DESTINATION_PORT:
                    moduleContainer.setOSCDestinationPort(moduleIndex, value);
                    return;
                case FIXTURE_UNIVERSE:
                    moduleContainer.setFixtureUniverse(moduleIndex, fixtureIndex, value);
                    return;
                case FIXTURE_CHANNEL_START:
                    moduleContainer.setFixtureChannelStart(moduleIndex, fixtureIndex, value);
                    return;
                case FIXTURE_CHANNEL_COUNT:
                    moduleContainer.setFixtureChannelCount(moduleIndex, fixtureIndex, value);
                    return;
                case START_STOP_MODULE:

                    System.out.println("start stop module");
                    startStopModule(moduleIndex);
                    return;
                case TEST_ARTNET_PACKET:
                    System.out.println("test artnet packet");
                    instanceController.sendPacket();
                default:
            }
        });
    }

    private void setupMenuListeners(Stage stage) {
        mainMenu.getSavePressed().addListener(cl -> {
            System.out.println("Save Pressed in Main");
            fileManager.saveFile(stage, moduleContainer);
            stage.setTitle(fileManager.getFileName());

        });

        mainMenu.getSaveAsPressed().addListener(cl -> {
            System.out.println("Save As Pressed in Main");
            fileManager.saveAsFile(stage, moduleContainer);
            stage.setTitle(fileManager.getFileName());

        });

        mainMenu.getLoadPressed().addListener(cl -> {
            System.out.println("Load Pressed in Main");
            try {
                moduleContainer.load(fileManager.loadFile(stage));
//                configView.generateView(moduleContainer.getModules());
                configView.refreshConfigView(moduleContainer);
                System.out.println("Able to load");
            } catch (IOException ex) {

            }
            stage.setTitle(fileManager.getFileName());

        });

        mainMenu.getNewPressed().addListener(cl -> {
            System.out.println("New Pressed in Main");
        });
    }

}
