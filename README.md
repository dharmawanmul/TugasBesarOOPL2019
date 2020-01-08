# TugasBesarOOPL2019
Tugas Besar Pemrograman Berorientasi Objek 2 B

## Table of Contents
- [Components](https://github.com/dharmawanmul/TugasBesarOOPL2019#components)
- [Credits](https://github.com/dharmawanmul/TugasBesarOOPL2019#credits)

### Components
This part will be reviewed and updated as the project progress increases.

The code at LoginEmployee.java which contains the login method
```java
    public void login() throws IOException {
        Employee e = new Employee();
        e.setUsername(txtUsername.getText());
        e.setPassword(txtPassword.getText());
        employeeDao = new EmployeeDaoImpl();
        temp = employeeDao.loginEmployee(e);
        switch (temp.getRoleByRoleIdRole().getIdRole()) {
            case 1 : loadStage(1);
                break;
            case 2 : loadStage(2);
                break;
            case 3 : {
                loadParkingAttendantsStage(temp);
                break;
            }
        }
    }
```

The code at LoginEmployee.java that passes an object to another controller
```java
    public void loadParkingAttendantsStage(Employee temp) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();

        loader.setLocation(Main.class.getResource("view/ParkingAttendants.fxml"));
        loader.setRoot(null);

        Pane root = loader.load();
        ParkingAttendantsController attendantsController = loader.getController();
        attendantsController.setController(this);
        attendantsController.setLoginEmployee(temp);

        Stage loginStage = (Stage) btnLogin.getScene().getWindow();
        loginStage.close();

        stage.setTitle("Parking Attendants");
        stage.setMaximized(true);
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("com/oopl/icon/parking.png"));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
```

The code at ParkingAttendantsController.java
```java
public class ParkingAttendantsController implements Initializable {
    @FXML
    private JFXTextField txtNRP;
    @FXML
    private JFXTextField txtRegNo;
    @FXML
    private JFXTextField txtVoucher;
    @FXML
    private JFXTextField txtPermission;
    @FXML
    private JFXTextArea txtPrice;
    @FXML
    private ImageView imgEmp;
    @FXML
    private Label lblEmpName;
    private Employee loginEmployee;
    @FXML
    private Label lblEmpId;
    private LoginEmployeeController controller;

    public void setController(LoginEmployeeController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setLoginEmployee(Employee employee) {
        this.loginEmployee = employee;
    }
}
```

## Credits
This project is created and maintained by Mulyadi Dharmawan and Nicolavickh Yohanes

This project is powered by :

<a href="https://www.jetbrains.com/idea/">
    <img src="https://github.com/Hexworks/zircon/blob/master/images/idea_logo.png" width="40" height="40" />
</a>

### Thanks
Big thanks to Nicolavickh Yohanes
