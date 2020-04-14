package Services;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import Model.Doctor;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctor")
public class DoctorService {
	Doctor doctor = new Doctor();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctors() {
		return doctor.readDoctors();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("Dname") String DName, @FormParam("DRegNo") String RegNo, 
			@FormParam("Specialization") String Specialization,  @FormParam("ContactNo") int ContactNo,
			@FormParam("Address") String Address, @FormParam("Email") String Email, @FormParam("HospitalName") String HospitalName)
	{
		String output = doctor.insertDoctors(DName, RegNo, Specialization, ContactNo, Address, Email, HospitalName);
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorData)
	{
		//Convert the input string to a JSON object
		JsonObject obj = new JsonParser().parse(doctorData).getAsJsonObject();
		
		//Read the values from the JSON object
		String DoctorId = obj.get("DoctorId").getAsString();
		String Dname = obj.get("Dname").getAsString();
		String DRegNo = obj.get("DRegNo").getAsString();
		String Specialization = obj.get("Specialization").getAsString();
		int ContactNo = obj.get("ContactNo").getAsInt();
		String Address = obj.get("Address").getAsString();
		String Email = obj.get("Email").getAsString();
		String 	HospitalName = obj.get("HospitalName").getAsString();
		
		String output = doctor.updateDoctor(DoctorId, Dname, DRegNo, Specialization, ContactNo, Address, Email, HospitalName);
		return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String doctorData)
	{
		//Convert the input string to an XML document
		Document document = Jsoup.parse(doctorData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String DoctorId = document.select("DoctorId").text();
		
		String output = doctor.deleteDoctor(DoctorId);
		return output;
	}

}
