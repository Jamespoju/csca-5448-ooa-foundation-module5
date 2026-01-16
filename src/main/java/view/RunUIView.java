package view;

import controller.SimulationEngine;

import java.util.Scanner;

public class RunUIView {
    private final SimulationEngine engine;
    public RunUIView(SimulationEngine engine) {
        this.engine = engine;
    }
    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Run Simulation ====");
        System.out.println("Enter board type: Test Board / Sensor Board / Gateway Board");
        String boardType = scanner.nextLine();

        System.out.println("Enter number of PCBs to simulate: ");
        int count = Integer.parseInt(scanner.nextLine());

        engine.runSimulation(boardType, count);
        System.out.println("Simulation run complete!");
    }
}
