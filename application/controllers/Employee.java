package application.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {    
    private  final SimpleStringProperty Airline;
    private  final SimpleStringProperty From;
    private  final SimpleStringProperty Date;
    private  final SimpleStringProperty Scheduled;
    private  final SimpleIntegerProperty Status;
    
       Employee(String Airline, String From, String Date, String Scheduled, String Status)
    {      
       this.Airline = new SimpleStringProperty(Airline);
       this.From = new SimpleStringProperty(From);
       this.Date =  new SimpleStringProperty(Date);
       this.Scheduled =  new SimpleStringProperty(Scheduled);
       this.Status =  new SimpleStringProperty(Status);
    
    }
    
         
    public String getAirline() {
        return Airline.get();
    }

    public void setAirline(String Airline) {
        this.Airline.set(Airline);
    }
    
    
    
    public String getFrom() {
      //  return From.get();
    }

    public void setFrom(String From) {
    	From.set(From);
    }
    
    
    
     public String getDate() {
        return Date.get();
    }

    public void setDate(String Date) {
    	//Date.set(Date);
    }
    
    
    public String getScheduled() {
        return Scheduled.get();
    }

     public void setScheduledt(String street) {
        this.Scheduled.set(street);
    }
    
   

    
    public int getStatus() {
        return Status.get();
    }

    
    public void setStatus(int Status) {
       // this.Status.set(Status);
    }
     }
    
    
