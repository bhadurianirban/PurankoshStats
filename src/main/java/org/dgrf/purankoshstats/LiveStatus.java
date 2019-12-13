/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.purankoshstats;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author bhaduri
 */
@Named(value = "liveStatus")
@ViewScoped
public class LiveStatus implements Serializable {

    private StatsData sd;
    private LineChartModel entryCount;
    private LineChartModel wordCount;
    private BarChartModel barModelWordByAuthor;
    private BarChartModel barModelCountByAuthor;

    /**
     * Creates a new instance of LiveStatus
     */
    public LiveStatus() {
    }

    @PostConstruct
    public void init() {
        getData();
        createEntryCountModel();
        createWordCountModel();
        createWordCountByAuthorModel();
        createPostCountByAuthorModel();
    }

    public LineChartModel getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(LineChartModel entryCount) {
        this.entryCount = entryCount;
    }

    public LineChartModel getWordCount() {
        return wordCount;
    }

    public void setWordCount(LineChartModel wordCount) {
        this.wordCount = wordCount;
    }

    public BarChartModel getBarModelWordByAuthor() {
        return barModelWordByAuthor;
    }

    public void setBarModelWordByAuthor(BarChartModel barModelWordByAuthor) {
        this.barModelWordByAuthor = barModelWordByAuthor;
    }

    public BarChartModel getBarModelCountByAuthor() {
        return barModelCountByAuthor;
    }

    public void setBarModelCountByAuthor(BarChartModel barModelCountByAuthor) {
        this.barModelCountByAuthor = barModelCountByAuthor;
    }
    
  

  

    private BarChartModel initPostCountByAuthorModel() {
        BarChartModel model = new BarChartModel();
        Map<String, Integer> wordCountByAuthor = sd.calculateThisMonthPostCountByAuthor();
        ChartSeries authorWordCountSeries = new ChartSeries();
        authorWordCountSeries.setLabel("Count");
        for (Map.Entry<String,Integer> authorWordCount: wordCountByAuthor.entrySet()) {
            authorWordCountSeries.set(authorWordCount.getKey(), authorWordCount.getValue());
        }
        
        model.addSeries(authorWordCountSeries);
        return model;
    }
    private void createPostCountByAuthorModel() {
        String month = sd.getLastMonthName();
        barModelCountByAuthor = initPostCountByAuthorModel();
 
        barModelCountByAuthor.setTitle("This "+month+" Entry Count");
        barModelCountByAuthor.setLegendPosition("ne");
        barModelCountByAuthor.setShowPointLabels(true);
        Axis xAxis = barModelCountByAuthor.getAxis(AxisType.X);
        xAxis.setLabel("Author");
 
        Axis yAxis = barModelCountByAuthor.getAxis(AxisType.Y);
        yAxis.setLabel("Entry Count");
//        yAxis.setMin(0);
//        yAxis.setMax(200);
    }
    
    private BarChartModel initWordCountByAuthorModel() {
        BarChartModel model = new BarChartModel();
        Map<String, Integer> wordCountByAuthor = sd.calculateThisMonthWordCountByAuthor();
        ChartSeries authorWordCountSeries = new ChartSeries();
        authorWordCountSeries.setLabel("Count");
        for (Map.Entry<String,Integer> authorWordCount: wordCountByAuthor.entrySet()) {
            authorWordCountSeries.set(authorWordCount.getKey(), authorWordCount.getValue());
        }
        
        model.addSeries(authorWordCountSeries);
        return model;
    }
    private void createWordCountByAuthorModel() {
        String month = sd.getLastMonthName();
        barModelWordByAuthor = initWordCountByAuthorModel();
        barModelWordByAuthor.setTitle("This "+month+" Word Count");
        barModelWordByAuthor.setLegendPosition("ne");
        barModelWordByAuthor.setShowPointLabels(true);
        Axis xAxis = barModelWordByAuthor.getAxis(AxisType.X);
        xAxis.setLabel("Author");
 
        Axis yAxis = barModelWordByAuthor.getAxis(AxisType.Y);
        yAxis.setLabel("Word Count");
//        yAxis.setMin(0);
//        yAxis.setMax(200);
    }
    private LineChartModel initWordCountModel() {
        Map<String, Integer> monthWiseWordCount = sd.calculateMonthWiseWordCount();;
        
        ArrayList<String> sortedKeys = new ArrayList<>(monthWiseWordCount.keySet());
        Collections.sort(sortedKeys);
        LineChartModel model = new LineChartModel();

        ChartSeries monthWisePostCountChartSeries = new ChartSeries();
        monthWisePostCountChartSeries.setLabel("Month Wise Word Count YoY");

        for (String yearMonth : sortedKeys) {
            int count = monthWiseWordCount.get(yearMonth);
            monthWisePostCountChartSeries.set(yearMonth, count);
        }

        model.addSeries(monthWisePostCountChartSeries);

        return model;
    }

    private void createWordCountModel() {

        wordCount = initWordCountModel();
        wordCount.setTitle("Word Count");
        wordCount.setLegendPosition("e");
        wordCount.setShowPointLabels(true);

        wordCount.getAxes().put(AxisType.X, new CategoryAxis("YearsMonth"));
        Axis yAxis = wordCount.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
//        yAxis.setMin(0);
//        yAxis.setMax(200);
        Axis xAxis = wordCount.getAxis(AxisType.X);
        xAxis.setTickAngle(90);

    }

    private LineChartModel initEntryCountModel() {
        Map<String, Integer> monthWisePostCount = sd.calculateMonthWisePostCount();
        ArrayList<String> sortedKeys = new ArrayList<String>(monthWisePostCount.keySet());
        Collections.sort(sortedKeys);
        LineChartModel model = new LineChartModel();

        ChartSeries monthWisePostCountChartSeries = new ChartSeries();
        monthWisePostCountChartSeries.setLabel("Post Count YoY");

        for (String yearMonth : sortedKeys) {
            int count = monthWisePostCount.get(yearMonth);
            monthWisePostCountChartSeries.set(yearMonth, count);
        }

        model.addSeries(monthWisePostCountChartSeries);

        return model;
    }

    private void createEntryCountModel() {

        entryCount = initEntryCountModel();
        entryCount.setTitle("Entry Count");
        entryCount.setLegendPosition("e");
        entryCount.setShowPointLabels(true);

        entryCount.getAxes().put(AxisType.X, new CategoryAxis("YearsMonth"));
        Axis yAxis = entryCount.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
//        yAxis.setMin(0);
//        yAxis.setMax(200);
        Axis xAxis = entryCount.getAxis(AxisType.X);
        xAxis.setTickAngle(90);

    }

    private void getData() {
        String statsFileName = "/root/purankosh/PuranStats.csv";
        //String statsFileName = "/home/bhaduri/MEGA/purankosh/stats/Content20191107.csv";
        sd = new ReadData(statsFileName).readStatsData();
    }


   
}
