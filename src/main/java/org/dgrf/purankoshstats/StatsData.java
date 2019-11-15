/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.purankoshstats;

import java.util.Calendar;
import java.util.Date;
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

    public Map<String, Integer> calculateMonthWisePostCount() {
        Map<String, Integer> monthWisePostCount = new HashMap<>();
        for (EntryDetails entryDetails : entryDetailsList) {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTime(entryDetails.getPostPublishDate());
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            String yearMonth = String.format("%04d", year) + "-" + String.format("%02d", month + 1);
            Integer count = monthWisePostCount.get(yearMonth);
            if (count == null) {
                monthWisePostCount.put(yearMonth, 1);
            } else {
                count = count + 1;
                monthWisePostCount.put(yearMonth, count);
            }
        }
        //monthWisePostCount.entrySet().stream().forEach(mwc->System.out.println(mwc.getKey()+" "+mwc.getValue()));
        return monthWisePostCount;
    }

    public Map<String, Integer> calculateMonthWiseWordCount() {
        Map<String, Integer> monthWiseWordCount = new HashMap<>();
        for (EntryDetails entryDetails : entryDetailsList) {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTime(entryDetails.getPostPublishDate());
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            String yearMonth = String.format("%04d", year) + "-" + String.format("%02d", month + 1);
            Integer wordCount = monthWiseWordCount.get(yearMonth);
            if (wordCount == null) {
                monthWiseWordCount.put(yearMonth, entryDetails.getPostWordCount());
            } else {
                wordCount = wordCount + entryDetails.getPostWordCount();
                monthWiseWordCount.put(yearMonth, wordCount);
            }
        }
        //monthWisePostCount.entrySet().stream().forEach(mwc->System.out.println(mwc.getKey()+" "+mwc.getValue()));
        return monthWiseWordCount;
    }

    public Map<String, Integer> calculateThisMonthWordCountByAuthor() {
        Map<String, Integer> wordCountByAuthor = new HashMap<>();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
        cal.add(Calendar.MONTH, -1);
        
        int thisMonth = cal.get(Calendar.MONTH);
        int thisYear = cal.get(Calendar.YEAR);
        for (EntryDetails entryDetails : entryDetailsList) {

            cal.setTime(entryDetails.getPostPublishDate());
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            //String yearMonth = String.format("%04d", year) + "-" + String.format("%02d", month + 1);
            
            if ((thisMonth == month) && (thisYear == year)) {
                String postAuthor = entryDetails.getPostAuthor();
                Integer wordCount = wordCountByAuthor.get(postAuthor);
                if (wordCount == null) {
                    wordCountByAuthor.put(postAuthor, entryDetails.getPostWordCount());
                } else {
                    wordCount = wordCount + entryDetails.getPostWordCount();
                    wordCountByAuthor.put(postAuthor, wordCount);
                }
            }
        }
        //monthWisePostCount.entrySet().stream().forEach(mwc->System.out.println(mwc.getKey()+" "+mwc.getValue()));
        return wordCountByAuthor;
    }
    
    public Map<String, Integer> calculateThisMonthPostCountByAuthor() {
        Map<String, Integer> postCountByAuthor = new HashMap<>();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
        cal.add(Calendar.MONTH, -1);
        
        int thisMonth = cal.get(Calendar.MONTH);
        int thisYear = cal.get(Calendar.YEAR);
        for (EntryDetails entryDetails : entryDetailsList) {

            cal.setTime(entryDetails.getPostPublishDate());
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            //String yearMonth = String.format("%04d", year) + "-" + String.format("%02d", month + 1);
            
            if ((thisMonth == month) && (thisYear == year)) {
                String postAuthor = entryDetails.getPostAuthor();
                Integer postCount = postCountByAuthor.get(postAuthor);
                if (postCount == null) {
                    postCountByAuthor.put(postAuthor, 1);
                } else {
                    postCount = postCount + 1;
                    postCountByAuthor.put(postAuthor, postCount);
                }
            }
        }
        //monthWisePostCount.entrySet().stream().forEach(mwc->System.out.println(mwc.getKey()+" "+mwc.getValue()));
        return postCountByAuthor;
    }
}
