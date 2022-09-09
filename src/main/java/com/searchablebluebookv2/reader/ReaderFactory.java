package com.searchablebluebookv2.reader;

import com.searchablebluebookv2.Log;

public class ReaderFactory implements ReaderFactoryIF {

    /**
     * Return the reader to collect the correct information
     */
    @Override
    public SteelReader createReader(String discrim, Log log) {
        if(discrim.equals("Universal Beams (UB)")) {
            return new UBReader(log);
        }

        else {
            return null;
        }
    }
}
