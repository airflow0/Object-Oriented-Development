package JavaFX;

import JavaFX.Scenes.LoginScene;
import Project.File.FileFactory.WriterFactory;
import Project.File.FileType.FileType;
import Project.Reservation.Place;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //public Package(String travelFrom, String travelTo, double price, int hoursOfTravel, TransportType type)
        /*Package pack1 = new Package("Atlanta","New York", 500.00, 12, TransportType.HELI);
        Package pack2 = new Package("New York","Knoxville", 450.00, 18, TransportType.TANK);
        Package pack3 = new Package("Miami","San Antonio", 800.00, 20, TransportType.PRIVATEHELI);
        Package pack4 = new Package("El Paso","Washington", 1000.00, 35, TransportType.VAN);

        List<Package> packages = new ArrayList<>();
        packages.add(pack1);
        packages.add(pack2);
        packages.add(pack3);
        packages.add(pack4);

        WriterFactory.createWriter(FileType.JSON).createPackage(packages);*/

        /*Agent agent1 = new Agent("James Lee");
        Agent agent2 = new Agent("Murdock");
        Agent agent3 = new Agent("Steven Garcia");
        Agent agent4 = new Agent("Aaron Badgett");
        Agent agent5 = new Agent("Amy Hadzic");
        List<Agent> agents = new ArrayList<>();
        agents.add(agent1);
        agents.add(agent2);
        agents.add(agent3);
        agents.add(agent4);
        agents.add(agent5);
        WriterFactory.createWriter(FileType.JSON).createAgentList(agents);*/;
        /*
        BufferedReader reader;
        List<Place> placeList = new ArrayList<>();
        try
        {
            reader = new BufferedReader(new FileReader("resources/placelist.txt"));
            String line = reader.readLine();
            while(line != null)
            {
                placeList.add(new Place(line));
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException ex)
        {

        }
        WriterFactory.createWriter(FileType.JSON).createPlaceList(placeList);

         */
        LoginScene loginScene = new LoginScene(primaryStage);
        loginScene.createLoginScene();

    }



    public static void main(String[] args) {

        launch(args);
    }
}
