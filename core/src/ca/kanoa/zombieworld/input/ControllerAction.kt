package ca.kanoa.zombieworld.input

private var currentActionId: Long = 0

class ControllerAction(actionType: ControllerActionType) {
    val actionId: Long = currentActionId++
        get

    val actionType: ControllerActionType = actionType
        get
}
