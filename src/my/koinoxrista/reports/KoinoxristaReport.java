package my.koinoxrista.reports;

import java.util.ArrayList;
import my.koinoxrista.entities.Apartment;
import my.koinoxrista.entities.Building;
import my.koinoxrista.entities.Expense;

public class KoinoxristaReport {

    int _buildingId = 0;
    String _period = "";
    Building _building = null;
    float _koinoxristaSum = 0;
    float _heatingSum = 0;
    float _coownershipSum = 0;
    float _elevatorSum = 0;
    float _sum = 0;
    ArrayList<Apartment> _apartments;

    public KoinoxristaReport(int buildingId, String period) {
        this._buildingId = buildingId;
        this._period = period;
        this._building = Building.getById(buildingId);
        this._koinoxristaSum = Expense.getCategorySum(_buildingId, 0, _period);
        this._heatingSum = Expense.getCategorySum(_buildingId, 1, _period);
        this._coownershipSum = Expense.getCategorySum(_buildingId, 2, _period);
        this._elevatorSum = Expense.getCategorySum(_buildingId, 3, _period);
        this._sum = _koinoxristaSum + _heatingSum + _coownershipSum + _elevatorSum;
        this._apartments = Building.getApartments(buildingId);
    }

    public String generate() {
        StringBuilder sb = new StringBuilder();

        sb
                .append("<h1 align='center'>Πίνακας Κοινοχρήστων περιόδου ")
                .append(this._period)
                .append("</h1>");
        sb
                .append("<p><strong>Στοιχεία πολυκατοικίας:</strong> ")
                .append(this._building.getDisplayName())
                .append("</p>");
        sb.append("<br><br>");
        sb.append("<table width='100%' border='1'>");
        sb.append("<thead>");
        sb.append("<tr>");
        sb.append("<th>Τύπος</th>");
        sb.append("<th>Κωδικός</th>");
        sb.append("<th>Χιλιοστά</th>");
        sb.append("<th>Ποσοστό Συμμετοχής</th>");
        sb.append("<th>Κοινόχρηστα</th>");
        sb.append("<th>Θέρμανση</th>");
        sb.append("<th>Συνιδιοκτησία</th>");
        sb.append("<th>Ανελκυστήρας</th>");
        sb.append("<th>Σύνολο</th>");
        sb.append("</tr>");
        sb.append("</thead>");
        sb.append("<tbody>");

        float elevatorActualSum = 0;

        for (Apartment apartment : this._apartments) {
            float perMille = apartment.getPerMille();
            float percentage = perMille / 10;
            float factor = percentage / 100;
            float koinoxrista = factor * _koinoxristaSum;
            float heating = factor * _heatingSum;
            float coownership = factor * _coownershipSum;

            float elevator;
            if (apartment.getFloor() == 0 || apartment.getType().equals("store")) {
                elevator = 0;
            } else {
                elevator = factor * _elevatorSum;
            }
            elevatorActualSum += elevator;

            float sum = koinoxrista + heating + coownership + elevator;

            sb.append("<tr>");
            sb
                    .append("<td>")
                    .append(Apartment.getTypeLiteral(apartment.getType()))
                    .append("</td>");
            sb
                    .append("<td>")
                    .append(apartment.getCode())
                    .append("</td>");
            sb
                    .append("<td>")
                    .append(apartment.getPerMille())
                    .append("</td>");
            sb
                    .append("<td>")
                    .append(percentage)
                    .append("</td>");
            sb
                    .append("<td>")
                    .append(koinoxrista)
                    .append("</td>");
            sb
                    .append("<td>")
                    .append(heating)
                    .append("</td>");
            sb
                    .append("<td>")
                    .append(coownership)
                    .append("</td>");
            sb
                    .append("<td>")
                    .append(elevator)
                    .append("</td>");
            sb
                    .append("<td>")
                    .append(sum)
                    .append("</td>");
            sb.append("</tr>");
        }
        sb.append("</tbody>");
        sb.append("</table>");

        sb.append("<br><br>");

        sb.append("<table width='100%' border='1'>");
        sb.append("<thead>");
        sb.append("<tr>");
        sb.append("<th>Σύνολο Κοινοχρήστων</th>");
        sb.append("<th>Σύνολο Θέρμανσης</th>");
        sb.append("<th>Σύνολο Συνιδιοκτησίας</th>");
        sb.append("<th>Σύνολο Ανελκυστήρα</th>");
        sb.append("<th>Υπόλοιπο Ανελκυστήρα</th>");
        sb.append("<th>Γενικό Σύνολο</th>");
        sb.append("</tr>");
        sb.append("</thead>");
        sb.append("<tbody>");
        sb.append("<tr>");
        sb
                .append("<td>")
                .append(this._koinoxristaSum)
                .append("</td>");
        sb
                .append("<td>")
                .append(this._heatingSum)
                .append("</td>");
        sb
                .append("<td>")
                .append(this._coownershipSum)
                .append("</td>");
        sb
                .append("<td>")
                .append(this._elevatorSum)
                .append("</td>");
        sb
                .append("<td color='red'>")
                .append(this._elevatorSum - elevatorActualSum)
                .append("</td>");
        sb
                .append("<td>")
                .append(this._sum)
                .append("</td>");
        sb.append("</tr>");
        sb.append("</tbody>");
        sb.append("</table>");

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
