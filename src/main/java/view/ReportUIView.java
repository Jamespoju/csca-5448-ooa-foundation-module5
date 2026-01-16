package view;

import controller.SimulationEngine;
import model.SimulationRunStorage;

import java.util.Scanner;

public class ReportUIView {
    private final SimulationRunStorage runStorage;

    public ReportUIView(SimulationRunStorage runStorage) {
        this.runStorage = runStorage;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Report Viewer ===");
        System.out.print("Enter run ID to display: ");
        String runId = scanner.nextLine();

        System.out.println(runStorage.getRunDetails(runId));
    }
}
