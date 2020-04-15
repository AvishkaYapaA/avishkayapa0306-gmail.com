package Services;

///  Shedule edit

  //import java.sql.Date;

//import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Model.Schedule;
//import model.appointment;


	@Path("/Schedule")

public class ScheduleService {
		Schedule scheduleObj = new Schedule();

		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readschedule() {
			return scheduleObj.readschedule();
			// return "Hello";
		}

		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertSchedule(
				@FormParam("Doctor_ID") int doctor_ID,
				@FormParam("Doctor_Name") String doctor_Name,
				@FormParam("Specialization") String specialization,
				@FormParam("Hospital_ID") int hospital_ID,
				@FormParam("Chargers") String chargers,
				@FormParam("Available_Time") String available_Time,
				@FormParam("End_Time") String end_Time,
				@FormParam("Sever_Time") String sever_Time
				) 
		{
			//String output = appointmentObj.insertSchedule(doctor_ID, doctor_Name, specialization, hospital_ID, available_Time, end_Time, Chargers, sever_Time);
			String output = scheduleObj.insertSchedule(doctor_ID, doctor_Name, specialization, hospital_ID, chargers, available_Time, end_Time, sever_Time);
			
			//String output = appointmentObj.insertSchedule(Doctor_ID, Doctor_Name, Specialization, Hospital_ID, Chargers);
			
			return output;
		}
		
		
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateSchedule(String scheduleData) { // Convert the input string to a JSON object
			JsonObject appointmentObject = new JsonParser().parse(scheduleData).getAsJsonObject();

			// Read the values from the JSON object
			int schedule_ID = appointmentObject.get("Schedule_ID").getAsInt();
			int doctor_ID = appointmentObject.get("Doctor_ID").getAsInt();
			String doctor_Name = appointmentObject.get("Doctor_Name").getAsString();
			String specialization = appointmentObject.get("Specialization").getAsString();
			int hospital_ID = appointmentObject.get("Hospital_ID").getAsInt();
			double chargers = appointmentObject.get("Chargers").getAsDouble();
			String available_Time = appointmentObject.get("Available_Time").getAsString();
			String end_Time =  appointmentObject.get("End_Time").getAsString();
			String sever_Time = appointmentObject.get("Sever_Time").getAsString();

			String output = scheduleObj.updateSchedule(schedule_ID, doctor_ID, doctor_Name, specialization, hospital_ID, chargers, available_Time, end_Time, sever_Time);
			return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteschedule(String scheduleData) {
			// Convert the input string to an XML document
			Document doc = Jsoup.parse(scheduleData, "", Parser.xmlParser());
			//Read the value from the element <itemID>  
			String schedule_ID = doc.select("Schedule_ID").text();

			String output = scheduleObj.deleteschedule(schedule_ID);

			return output;
		}
	}
