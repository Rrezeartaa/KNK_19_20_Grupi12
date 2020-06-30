package application.controllers;


public abstract class ChildController extends BaseController{
  public info parentController;

  public void setParentController(info controller) {
    this.parentController = controller;
  }
}