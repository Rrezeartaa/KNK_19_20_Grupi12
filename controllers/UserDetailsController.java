package pwm.controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pwm.components.ErrorPopupComponent;
import pwm.models.User;
import pwm.models.UserRole;
import pwm.models.view.UserViewModel;
import pwm.repositories.UserRepository;
import pwm.utils.DateHelper;
import pwm.utils.SecurityHelper;
import pwm.utils.Util;

public class UserDetailsController extends ChildController {
  private User originalModel;
  private UserViewModel viewModel;

  @FXML
  private TextField idField;
  @FXML
  private TextField nameField;
  @FXML
  private TextField emailField;
  @FXML
  private PasswordField passwordField;
