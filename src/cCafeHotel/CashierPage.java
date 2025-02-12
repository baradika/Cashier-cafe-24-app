/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cCafeHotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author Dinda
 */
public class CashierPage extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    public CashierPage() {
        initComponents();
        // Tambahkan action listener ke tombol
        look1.addActionListener(e -> showLookupDialog(1));
        look2.addActionListener(e -> showLookupDialog(2));
        look3.addActionListener(e -> showLookupDialog(3));
        
        lookKasir.addActionListener(e -> showLookupKasirDialog());
    }

    private void showLookupDialog(int itemNumber) {
        try {
            // Mendapatkan koneksi ke database
            Connection conn = koneksi.getKoneksi();

            // Query untuk mengambil data barang
            String sql = "SELECT kdBarang, namaBarang, harga FROM barang";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Membuat model tabel untuk tampilan lookup
            DefaultTableModel model = new DefaultTableModel(new String[]{"Kode Barang", "Nama Barang", "Harga"}, 0);
            while (resultSet.next()) {
                model.addRow(new Object[]{
                    resultSet.getInt("kdBarang"),
                    resultSet.getString("namaBarang"),
                    resultSet.getDouble("harga")
                });
            }

            // Menampilkan dialog lookup
            JTable tblLookup = new JTable(model);
            JOptionPane.showMessageDialog(this, new JScrollPane(tblLookup), "Pilih Barang", JOptionPane.PLAIN_MESSAGE);

            // Mendapatkan data dari baris yang dipilih
            int selectedRow = tblLookup.getSelectedRow();
            if (selectedRow != -1) {
                String kodeBarang = tblLookup.getValueAt(selectedRow, 0).toString();
                String namaBarang = tblLookup.getValueAt(selectedRow, 1).toString();
                String hargaBarang = tblLookup.getValueAt(selectedRow, 2).toString();

                // Mengisi field berdasarkan item yang dipilih
                if (itemNumber == 1) {
                    menu1.setText(namaBarang);
                    hrgMenu1.setText(hargaBarang);
                } else if (itemNumber == 2) {
                    menu2.setText(namaBarang);
                    hrgMenu2.setText(hargaBarang);
                } else if (itemNumber == 3) {
                    menu3.setText(namaBarang);
                    hrgMenu3.setText(hargaBarang);
                }
                hitungTotalHarga();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data barang: " + ex.getMessage());
        }
    }
    
private void showLookupKasirDialog() {
        try {
            // Mendapatkan koneksi ke database
            Connection conn = koneksi.getKoneksi();

            // Query untuk mengambil data kasir
            String sql = "SELECT ID, namaUser FROM user";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Membuat model tabel untuk tampilan lookup
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID Kasir", "Nama Kasir"}, 0);
            while (resultSet.next()) {
                model.addRow(new Object[]{
                    resultSet.getInt("ID"),
                    resultSet.getString("namaUser")
                });
            }

            // Menampilkan dialog lookup
            JTable tblLookup = new JTable(model);
            JOptionPane.showMessageDialog(this, new JScrollPane(tblLookup), "Pilih Kasir", JOptionPane.PLAIN_MESSAGE);

            // Mendapatkan data dari baris yang dipilih
            int selectedRow = tblLookup.getSelectedRow();
            if (selectedRow != -1) {
                String namaKasir = tblLookup.getValueAt(selectedRow, 1).toString();
                namaKasirField.setText(namaKasir);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data kasir: " + ex.getMessage());
        }
    }
   
    
private void hitungTotalHarga() {
    double jumlah1, tHarga1, harga1;
    if(hrgMenu1.getText().trim().equals("")) harga1=0;
    else harga1 = Double.parseDouble(hrgMenu1.getText().trim());
    if(jml1.getText().trim().equals("")) jumlah1=0;
    else jumlah1 = Double.parseDouble(jml1.getText().trim());
    tHarga1 = jumlah1 * harga1;
    tHargaItem1Field.setText(String.valueOf(tHarga1));
    System.out.println(tHarga1);
    
    double jumlah2, tHarga2, harga2;
    if(hrgMenu2.getText().trim().equals("")) harga2=0;
    else harga2 = Double.parseDouble(hrgMenu2.getText().trim());
    if(jml2.getText().trim().equals("")) jumlah2=0;
    else jumlah2 = Double.parseDouble(jml2.getText().trim());
    tHarga2 = jumlah2 * harga2;
    tHargaItem2Field.setText(String.valueOf(tHarga2));
    System.out.println(tHarga2);
    
    double jumlah3, tHarga3, harga3;
    if(hrgMenu3.getText().trim().equals("")) harga3=0;
    else harga3 = Double.parseDouble(hrgMenu3.getText().trim());
    if(jml3.getText().trim().equals("")) jumlah3=0;
    else jumlah3 = Double.parseDouble(jml3.getText().trim());
    tHarga3 = jumlah3 * harga3;
    tHargaItem3Field.setText(String.valueOf(tHarga3));
    System.out.println(tHarga3);
    
    double totalHargaAkhir = tHarga1 + tHarga2 + tHarga3;
    totalHarga.setText(String.valueOf(totalHargaAkhir));
    System.out.println(totalHargaAkhir);
    
    
//    try {
        
//        // Pastikan semua field memiliki nilai
//        double harga1 = hrgMenu1.getText().trim().isEmpty() ? 0 : Double.parseDouble(hrgMenu1.getText().trim());
//        System.out.println(harga1);
//        double jumlah1 = jml1.getText().trim().isEmpty() ? 0 : Double.parseDouble(jml1.getText().trim());
//        double totalItem1 = harga1 * jumlah1;
//        tHargaItem1Field.setText(String.valueOf(totalItem1)); // Update total item 1
//
//        double harga2 = hrgMenu2.getText().trim().isEmpty() ? 0 : Double.parseDouble(hrgMenu2.getText().trim());
//        double jumlah2 = jml2.getText().trim().isEmpty() ? 0 : Double.parseDouble(jml2.getText().trim());
//        double totalItem2 = harga2 * jumlah2;
//        tHargaItem2Field.setText(String.valueOf(totalItem2)); // Update total item 2
//
//        double harga3 = hrgMenu3.getText().trim().isEmpty() ? 0 : Double.parseDouble(hrgMenu3.getText().trim());
//        double jumlah3 = jml3.getText().trim().isEmpty() ? 0 : Double.parseDouble(jml3.getText().trim());
//        double totalItem3 = harga3 * jumlah3;
//        tHargaItem3Field.setText(String.valueOf(totalItem3)); // Update total item 3
//
//        // Menghitung total harga keseluruhan
//        double totalHargaAkhir = totalItem1 + totalItem2 + totalItem3;
//        totalHarga.setText(String.valueOf(totalHargaAkhir));
//
//    } catch (NumberFormatException e) {
//        JOptionPane.showMessageDialog(this, "Masukkan angka yang valid pada jumlah dan harga!", "Error", JOptionPane.ERROR_MESSAGE);
//        totalHarga.setText("0");
//    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        noOrder = new javax.swing.JTextField();
        namaKasirField = new javax.swing.JTextField();
        menu1 = new javax.swing.JTextField();
        menu2 = new javax.swing.JTextField();
        menu3 = new javax.swing.JTextField();
        totalHarga = new javax.swing.JTextField();
        look1 = new javax.swing.JButton();
        look2 = new javax.swing.JButton();
        look3 = new javax.swing.JButton();
        hrgMenu1 = new javax.swing.JTextField();
        hrgMenu2 = new javax.swing.JTextField();
        hrgMenu3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cmbMetod = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblpesanan = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tglOrder = new com.toedter.calendar.JDateChooser();
        jml1 = new javax.swing.JTextField();
        jml3 = new javax.swing.JTextField();
        jml2 = new javax.swing.JTextField();
        lookKasir = new javax.swing.JButton();
        tHargaItem1Field = new javax.swing.JTextField();
        tHargaItem2Field = new javax.swing.JTextField();
        tHargaItem3Field = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 102, 0));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel1.setText("CHECKOUT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("No Order");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Kasir");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Item 1");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Item 2");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Item 3");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Total Harga");

        namaKasirField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaKasirFieldActionPerformed(evt);
            }
        });

        menu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu3ActionPerformed(evt);
            }
        });

        look1.setText("Look Up");
        look1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                look1ActionPerformed(evt);
            }
        });

        look2.setText("Look Up");

        look3.setText("Look Up");
        look3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                look3ActionPerformed(evt);
            }
        });

        hrgMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hrgMenu1ActionPerformed(evt);
            }
        });

        hrgMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hrgMenu3ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Metode Pembayaran");

        cmbMetod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Debit/Kredit", "QRIS" }));

        tblpesanan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblpesanan);

        btnSave.setBackground(new java.awt.Color(51, 255, 51));
        btnSave.setForeground(new java.awt.Color(240, 240, 240));
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(0, 0, 0));
        btnDelete.setForeground(new java.awt.Color(240, 240, 240));
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Tgl Order");

        jml1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jml1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jml1KeyTyped(evt);
            }
        });

        jml3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jml3ActionPerformed(evt);
            }
        });
        jml3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jml3KeyReleased(evt);
            }
        });

        jml2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jml2KeyReleased(evt);
            }
        });

        lookKasir.setText("Look Up");
        lookKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookKasirActionPerformed(evt);
            }
        });

        tHargaItem1Field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tHargaItem1FieldKeyReleased(evt);
            }
        });

        tHargaItem2Field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tHargaItem2FieldKeyReleased(evt);
            }
        });

        tHargaItem3Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tHargaItem3FieldActionPerformed(evt);
            }
        });
        tHargaItem3Field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tHargaItem3FieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5))))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hrgMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel10)
                                        .addGap(34, 34, 34))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(look2))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(namaKasirField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lookKasir))
                                            .addComponent(noOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(hrgMenu3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(look1))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(menu3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(hrgMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(look3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jml3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(tHargaItem3Field, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jml2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(tHargaItem2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jml1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(tHargaItem1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(23, 23, Short.MAX_VALUE)))
                                .addComponent(tglOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(32, 32, 32)
                                    .addComponent(totalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(65, 65, 65)
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbMetod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(tglOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(look1)
                            .addComponent(jml1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(tHargaItem1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hrgMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(look2)
                            .addComponent(jml2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(tHargaItem2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hrgMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(menu3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(look3)
                            .addComponent(jml3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(tHargaItem3Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hrgMenu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(noOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(namaKasirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lookKasir))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMetod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(totalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnReset))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hrgMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hrgMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hrgMenu1ActionPerformed

    private void jml3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jml3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jml3ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

    }//GEN-LAST:event_btnSaveActionPerformed

    private void look3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_look3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_look3ActionPerformed

    private void look1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_look1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_look1ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        noOrder.setText("");               // Reset the JTextField (noOrder)
        tglOrder.setDate(null);            // Reset the JDateChooser (tglOrder)
        namaKasirField.setText("");                // Reset the JTextField (kasir)
        menu1.setText("");                // Reset the JTextField (menu1)
        hrgMenu1.setText("");             // Reset the JTextField (jmlMenu1)
        menu2.setText("");                // Reset the JTextField (menu2)
        hrgMenu2.setText("");             // Reset the JTextField (jmlMenu2)
        menu3.setText("");                // Reset the JTextField (menu3)
        hrgMenu3.setText("");             // Reset the JTextField (jmlMenu3)
        totalHarga.setText("");        // Reset the JTextField (txtTotalHarga)
        jml1.setText("");
        tHargaItem1Field.setText("");
        jml2.setText("");
        tHargaItem2Field.setText("");
        jml3.setText("");
        tHargaItem3Field.setText("");
        cmbMetod.setSelectedIndex(0);     // Reset the JComboBox (cmbMetod) to the default index

    }//GEN-LAST:event_btnResetActionPerformed

    private void lookKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookKasirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lookKasirActionPerformed

    private void menu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu3ActionPerformed

    private void tHargaItem3FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tHargaItem3FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tHargaItem3FieldActionPerformed

    private void jml1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jml1KeyTyped
        // TODO add your handling code h
    }//GEN-LAST:event_jml1KeyTyped

    private void jml1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jml1KeyReleased
        // TODO add your handling code here:
        hitungTotalHarga();
    }//GEN-LAST:event_jml1KeyReleased

    private void jml2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jml2KeyReleased
        // TODO add your handling code here:
        hitungTotalHarga();
    }//GEN-LAST:event_jml2KeyReleased

    private void jml3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jml3KeyReleased
        // TODO add your handling code here:
        hitungTotalHarga();
    }//GEN-LAST:event_jml3KeyReleased

    private void tHargaItem1FieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tHargaItem1FieldKeyReleased
        // TODO add your handling code here:
        hitungTotalHarga();
    }//GEN-LAST:event_tHargaItem1FieldKeyReleased

    private void tHargaItem2FieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tHargaItem2FieldKeyReleased
        // TODO add your handling code here:\
        hitungTotalHarga();
    }//GEN-LAST:event_tHargaItem2FieldKeyReleased

    private void tHargaItem3FieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tHargaItem3FieldKeyReleased
        // TODO add your handling code here:
        hitungTotalHarga();
    }//GEN-LAST:event_tHargaItem3FieldKeyReleased

    private void namaKasirFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaKasirFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaKasirFieldActionPerformed

    private void hrgMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hrgMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hrgMenu3ActionPerformed

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
            java.util.logging.Logger.getLogger(CashierPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashierPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashierPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashierPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CashierPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbMetod;
    public static javax.swing.JTextField hrgMenu1;
    public static javax.swing.JTextField hrgMenu2;
    public static javax.swing.JTextField hrgMenu3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jml1;
    private javax.swing.JTextField jml2;
    private javax.swing.JTextField jml3;
    public static javax.swing.JButton look1;
    private javax.swing.JButton look2;
    public static javax.swing.JButton look3;
    private javax.swing.JButton lookKasir;
    public static javax.swing.JTextField menu1;
    public static javax.swing.JTextField menu2;
    public static javax.swing.JTextField menu3;
    private javax.swing.JTextField namaKasirField;
    private javax.swing.JTextField noOrder;
    private javax.swing.JTextField tHargaItem1Field;
    private javax.swing.JTextField tHargaItem2Field;
    private javax.swing.JTextField tHargaItem3Field;
    private javax.swing.JTable tblpesanan;
    private com.toedter.calendar.JDateChooser tglOrder;
    private javax.swing.JTextField totalHarga;
    // End of variables declaration//GEN-END:variables
}
