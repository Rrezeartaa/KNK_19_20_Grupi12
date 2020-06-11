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
       MainController controller = loader.getController();
      controller.loadView(MainController.PRODUCT_LIST_VIEW);
      Scene scene = new Scene(root);

      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }

  private User login(String email, String password) throws Exception {
    User user = UserRepository.find(email);
    if (user == null)
      return null;
    return SecurityHelper.computeHash(password, user.getSalt()).equals(user.getPassword()) ? user : null;
  }

  private User register(String email, String password) throws Exception {
    String salt = SecurityHelper.generateSalt();
    password = SecurityHelper.computeHash(password, salt);
    User user = new User(-1, "Default Admin", email, password, salt, UserRole.Admin, true, null, null);
    user = UserRepository.create(user);
    return user;
  }

  private boolean hasUsers() throws Exception {
    return UserRepository.count() > 0;
  }

  @Override
  public void loadLangTexts(ResourceBundle langBundle) {
    String emailTxt = langBundle.getString("login_email");
    String passwordTxt = langBundle.getString("login_password");
    String loginTxt = langBundle.getString("login_button_login");
    String registerTxt = langBundle.getString("login_button_register");

    emailField.setPromptText(emailTxt);
    passwordField.setPromptText(passwordTxt);
    try {
      if (hasUsers()) {
        loginButton.setText(loginTxt);
      } else {
        loginButton.setText(registerTxt);
      }
    } catch (Exception e) {
      loginButton.setText(loginTxt);
    }
  }

}
