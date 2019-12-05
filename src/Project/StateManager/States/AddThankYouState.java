package Project.StateManager.States;

import Data.DataController;
import JavaFX.Controller.MainSceneController;
import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.Notes.ThankYou;
import Project.StateManager.TripContext;
import Project.StateManager.iTripWriter;

public class AddThankYouState implements iTripWriter
{
    private MainSceneController mainScene;
    @Override
    public void state(TripContext context)
    {
        context.setStateIndex(6);
        mainScene = context.getMainScene();
        load();
    }

    @Override
    public void load()
    {
        ThankYou temp = new ThankYou();
        temp = ReaderFactory.readFile(FileType.JSON).readThankYou(DataController.getSelectedTrip().getFilePath());
        DataController.getSelectedTrip().setNote(temp);
        mainScene.getThankyouTextArea().setText(DataController.getSelectedTrip().getNote().getThankYouString());
    }
}
