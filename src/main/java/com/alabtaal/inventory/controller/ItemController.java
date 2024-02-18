package com.alabtaal.inventory.controller;
import com.alabtaal.inventory.entity.ItemEntity;
import com.alabtaal.inventory.service.ItemService;
import com.alabtaal.inventory.util.FXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class ItemController implements Initializable {

    private final ItemService itemService;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TableView<ItemEntity> itemsTable;

    @FXML
    private TableColumn<ItemEntity , String> nameColumn;

    @FXML
    private TableColumn<ItemEntity , String> brandColumn;

    @FXML
    private TableColumn<ItemEntity , String> priceColumn;

    @FXML
    private ChoiceBox<String> itemTypeChoiceBox;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemTypeChoiceBox.setItems(FXCollections.observableList(getItemTypes()));
        setupListeners();
        setupTable();
    }

    private List<ItemEntity> findAll(){
       return itemService.findAll();
    }

    private void setupTable(){
        nameColumn.setCellValueFactory(
                (cellData) -> new SimpleStringProperty(cellData.getValue().getName())
        );
        brandColumn.setCellValueFactory(
                (cellData) -> new SimpleStringProperty(cellData.getValue().getBrand())
        );
        priceColumn.setCellValueFactory(
                (cellData) -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrice()))
        );
        itemsTable.setItems(FXCollections.observableList(findAll()));

        itemsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldSelection, newSelection) -> {
                    if (newSelection != null){
                        idTextField.setText(String.valueOf(newSelection.getId()));
                        nameTextField.setText(newSelection.getName());
                        brandTextField.setText(newSelection.getBrand());
                        priceTextField.setText(String.valueOf(newSelection.getPrice()));
                        if (newSelection.getItemType() != null){
                            itemTypeChoiceBox.setValue(newSelection.getItemType());
                        }else {
                            itemTypeChoiceBox.setValue("");
                        }
                    }
                }
                );
    }

    private void setupListeners(){
        idTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> onIdTextFieldChanged(oldValue,newValue)
        );
        nameTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> onNameTextFieldChanged(oldValue,newValue)
        );
        brandTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> onBrandTextFieldChanged(oldValue,newValue)
        );
        priceTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> onPriceTextFieldChanged(oldValue,newValue)
        );
    }

    public void onIdTextFieldChanged(String oldValue , String newValue){
        try {
            System.out.println(newValue);
            System.out.println(oldValue);
            Double.parseDouble(newValue);
        }catch (Exception e){
            idTextField.setText(oldValue);
        }
    }

    public void onNameTextFieldChanged(String oldValue , String newValue){
        System.out.println(newValue);
        System.out.println(oldValue);
    }

    public void onBrandTextFieldChanged(String oldValue , String newValue){
        System.out.println(newValue);
        System.out.println(oldValue);
    }

    public void onPriceTextFieldChanged(String oldValue , String newValue){
        System.out.println(newValue);
        System.out.println(oldValue);
    }

    public void onSaveButtonPressed(){
        try {
            final ItemEntity entity = new ItemEntity();
            if (!idTextField.getText().isEmpty()) {
                entity.setId(Long.parseLong(idTextField.getText()));
            }
            entity.setName(nameTextField.getText());
            entity.setBrand(brandTextField.getText());
            if (priceTextField.getText().isEmpty()){
                FXUtils.showMessage(Alert.AlertType.ERROR,"Price must be entered");
                return;
            }
            entity.setPrice(Double.parseDouble(priceTextField.getText()));
            entity.setItemType(itemTypeChoiceBox.getValue());
            itemService.save(entity);
            FXUtils.showMessage(Alert.AlertType.INFORMATION , "Item saved successfully");
            itemsTable.setItems(FXCollections.observableList(findAll()));
//            clearFields();
        }catch (Exception e){
            String error = e.getMessage();
            if (error.contains("items_uk1")){
                error = "Name Already Exists";
            }
            FXUtils.showMessage(Alert.AlertType.ERROR,error);
        }
    }

    public void onDeleteButtonPressed(){
        if (idTextField.getText().isEmpty()){
            FXUtils.showMessage(Alert.AlertType.ERROR,"Id must be entered");
            return;
        }
        try {
            itemService.delete(Long.valueOf(idTextField.getText()));
            FXUtils.showMessage(Alert.AlertType.INFORMATION, "Item deleted Successfully");
            itemsTable.setItems(FXCollections.observableList(findAll()));
            clearFields();
        }catch (Exception e){
            FXUtils.showMessage(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    public void onFindButtonPressed(){
        try {

            Optional<ItemEntity> optionalItem = itemService.findByName(nameTextField.getText());
            ItemEntity entity =optionalItem.orElse(new ItemEntity());
            idTextField.setText(String.valueOf(entity.getId()));
            nameTextField.setText(entity.getName());
            brandTextField.setText(entity.getBrand());
            priceTextField.setText(String.valueOf(entity.getPrice()));
            itemTypeChoiceBox.setValue(entity.getItemType());
            System.out.println(entity);
        }catch (Exception e){
            FXUtils.showMessage(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    private static List<String> getItemTypes(){
        return List.of("Finish Good", "Raw Material", "All");
    }

    public void clearFields(){
        idTextField.clear();
        nameTextField.clear();
        brandTextField.clear();
        priceTextField.clear();
        itemTypeChoiceBox.setValue("");
    }
}