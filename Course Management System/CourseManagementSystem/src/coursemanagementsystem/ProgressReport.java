/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.*;
/**
 *
 * @author CPA
 */
public class ProgressReport extends javax.swing.JFrame {

    /**
     * Creates new form ProgressReport
     */
    public ProgressReport() {
        initComponents();
    }

    private void LoadCourse()
    {
        ArrayList<Course> clist=ServiceClass.LoadActiveCourse();
        for(Course c: clist){
           this.jComboBox1.addItem(c.CourseName); 
        }
    }
    private void LoadLevel(String coursename)
    {
        this.jComboBox2.removeAllItems();
        ArrayList<Level> list=ServiceClass.GetLevelsByCourseName(coursename);
        for(Level l: list){
           this.jComboBox2.addItem(l.LevelName); 
        }        
    }
    private void LoadStudent(String coursename,String levelname)
    {
        this.jComboBox3.removeAllItems();
        try (Connection con = DriverManager.getConnection(MainJFrame.connectionUrl);Statement st= con.createStatement();) 
        {
            ResultSet rset = st.executeQuery("select StdID from Student Where EnrolledLevelID=(Select LevelID from LevelList Where LevelName like '"+levelname+"' And CourseID=(Select CID From CourseList Where coursename like '"+coursename+"')) order by StdID;");
            while (rset.next()) {
                String stdid=rset.getString("StdID");
                this.jComboBox3.addItem(stdid);                                
            }                         
        }        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Error while loading student."+ex); 
        }
    }
    private void LoadModuleMark(String coursename,String levelname,String stdid)
    {
        int totalcount=0,passcount=0;
        String remarks="";
        try (Connection con = DriverManager.getConnection(MainJFrame.connectionUrl);Statement st= con.createStatement();) 
        {
            ResultSet rset = st.executeQuery("select ModuleList.MID,MName,Mark  from ModuleList inner join MarkRecord on ModuleList.MID=MarkRecord.MID Where StdID="+stdid+" And LevelID=(Select LevelID from LevelList Where LevelName like '"+levelname+"' And CourseID=(Select CID From CourseList Where coursename like '"+coursename+"')) order by MID;");
            DefaultTableModel model = new DefaultTableModel(new String[]{"Module ID", "Module Name","Obtained Mark","Obtained Grade"},0);
            while (rset.next()) {
                int mid=rset.getInt("MID");
                String mname=rset.getString("MName");
                int mark=rset.getInt("Mark");
                String grade;
                if(mark>=80)
                    grade="A";
                else if(mark>=60)
                    grade="B";
                else if(mark>=50)
                    grade="C";
                else if(mark>=40)
                    grade="D";
                else
                    grade="F";
                model.addRow(new Object[]{mid,mname,mark,grade});
                if(mark>=40){
                  passcount++;
                }
                totalcount++;                
            } 
            this.jTable1.setModel(model);  
            if(passcount>=totalcount/2)//as there are exactly 4 modules in each level one has to pass minimum of 2 modules
            {
                remarks="Remarks: Student can be upgraded to higher level study.";
            }
            else{
               remarks="Remarks: Student needs to repeat the same level for better result";
            }
            this.jLabel4.setText(remarks);
        }        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Error while loading student mark."+ex); 
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Course Name:");

        jLabel3.setText("Student ID:");

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jButton3.setText("Export To Text File");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Level Name:");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module Name", "Obtained Mark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 220, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        LoadCourse();
    }//GEN-LAST:event_formWindowOpened

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        LoadLevel(this.jComboBox1.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        if(this.jComboBox1.getSelectedIndex()>=0 && this.jComboBox2.getSelectedIndex()>=0 && this.jComboBox3.getSelectedIndex()>=0)
        {
           LoadModuleMark(this.jComboBox1.getSelectedItem().toString(),this.jComboBox2.getSelectedItem().toString(),this.jComboBox3.getSelectedItem().toString());
           this.jLabel5.setText(getStudentName(Integer.parseInt(this.jComboBox3.getSelectedItem().toString())));
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed
    private String getStudentName(int id){
        try (Connection con = DriverManager.getConnection(MainJFrame.connectionUrl);Statement st= con.createStatement();) 
        {
            ResultSet rset = st.executeQuery("select StdName From Student where StdID="+id);
            if (rset.next()) {
                return rset.getString("StdName");                
            }                                         
        }        
        catch(Exception ex)
        {           
        }
        return "";
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try (FileWriter f = new FileWriter(this.jComboBox3.getSelectedItem().toString()+".txt",false); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b);) 
        { 
            p.println("Course Name: "+this.jComboBox1.getSelectedItem().toString());
            p.println("Level Name: "+this.jComboBox2.getSelectedItem().toString());
            p.println("Student ID: "+this.jComboBox3.getSelectedItem().toString());
            p.println("Name Of Student: "+this.jLabel5.getText());
            p.println("                               Progress Report                         ");
            p.println("============================================================================================");
            p.println("Module ID\tModule Name\tObtained Mark\tObtained Grade");
            int modulecount = (int)this.jTable1.getRowCount();
            for(int i=0;i<modulecount;i++)
            {
               p.println(this.jTable1.getModel().getValueAt(i, 0).toString()+"\t\t"+this.jTable1.getModel().getValueAt(i, 1).toString()+"\t\t"+this.jTable1.getModel().getValueAt(i, 2).toString()+"\t\t"+this.jTable1.getModel().getValueAt(i, 3).toString());
            }
            p.println("============================================================================================");
            p.println("Remarks: "+this.jLabel4.getText());
            p.println("============================================================================================");
            JOptionPane.showMessageDialog(new JFrame(),"Report exported successfully.");
        }
        catch(Exception e)
        {
          System.out.println(e.toString());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if(this.jComboBox1.getSelectedIndex()>=0 && this.jComboBox2.getSelectedIndex()>=0)
        LoadStudent(this.jComboBox1.getSelectedItem().toString(),this.jComboBox2.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox2ActionPerformed

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
            java.util.logging.Logger.getLogger(ProgressReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgressReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgressReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgressReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProgressReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
