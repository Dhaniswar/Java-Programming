/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AddStudentRecord extends javax.swing.JFrame {

    public StudentList stdList;
    public String SelectedCourse;
    public String SelectedLevel;
    private int ElectiveCount=0;
    public AddStudentRecord() {
        initComponents();
    }
    public void LoadModule(String coursename,String levelname)
    {
        ElectiveCount=0;
        try (Connection con = DriverManager.getConnection(MainJFrame.connectionUrl);Statement st= con.createStatement();) 
        {
            ResultSet rset = st.executeQuery("select MID,MName,IsElective,(Select IName from Instructor Where ID=ModuleList.InstructorID) as IName from ModuleList Where LevelID=(Select LevelID from LevelList Where LevelName like '"+levelname+"' And CourseID=(Select CID From CourseList Where coursename like '"+coursename+"')) order by MID;");                        
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Module Name","Is Elective?","Instructor Name"},0);
            while (rset.next()) {
                int mid=rset.getInt("MID");
                String mname=rset.getString("MName");
                boolean iselective=rset.getBoolean("IsElective");
                String iname=rset.getString("IName");
                model.addRow(new Object[]{mid,mname,iselective,iname});                
                if(iselective)
                    ElectiveCount++;
            } 
            this.jTable1.setModel(model);                            
        }        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Error while loading module."+ex); 
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(400, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module ID", "Module Name", "Is Elective", "Instructor Name", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Name of Student: ");

        jButton2.setText("Enroll Student");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Remove Selected");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if("".equals(this.jTextField1.getText()))
        {
            JOptionPane.showMessageDialog(new JFrame(),"Student name cannot be left blank.");
            return;
        }
        try (Connection con = DriverManager.getConnection(MainJFrame.connectionUrl);)
        {
            String SQL = "Insert into Student(StdID,StdName,EnrolledLevelID) values((Select (Case When (Select Max(StdID) From Student) Is Null Then 0 Else (Select Max(StdID) From Student) End)+1),?,(Select LevelID from LevelList Where LevelName Like '"+SelectedLevel+"' And CourseID=(Select CID from courseList where Coursename like '"+SelectedCourse+"')));";
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setString (1, this.jTextField1.getText());
            // execute the preparedstatement
            preparedStmt.execute();
            
            int modulecount = (int)this.jTable1.getRowCount();
            for(int i=0;i<modulecount;i++)
            {
                int moduleID=Integer.parseInt(this.jTable1.getModel().getValueAt(i, 0).toString());
                SQL = "Insert into MarkRecord(Mark,StdID,MID) values(0,(Select Max(StdID) From Student),?);";
                preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setInt(1,moduleID);                  
                preparedStmt.execute(); 
            }            
            JOptionPane.showMessageDialog(new JFrame(),"Student added successfully.");
            this.stdList.LoadStudent(SelectedCourse,SelectedLevel);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Error while adding student."+ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean isElective = (boolean)this.jTable1.getModel().getValueAt(this.jTable1.getSelectedRow(), 2);
        if(isElective){
           if(ElectiveCount>2)
           {
                DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
                model.removeRow(this.jTable1.getSelectedRow());
                ElectiveCount--;
           } 
           else
           {
           JOptionPane.showMessageDialog(new JFrame(),"You must have 2 elective subjects.");
           }
        }
        else{
           JOptionPane.showMessageDialog(new JFrame(),"Compulsory subject cannot be removed.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AddStudentRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddStudentRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddStudentRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddStudentRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddStudentRecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private class jButton4 {

        public jButton4() {
        }
    }
}
