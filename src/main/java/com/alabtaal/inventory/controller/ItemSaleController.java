package com.alabtaal.inventory.controller;

import com.alabtaal.inventory.entity.ItemEntity;
import com.alabtaal.inventory.entity.ItemSaleEntity;
import com.alabtaal.inventory.repository.ItemSaleRepo;
import com.alabtaal.inventory.service.ItemService;
import com.alabtaal.inventory.util.FXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class ItemSaleController implements Initializable {

    private final ItemService itemService;

    private final ItemSaleRepo saleRepo;

    @FXML
    private ChoiceBox<String> itemTypeChoiceBox;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TableView<ItemSaleEntity> itemSaleTable;

    @FXML
    private TableColumn<ItemSaleEntity , String> nameColumn;

    @FXML
    private TableColumn<ItemSaleEntity , String> brandColumn;



    @FXML
    private TableColumn<ItemSaleEntity , String> quantityColumn;

    @FXML
    private TableColumn<ItemSaleEntity , String> priceColumn;



    public ItemSaleController(ItemService itemService, ItemSaleRepo saleRepo) {
        this.itemService = itemService;
        this.saleRepo = saleRepo;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemTypeChoiceBox.setItems(FXCollections.observableList(getItems()));
        setupItemSaleTable();
    }

    private List<ItemEntity> findAll(){
       return itemService.findAll();
    }

    private void setupItemSaleTable(){
        nameColumn.setCellValueFactory(
                (cellData) -> new SimpleStringProperty(cellData.getValue().getItem().getName())
        );
        brandColumn.setCellValueFactory(
                (cellData) -> new SimpleStringProperty(cellData.getValue().getItem().getBrand())
        );
        quantityColumn.setCellValueFactory(
                (cellData) -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantity()))
        );
        priceColumn.setCellValueFactory(
                (cellData) -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrice()))
        );
        itemSaleTable.setItems(FXCollections.observableList(saleRepo.findAll()));

        itemSaleTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldSelection, newSelection) -> {
                    if (newSelection != null){
                        quantityTextField.setText(String.valueOf(newSelection.getQuantity()));
                        priceTextField.setText(String.valueOf(newSelection.getPrice()));
                        if (newSelection.getItem().getItemType() != null){
                            itemTypeChoiceBox.setValue(newSelection.getItem().getItemType());
                        }else {
                            itemTypeChoiceBox.setValue("");
                        }
                    }
                }
                );
    }



    public void onSaveButtonPressed(){
        try {
            final ItemEntity item = itemService.getByName2(itemTypeChoiceBox.getValue());
            final ItemSaleEntity entity = new ItemSaleEntity();
            entity.setItem(item);
            if (priceTextField.getText().isEmpty()){
                FXUtils.showMessage(Alert.AlertType.ERROR,"Price must be entered");
                return;
            }
            entity.setPrice(Double.parseDouble(priceTextField.getText()));
            entity.setQuantity(Long.valueOf(quantityTextField.getText()));
            saleRepo.save(entity);
            FXUtils.showMessage(Alert.AlertType.INFORMATION , "Item saved successfully");
            itemSaleTable.setItems(FXCollections.observableList(saleRepo.findAll()));
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
//        if (idTextField.getText().isEmpty()){
//            FXUtils.showMessage(Alert.AlertType.ERROR,"Id must be entered");
//            return;
//        }
//        try {
//            itemService.delete(Long.valueOf(idTextField.getText()));
//            FXUtils.showMessage(Alert.AlertType.INFORMATION, "Item deleted Successfully");
//            itemsTable.setItems(FXCollections.observableList(findAll()));
//            clearFields();
//        }catch (Exception e){
//            FXUtils.showMessage(Alert.AlertType.ERROR,e.getMessage());
//        }

    }

    public void onFindButtonPressed(){
        try {

            Optional<ItemEntity> optionalItem = itemService.findByName(quantityTextField.getText());
            ItemEntity entity =optionalItem.orElse(new ItemEntity());
            quantityTextField.setText(entity.getName());
            priceTextField.setText(String.valueOf(entity.getPrice()));
            itemTypeChoiceBox.setValue(entity.getItemType());
            System.out.println(entity);
        }catch (Exception e){
            FXUtils.showMessage(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    private List<String> getItems(){
        List<String> itemNames = new ArrayList<>();
        List<ItemEntity> items = findAll();
        for (ItemEntity item : items){
            itemNames.add(item.getName());
        }
        return itemNames;
    }

    public void clearFields(){
        quantityTextField.clear();
        priceTextField.clear();
        itemTypeChoiceBox.setValue("");
    }
}