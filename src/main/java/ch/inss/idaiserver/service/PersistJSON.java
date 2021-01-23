package ch.inss.idaiserver.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class PersistJSON {

	public static boolean stringToJson(String s) {
//		JSONObject jo = new JSONObject(o);
		JSONObject jo = new JSONObject(
				s
//				  "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}"
				);
		
		
		return true;
	}
	
}
