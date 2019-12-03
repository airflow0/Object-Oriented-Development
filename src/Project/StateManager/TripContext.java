package Project.StateManager;

import JavaFX.Controller.MainSceneController;
import Project.StateManager.States.AddPackageState;
import Project.StateManager.States.AddPaymentPersonState;
import Project.StateManager.States.AddPaymentSelectState;
import Project.StateManager.States.AddTravelerState;

public class TripContext
{
    private iTripWriter state;
    private MainSceneController mainScene;
    private int stateIndex;
    public TripContext(MainSceneController mainScene)
    {
        this.mainScene = mainScene;
        setState(new AddTravelerState(), mainScene);
        stateIndex = -1;
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
    public int getStateIndex()
    {
       return stateIndex;
    }
    public void setStateIndex(int state)
    {
        stateIndex = state;
    }
}
