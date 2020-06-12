package pwm.controllers;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import pwm.components.ErrorPopupComponent;
import pwm.components.PaginationComponent;
import pwm.components.UserCardComponent;
import pwm.models.User;
import pwm.repositories.UserRepository;

public class UserListController extends ChildController {
  private final int PAGE_SIZE = 10;

  private PaginationComponent paginationComponent;

  @FXML
  private FlowPane usersPane;
  @FXML
  private HBox paginationPane;

  @Override
  public void initialize(URL url, ResourceBundle bundle) {
    super.initialize(url, bundle);
    try {
      paginationComponent = new PaginationComponent(userCount(), PAGE_SIZE);
      paginationComponent.render(paginationPane, (page) -> {
        try {
          showUsers(page);
        } catch (Exception e) {
          ErrorPopupComponent.show(e);
        }
      });
        private int userCount() throws Exception {
    return UserRepository.count();
  }

  private void showUsers(int page) throws Exception {
    usersPane.getChildren().clear();
    List<User> users = UserRepository.getAll(PAGE_SIZE, page);
    UserCardComponent userCard = new UserCardComponent();
    for (User user : users) {
      usersPane.getChildren()

      showUsers(0);
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }
