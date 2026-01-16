package controller;

import model.SimulationRunStorage;
import view.QueryUIView;
import view.ReportUIView;
import view.RunUIView;

import java.util.Scanner;

public class ViewController {
    private final RunUIView runView;
    private final QueryUIView queryView;
    private final ReportUIView reportView;

    public ViewController(SimulationEngine engine, SimulationRunStorage runStorage) {
        this.runView = new RunUIView(engine);
        this.queryView = new QueryUIView(runStorage);
        this.reportView = new ReportUIView(runStorage);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== View Controller ===");
            System.out.println("1. Run Simulation");
            System.out.println("2. Query Past Runs");
            System.out.println("3. View Report");
            System.out.println("0. Exit");
            System.out.print("Select option: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> runView.display();
                case "2" -> queryView.display();
                case "3" -> reportView.display();
                case "0" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid value.");
            }
        }
    }
}
