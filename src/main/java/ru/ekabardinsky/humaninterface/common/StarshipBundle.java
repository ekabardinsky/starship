package ru.ekabardinsky.humaninterface.common;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

public class StarshipBundle {
	private static final ResourceBundle ar = ResourceBundle.getBundle ("wording");
	private static final ResourceBundle ar_ru = ResourceBundle.getBundle ("wording_ru");
       
	public static String get(String key, String lang){
		if("ru".equals(lang)){
			if(ar_ru.containsKey(key))
				try {
					return new String(ar_ru.getString(key).getBytes("ISO-8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		if(ar.containsKey(key))
		return ar.getString(key);
		
		return null;
	}
}


