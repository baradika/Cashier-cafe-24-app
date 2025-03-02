/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cCafeHotel;

import static cCafeHotel.CashierPage.hrgMenu1;
import static cCafeHotel.CashierPage.hrgMenu2;
import static cCafeHotel.CashierPage.hrgMenu3;
import static cCafeHotel.CashierPage.menu1;
import static cCafeHotel.CashierPage.menu2;
import static cCafeHotel.CashierPage.menu3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author Fathan
 */
public class KichenPage extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Creates new form KichenPage
     */
    public KichenPage() {
        initComponents();
        showTableBarang();
        kdBarang.setEditable(false);
        Look1.addActionListener(e -> showLookupDialog());
    }
    
    
    private void showLookupDialog() {
        try {
            // Mendapatkan koneksi ke database
            Connection conn = koneksi.getKoneksi();

            // Query untuk mengambil data kasir
            String sql = "SELECT namaBarang, harga, stokBarang FROM barang";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Membuat model tabel untuk tampilan lookup
            DefaultTableModel model = new DefaultTableModel(new String[]{"Nama Barang", "Harga", "Stok"}, 0);
            while (resultSet.next()) {
                model.addRow(new Object[]{
                    resultSet.getString("namaBarang"),
                    resultSet.getDouble("harga"),
                    resultSet.getInt("stokBarang")
                });
            }

            // Menampilkan dialog lookup
            JTable tblLookup = new JTable(model);
            JOptionPane.showMessageDialog(this, new JScrollPane(tblLookup), "Pilih Barang", JOptionPane.PLAIN_MESSAGE);

            // Mendapatkan data dari baris yang dipilih
            int selectedRow = tblLookup.getSelectedRow();
            if (selectedRow != -3) {
            // Ambil data dari tabel
            String namaBarang = tblLookup.getValueAt(selectedRow, 0).toString();
            String harga = tblLookup.getValueAt(selectedRow, 1).toString();
            String stok = tblLookup.getValueAt(selectedRow, 2).toString();

            nmBarang.setText(namaBarang);
            HrgBrang.setText(harga);
            stokBrang.setText(stok);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data barang: " + ex.getMessage());
        }
    }
    
    
    
    
    
    private void showTableBarang() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Kode Barang");
    model.addColumn("Nama Barang");
    model.addColumn("Stok");
    model.addColumn("Harga");

    try {
        // Koneksi ke database
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbcafe24", "root", "");
        String sql = "SELECT * FROM barang";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // Mengisi data ke JTable
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("kdBarang"),
                rs.getString("namaBarang"),
                rs.getInt("stokBarang"),
                rs.getInt("harga")
            });
        }

        // Set model ke JTable
        tblKitchen.setModel(model);

        // Tutup koneksi
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal menampilkan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void save() {
    String namaBarang = nmBarang.getText();
    String stokBarangStr = stokBrang.getText(); // stokBrang harus berupa JTextField
    String hargaStr = HrgBrang.getText();

    // Validasi input
    if (namaBarang.isEmpty() || stokBarangStr.isEmpty() || hargaStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        int stokBarang = Integer.parseInt(stokBarangStr); // Ubah teks ke int
        int harga = Integer.parseInt(hargaStr); // Ubah teks ke int

        // Koneksi ke database
        String sql = "INSERT INTO barang (namaBarang, stokBarang, harga) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbcafe24", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, namaBarang);
            stmt.setInt(2, stokBarang);
            stmt.setInt(3, harga);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                nmBarang.setText("");   // Kosongkan input
                stokBrang.setText("");  // Kosongkan input
                HrgBrang.setText("");   // Kosongkan input
            }
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Stok dan Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    

    private void delete() {
    int selectedRow = tblKitchen.getSelectedRow(); // Ambil baris yang dipilih

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            int kdBarang = Integer.parseInt(tblKitchen.getValueAt(selectedRow, 0).toString()); // Ambil kdBarang dari tabel

            // Koneksi ke database
            String sql = "DELETE FROM barang WHERE kdBarang = ?";
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbcafe24", "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, kdBarang);

                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");

                    // Refresh tabel setelah menghapus data
                    showTableBarang();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
    private void update() {
    int selectedRow = tblKitchen.getSelectedRow(); // Ambil baris yang dipilih

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String namaBarang = nmBarang.getText();
    String stokBarangStr = stokBrang.getText();
    String hargaStr = HrgBrang.getText();

    // Validasi input
    if (namaBarang.isEmpty() || stokBarangStr.isEmpty() || hargaStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        int stokBarang = Integer.parseInt(stokBarangStr);
        int harga = Integer.parseInt(hargaStr);
        int kdBarang = Integer.parseInt(tblKitchen.getValueAt(selectedRow, 0).toString()); // Ambil kdBarang dari tabel

        // Konfirmasi update
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin mengupdate data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Koneksi ke database
            String sql = "UPDATE barang SET namaBarang = ?, stokBarang = ?, harga = ? WHERE kdBarang = ?";
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbcafe24", "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, namaBarang);
                stmt.setInt(2, stokBarang);
                stmt.setInt(3, harga);
                stmt.setInt(4, kdBarang);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");

                    // Refresh tabel setelah update
                    showTableBarang();

                    // Kosongkan input field setelah update
                    nmBarang.setText("");
                    stokBrang.setText("");
                    HrgBrang.setText("");
                }
            }
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Stok dan Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal mengupdate data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void reset() {
    nmBarang.setText("");   // Kosongkan field Nama Barang
    stokBrang.setText("");  // Kosongkan field Stok Barang
    HrgBrang.setText("");   // Kosongkan field Harga Barang
}





    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nmBarang = new javax.swing.JTextField();
        kdBarang = new javax.swing.JTextField();
        HrgBrang = new javax.swing.JTextField();
        stokBrang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKitchen = new javax.swing.JTable();
        save = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Look1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("kitchen");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(324, 324, 324))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel2.setText("Kode Barang");

        jLabel3.setText("Nama Barang");

        jLabel4.setText("Harga");

        jLabel5.setText("Stok");

        kdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdBarangActionPerformed(evt);
            }
        });

        tblKitchen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKitchen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKitchenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKitchen);

        save.setText("SAVE");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("RESET");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        Look1.setText("LOOKUP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(kdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(nmBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(29, 29, 29)
                                .addComponent(Look1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HrgBrang, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stokBrang, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jButton2)
                        .addGap(44, 44, 44)
                        .addComponent(jButton3)
                        .addGap(40, 40, 40)
                        .addComponent(jButton4)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HrgBrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stokBrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(kdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nmBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(41, 41, 41))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Look1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdBarangActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
      save();
      showTableBarang();
    }//GEN-LAST:event_saveActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //int selectedRow = tblpesanan.getSelectedRow();
   // if (selectedRow != -1) {
      //  ((javax.swing.table.DefaultTableModel) tblpesanan.getModel()).removeRow(selectedRow);
       // javax.swing.JOptionPane.showMessageDialog(this, "Baris berhasil dihapus!");
    //} else {
       // javax.swing.JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
   // }
   delete();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblKitchenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKitchenMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tblKitchenMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(KichenPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KichenPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KichenPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KichenPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KichenPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField HrgBrang;
    private javax.swing.JButton Look1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField kdBarang;
    public static javax.swing.JTextField nmBarang;
    private javax.swing.JButton save;
    private javax.swing.JTextField stokBrang;
    private javax.swing.JTable tblKitchen;
    // End of variables declaration//GEN-END:variables
}
