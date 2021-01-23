package ch.inss.idaiserver.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.core.type.*;

import ch.inss.idaiserver.model.*;


@Service
public class PersistJSON {
	
	private static final Logger logger = LoggerFactory.getLogger(PersistJSON.class);

	/**
	 * @param filePath
	 * @param report
	 * @return
	 */
	public static boolean writeJSON(String filePath, Report report) {
		boolean ok = false;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(filePath), report);
			ok = true;
		} catch (IOException e) {
			logger.error("Could not write JSON object.");
			e.printStackTrace();
		}
		return ok;
	}
	
	public static Report reportFromJSON(String string) {
//		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		ObjectMapper objectMapper = new ObjectMapper();
		Report report = null;
		try {
			report = objectMapper.readValue(string, Report.class);
		} catch (JsonProcessingException e) {
			logger.error("Could not create JSON object.");
			e.printStackTrace();
		}
		return report;
	}
	
	public static List<Report> readArray(String array){
		String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";

		ObjectMapper objectMapper = new ObjectMapper();

		Report[] reports = null;
		try {
			reports = objectMapper.readValue(jsonArray, Report[].class);
		} catch (JsonProcessingException e) {
			logger.error("Could not create Report object.");
			e.printStackTrace();
		}
		return Arrays.asList(reports);
	}
	
	public static String writeArray(List<Report> list, String filePath) {
		String string = null;
		ObjectMapper objectMapper = new ObjectMapper();
	    ArrayNode arrayNode = objectMapper.createArrayNode();
	    try {
		    for (Report report : list) {
		    	String json = objectMapper.writeValueAsString(report);
				
		    	JsonNode jsonNode = objectMapper.readTree(json);
		    	arrayNode.add(jsonNode);
		    	
		    }

		    // convert `ArrayNode` to pretty-print JSON
		    // 	without pretty-print, use `arrayNode.toString()` method
	    	string = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
	    	objectMapper.writeValue(new File(filePath), string);
	    } catch (JsonProcessingException e) {
	    	logger.error("Could not create JSON object.");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Could not write JSON object.");
			e.printStackTrace();
		} 
		return string;
	}
	
	public static List<Report> getReportList(String string){
//		String jsonCarArray = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
		ObjectMapper objectMapper = new ObjectMapper();
		List<Report> list = null;
		try {
			list = objectMapper.readValue(string, new TypeReference<List<Report>>(){});
		} catch (JsonProcessingException e) {
			logger.error("Could not create JSON object.");
			e.printStackTrace();
		}
		
		return list;

	}
	
	//TODO just sample.
	public static Map<String, Object> getMap()
	{
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		Map<String, Object> map = null;
		try {
			map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
