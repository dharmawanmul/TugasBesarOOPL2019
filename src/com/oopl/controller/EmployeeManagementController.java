package com.oopl.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.oopl.Main;
import com.oopl.dao.EmployeeDaoImpl;
import com.oopl.dao.RoleDaoImpl;
import com.oopl.entity.Employee;
import com.oopl.entity.Role;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeManagementController implements Initializable {
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton refreshBtn;
    @FXML
    private TableView<Employee> tableEmployee;
    @FXML
    private TableColumn<Employee, ImageView> colPhotos;
    @FXML
    private TableColumn<Employee, String> colName;
    @FXML
    private TableColumn<Employee, String> colUsername;
    @FXML
    private TableColumn<Employee, String> colPosition;
    @FXML
    private TableColumn<Employee, String> colEmpId;
    @FXML
    private JFXComboBox<Role> cmbxRole;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXPasswordField txtRePass;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton deleteBtn;
    private final ObservableList<Employee> dataList = FXCollections.observableArrayList();
    @FXML
    private VBox vbox;
    private RoleDaoImpl roleDao;
    private ObservableList<Role> roles;
    private EmployeeDaoImpl employeeDao;
    private ObservableList<Employee> employees;
    @FXML
    private JFXButton uploadBtn;
    @FXML
    private JFXButton removeBtn;
    @FXML
    private ImageView imgChecker;
    @FXML
    private Label imgMessageLabel;
    private FileChooser fileChooser;
    private File file;
    private String imageName;
    private Path copy,files;
    @FXML
    private Label lblRePass;
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    private Employee selectedItems;
    @FXML
    private HBox dragTarget;


    public ObservableList<Role> getRole() {
        if (roles == null) {
            roles = FXCollections.observableArrayList();
            roles.addAll(getRoleDao().showAll());
        }
        return roles;
    }

    public RoleDaoImpl getRoleDao() {
        if (roleDao == null) {
            roleDao = new RoleDaoImpl();
        }
        return roleDao;
    }

    public ObservableList<Employee> getEmployees() {
        if (employees == null) {
            employees = FXCollections.observableArrayList();
            employees.addAll(getEmployeeDao().showAll());
        }
        return employees;
    }

    public EmployeeDaoImpl getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpg")
        );

        dataList.addAll(getEmployees());
        FilteredList<Employee> filteredData = new FilteredList<>(dataList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (employee.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (employee.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (employee.getRoleByRoleIdRole().getRole().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<Employee> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableEmployee.comparatorProperty());

        tableEmployee.setItems(sortedData);

        txtPass.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!txtPass.getText().equals(txtRePass.getText())) {
                    lblRePass.setStyle("-fx-text-fill: red");
                    lblRePass.setText("Password Didn't Match");
                    saveBtn.setDisable(true);
                } else {
                    lblRePass.setStyle("-fx-text-fill: green");
                    lblRePass.setText("Password match");
                    saveBtn.setDisable(false);
                }
            }
        });

        cmbxRole.setItems(getRole());

        colPhotos.setMaxWidth(1350);
        colPhotos.setCellValueFactory(d -> new SimpleObjectProperty<ImageView>(loadImage(new Image(this.getClass().getResourceAsStream(d.getValue().getPhotos())))));
        colEmpId.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getIdEmployee().toString()));
        colName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        colUsername.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUsername()));
        colPosition.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoleByRoleIdRole().getRole()));
    }

    private ImageView loadImage(Image image) {
        ImageView imageView= new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(80);
        imageView.setImage(image);

        return imageView;
    }


    @FXML
    private void saveAct(ActionEvent actionEvent) {
        Employee e = new Employee();
        e.setName(txtName.getText());
        e.setPassword(txtPass.getText());
        e.setUsername(txtUsername.getText());
        e.setPhotos(imageName);
        e.setRoleByRoleIdRole(cmbxRole.getSelectionModel().getSelectedItem());
        if (txtName.getText().isEmpty()) {
            alert.setTitle("Error");
            alert.setContentText("Employee name cannot be empty");
            alert.showAndWait();
        } else if (txtUsername.getText().isEmpty()) {
            alert.setTitle("Error");
            alert.setContentText("Username cannot be empty");
            alert.showAndWait();
        } else if (txtPass.getText().isEmpty()) {
            alert.setTitle("Error");
            alert.setContentText("Password cannot be empty");
            alert.showAndWait();
        } else if (txtRePass.getText().isEmpty()) {
            alert.setTitle("Error");
            alert.setContentText("Password cannot be empty");
            alert.showAndWait();
        } else if (!txtPass.getText().equals(txtRePass.getText())) {
            alert.setTitle("Error");
            alert.setContentText("Password didn't match");
            alert.showAndWait();
        } else if (cmbxRole.getValue() == null) {
            alert.setTitle("Error");
            alert.setContentText("Employee role cannot be empty");
            alert.showAndWait();
        } else if (imageName == null) {
            alert.setTitle("Error");
            alert.setContentText("Please choose employee's photo");
            alert.showAndWait();
        } else {
            try {
                employeeDao.addData(e);
                File dir = new File(System.getProperty("user.dir"));
                copy = Paths.get(dir + "/src/com/oopl/images/" + imageName);
                CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                };
                tableEmployee.refresh();
                clearForm();
                refreshTable();
                Files.copy(files, copy, options);
                dataList.clear();
                dataList.addAll(getEmployees());
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void updateAct(ActionEvent actionEvent) {
        selectedItems.setName(txtName.getText());
        selectedItems.setUsername(txtUsername.getText());
        selectedItems.setPassword(txtPass.getText());
        if (txtPass.getText().equals(txtRePass.getText())) {
            selectedItems.setRoleByRoleIdRole(cmbxRole.getSelectionModel().getSelectedItem());
            getEmployeeDao().updateData(selectedItems);
            dataList.clear();
            dataList.addAll(getEmployees());
            clearForm();
            refreshTable();
        } else {
            alert.setTitle("Error");
            alert.setContentText("Password didn't match");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteAct(ActionEvent actionEvent) {
        if (tableEmployee.getItems().isEmpty()) {
            refreshTable();
        } else {
            Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmation.setContentText("Are you sure you want to delete this data ?");
            deleteConfirmation.setTitle("Removal Confirmation");
            deleteConfirmation.showAndWait();
            if (deleteConfirmation.getResult() == ButtonType.OK) {
                selectedItems = tableEmployee.getSelectionModel().getSelectedItem();
                getEmployeeDao().deleteData(selectedItems);
                dataList.clear();
                dataList.addAll(getEmployees());
                refreshTable();
                clearForm();
            }
        }
    }

    @FXML
    private void uploadAct(ActionEvent actionEvent) {
        fileChooser.setTitle("Choose File");
        file =  fileChooser.showOpenDialog(null);
        if(file != null){
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imgChecker.setPreserveRatio(true);
                imgChecker.setFitWidth(150);
                imgChecker.setFitHeight(200);
                imgChecker.setImage(image);
                imgMessageLabel.setText(file.getName());

                imageName = file.getName();
                files = Paths.get(file.toURI());
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void onClicked(MouseEvent mouseEvent) {
        saveBtn.setDisable(true);
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
        selectedItems = tableEmployee.getSelectionModel().getSelectedItem();
        txtName.setText(selectedItems.getName());
        txtUsername.setText(selectedItems.getUsername());
        cmbxRole.setValue(selectedItems.getRoleByRoleIdRole());
        imgChecker.setPreserveRatio(true);
        imgChecker.setImage(new Image(this.getClass().getResourceAsStream(selectedItems.getPhotos())));
    }

    @FXML
    private void refreshAct(ActionEvent actionEvent) {
        imgChecker.setImage(null);
        imgMessageLabel.setText("");
        clearForm();
        refreshTable();
        saveBtn.setDisable(false);
        updateBtn.setDisable(true);
        dataList.clear();
        dataList.addAll(getEmployees());
    }

    private void clearForm() {
        txtName.clear();
        txtUsername.clear();
        txtPass.clear();
        txtRePass.clear();
        txtSearch.clear();
        cmbxRole.setValue(null);
        tableEmployee.getSelectionModel().clearSelection();
        selectedItems = null;
        dragTarget.getStyleClass().clear();
        imgMessageLabel.setText("");
        lblRePass.setText("");
        lblRePass.getStyleClass().clear();
        imgChecker.setImage(null);
        //
        imageName = null;
    }

    private void refreshTable() {
        getEmployees().clear();
        getEmployees().addAll(getEmployeeDao().showAll());
//        tableEmployee.refresh();
    }

    @FXML
    private void droppedAct(DragEvent dragEvent) throws IOException {
        Dragboard db = dragEvent.getDragboard();
        if (db.hasFiles()) {
            db.getFiles().forEach(file -> {
                try {
                    Image image = new Image(file.toURI().toURL().toExternalForm());
                    imgChecker.setFitWidth(150);
                    imgChecker.setFitHeight(200);
                    imgChecker.setImage(image);
                    imgMessageLabel.setText(file.getName());
                    imageName = file.getName();
                    files = Paths.get(file.toURI());
                } catch (Exception exc) {
                    System.out.println("Could not load image " + file);
                }
            });
            dragEvent.setDropCompleted(true);
        }
        dragTarget.getStyleClass().clear();
//        Image path;
//        if (db.hasImage() || db.hasFiles()) {
//            File file = new File(db.getUrl());
//            BufferedImage bufferedImage = ImageIO.read(file);
//            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//            path = db.getImage();
//            imgChecker.setPreserveRatio(true);
//            imgChecker.setImage(path);
//            imgMessageLabel.setText(file.getName());
//            imageName = file.getName();
//            files = Paths.get(file.toURI());
//            System.out.println(files);
//            System.out.println(imageName);
//            System.out.println(files.getFileName());
//            System.out.println(files.getName(0));
//            System.out.println(files.getFileSystem());
//            dragEvent.setDropCompleted(true);
//        }
    }

    @FXML
    private void dragOverAct(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        if (db.hasFiles() || db.hasImage()) {
            dragEvent.acceptTransferModes(TransferMode.COPY);
            dragTarget.setStyle(
                    "-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" +
                    "-fx-border-color: blue;"
            );
        }
        dragTarget.getStyleClass().clear();
    }
}
