package com.alabtaal.inventory.controller;


import com.alabtaal.inventory.config.SpringFXMLLoader;
import com.alabtaal.inventory.config.StageManager;
import com.alabtaal.inventory.enums.FxmlView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MenuController implements Initializable {

    private final StageManager stageManager;

    private final SpringFXMLLoader fxmlLoader;

    @FXML
    private BorderPane rootBorderPane;

    public MenuController(@Lazy StageManager stageManager, SpringFXMLLoader fxmlLoader) {
        this.stageManager = stageManager;
        this.fxmlLoader = fxmlLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            switchView(FxmlView.HOME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onItemMenuItemSelected() throws IOException {
        switchView(FxmlView.ITEM);
    }

    @FXML
    public void onPage2MenuItemSelected() throws IOException {
        switchView(FxmlView.PAGE2);
    }

    @FXML
    public void onPage3MenuItemSelected() throws IOException {
        switchView(FxmlView.PAGE3);
    }

    @FXML
    public void onCloseMenuItemSelected() throws IOException {
        Platform.exit();
    }
    @FXML
    public void onSaleMenuItemSelected() throws IOException {
        switchView(FxmlView.SALE);
    }

    @FXML
    public void onPurchaseMenuItemSelected() throws IOException {
        switchView(FxmlView.PURCHASE);
    }

    @FXML
    public void onHelpMenuItemSelected() throws IOException {
        switchView(FxmlView.HELP);
    }

    private void switchView(FxmlView fxmlView) throws IOException {
        Parent view = fxmlLoader.load(fxmlView.getFxmlFile());
        stageManager.getStage().setTitle(fxmlView.getTitle());
        rootBorderPane.setCenter(view);
    }


}
