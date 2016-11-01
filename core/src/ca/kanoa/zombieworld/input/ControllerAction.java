package ca.kanoa.zombieworld.input;

public class ControllerAction {

    private static long currentActionId = 0;

    private ControllerActionType actionType;
    private long actionId;

    public ControllerAction(ControllerActionType type) {
        this.actionType = type;
        this.actionId = currentActionId++;
    }

    public long getActionId() {
        return this.actionId;
    }

}
