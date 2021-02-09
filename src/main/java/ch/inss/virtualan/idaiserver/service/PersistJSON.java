package ch.inss.virtualan.idaiserver.service;

import ch.inss.virtualan.idaiserver.model.Report;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.core.type.*;


/**
 * The type Persist json.
 */
@Service
public class PersistJSON {
	
	private static final Logger logger = LoggerFactory.getLogger(PersistJSON.class);

	/**
	 * Write json boolean.
	 *
	 * @param filePath the file path
	 * @param report   the report
	 * @return boolean
	 * @throws JsonGenerationException the json generation exception
	 * @throws JsonMappingException    the json mapping exception
	 * @throws IOException             the io exception
	 */
	public static boolean writeJSON(String filePath, Report report) throws JsonGenerationException, JsonMappingException, IOException {
		boolean ok = false;
		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(new File(filePath), report);
			ok = true;

		return ok;
	}

	/**
	 * Report from json report.
	 *
	 * @param string the string
	 * @return report
	 * @throws JsonMappingException    the json mapping exception
	 * @throws JsonProcessingException the json processing exception
	 */
	public static Report reportFromJSON(String string) throws JsonMappingException, JsonProcessingException {
		logger.debug("Going to read JSON from file " + string);
		ObjectMapper objectMapper = new ObjectMapper();
		Report report = null;
			report = objectMapper.readValue(string, Report.class);
		
		return report;
	}

	/**
	 * Read array list.
	 *
	 * @param array the array
	 * @return the list
	 * @throws JsonMappingException    the json mapping exception
	 * @throws JsonProcessingException the json processing exception
	 */
	public static List<Report> readArray(String array) throws JsonMappingException, JsonProcessingException{
		String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";

		ObjectMapper objectMapper = new ObjectMapper();

		Report[] reports = null;
			reports = objectMapper.readValue(jsonArray, Report[].class);
		
		return Arrays.asList(reports);
	}

	/**
	 * Write array string.
	 *
	 * @param list     the list
	 * @param filePath the file path
	 * @return the string
	 * @throws IOException the io exception
	 */
	public static String writeArray(List<Report> list, String filePath) throws IOException {
		String string = null;
		ObjectMapper objectMapper = new ObjectMapper();
	    ArrayNode arrayNode = objectMapper.createArrayNode();
		    for (Report report : list) {
		    	String json = objectMapper.writeValueAsString(report);
				
		    	JsonNode jsonNode = objectMapper.readTree(json);
		    	arrayNode.add(jsonNode);
		    	
		    }

		    // convert `ArrayNode` to pretty-print JSON
		    // 	without pretty-print, use `arrayNode.toString()` method
	    	string = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
//	    	logger.debug(string);
//	    	logger.debug(arrayNode.toPrettyString());
//	    	logger.debug(arrayNode.toString());
//	    	objectMapper.writeValue(new File(filePath), string);
		return string;
	}

	/**
	 * Gets report list.
	 *
	 * @param string the string
	 * @return the report list
	 * @throws JsonProcessingException the json processing exception
	 */
	public static List<Report> getReportList(String string) throws JsonProcessingException{
//		String jsonCarArray = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
		ObjectMapper objectMapper = new ObjectMapper();
		List<Report> list = null;
			list = objectMapper.readValue(string, new TypeReference<List<Report>>(){});
		
		return list;

	}

	/**
	 * Gets map.
	 *
	 * @return the map
	 * @throws JsonMappingException    the json mapping exception
	 * @throws JsonProcessingException the json processing exception
	 */
//TODO just sample.
	public static Map<String, Object> getMap() throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		Map<String, Object> map = null;
			map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
		return map;
	}

	/**
	 * Read reports list.
	 *
	 * @param filename the filename
	 * @return the list
	 * @throws JsonParseException   the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException          the io exception
	 */
	public static List<Report> readReports(String filename) throws JsonParseException, JsonMappingException, IOException{
		logger.debug("Reading into List<Report>: " + filename);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Report> list = objectMapper.readValue(
		        new File(filename), 
		        new TypeReference<List<Report>>(){});
		return list;
	}


	/**
	 * Read latest report report.
	 *
	 * @param filename the filename
	 * @return the report
	 * @throws JsonParseException   the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException          the io exception
	 */
	public static Report readLatestReport(String filename) throws JsonParseException, JsonMappingException, IOException{
		logger.debug("Reading into Report: " + filename);
		ObjectMapper objectMapper = new ObjectMapper();
		Report list = objectMapper.readValue(new File(filename),Report.class);
		return list;
	}

}
