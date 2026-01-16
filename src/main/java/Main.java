import controller.SimulationEngine;
import controller.ViewController;
import model.SimulationModelStorage;
import model.SimulationRunStorage;

public class Main {
    public static void main(String[] args) {
        SimulationModelStorage modelStorage = new SimulationModelStorage();
        SimulationRunStorage runStorage = new SimulationRunStorage();
        SimulationEngine engine = new SimulationEngine(modelStorage, runStorage);

        ViewController controller = new ViewController(engine, runStorage);
        controller.start();
    }
}
