package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimulationRunStorage {
    private final Map<String, RunRecord> records = new LinkedHashMap<>();
    private int counter = 1;
    public void saveRunResults(String pcbType, int totalRun, Map<String, Integer> defectFailures, Map<String, Integer> stationFailures) {
        String runId = "Run-" + counter++;
        records.put(runId, new RunRecord(pcbType, totalRun, defectFailures, stationFailures));
    }
    public List<String> getRunRecords() {
        return new ArrayList<>(records.keySet());
    }
    public String getRunDetails(String runId){
        RunRecord record = records.get(runId);
        return (record != null) ? record.generateReport() : "The run is not found with runId: " + runId;
    }

    private static class RunRecord {
        String pcbType;
        int totalRun;
        Map<String, Integer> defectFailures;
        Map<String, Integer> stationFailures;
        RunRecord(String pcbType, int totalRun, Map<String, Integer> defectFailures, Map<String, Integer> stationFailures) {
            this.pcbType = pcbType;
            this.totalRun = totalRun;
            this.defectFailures = new LinkedHashMap<>(defectFailures);
            this.stationFailures = new LinkedHashMap<>(stationFailures);
        }
        String generateReport() {
            StringBuilder sb = new StringBuilder();
            sb.append("PCB type: ").append(pcbType).append("\n");
            sb.append("PCBs run: ").append(totalRun).append("\n");

            sb.append("Station Failures:\n");
            stationFailures.forEach((station, count) -> sb.append(station).append(": ").append(count).append("\n"));

            sb.append("PCB Defect Failures:\n");
            defectFailures.forEach((station, count) -> sb.append(station).append(" ").append(count).append("\n"));

            int totalFailures = defectFailures.values().stream().mapToInt(Integer::intValue).sum()
                    + stationFailures.values().stream().mapToInt(Integer::intValue).sum();

            sb.append("Final Results\n");
            sb.append("Total failed PCBs: ").append(totalFailures).append("\n");
            sb.append("Total PCBs produced: ").append(totalRun - totalFailures).append("\n");

            return sb.toString();
        }
    }
}
