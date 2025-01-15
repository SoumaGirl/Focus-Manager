package com.focus.Service;

import com.focus.Model.Usage;
import com.focus.DAO.UsageDAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class AppTrackerService {

    private final UsageDAO usageDAO;
    private final Set<String> trackedProcesses; // To track already seen applications.

    public AppTrackerService() {
        this.usageDAO = new UsageDAO();
        this.trackedProcesses = new HashSet<>(); // Use a Set to avoid duplicates.
    }

    public void trackApplications() {
        try {
            // Use tasklist to get a list of running processes
            String command = "tasklist /FO CSV /V"; // Detailed output
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
            builder.redirectErrorStream(true);
            Process process = builder.start();
    
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
    
            boolean isFirstLine = true; // Skip the header
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
    
                // Parse process name and window title
                String[] fields = line.split(",");
                String processName = fields[0].replace("\"", "").toLowerCase();
                String windowTitle = fields.length > 1 ? fields[1].replace("\"", "") : "";
    
                // Filter for user applications with a window title
                if (isUserApplication(processName) && !windowTitle.isEmpty()) {
                    if (!trackedProcesses.contains(processName)) {
                        trackedProcesses.add(processName);
                    }
    
                    // Check if the app exists in the database
                    Usage existingUsage = usageDAO.getUsageByAppName(processName);
    
                    if (existingUsage != null) {
                        // Increment the usage time
                        existingUsage.setUsageTime(existingUsage.getUsageTime() + 1);
                        usageDAO.updateUsage(existingUsage);
                    } else {
                        // Save as a new entry with initial usage time
                        Usage usage = new Usage(0, processName, 1); // 1 second
                        usageDAO.saveUsage(usage);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private boolean isUserApplication(String processName) {
        // List of known system processes to exclude
        String[] systemProcesses = {
            "svchost.exe", "explorer.exe", "taskmgr.exe", "cmd.exe",
            "csrss.exe", "winlogon.exe", "lsass.exe", "services.exe",
            "smss.exe", "system", "idle"
        };

        for (String systemProcess : systemProcesses) {
            if (processName.equals(systemProcess)) {
                return false;
            }
        }

        // Include only user applications like Chrome, Word, Firefox, etc.
        String[] userApplications = {
            // Browsers and Communication Apps
            "chrome.exe", "firefox.exe", "edge.exe", "opera.exe", "brave.exe",
            "discord.exe", "skype.exe", "teams.exe", "zoom.exe", "telegram.exe", "outlook.exe",
        
            // Development Tools
            "androidstudio.exe", "intellij.exe", "idea.exe", "eclipse.exe", "vscode.exe",
            "visualstudio.exe", "netbeans.exe", "pycharm.exe", "gns3.exe", "mysqlworkbench.exe",
            "postman.exe", "xampp.exe", "git.exe", "githubdesktop.exe", "docker.exe",
        
            // Programming and Runtime
            "java.exe", "python.exe", "node.exe", "ruby.exe", "perl.exe", "go.exe",
            "javac.exe", "javap.exe", "javapackager.exe",
        
            // Productivity Tools
            "word.exe", "excel.exe", "powerpoint.exe", "onenote.exe",
            "notepad.exe", "notepad++.exe", "snippingtool.exe", "paint.exe",
            "adobeacrobat.exe", "foxitreader.exe",
        
            // Media and Entertainment
            "spotify.exe", "vlc.exe", "winamp.exe", "media.exe", "obs.exe",
        
            // Security
            "malwarebytes.exe", "windowsdefender.exe", "antivirus.exe", "firewall.exe",
            "nmap.exe", "wireshark.exe", "openvpn.exe",
        
            // System and Utilities
            "cmd.exe", "powershell.exe", "taskmanager.exe", "hyperv.exe", "vmware.exe", "virtualbox.exe",
            "7zfm.exe", "winrar.exe", "speedtest.exe", "putty.exe", "routerconfig.exe",
        
            // Miscellaneous
            "torrent.exe", "utorrent.exe", "protonvpn.exe", "vpn.exe"
        };
        

        for (String app : userApplications) {
            if (processName.contains(app)) {
                return true;
            }
        }

        return false;
    }
}
