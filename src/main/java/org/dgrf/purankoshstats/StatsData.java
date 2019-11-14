/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.purankoshstats;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author dgrfi
 */
public class StatsData {
   List<EntryDetails> entryDetailsList;

    public StatsData(List<EntryDetails> entryDetailsList) {
        this.entryDetailsList = entryDetailsList;
    }
    
    public Map<String, Integer>  calculateMonthWisePostCount() {
        Map<String, Integer> monthWisePostCount = new HashMap<>();
        for (EntryDetails entryDetails : entryDetailsList) {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTime(entryDetails.getPostPublishDate());
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            String yearMonth = String.format("%04d", year)+"-"+String.format("%02d", month+1);
            Integer count= monthWisePostCount.get(yearMonth);
            if (count == null) {
                monthWisePostCount.put(yearMonth, 1);
            } else {
                count = count+1;
                monthWisePostCount.put(yearMonth, count);
            }
        }
        //monthWisePostCount.entrySet().stream().forEach(mwc->System.out.println(mwc.getKey()+" "+mwc.getValue()));
        return monthWisePostCount;
    }
   
}
