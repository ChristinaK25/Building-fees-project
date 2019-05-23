package my.koinoxrista;

import my.koinoxrista.utils.SqliteConnection;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import my.koinoxrista.utils.SingleListSelectionModel;

public class BuildingsListForm extends javax.swing.JFrame {

    public BuildingsListForm() {
        initComponents();
        updateBuildingsTable();
    }

    public void updateBuildingsTable() {
        Connection connection = SqliteConnection.getConnection();

        String query = "select buildings.id, buildings.address, buildings.city, buildings.zip_code, "
                + "count(apartments.id) as apartments_count, buildings.manager_name, buildings.manager_phone "
                + "from buildings "
                + "left join apartments on apartments.building_id = buildings.id "
                + "group by buildings.id";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            DefaultTableModel dtm = buildTableModel(rs);
            this.buildingsTable.setDefaultEditor(Object.class, null);
            this.buildingsTable.setSelectionModel(new SingleListSelectionModel());
            this.buildingsTable.setModel(dtm);
            dtm.fireTableDataChanged();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Κωδικός Πολυκατοικίας");
        columnNames.add("Διεύθυνση");
        columnNames.add("Πόλη");
        columnNames.add("Ταχυδρομικός Κώδικας");
        columnNames.add("Αριθμός Διαμερισμάτων");
        columnNames.add("Ονοματεπώνυμο Διαχειριστή");
        columnNames.add("Τηλέφωνο Διαχειριστή");

        // data of the table
        int columnCount = metaData.getColumnCount();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        DefaultTableModel dtm = new DefaultTableModel(data, columnNames);

        return dtm;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buildingsTableContainer = new javax.swing.JScrollPane();
        buildingsTable = new javax.swing.JTable();
        newRecordButton = new javax.swing.JButton();
        editRecordButton = new javax.swing.JButton();
        deleteRecordButton = new javax.swing.JButton();
        expensesButton = new javax.swing.JButton();
        calculateKoinoxristaButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        apartmentsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Διαχείριση Κοινοχρήστων - Λίστα Πολυκατοικιών");

        buildingsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Κωδικός Πολυκατοικίας", "Διεύθυνση", "Πόλη", "Ταχυδρομικός Κώδικας", "Αριθμός Διαμερισμάτων", "Ονοματεπώνυμο Διαχειριστή", "Τηλέφωνο Διαχειριστή"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        buildingsTableContainer.setViewportView(buildingsTable);

        newRecordButton.setText("Νέα Εγγραφή");
        newRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRecordButtonActionPerformed(evt);
            }
        });

        editRecordButton.setText("Επεξεργασία Εγγραφής");
        editRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRecordButtonActionPerformed(evt);
            }
        });

        deleteRecordButton.setText("Διαγραφή Εγγραφής");
        deleteRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRecordButtonActionPerformed(evt);
            }
        });

        expensesButton.setText("Καταχώρηση Εξόδων");
        expensesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expensesButtonActionPerformed(evt);
            }
        });

        calculateKoinoxristaButton.setText("Υπολογισμός Κοινοχρήστων");
        calculateKoinoxristaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateKoinoxristaButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titleLabel.setText("Λίστα Πολυκατοικιών");

        apartmentsButton.setText("Διαμερίσματα/Καταστήματα");
        apartmentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apartmentsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buildingsTableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deleteRecordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editRecordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(calculateKoinoxristaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(expensesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(apartmentsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buildingsTableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newRecordButton)
                            .addComponent(apartmentsButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editRecordButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteRecordButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(expensesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calculateKoinoxristaButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRecordButtonActionPerformed
        BuildingDetailsForm form = new BuildingDetailsForm(0, this);
        form.setVisible(true);
    }//GEN-LAST:event_newRecordButtonActionPerformed

    private void editRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRecordButtonActionPerformed
        int row = this.buildingsTable.getSelectedRow();

        // Check if user has actually selected a building to edit
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Παρακαλώ επιλέξτε πολυκατοικία για επεξεργασία.",
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            // Get currently selected building ID
            int building_id = (int) this.buildingsTable.getModel().getValueAt(row, 0);
            BuildingDetailsForm form = new BuildingDetailsForm(building_id, this);
            form.setVisible(true);
        }
    }//GEN-LAST:event_editRecordButtonActionPerformed

    private void deleteRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRecordButtonActionPerformed
        int row = this.buildingsTable.getSelectedRow();

        // Check if user has actually selected a building to delete
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Παρακαλώ επιλέξτε πολυκατοικία για διαγραφή.",
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            int result = JOptionPane.showConfirmDialog(this,
                    "Είστε βέβαιοι πως επιθυμείτε να διαγράψετε την επιλεγμένη πολυκατοικία;\n"
                    + "Η ενέργεια αυτή δεν μπορεί να αναιρεθεί.",
                    "Επιβεβαίωση Διαγραφής",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == 0) {
                int building_id = (int) this.buildingsTable.getModel().getValueAt(row, 0);
                String query = "delete from buildings where id = ?";
                Connection connection = SqliteConnection.getConnection();
                PreparedStatement pstmt = null;
                try {
                    pstmt = connection.prepareStatement(query);
                    pstmt.setInt(1, building_id);
                    pstmt.executeUpdate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                this.updateBuildingsTable();
            }
        }
    }//GEN-LAST:event_deleteRecordButtonActionPerformed

    private void apartmentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apartmentsButtonActionPerformed
        int row = this.buildingsTable.getSelectedRow();

        // Check if user has actually selected a building 
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Παρακαλώ επιλέξτε πολυκατοικία για να δείτε τα διαμερίσματά/καταστήματά της.",
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            int building_id = (int) this.buildingsTable.getModel().getValueAt(row, 0);
            ApartmentsListForm form = new ApartmentsListForm(building_id);
            form.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_apartmentsButtonActionPerformed

    private void expensesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expensesButtonActionPerformed
        int row = this.buildingsTable.getSelectedRow();

        // Check if user has actually selected a building 
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Παρακαλώ επιλέξτε πολυκατοικία για να καταχωρήσετε τα έξοδά της.",
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            int building_id = (int) this.buildingsTable.getModel().getValueAt(row, 0);
            LoginDialog dialog = new LoginDialog(this, true, building_id);
            dialog.setVisible(true);
            if (dialog.isSuccessful()) {
                ExpensesListForm form = new ExpensesListForm(building_id);
                form.setVisible(true);
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_expensesButtonActionPerformed

    private void calculateKoinoxristaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateKoinoxristaButtonActionPerformed
        int row = this.buildingsTable.getSelectedRow();

        // Check if user has actually selected a building 
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Παρακαλώ επιλέξτε πολυκατοικία για να υπολογίσετε τα κοινόχρηστά της.",
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            int building_id = (int) this.buildingsTable.getModel().getValueAt(row, 0);
            LoginDialog dialog = new LoginDialog(this, true, building_id);
            dialog.setVisible(true);
            if (dialog.isSuccessful()) {

                String period = (String) JOptionPane.showInputDialog(
                        this,
                        "Εισάγετε την περίοδο που επιθυμείτε για τον υπολογισμό κοινοχρήστων:",
                        "Υπολογισμός Κοινοχρήστων",
                        JOptionPane.PLAIN_MESSAGE
                );
                if ((period != null) && (period.length() > 0)) {
                    CalculateForm form = new CalculateForm(building_id, period);
                    form.setVisible(true);
                    this.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_calculateKoinoxristaButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuildingsListForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuildingsListForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuildingsListForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuildingsListForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuildingsListForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton apartmentsButton;
    private javax.swing.JTable buildingsTable;
    private javax.swing.JScrollPane buildingsTableContainer;
    private javax.swing.JButton calculateKoinoxristaButton;
    private javax.swing.JButton deleteRecordButton;
    private javax.swing.JButton editRecordButton;
    private javax.swing.JButton expensesButton;
    private javax.swing.JButton newRecordButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}