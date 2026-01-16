package view;

import model.SimulationRunStorage;

public class QueryUIView {
    private final SimulationRunStorage runStorage;

    public QueryUIView(SimulationRunStorage runStorage) {
        this.runStorage = runStorage;
    }

    public void display() {
        System.out.println("==== Query Simulation Runs ====");
        for (String runId : runStorage.getRunRecords()) {
            System.out.println(runId);
        }
    }
}
