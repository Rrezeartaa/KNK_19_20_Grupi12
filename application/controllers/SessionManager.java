package application.controllers;

import java.util.Date;
import java.util.Locale;

import application.controllers.LangEnum;

public class SessionManager {

  public static Locale getLocale() {
    LangEnum lang = AppLanguage.get().getLanguage();
    if (lang == LangEnum.EN)
      return new Locale("en", "US");
    else
      return new Locale("al", "AL");
  }
}
