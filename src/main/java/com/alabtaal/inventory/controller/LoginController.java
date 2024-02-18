package com.alabtaal.inventory.controller;

import com.alabtaal.inventory.config.StageManager;
import com.alabtaal.inventory.enums.FxmlView;
import com.alabtaal.inventory.util.FXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LoginController implements Initializable {

    private final String username = "1234";

    private final String password = "1234";

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    private final StageManager stageManager;

    public LoginController(@Lazy StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    enableOrDisableLoginButton();
    }

    @FXML
    public void onUsernameTextFieldChanged(){
        enableOrDisableLoginButton();
    }

    @FXML
    public void onPasswordFieldChanged(){
        enableOrDisableLoginButton();
    }

    public void onLoginButtonPressed(){
        if (!usernameTextField.getText().equalsIgnoreCase(username)){
            // error
            FXUtils.showMessage(Alert.AlertType.ERROR,"Username or Password is invalid");
            usernameTextField.requestFocus();
        }else if (!passwordTextField.getText().equals(password)){
            // error
            FXUtils.showMessage(Alert.AlertType.ERROR,"Username or Password is invalid");
            usernameTextField.requestFocus();
        }else {
            stageManager.switchScene(FxmlView.MENU);
        }
    }

    private boolean isFormValid(){
        return usernameTextField.getText().length() >= 3 && passwordTextField.getText().length()>=3;

    }

    private void enableOrDisableLoginButton(){
        loginButton.setDisable(!isFormValid());
    }
}
