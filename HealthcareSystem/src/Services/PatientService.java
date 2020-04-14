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
	
//Item itemObj = new Item();
	Patient patientObj = new Patient();
	
	
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
	
	
	
}
