package controller;

import model.SimulationModelStorage;
import model.SimulationRunStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SimulationEngine {
    private final SimulationModelStorage modelStorage;
    private final SimulationRunStorage runStorage;
    private final Random random = new Random();
    private static final double STATION_FAILURE_RATE = 0.2;

    public SimulationEngine(SimulationModelStorage modelStorage, SimulationRunStorage runStorage) {
        this.modelStorage = modelStorage;
        this.runStorage = runStorage;
    }

    public void runSimulation(String boardType, int totalRun) {
        Map<String, Double> defectChances = modelStorage.getDefectChances(boardType);
        Map<String, Integer> defectFailures = initStationMap(defectChances);
        Map<String, Integer> stationFailures = initStationMap(defectChances);

        int totalPassed = 0;

        for (int i = 0; i < totalRun; i++) {
            boolean rejected = false;

            for (String station : defectChances.keySet()) {
                if (random.nextDouble() * 100 < STATION_FAILURE_RATE) {
                    stationFailures.put(station, stationFailures.get(station) + 1);
                    rejected = true;
                    break;
                }

                double defectRate = defectChances.get(station);
                if (random.nextDouble() * 100 < defectRate) {
                    defectFailures.put(station, defectFailures.get(station) + 1);
                    rejected = true;
                    break;
                }
            }

            if (!rejected) totalPassed++;
        }

        runStorage.saveRunResults(boardType, totalRun, defectFailures, stationFailures);
        System.out.println("Simulation complete. " + totalPassed + " PCBs passed.");
    }

    private Map<String, Integer> initStationMap(Map<String, Double> source) {
        Map<String, Integer> map = new HashMap<>();
        for (String station : source.keySet()) {
            map.put(station, 0);
        }
        return map;
    }
}
