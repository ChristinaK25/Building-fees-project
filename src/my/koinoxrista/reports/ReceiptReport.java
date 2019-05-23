package my.koinoxrista.reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import my.koinoxrista.entities.Apartment;
import my.koinoxrista.entities.Building;
import my.koinoxrista.entities.Expense;

public class ReceiptReport {

    float _amount;
    String _period;
    Building _building;
    Apartment _apartment;

    ArrayList<Expense> _expenses;

    public ReceiptReport(int buildingId, int apartmentId, float amount, String period) {
        this._amount = amount;
        this._period = period;
        this._building = Building.getById(buildingId);
        this._apartment = Apartment.getById(apartmentId);
    }

    public String generate() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(now);
        StringBuilder sb = new StringBuilder();

        sb.append("<h1 align='center'>Απόδειξη Πληρωμής</h1>");
        sb
                .append("<p><strong>Στοιχεία πολυκατοικίας:</strong> ")
                .append(this._building.getDisplayName())
                .append("</p>");
        sb.append("<br><br>");
        sb
                .append("<p>Βεβαιώνεται ότι εξοφλήθηκαν σήμερα, <strong>")
                .append(currentDate)
                .append("</strong>, τα κοινόχρηστα του διαμερίσματος ")
                .append(this._apartment.getCode())
                .append(".</p>");
        sb
                .append("<p>Το ποσό που εισπράχθηκε για την περίοδο <strong>")
                .append(this._period)
                .append("</strong> ανέρχεται στα <strong>")
                .append(this._amount)
                .append(" ευρώ</strong>.</p>");
        sb.append("<br><br>");
        sb
                .append("<p align='center'>")
                .append("Ο Διαχειριστής")
                .append("</p>");
        sb.append("<br><br>");
        sb
                .append("<p align='center'>")
                .append(this._building.getManagerName())
                .append("</p>");

        return sb.toString();
    }
}
