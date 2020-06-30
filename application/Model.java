package application;

//import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model extends RecursiveTreeObject<Model> {

	StringProperty emri, mbiemri, datelindja, email;

	public Model(String emri, String mbiemri,  String email) {

		this.emri = new SimpleStringProperty(emri);
		this.mbiemri = new SimpleStringProperty(mbiemri);
		//this.datelindja = new SimpleStringProperty(datelindja);
		this.email = new SimpleStringProperty(email);
	}

	public String getEmri() {
		return emri.get();
	}

	public String getMbiemri() {
		return mbiemri.get();
	}
	
	public String getAge() {
		return datelindja.get();
	}
	
	public String getEmail() {
		return email.get();
	}
	
	public StringProperty emri() {
		return emri;
	}
	
	public StringProperty mbiemriProperty() {
		return mbiemri;
	}

	public StringProperty datelindjaProperty() {
		return datelindja;
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	public void setEmri(String emri) {
		this.emri.set(emri);
	}


	public void setMbiemri(String mbiemri) {
		this.mbiemri.set(mbiemri);
	}


	public void setAge(String datelindja) {
		this.datelindja.set(datelindja);
	}


	public void setEmail(String email) {
		this.email.set(email);
	}
}
