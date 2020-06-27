package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class database {

    @FXML
    private TreeTableView<?> treeTableView;

    @FXML
    private TreeTableColumn<?, ?> nameCol;

    @FXML
    private TreeTableColumn<?, ?> jobCol;

    @FXML
    private TreeTableColumn<?, ?> ageCol;

    @FXML
    private TreeTableColumn<?, ?> genderCol;

    @FXML
    private TextField searchTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField jobTF;

    @FXML
    private TextField ageTF;

    @FXML
    private ComboBox<?> genderCombo;

    @FXML
    private Label nameLB;

    @FXML
    private Label jobLB;

    @FXML
    private Label ageLB;

    @FXML
    private Label genderLB;

}