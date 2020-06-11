package pwm.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pwm.components.AboutComponent;
import pwm.components.ErrorPopupComponent;
import pwm.models.LangEnum;
import pwm.models.Product;
import pwm.models.User;
import pwm.utils.AppConfig;
import pwm.utils.DateHelper;
import pwm.utils.SessionManager;

public class MainController extends BaseController {
  private final static String VIEW_PATH = "../views";
  public final static String PRODUCT_DETAILS_VIEW = "product-details";
  public final static String PRODUCT_LIST_VIEW = "product-list";
  public final static String USER_DETAILS_VIEW = "user-details";
  public final static String USER_LIST_VIEW = "user-list";

  private BaseController childController;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    super.initialize(arg0, arg1);
    boolean enSelected = AppConfig.get().getLanguage() == LangEnum.EN;
    enCheckMenuItem.setSelected(enSelected);
    alCheckMenuItem.setSelected(!enSelected);
  }

  @FXML
  private Label navLabel;
  @FXML
  private Button navProductsButton;
  @FXML
  private Button navUsersButton;
  @FXML
  private Button navLogoutButton;
  @FXML
  private VBox contentPane;
  @FXML
  private Label statusLabel;
  @FXML
  private CheckMenuItem enCheckMenuItem;
  @FXML
  private CheckMenuItem alCheckMenuItem;

  public void loadView(String screen) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    Parent node;
    switch (screen) {
      case PRODUCT_DETAILS_VIEW:
        loader.setLocation(getClass().getResource(getViewPath(PRODUCT_DETAILS_VIEW)));
        node = loader.load();
        break;
      case PRODUCT_LIST_VIEW:
        loader.setLocation(getClass().getResource(getViewPath(PRODUCT_LIST_VIEW)));
        node = loader.load();
        break;
      case USER_DETAILS_VIEW:
        loader.setLocation(getClass().getResource(getViewPath(USER_DETAILS_VIEW)));
        node = loader.load();
        break;
      case USER_LIST_VIEW:
        loader.setLocation(getClass().getResource(getViewPath(USER_LIST_VIEW)));
        node = loader.load();
        break;
      default:
        throw new Exception("ERR_SCREEN_NOT_FOUND");
    }

    ChildController controller = loader.getController();
    loadView(screen, node, controller);
  }

  public void loadView(String screen, Parent node, ChildController controller) throws Exception {
    controller.setParentController(this);
    this.childController = controller;

    contentPane.getChildren().clear();
    contentPane.getChildren().add(node);
    VBox.setVgrow(node, Priority.ALWAYS);

    switch (screen) {
      case PRODUCT_DETAILS_VIEW:
        contentPane.setAlignment(Pos.TOP_CENTER);
        break;
      case PRODUCT_LIST_VIEW:
        contentPane.setAlignment(Pos.TOP_LEFT);
        break;
      case USER_DETAILS_VIEW:
        contentPane.setAlignment(Pos.TOP_CENTER);
        break;
      case USER_LIST_VIEW:
        contentPane.setAlignment(Pos.TOP_CENTER);
        break;
      default:
        throw new Exception("ERR_SCREEN_NOT_FOUND");
    }
  }

  @FXML
  private void onProductsNavClick(ActionEvent event) {
    try {
      this.loadView(PRODUCT_LIST_VIEW);
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }

  @FXML
  private void onUsersNavClick(ActionEvent event) {
    try {
 this.loadView(USER_LIST_VIEW);
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }

  @FXML
  private void onLogoutNavClick(ActionEvent event) {
    try {
      Parent root = FXMLLoader.load(getClass().getResource(getViewPath("login")));
      Scene scene = new Scene(root);

      Stage primaryStage = null;
      if (event.getSource() instanceof MenuItem) {
        primaryStage = (Stage) statusLabel.getScene().getWindow();
      } else {
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      }
      primaryStage.setScene(scene);

      SessionManager.user = null;
      primaryStage.show();
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }

  @FXML
  private void onExitNavClick(ActionEvent event) {
    try {
      Stage primaryStage = (Stage) statusLabel.getScene().getWindow();
      primaryStage.close();
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }

  @FXML
  private void onInsertProductClick(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(getViewPath(PRODUCT_DETAILS_VIEW)));

      Pane pane = loader.load();
      ProductDetailsController controller = loader.getController();
      controller.setModel(new Product());
      controller.setEditable(true);

      this.loadView(PRODUCT_DETAILS_VIEW, pane, controller);
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }

  @FXML
  private void onInsertUserClick(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(getViewPath(USER_DETAILS_VIEW)));

      Pane pane = loader.load();
      UserDetailsController controller = loader.getController();
      controller.setModel(new User());

      this.loadView(USER_DETAILS_VIEW, pane, controller);
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }

  @FXML
  private void onAboutClick(ActionEvent event) {
    try {
      new AboutComponent().showDialog();
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }

  @FXML
  private void onChangeLangMenuItemEnClick(ActionEvent event) {
    enCheckMenuItem.setSelected(true);
    alCheckMenuItem.setSelected(false);
    changeUILanguage();
  }

  @FXML
  private void onChangeLangMenuItemAlClick(ActionEvent event) {
    enCheckMenuItem.setSelected(false);
    alCheckMenuItem.setSelected(true);
    changeUILanguage();
  }


  private void changeUILanguage() {
    try {
      LangEnum lang = enCheckMenuItem.isSelected() ? LangEnum.EN : LangEnum.AL;
      AppConfig.get().setLanguage(lang);
      
      ResourceBundle langBundle = getLangBundle();
      loadLangTexts(langBundle);
      childController.loadLangTexts(langBundle);
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }

  private String getViewPath(String file) {
    return VIEW_PATH + "/" + file + ".fxml";
  }

  @Override
  public void loadLangTexts(ResourceBundle langBundle) {
    String navLabelTxt = langBundle.getString("main_nav_label");
    String navProductsTxt = langBundle.getString("main_nav_products");
    String navUsersTxt = langBundle.getString("main_nav_users");
    String navLogoutTxt = langBundle.getString("main_nav_logout");
    String statusLabelTxt = langBundle.getString("main_status_label");

    String user = SessionManager.user.getEmail();
    String loginTime = DateHelper.toSqlFormat(SessionManager.lastLogin);

    statusLabel.setText(String.format(statusLabelTxt, user, loginTime));
    navLabel.setText(navLabelTxt);
    navProductsButton.setText(navProductsTxt);
    navUsersButton.setText(navUsersTxt);
    navLogoutButton.setText(navLogoutTxt);
  }
}
