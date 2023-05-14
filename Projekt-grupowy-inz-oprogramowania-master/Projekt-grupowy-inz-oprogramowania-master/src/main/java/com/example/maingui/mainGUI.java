package com.example.maingui;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.TableColumn.CellDataFeatures;

public class mainGUI extends Application {
    public int sqlCounter;
    public TableView sqlTableView = new TableView<>();
    public ObservableList<ObservableList<String>> sqlData = FXCollections.observableArrayList();
    /* LINK DO GUIDE'A Z TWORZENIEM DYNAMIC TABLE VIEW FOR SQL
    https://blog.ngopal.com.np/2011/10/19/dyanmic-tableview-data-from-database/comment-page-1/
    */

    private ObservableList<ObservableList<String>> readDataFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            ObservableList<String> rowData = FXCollections.observableArrayList();
            for (String column : columns) {
                rowData.add(column);
            }
            data.add(rowData);
        }
        reader.close();
        return data;
    }



    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SQL generator");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SQL generator");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label raport = new Label("Raport:");
        grid.add(raport, 0, 1);

        ObservableList<String> raporty =
                FXCollections.observableArrayList(
                        "Raport 1",
                        "Raport 2",
                        "Raport 3"
                );
        final ComboBox comboBox = new ComboBox(raporty);
        grid.add(comboBox, 0, 2);


        Label data = new Label("Data:");
        grid.add(data, 0, 4);
        DatePicker datePicker = new DatePicker();
        grid.add(datePicker, 0, 5);


        Label ID = new Label("ID:");
        grid.add(ID, 8, 4);
        TextField idInput = new TextField("");
        grid.add(idInput, 8, 5);

        Label kraj = new Label("Kraj:");
        grid.add(kraj, 16, 4);
        TextField krajInput = new TextField("");
        grid.add(krajInput, 16, 5);

        Label miasto = new Label("Miasto:");
        grid.add(miasto, 24, 4);
        TextField miastoInput = new TextField("");
        grid.add(miastoInput, 24, 5);

        Label appWeb = new Label("App/Web:");
        grid.add(appWeb, 32, 4);
        ObservableList<String> appWebLista =
                FXCollections.observableArrayList(
                        "App",
                        "Web"
                );
        ComboBox appWebChoice = new ComboBox(appWebLista);
        grid.add(appWebChoice, 32, 5);

        Button generateButton = new Button("Generate");
        grid.add(generateButton, 40, 5);

        //EVENTHANDLER DO PÓŁ-DYNAMICZNEGO DODAWANIA KOLUMN DO TABELI
        //DYNAMICZNE DODWANIE TUTORIAL LINK WYŻEJ
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                idInput.getText();
                appWebChoice.getItems();
                krajInput.getText();
                datePicker.getValue();
                miastoInput.getText();
                sqlTableView.getColumns().clear();
                /*
                TableColumn first = new TableColumn("Test");
                TableColumn second = new TableColumn("Test2");
                sqlTableView.getColumns().addAll(first, second);
                //sqlTableView.add();*/

                //Podanie sciezek do raportow z komputera
                String raportName = (String) comboBox.getValue();
                String fileName = "";
                if(raportName.equals("Raport 1")){
                    fileName = "C:\\Users\\agus9\\Desktop\\Raporty inżynieria oprogramowania\\report_library1.txt";
                } else if(raportName.equals("Raport 2")) {
                    fileName = "C:\\Users\\agus9\\Desktop\\Raporty inżynieria oprogramowania\\report_library2.txt";
                }    else if(raportName.equals("Raport 3")){
                        fileName = "C:\\Users\\agus9\\Desktop\\Raporty inżynieria oprogramowania\\report_variables3.txt";
                }

                try{
                    ObservableList<ObservableList<String>> data = readDataFromFile(fileName);
                    sqlData.clear();
                    sqlData.addAll(data);
                } catch(IOException e){
                    e.printStackTrace();
                }

                for(int i=0;i<sqlData.get(0).size();i++){
                    TableColumn col = new TableColumn("Column " +(i+1));
                    final int colIndex = i;
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>(){
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(colIndex).toString());
                        }
                    });
                    sqlTableView.getColumns().add(col);
                }
                sqlTableView.setItems(sqlData);

            }
        };
        //DODANIE EVENTHANDLERA DO PRZYCIKSU
        generateButton.setOnAction(buttonHandler);

        sqlTableView = new TableView();
        grid.add(sqlTableView, 0, 15, 60, 40);
        idInput.getText();
        //sqlTableView.add(idInput);
        sqlTableView.setEditable(true);

        Text counter = new Text("Count: " + sqlCounter);
        grid.add(counter, 50, 55);

        Scene scene = new Scene(grid, 1366, 768);
        primaryStage.setScene(scene);


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}