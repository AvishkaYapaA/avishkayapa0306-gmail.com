package Services;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import Model.Patient;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/Patient")
public class PatientService {
	

	Patient patientObj = new Patient();
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("userName") String username,@FormParam("userAddress") String address,
			@FormParam("nicNo") String nic,@FormParam("dateOfBirth") String dob,@FormParam("gender") String gender,
			@FormParam("mobileNumber") String mobile_number,@FormParam("email") String email,@FormParam("password") String password){
	
		String output = patientObj.insertPatient(username, address, nic, dob, gender, mobile_number, email, password);
		
		return output;
	}
	
	
	//view
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatients(){
		
		return patientObj.readPatients();
	}
	
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String patientData)
	{
	//Convert the input string to a JSON object
	 JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
	//Read the values from the JSON object
	 
	 String userid = patientObject.get("userID").getAsString();
	 String username = patientObject.get("userName").getAsString();
	 String address = patientObject.get("userAddress").getAsString();
	 String nic = patientObject.get("nicNo").getAsString();
	 String dob = patientObject.get("dateOfBirth").getAsString();
	 String gender = patientObject.get("gender").getAsString();
	 String mobile_number = patientObject.get("mobileNumber").getAsString();
	 String email = patientObject.get("email").getAsString();
	 String password = patientObject.get("password").getAsString();
	 
	 String output = patientObj.updatePatient(userid, username, address, nic, dob, gender, mobile_number, email, password);
	 return output;
	}
	
	
	//delete-----
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String userid = doc.select("userID").text();
	 String output = patientObj.deletePatient(userid);
	 
	 return output;
	}
	
}
