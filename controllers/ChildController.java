package pwm.controllers;

public abstract class ChildController extends BaseController {
  public MainController parentController;
    public void setParentController(MainController controller) {
    this.parentController = controller;
  }
}
