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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public StatsData readStatsData() {
        String cvsSplitBy = ",";
        List<EntryDetails> inputDataSeries = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(statsDataFileName));
            String line;
            line = bf.readLine();
            while ((line = bf.readLine()) != null) {
                String[] lineItems = line.split(cvsSplitBy);
                Integer postWordCount = Integer.parseInt(lineItems[0]);
                String postTitle = lineItems[1];
                Integer postid = Integer.parseInt(lineItems[2]);
                Date postPublishDate = new Date();
                SimpleDateFormat sd =  new SimpleDateFormat("yyyy-MM-dd");
                try {
                    postPublishDate =  sd.parse(lineItems[3]);
                } catch (ParseException ex) {
                    Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String postAuthor = lineItems[4];
                EntryDetails entryDetails = new EntryDetails(postWordCount, postTitle, postid, postPublishDate, postAuthor);

                inputDataSeries.add(entryDetails);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadData.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadData.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return new StatsData(inputDataSeries);
    }

}
