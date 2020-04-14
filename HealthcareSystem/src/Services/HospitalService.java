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




@Path("/Hospital")
public class HospitalService {
	
	
	Hospital hospitalObj = new Hospital();
		
		
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

}
