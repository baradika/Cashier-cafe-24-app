/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cCafeHotel;

/**
 *
 * @author fachri
 */
public class lookupView extends javax.swing.JFrame {

    /**
     * Creates new form lookupView
     */
    public lookupView() {
        initComponents();
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
        lblLookup = new javax.swing.JLabel();
        scrLookup = new javax.swing.JScrollPane();
        tblLookup = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        lblLookup.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLookup.setForeground(new java.awt.Color(255, 255, 255));
        lblLookup.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLookup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblLookup)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tblLookup.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLookup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLookupMouseClicked(evt);
            }
        });
        scrLookup.setViewportView(tblLookup);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrLookup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrLookup, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblLookupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLookupMouseClicked
        // TODO add your handling code here:
        switch (lblLookup.getText()) {
    case "Menu1":
        // Logika untuk Menu1
        CashierPage.menu1.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 0).toString());
        CashierPage.hrgMenu1.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 1).toString());
        break;
    case "Menu2":
        // Logika untuk Menu2
        CashierPage.menu2.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 0).toString());
        CashierPage.hrgMenu2.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 1).toString());
        break;
    case "Menu3":
        // Logika untuk Menu3
        CashierPage.menu3.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 0).toString());
        CashierPage.hrgMenu3.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 1).toString());
        break;
    case "nmBarang":
        // Logika untuk 
        KichenPage.kdBarang.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 0).toString());
        KichenPage.nmBarang.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 1).toString());
        break;
    
    default:
        System.out.println("Unknown lookup type: " + lblLookup.getText());
        break;
//            // Jika lookup Siswa, isi field NIS dan Nama Siswa
//            Cashie.nis.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 0).toString());
//            nilaiView.namaSiswa.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 1).toString());
//            break;
//            case "Data Mapel":
//            // Jika lookup Siswa, isi field NIS dan Nama Siswa
//            nilaiView.kdMapel.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 0).toString());
//            nilaiView.mapel.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 1).toString());
//            break;
//            case "Data Guru":
//            // Jika lookup Guru, isi field Kode Guru dan Nama Guru
//            nilaiView.kdGuru.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 0).toString());
//            nilaiView.namGuru.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(), 1).toString());
//            break;
//            case "Data Siswa BK":
//            cabekaView.nisTxt.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(),0).toString());
//            cabekaView.namaSiswaTxt.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(),1).toString());
//            break;
//            case "Data Guru BK":
//            cabekaView.nikTxt.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(),0).toString());
//            cabekaView.namaGTxt.setText(tblLookup.getValueAt(tblLookup.getSelectedRow(),1).toString());
//            break;
//            default:
//            break;
        }
        // Tutup jendela lookup setelah memilih data
        dispose();
    }//GEN-LAST:event_tblLookupMouseClicked

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
            java.util.logging.Logger.getLogger(lookupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lookupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lookupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lookupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lookupView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JLabel lblLookup;
    public static javax.swing.JScrollPane scrLookup;
    public static javax.swing.JTable tblLookup;
    // End of variables declaration//GEN-END:variables
}
