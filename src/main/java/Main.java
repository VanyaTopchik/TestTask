import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws ParseException {
        String fileName = "src/test/resources/tickets.json";
        JSONTicketsParser jsonTicketsParser = new JSONTicketsParser();
        Tickets tickets = jsonTicketsParser.parse(fileName);//Парсим и получаем билеты
        long count = 0, sum = 0;
        for (Ticket ticket : tickets.getTickets()) {//вычисление времени полета для каждого полета
            //Пример: "departure_date": "12.05.18", "departure_time": "16:20",
            String time1 = ticket.getDeparture_date() + " " + ticket.getDeparture_time();
            String time2 = ticket.getArrival_date() + " " + ticket.getArrival_time();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault());
            Date departureDate = dateFormat.parse(time1);
            Date arrivalDate = dateFormat.parse(time2);
            long time = arrivalDate.getTime() - departureDate.getTime();
            sum += time;
            count++;
        }
        long avgTime = sum/count;//Среднее время полета в миллисекундах
        long second = (avgTime / 1000) % 60;
        long minute = (avgTime / (1000 * 60)) % 60;
        long hour = (avgTime / (1000 * 60 * 60)) % 24;
        System.out.printf("Среднее время полета между городами: %02d:%02d:%02d.\n", hour, minute, second);
        long avgTime90 = (long) (avgTime * 0.9);
        long second90 = (avgTime90 / 1000) % 60;
        long minute90 = (avgTime90 / (1000 * 60)) % 60;
        long hour90 = (avgTime90 / (1000 * 60 * 60)) % 24;
        System.out.printf("90-й процентиль среднего времени полета между городами Владивосток и Тель-Авив: %02d:%02d:%02d.\n", hour90, minute90, second90);
    }
}
