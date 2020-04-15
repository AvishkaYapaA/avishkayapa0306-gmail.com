package Services;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import Model.Payment;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService {

	Payment payment = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return payment.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("cardNo") int cardNo, @FormParam("nameOnCard") String nameOnCard,
			@FormParam("expDate") String expDate, @FormParam("cvc") int cvc) {
		String output = payment.insertPayment(cardNo, nameOnCard, expDate, cvc);
		return output;
	}


	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String paymentData) {
		// Convert the input string to an XML document
		Document document = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String pamentID = document.select("pamentID").text();

		String output = payment.deletePayment(pamentID);
		return output;
	}

}
