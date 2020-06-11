package pwm.controllers;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pwm.components.ErrorPopupComponent;
import pwm.models.Product;
import pwm.models.view.ProductViewModel;
import pwm.repositories.ProductRepository;
import pwm.utils.DateHelper;
import pwm.utils.FileHelper;
import pwm.utils.Util;

public class ProductDetailsController extends ChildController {
  @FXML
  private TextField idField;
  @FXML
  private TextField titleField;
  @FXML
  private TextArea descriptionField;
  @FXML
  private ImageView imageField;
  @FXML
  private TextField priceField;
  @FXML
  private TextField qtyField;
  @FXML
  private TextField createdAtField;
  @FXML
  private TextField updatedAtField;

  private boolean isEditable = false;
  private ProductViewModel viewModel;
  private FileChooser fileChooser;

  @Override
  public void initialize(URL url, ResourceBundle bundle) {
    super.initialize(url, bundle);
    fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png", "*.jpg", "*.jpeg"));
  }

  @FXML
  private void onCancelButtonClick(ActionEvent event) {
    try {
      parentController.loadView(MainController.PRODUCT_LIST_VIEW);
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }

  @FXML
  private void onSaveButtonClick(ActionEvent event) {
    try {
      if (viewModel.getId() > 0) {
        ProductRepository.update(viewModel.getModel());
      } else {
        ProductRepository.create(viewModel.getModel());
      }
      parentController.loadView(MainController.PRODUCT_LIST_VIEW);
    } catch (Exception e) {
      ErrorPopupComponent.show(e);
    }
  }
