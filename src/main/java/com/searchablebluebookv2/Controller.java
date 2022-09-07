package com.searchablebluebookv2;

import com.searchablebluebookv2.reader.Populator;
import com.searchablebluebookv2.reader.SteelReader;
import com.searchablebluebookv2.sections.Section;
import com.searchablebluebookv2.sections.UniversalBeam;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/***
 * This class is the controller class for the main-view.fxml
 *
 * @author Nathan Aspinall
 */
public class Controller {

    //TODO: Create Log Control to hold all print statements within the UI

    /*
     * FXML Control Elements
     */
    @FXML
    public HBox resultsBox;



    /*
     * UI ComboBox Elements
     **/
    @FXML
    public ComboBox<String> shapeSelect;
    @FXML
    public ComboBox<String> preDesSelect;
    @FXML
    public ComboBox<String> subDesSelect;
    @FXML
    public TreeView<String> treeView;
    @FXML
    public ListView<String> logView;




    /*
     * User Entered Data Fields
     */
    String selectedPreDes;
    String selectedSubDes;


    /*
     * Current data being read by the application
     */
    //Holds the currently searched objects
    public List<Section> sections = new ArrayList<>();


    /*
     * Packaged Classes
     */
    SteelReader reader;

    Populator populator;

    Log log = new Log(this);



    /***
     * Method to allow the user to close the application.
     */
    public void quitApplication() {
        Platform.exit();
    }







    /***
     * Set the ObservableList for the logView so it will be updated as new
     * statements are added.
     */
    public void createLog() {
        if(logView != null) {
            logView.setItems(log.getLog());
        }
    }


    public void updateLog(String line) {
        logView.getItems().add(line);
    }







    /***
     * Creates and adds a context menu to the listView when requested
     */
    public void setContextMenu() {
        MenuItem menuItem = new MenuItem("Refresh");

        menuItem.setOnAction((event) -> {
            refreshListView();
        });

        ContextMenu viewContextMenu = new ContextMenu();

        viewContextMenu.getItems().add(menuItem);

        if(treeView != null) {
            treeView.setContextMenu(viewContextMenu);
        }
    }




    /***
     * Used in a ContextMenu to refresh the TreeView
     */
    public void refreshListView() {
        treeView.refresh();
    }







    /***
     * Event Listener for the 'shapeSelect' combo-box, when the shape is selected it
     * will call the 'populator' class to read the data into relevant objects. <br>
     *
     * @param actionEvent
     */
    @FXML
    protected void onShapeSelect(ActionEvent actionEvent) {
        String currentShape = shapeSelect.getValue();
        log.addLog("SHAPE SELECTED : " +currentShape);

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
            //TODO: Create new Reader classes to read the objects
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
     * populate 'subDesSelect' combo-box with matching pre-designations. <br>
     *
     * @param selectedPreDes the currently selected pre-designation measurement
     */
    public void populateSubDes(String selectedPreDes) {

        /* List holds valid sub-designations to pick from based on
         selected pre-designation. */
        ObservableList<String> subDesData = FXCollections.observableArrayList();

        //TODO : move this into the populator class
        //find objects with matching sub-designation and add them to combo-box selections
        for(Section s : sections) {
            if(s.getPreDesignation().equals(selectedPreDes)) {
                subDesData.add(s.getSubDesignation());
                subDesSelect.setItems(subDesData);
            }
        }
    }







    /***
     * Event Listener for 'preDesSelect' combo-box, captures the selected
     * pre-designation. <br>
     *
     * @param actionEvent
     */
    @FXML
    public void onPreDesSelect(ActionEvent actionEvent) {

        //if preDes is changed after a full selection has been made
        if((selectedSubDes != null) && (!selectedSubDes.isBlank())) {
            selectedSubDes = "";
        }
        else {
            clearResults();
        }


        selectedPreDes = preDesSelect.getValue();
        populateSubDes(selectedPreDes);
        subDesSelect.setDisable(false);
    }







    /***
     * Event Listener for 'subDesSelect' combo-box, captures the selected
     * sub-designation. <br>
     *
     * @param actionEvent
     */
    @FXML
    public void onSubDesSelect(ActionEvent actionEvent) {
        //TODO: if preDes is changed error occurs, prevent this.
        selectedSubDes = subDesSelect.getValue();
        log.addLog("Designation : " +selectedPreDes +selectedSubDes+ " selected.");

        if((selectedSubDes != null) && (!selectedSubDes.isBlank())) {
            populateResults(selectedSubDes);
        }
        else {
            clearResults();
        }
    }







    /***
     * Test method to display one of the fields of the nested 'Dimensions' object. <br>
     *
     * @param subDes
     */
    public void populateResults(String subDes) {
        //TODO: Update this method to work with anonymous objects, not just UniversalBeam
        UniversalBeam ub = new UniversalBeam("test", "test");

        for(Section s : sections) {

            if(s.getPreDesignation().equals(selectedPreDes)) {
                if (s.getSubDesignation().equals(subDes)) {
                    //System.out.println("success");
                    ub = (UniversalBeam) s;
                }
            }
        }


        //get List of fields from the chosen object
        List<Field> fields = populator.getFields(ub);

        //get List of fields (objects) from the chosen object
        List<Object> objects = new ArrayList<>();

        for(Field f : fields) {
            //get the field (f) from the specified object (anonObject)
            // e.g f.get(anonObject);
            try {
                //the fields (f) in this case are nested objects containing more fields
                objects.add(f.get(ub));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        //The Root item for the tree
        TreeItem<String> rootItem = new TreeItem<>(ub.getPreDesignation() + ub.getSubDesignation());

        //Recursively add each object and its fields
        for(Object o : objects) {

            //get object class name
            TreeItem<String> heading = new TreeItem<>(o.getClass().getSimpleName());

            //get fields
            Field[] nestedFields = o.getClass().getFields();

            //add fields to tree view element
            for(Field f : nestedFields) {
                try {
                    heading.getChildren().add(new TreeItem<>(f.getName() +" : "+ f.get(o)));
                    heading.setGraphic(new Separator(Orientation.HORIZONTAL));
                } catch (IllegalAccessException e) {
                    log.addStackTrace(e);
                }
            }

            //finalise the TreeView to be displayed
            rootItem.getChildren().add(heading);
            treeView.setRoot(rootItem);
        }

    }







    public void clearResults() {
        treeView.setRoot(null);
    }





    /***
     * Update the log with a new Statement
     */
    public void populateLog() {
        logView.getItems().add(log.getLastLog());
    }

}