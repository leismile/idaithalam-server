package ch.inss.idaiserver.service;

import org.springframework.stereotype.Service;

import io.jsondb.JsonDBTemplate;

@Service
public class PersistJSON {

	public JsonDBTemplate getJsonDB() {
	String dbFilesLocation = "./db/idaijson.db";

	//Java package name where POJO's are present
	String baseScanPackage = "ch.inss.idaiserver.model";

	//Optionally a Cipher object if you need Encryption
	//ICipher cipher = new Default1Cipher("1r8+24pibarAWgS85/Heeg==");

	JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage);
	return jsonDBTemplate;
	}
	
}
