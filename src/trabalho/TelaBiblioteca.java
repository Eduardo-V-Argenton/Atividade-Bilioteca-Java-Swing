/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package trabalho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduardo
 */
public class TelaBiblioteca extends javax.swing.JFrame {

    /**
     * Creates new form TelaBiblioteca
     */
    Livro livro = new Livro();
    
    public TelaBiblioteca() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadTable();
    }
    
    private void executeBuscar(){
        List<String> searchParameters = getTextFieldsNotNull();
        if(checkIfTheCorrectNumberOfParameterHasBeenSelected(searchParameters))
            loadSearchResults(searchParameters);
    }
    
    private void executeInserir(){
        List<String> insertParameters = createInsertParametersList();
        livro.executeInsert(this, insertParameters);
        loadTable();
        cleanTable();
    }
    
    private void executeLimpar(){
        cleanTable();
    }
    
    private void executeRemover(){
        livro.executeDelete(this, "CodigoLivro", jTextField1.getText());
        loadTable();
        cleanTable();
    }
    
    private void executeAlterar(){
        livro.executeUpdate(this, createUpdateParametersList(), "CodigoLivro", 
                jTextField1.getText());
        loadTable();
        cleanTable();
    }
    
    private void executeSelectWithClick(){
        setTextFields(jTable1.getSelectedRow());
        enableAndDisableButtons(new ArrayList<>(Arrays.asList(2,3,4)));
        jTextField1.setEditable(false);
    }
    
    private void loadTable(){
        ((DefaultTableModel)jTable1.getModel()).setRowCount(0);
        livro.printTable(livro.executeSelect(this), jTable1, this);    
    }
    
    private void loadTable(String column, String search){
        ((DefaultTableModel)jTable1.getModel()).setRowCount(0);
        livro.printTable(livro.executeSelect(this, column, search), jTable1, 
                this);    
    }
    
    private List<String> getTextFieldsNotNull(){
        List<String> textFieldsNotNull = new ArrayList<>();
        
        if(!jTextField2.getText().isBlank()){
            textFieldsNotNull.add("Nome");
            textFieldsNotNull.add(jTextField2.getText());
            textFieldsNotNull.add("1");
        }
        
        if(!jTextField3.getText().isBlank()){
            textFieldsNotNull.add("Escritor");
            textFieldsNotNull.add(jTextField3.getText());
            textFieldsNotNull.add("2");
        }
        
        if(!jTextField4.getText().isBlank()){
            textFieldsNotNull.add("Editora");
            textFieldsNotNull.add(jTextField4.getText());
            textFieldsNotNull.add("3");
        }
        if(!jTextField5.getText().isBlank()){
            textFieldsNotNull.add("AnoLancamento");
            textFieldsNotNull.add(jTextField5.getText());
            textFieldsNotNull.add("4");
        }
        
        if(!jTextField6.getText().isBlank()){
            textFieldsNotNull.add("Genero");
            textFieldsNotNull.add(jTextField6.getText());
            textFieldsNotNull.add("5");
        }
        
        if(!jTextField7.getText().isBlank()){
            textFieldsNotNull.add("Localizacao");
            textFieldsNotNull.add(jTextField7.getText());
            textFieldsNotNull.add("6");
        }
        
        return textFieldsNotNull;
    }
    
    private boolean checkIfTheCorrectNumberOfParameterHasBeenSelected(
            List<String> parameters){
        if(parameters.size() > 3){
            JOptionPaneDialogMessages.getInstance().errorMessage(this, 
                    "Insira apenas um parâmetro");
            return false;
        }
        else if(parameters.isEmpty()){
            JOptionPaneDialogMessages.getInstance().errorMessage(this, 
                    "Insira um parâmetro");
            return false;
        }
        else{
            return true;
        }
    }
    
    private void loadSearchResults(List<String> searchParameters){
        loadTable(searchParameters.get(0), searchParameters.get(1));
        if(jTable1.getRowCount() > 0){
            jTable1.setRowSelectionInterval(0, 0);
            setTextFields(jTable1.getSelectedRow());
            enableAndDisableButtons(new ArrayList<>(Arrays.asList(2,3,4)));
            jTextField1.setEditable(false);
        }
        else{
            JOptionPaneDialogMessages.getInstance().errorMessage(this, 
                    "Nenhum livro com o parâmetro indicado foi encontrado");
            cleanTable();
        }
    }
    
    private void cleanTable(){
        jTable1.clearSelection();
        setTextFields();
        enableAndDisableButtons(new ArrayList<>(Arrays.asList(1,2,5)));
        loadTable();
    }
    
    private void enableAndDisableButtons(List<Integer> buttonsToEnable){
        
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
        
        if(buttonsToEnable.contains(1))
            jButton1.setEnabled(true);
        if(buttonsToEnable.contains(2))
            jButton2.setEnabled(true);
        if(buttonsToEnable.contains(3))
            jButton3.setEnabled(true);
        if(buttonsToEnable.contains(4))
            jButton4.setEnabled(true);
        if(buttonsToEnable.contains(5))
            jButton5.setEnabled(true);

    }
    
    private void setTextFields(Integer i){
        jTextField1.setText(jTable1.getValueAt(i, 0).toString());
        jTextField2.setText(jTable1.getValueAt(i, 1).toString());
        jTextField3.setText(jTable1.getValueAt(i, 2).toString());
        jTextField4.setText(jTable1.getValueAt(i, 3).toString());
        jTextField5.setText(jTable1.getValueAt(i, 4).toString());
        jTextField6.setText(jTable1.getValueAt(i, 5).toString());
        jTextField7.setText(jTable1.getValueAt(i, 6).toString());
    }
    
    private void setTextFields(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
    }
    
    private List<String> createInsertParametersList(){
        List<String> textFieldsNotNull = getTextFieldsNotNull();
        List<String> insertParameters = new ArrayList<>();
        for(Integer i = 1; i < textFieldsNotNull.size(); i += 3){
            insertParameters.add(textFieldsNotNull.get(i));
        }
        return insertParameters;
    }
    
    private List<String> createUpdateParametersList(){
        List<String> textFieldsNotNull = getTextFieldsNotNull();
        List<String> updateParameters = new ArrayList<>();
        for(Integer i = 0, x = 0; i < textFieldsNotNull.size(); i ++){
            if((i > 0) && (2+(3*x) == i)){
                x++;
                continue;
            }
            updateParameters.add(textFieldsNotNull.get(i));
        }
        System.out.println(updateParameters);
        return updateParameters;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Código Livro:");
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 18, -1, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nome:");
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 55, -1, -1));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 52, -1, -1));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 84, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Escritor:");
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 92, -1, -1));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Editora:");
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 129, -1, -1));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Ano Lançamento:");
        jLabel7.setOpaque(true);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 166, -1, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Gênero:");
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 203, -1, -1));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Localização:");
        jLabel9.setOpaque(true);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 240, -1, -1));

        jTextField1.setEditable(false);
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 15, 150, -1));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 52, 150, -1));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 15, -1, -1));

        jButton2.setText("Limpar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 15, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Livro", "Nome", "Escritor", "Editora", "AnoLancamento", "Genero", "Localizacao"
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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 52, 786, 206));

        jButton3.setText("Alterar");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(976, 15, -1, -1));

        jButton4.setText("Remover");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 15, -1, -1));

        jButton5.setText("Inserir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 15, -1, -1));
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 89, 150, -1));

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 126, 150, -1));
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 163, 150, -1));

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 200, 150, -1));
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 237, 150, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalho/imagens/15b709f745817a1534107508.jpg"))); // NOI18N
        jLabel10.setText("jLabel10");
        jLabel10.setPreferredSize(new java.awt.Dimension(750, 750));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -610, 1180, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        executeLimpar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        executeRemover();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        executeBuscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        executeInserir();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        executeAlterar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            executeSelectWithClick();
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(TelaBiblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaBiblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaBiblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaBiblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaBiblioteca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
