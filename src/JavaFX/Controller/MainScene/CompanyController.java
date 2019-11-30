package JavaFX.Controller.MainScene;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.Person.Company;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.xml.crypto.Data;

public class CompanyController
{
    private MainSceneController mainScene;
    public CompanyController(MainSceneController mainScene)
    {
        this.mainScene = mainScene;
    }
    public void initalize()
    {
        mainScene.getCompanyList().getItems().addAll(DataController.getCompanies());
        mainScene.getSelectedAgentLabel().setText("Welcome back, " + DataController.getSelectedAgent().getName());
        mainScene.getCompanyList().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Company>()
        {
            @Override
            public void changed(ObservableValue<? extends Company> observable, Company oldValue, Company newValue)
            {
                mainScene.setTripVisible(true);

                DataController.setSelectedCompanyIndex(mainScene.getCompanyList().getItems().indexOf(mainScene.getCompanyList().getSelectionModel().getSelectedItem()));

                System.out.println("Selected Company Index: " + DataController.getSelectedCompanyIndex());
                TripController tripController = new TripController(mainScene);
                tripController.initialize();
                mainScene.setTravelerVisible(false);
            }
        });

    }
}
