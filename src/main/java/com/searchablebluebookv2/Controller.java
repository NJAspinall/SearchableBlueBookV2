package com.searchablebluebookv2;

import com.searchablebluebookv2.models.Dimensions;
import com.searchablebluebookv2.models.Properties;
import com.searchablebluebookv2.reader.Populator;
import com.searchablebluebookv2.reader.ReaderFactory;
import com.searchablebluebookv2.reader.SteelReader;
import com.searchablebluebookv2.reader.UBReader;
import com.searchablebluebookv2.sections.OpenRolledSection;
import com.searchablebluebookv2.sections.Section;
import com.searchablebluebookv2.sections.UniversalBeam;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import java.util.ArrayList;
import java.util.List;


/***
 * This class is the controller class for the main-view.fxml
 *
 * @author Nathan Aspinall
 */
public class Controller {

    @FXML
    public Label massPerMetre;
    @FXML
    public TableColumn<Section, String> Property;
    @FXML
    public TableColumn<Section, String> Value;



    String selectedPreDes;


    //UI ComboBox Elements
    @FXML
    public ComboBox<String> shapeSelect;
    @FXML
    public ComboBox<String> preDesSelect;
    @FXML
    public ComboBox<String> subDesSelect;



    //Holds the currently searched objects
    public List<Section> sections = new ArrayList<>();



    SteelReader reader;

    Populator populator;



    public void quitApplication() {
        Platform.exit();
    }






    /***
     * Event Listener for the 'shapeSelect' combo-box, when the shape is selected it
     * will call the populator class to read the data into relevant objects.
     * @param actionEvent
     */
    @FXML
    protected void onShapeSelect(ActionEvent actionEvent) {
        String currentShape = shapeSelect.getValue();
        System.out.println("SHAPE SELECTED : " +currentShape);

        preDesSelect.setDisable(false);

        populator = new Populator();

        switch (currentShape) {
            case "Universal Beams (UB)":
                populator.populateUniversalBeams();

                ObservableList<String> preDesData = FXCollections.observableArrayList(populator.getPreDesList());
                preDesSelect.setItems(preDesData);

                List<UniversalBeam> ubs = populator.getUniversalBeams();
                sections.addAll(ubs);
                break;


            //TODO: create relevant methods and reference inside 'if' statements
            case "Universal Columns (UC)":

                break;
            case "Universal Bearing Piles (UBP)":

                break;
            case "Parallel Flange Channels (PFC)":

                break;
            case "Equal Leg Angles (L)":

                break;
            case "Unequal Leg Angles (L)":

                break;
            case "Back to back Equal Leg Angles (L)":

                break;
            case "Back to back Unequal Leg Angles (L)":

                break;
            case "Tees (T) split from UB":

                break;
            case "Tees (T) split from UC":

                break;
        }
    }





    /***
     * populate 'subDesSelect' combo-box with matching pre-designations
     * @param selectedPreDes - the currently selected pre-designation measurement
     */
    public void populateSubDes(String selectedPreDes) {

        /* List holds valid sub-designations to pick from based on
         selected pre-designation. */
        ObservableList<String> subDesData = FXCollections.observableArrayList();

        //TODO : move this into the populator class
        //find objects with matching sub-designation and add them to combo-box selections
        for(Section s : sections) {
            s.methodOverrideTest();

            if(s.getPreDesignation().equals(selectedPreDes)) {
                subDesData.add(s.getSubDesignation());
                subDesSelect.setItems(subDesData);
            }
        }
    }




    /***
     * Event Listener for 'preDesSelect' combo-box, captures the selected
     * pre-designation
     * @param actionEvent
     */
    @FXML
    public void onPreDesSelect(ActionEvent actionEvent) {
       selectedPreDes = preDesSelect.getValue();
       populateSubDes(selectedPreDes);
       subDesSelect.setDisable(false);
    }


    /***
     * Event Listener for 'subDesSelect' combo-box, captures the selected
     * sub-designation
     * @param actionEvent
     */
    @FXML
    public void onSubDesSelect(ActionEvent actionEvent) {
        populateResults(subDesSelect.getValue());
    }


    /***
     * Test method to display one of the fields of the nested 'Dimensions' object
     * @param subDes
     */
    public void populateResults(String subDes) {
        UniversalBeam ub = new UniversalBeam("test", "test");

        for(Section s : sections) {


            if(s.getPreDesignation().equals(selectedPreDes)) {
                if (s.getSubDesignation().equals(subDes)) {
                    System.out.println("success");
                    ub = (UniversalBeam) s;
                } else {
                    System.out.println("Error 2");
                }
            } else {
                System.out.println("Error 1");
            }
        }

        massPerMetre.setText(ub.dimensions.areaPerMetre);
    }















}