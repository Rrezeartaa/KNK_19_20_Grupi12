package pwm.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pwm.components.ErrorPopupComponent;
import pwm.models.User;
import pwm.models.UserRole;
import pwm.repositories.UserRepository;
import pwm.utils.SecurityHelper;
import pwm.utils.SessionManager;

public class LoginController extends BaseController {
  @FXML
  private TextField emailField;
  @FXML
  private PasswordField passwordField;
  @FXML
  private Button loginButton;

  @FXML
  private void onLoginButtonClick(ActionEvent event) {
    try {
      String email = emailField.getText();
      String password = passwordField.getText();

      User user = null;
      if (hasUsers()) {
        user = login(email, password);
        if (user == null)
          throw new Exception("Invalid Credentials");
      } else {
        user = register(email, password);
      }

      SessionManager.user = user;
      SessionManager.lastLogin = new Date();

      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../views/main-screen.fxml"));
      Parent root = loader.load();
