import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONTicketsParser {
    public Tickets parse(String fileName){
        Tickets result = new Tickets();
        try(FileReader reader = new FileReader(fileName)) {
            JSONParser parser = new JSONParser();
            String str = reader.getEncoding();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonObject.get("tickets");
            ArrayList<Ticket> tickets = new ArrayList<>();
            for(Object obj: jsonArray) {
                JSONObject ticketJsonObject = (JSONObject) obj;
                Ticket ticket = new Ticket();
                ticket.setOrigin((String) ticketJsonObject.get("origin"));
                ticket.setOrigin_name((String) ticketJsonObject.get("origin_name"));
                ticket.setDestination((String) ticketJsonObject.get("destination"));
                ticket.setDestination_name((String) ticketJsonObject.get("destination_name"));
                ticket.setDeparture_date((String) ticketJsonObject.get("departure_date"));
                ticket.setDeparture_time((String) ticketJsonObject.get("departure_time"));
                ticket.setArrival_date((String) ticketJsonObject.get("arrival_date"));
                ticket.setArrival_time((String) ticketJsonObject.get("arrival_time"));
                ticket.setCarrier((String) ticketJsonObject.get("carrier"));
                long stops = (Long)ticketJsonObject.get("stops");
                ticket.setStops((int)stops);
                long price = (Long)ticketJsonObject.get("price");
                ticket.setPrice((int)price);
                tickets.add(ticket);
            }
            result.setTickets(tickets);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
