package com.searchablebluebookv2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * This Class will hold any error messages, debug statements and such so that it can be
 * viewed within the UI rather than the Console.
 */
public class Log {
    public ObservableList<String> log;
    public Controller controller;


    /**
     * Constructor
     *
     * @param controller the instance of the Controller class
     */
    public Log(Controller controller) {
        log = FXCollections.observableArrayList();
        this.controller = controller;
    }







    /***
     * Add a new statement to the log <br>
     *
     * @param line - String, the next statement to add to the log.
     */
    public void addLog(String line) {
        log.add(line);
        controller.updateLog(line);
    }


    /***
     * Add a StackTrace to the log
     */
    public void addStackTrace(Throwable throwable) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            throwable.printStackTrace(pw);
            addLog(sw.toString());
        }
        catch (Exception ioe)
        {
            ioe.printStackTrace();
        }
    }






    /***
     * Return the last statement from the log <br>
     *
     * @return String
     */
    public String getLastLog() {
        return log.get(log.size()-1);
    }


    /***
     * Get the full list of all log statements
     * @return
     */
    public ObservableList<String> getLog() {
        return log;
    }


    public void printLast() {
        System.out.println(log.get(log.size()-1));
    }
}
