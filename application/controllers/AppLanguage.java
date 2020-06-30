package application.controllers;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.Properties;
import application.controllers.LangEnum;

public class AppLanguage {
	private Properties props;
	private AppLanguage() {
		
	    try {
	      props = new Properties();
	      props.load(getClass().getResourceAsStream("../application/bundles/config.properties"));
	    } catch (Exception e) {
	    }
	  }

	 public LangEnum getLanguage() {
		    return props.getProperty("lang", "en").equals("en") ? LangEnum.EN : LangEnum.AL;
		  }
	 private static AppLanguage instance;

	  public static AppLanguage get() {
	    if (instance == null) {
	      instance = new AppLanguage();
	    }
	    return instance;
	  }
	  public void setLanguage(LangEnum lang) throws Exception {
		    URI confPath = getClass().getResource("../application/bundles/config.properties").toURI();
		    props.setProperty("lang", lang == LangEnum.EN ? "en" : "al");
		    props.store(new FileOutputStream(new File(confPath)), "");
		  }
}
