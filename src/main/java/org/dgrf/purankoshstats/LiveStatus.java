/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.purankoshstats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author bhaduri
 */
@Named(value = "liveStatus")
@ViewScoped
public class LiveStatus  implements Serializable{
    private LineChartModel entryCount;
    /**
     * Creates a new instance of LiveStatus
     */
    public LiveStatus() {
    }
    @PostConstruct
    public void init() {
        createLineModels();
    }

    public LineChartModel getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(LineChartModel entryCount) {
        this.entryCount = entryCount;
    }

    
    
    private LineChartModel initCategoryModel() {
        Map<String,Integer> monthWisePostCount = getData();
        ArrayList<String> sortedKeys = new ArrayList<String>(monthWisePostCount.keySet()); 
        Collections.sort(sortedKeys);
        LineChartModel model = new LineChartModel();
 
        ChartSeries monthWisePostCountChartSeries = new ChartSeries();
        monthWisePostCountChartSeries.setLabel("Month Wise Post Count");
        for (String yearMonth: sortedKeys) {
            monthWisePostCountChartSeries.set(yearMonth, monthWisePostCount.get(yearMonth));
        }
//        boys.set("2004", 120);
//        boys.set("2005", 100);
//        boys.set("2006", 44);
//        boys.set("2007", 150);
//        boys.set("2008", 25);
// 
//        ChartSeries girls = new ChartSeries();
//        girls.setLabel("Girls");
//        girls.set("2004", 52);
//        girls.set("2005", 60);
//        girls.set("2006", 110);
//        girls.set("2007", 90);
//        girls.set("2008", 120);
 
        model.addSeries(monthWisePostCountChartSeries);
        //model.addSeries(girls);
 
        return model;
    }
    private void createLineModels() {
        
 
        entryCount = initCategoryModel();
        entryCount.setTitle("Category Chart");
        entryCount.setLegendPosition("e");
        entryCount.setShowPointLabels(true);
        entryCount.setDatatipFormat("%'.0f");
        entryCount.getAxes().put(AxisType.X, new CategoryAxis("YearsMonth"));
        Axis yAxis = entryCount.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
        yAxis.setMin(0);
        yAxis.setMax(200);
        Axis xAxis = entryCount.getAxis(AxisType.X);
        xAxis.setTickAngle(90);
        
        
    }
    private Map<String,Integer> getData() {
        String statsFileName = "/home/dgrfi/MEGA/purankosh/stats/Content20191107.csv";
        StatsData sd = new ReadData(statsFileName).readStatsData();
        Map<String,Integer> monthWisePostCount = sd.calculateMonthWisePostCount();
        return monthWisePostCount;
    }
}
