package pwm.controllers;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import pwm.utils.SessionManager;

public abstract class BaseController implements Initializable {

  
  @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    loadLangTexts(getLangBundle());
  }

  public ResourceBundle getLangBundle() {
    Locale locale = SessionManager.getLocale();
    return ResourceBundle.getBundle("pwm.bundles.LangBundle", locale);
  }

  public abstract void loadLangTexts(ResourceBundle langBundle);
}
