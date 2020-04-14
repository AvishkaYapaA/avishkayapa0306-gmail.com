package Services;

//For REST Service
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import Model.Hospital;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


//HealthcareService/Hospital


@Path("/Hospital")
public class HospitalService {
	
	
	Hospital hospitalObj = new Hospital();
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospital() {
		return hospitalObj.readHospital();
	}
		
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertHospital(@FormParam("MOHcode") String MOHCode,@FormParam("ManagerName") String Managername,
				@FormParam("HospitalName") String Hospitalname,@FormParam("Address") String address,@FormParam("TPnumber") String TPNumber,
				@FormParam("Location") String location,@FormParam("Username") String username,@FormParam("Password") String password){
		
			String output = hospitalObj.insertHospital(MOHCode, Managername, Hospitalname, address, TPNumber, location, username, password);
			
			return output;
		}
		
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateHospital(String hospitalData)
		{
			//Convert the input string to a JSON object
			JsonObject obj = new JsonParser().parse(hospitalData).getAsJsonObject();
			
			//Read the values from the JSON object
			String HospitalID = obj.get("HospitalID").getAsString();
			String MOHCode = obj.get("MOHcode").getAsString();
			String Managername = obj.get("ManagerName").getAsString();
			String Hospitalname = obj.get("HospitalName").getAsString();
			String address = obj.get("Address").getAsString();
			String TPNumber = obj.get("TPnumber").getAsString();
			String 	location = obj.get("Location").getAsString();
			String 	username = obj.get("Username").getAsString();
			String 	password = obj.get("Password").getAsString();
			
			String output = hospitalObj.updateHospital(HospitalID, MOHCode, Managername, Hospitalname, address, TPNumber, location, username, password);
			
			return output;
		}

}
