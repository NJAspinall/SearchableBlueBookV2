package com.searchablebluebookv2;

import com.searchablebluebookv2.reader.ReaderFactory;
import com.searchablebluebookv2.reader.SteelReader;
import com.searchablebluebookv2.sections.OpenRolledSection;
import com.searchablebluebookv2.sections.Section;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");


        ReaderFactory rFactory = new ReaderFactory();

        SteelReader newReader = rFactory.createReader("(UB) Universal Beams");

        List<OpenRolledSection> sections = newReader.readDimensionsAndProperties();

        for(OpenRolledSection s : sections) {
            System.out.println();
        }






    }
}