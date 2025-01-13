package com.focus.utils;

import com.focus.DAO.UsageDAO;
import com.focus.Model.Usage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppUsageTracker {

    private final UsageDAO usageDAO;

    public AppUsageTracker() throws SQLException {
        this.usageDAO = new UsageDAO();
    }

    // Récupérer les données d'utilisation des applications
    public Map<String, Integer> getAppUsage() throws SQLException {
        List<Usage> usages = usageDAO.getAllUsages();
        Map<String, Integer> usageMap = new HashMap<>();

        for (Usage usage : usages) {
            usageMap.put(usage.getAppName(), usage.getUsageTime());
        }

        return usageMap;
    }

    // Récupérer le temps total d'utilisation
    public int getTotalTime() throws SQLException {
        return usageDAO.getTotalTime();
    }

    // Récupérer le temps productif
    public int getProductiveTime() throws SQLException {
        return usageDAO.getProductiveTime();
    }

    // Récupérer le temps non productif
    public int getUnproductiveTime() throws SQLException {
        int totalTime = getTotalTime();
        int productiveTime = getProductiveTime();
        return totalTime - productiveTime;
    }

    // Récupérer la liste des applications productives
    public List<String> getProductiveApps() throws SQLException {
        return usageDAO.getProductiveApps();
    }

    // Récupérer la liste des applications non productives
    public List<String> getUnproductiveApps() throws SQLException {
        return usageDAO.getUnproductiveApps();
    }
}
