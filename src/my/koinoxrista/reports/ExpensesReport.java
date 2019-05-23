package my.koinoxrista.reports;

import java.util.ArrayList;
import my.koinoxrista.entities.Building;
import my.koinoxrista.entities.Expense;

public class ExpensesReport {

    int _buildingId;
    Building _building;
    String _period;
    ArrayList<Expense> _expenses;

    public ExpensesReport(int buildingId, String period) {
        this._buildingId = buildingId;
        this._period = period;
        this._building = Building.getById(buildingId);
        if (period.equals("")) {
            this._expenses = Building.getExpenses(buildingId);
        } else {
            this._expenses = Building.getExpenses(buildingId, period);
        }
    }

    public String generate() {
        StringBuilder sb = new StringBuilder();

        sb.append("<h1 align='center'>Πίνακας Εξόδων</h1>");
        sb
                .append("<p><strong>Στοιχεία πολυκατοικίας:</strong> ")
                .append(this._building.getDisplayName())
                .append("</p>");
        if (this._period.equals("")) {
            sb.append("<p>Εμφανίζονται όλα τα καταχωρημένα έξοδα.</p>");
        } else {
            sb
                    .append("<p><strong>Περίοδος:</strong> ")
                    .append(this._period)
                    .append("</p>");
        }
        sb.append("<br><br>");
        sb.append("<table width='100%' border='1'>");
        sb.append("<thead>");
        sb.append("<tr>");
        sb.append("<th>Κατηγορία</th>");
        sb.append("<th>Περιγραφή</th>");
        sb.append("<th>Ποσό</th>");
        sb.append("</tr>");
        sb.append("</thead>");
        sb.append("<tbody>");
        for (Expense expense : this._expenses) {
            sb.append("<tr>");
            sb
                    .append("<td>")
                    .append(Expense.getCategoryLiteral(expense.getCategory()))
                    .append("</td>");
            sb
                    .append("<td>")
                    .append(expense.getDescription())
                    .append("</td>");
            sb
                    .append("<td>")
                    .append(expense.getAmount())
                    .append("</td>");
            sb.append("</tr>");
        }
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
