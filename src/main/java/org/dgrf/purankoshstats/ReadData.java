/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.purankoshstats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dgrfi
 */
public class ReadData {
    String statsDataFileName;

    public ReadData(String statsDataFileName) {
        this.statsDataFileName = statsDataFileName;
    }
    
    public StatsData readStatsData () {
        String cvsSplitBy = ",";
        List<EntryDetails> inputDataSeries = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(statsDataFileName));
            String line;
            while ((line = bf.readLine()) != null) {
                String[] lineItems = line.split(cvsSplitBy);
                EntryDetails entryDetails = new EntryDetails(Integer.parseInt(lineItems[0]),lineItems[1], Integer.parseInt(lineItems[2]), new Date(lineItems[3]), lineItems[4]);
                inputDataSeries.add(entryDetails);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new StatsData(inputDataSeries);
    }
    
}
