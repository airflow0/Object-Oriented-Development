package Project.StateManager;

import JavaFX.Controller.MainSceneController;
import Project.StateManager.States.AddTravelerState;

public class TripContext
{
    private iTripWriter state;
    private MainSceneController mainScene;
    public TripContext(MainSceneController mainScene)
    {
        this.mainScene = mainScene;
        setState(new AddTravelerState(), mainScene);
    }
    public void setState(final iTripWriter newState, MainSceneController mainScene)
    {
        state = newState;
    }
    public MainSceneController getMainScene()
    {
        return mainScene;
    }
    public void jumpState()
    {
        state.state(this);
    }

}
