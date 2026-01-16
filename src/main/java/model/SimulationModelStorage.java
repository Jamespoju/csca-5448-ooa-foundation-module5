package model;

import java.util.HashMap;
import java.util.Map;

public class SimulationModelStorage {
    private final Map<String, Map<String, Double>> failureChances = new HashMap<>();
    public SimulationModelStorage() {
        loadFailureChances();
    }
    public void loadFailureChances() {
        failureChances.put("Test Board", createChances(5.0, 10.0, 5.0, 10.0));
        failureChances.put("Gateway Board", createChances(0.2, 0.2, 0.4, 0.4));
        failureChances.put("Sensor Board", createChances(0.4, 0.4, 0.8, 0.8));
    }

    private Map<String, Double> createChances(double pc, double oi, double hs, double test) {
        return Map.of(
                "Place Components", pc,
                "Optical Inspection", oi,
                "Hand Soldering/Assembly", hs,
                "Test(ICT or Flying Probe)", test
        );
    }
    public Map<String, Double> getDefectChances(String boardType) {
        return failureChances.getOrDefault(boardType, new HashMap<>());
    }
    public Map<String, Map<String, Double>> getAllBoardTypes() {
        return failureChances;
    }
}
