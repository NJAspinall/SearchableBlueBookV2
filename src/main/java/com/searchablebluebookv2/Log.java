package com.searchablebluebookv2;

import java.util.ArrayList;
import java.util.List;

public class Log {


    public List<String> log = new ArrayList<>();


    public Log() {

    }



    public void addLog(String line) {
        log.add(line);
    }


    /***
     * Return the last statement from the log
     * @return String
     */
    public String getLastLog() {
        return log.get(log.size()-1);
    }

}
