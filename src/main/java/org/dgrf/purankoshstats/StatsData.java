/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.purankoshstats;

import java.util.List;

/**
 *
 * @author dgrfi
 */
public class StatsData {
   List<EntryDetails> entryDetailsList;

    public StatsData(List<EntryDetails> entryDetailsList) {
        this.entryDetailsList = entryDetailsList;
    }
    
    public void printOnConsole() {
        entryDetailsList.stream().forEach(ed -> {
            System.out.println(ed.getPostAuthor()+ed.getPostTitle()+ed.getPostPublishDate());
        });
    }
   
}
