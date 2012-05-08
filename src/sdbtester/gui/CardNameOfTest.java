/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester.gui;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.sound.midi.SysexMessage;
import javax.swing.*;
import org.apache.log4j.Logger;
import sdbtester.TestCaseHelper;
import sdbtester.validators.DbTestValidator;

/**
 *
 * @author boris
 */
public class CardNameOfTest extends javax.swing.JPanel {

    private static TestCaseHelper appSettings;
    private DefaultComboBoxModel comboModel_ExistingTests;
    private static Logger logger = Logger.getLogger(CardNameOfTest.class);
    private DbTestValidator validator = new DbTestValidator();
    private String TMP_TESTCASE_NAME = "";
    private String TMP_TESTCASE_DESCRIPTION = "";

    /**
     * Creates new form CardNameOfTest
     */
    public CardNameOfTest() {
        initComponents();
        appSettings = TestCaseHelper.getInstance();
        ComboBoxDbTypes.removeAllItems();
        ComboBoxDbTypes.addItem(appSettings.DB_TYPE_MYSQL);
        ComboBoxDbTypes.addItem(appSettings.DB_TYPE_MONGO);

        //select template
        comboModel_ExistingTests = new DefaultComboBoxModel();
        LoadExistingTestCases(appSettings.DB_TYPE_MYSQL);
        comboBoxExistingTests.setModel(comboModel_ExistingTests);
        //buttons
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ComboBoxDbTypes = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textNameOfTestCase = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        area_DescriptionOfTestCase = new javax.swing.JTextArea();
        comboBoxExistingTests = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Создание теста");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ComboBoxDbTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBoxDbTypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxDbTypesActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Тип БД:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Название:");

        textNameOfTestCase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textNameOfTestCaseKeyReleased(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Описание:");

        area_DescriptionOfTestCase.setColumns(20);
        area_DescriptionOfTestCase.setRows(5);
        jScrollPane2.setViewportView(area_DescriptionOfTestCase);

        comboBoxExistingTests.setMaximumRowCount(10000);
        comboBoxExistingTests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxExistingTestsActionPerformed(evt);
            }
        });

        jLabel6.setText("Выбрать тест:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxExistingTests, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxDbTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 612, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNameOfTestCase)
                            .addComponent(jScrollPane2))
                        .addContainerGap())))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxDbTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxExistingTests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textNameOfTestCase, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 167, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ComboBoxDbTypes, textNameOfTestCase});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxExistingTestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxExistingTestsActionPerformed
        if ((comboModel_ExistingTests.getSize() > 0) && comboModel_ExistingTests.getSelectedItem().equals(comboModel_ExistingTests.getElementAt(0))) {
            textNameOfTestCase.setText(TMP_TESTCASE_NAME);
            area_DescriptionOfTestCase.setText(TMP_TESTCASE_DESCRIPTION);
            textNameOfTestCase.setEnabled(true);
        } else if (comboModel_ExistingTests.getSize() > 0) {
            TMP_TESTCASE_NAME = textNameOfTestCase.getText();
            TMP_TESTCASE_DESCRIPTION = area_DescriptionOfTestCase.getText();
            appSettings.loadTestFromTemplate(comboModel_ExistingTests.getSelectedItem().toString(), ComboBoxDbTypes.getSelectedItem().toString());
            textNameOfTestCase.setEnabled(false);
            textNameOfTestCase.setText(comboModel_ExistingTests.getSelectedItem().toString());
            area_DescriptionOfTestCase.setText(appSettings.getTestCaseDescription());
        }
    }//GEN-LAST:event_comboBoxExistingTestsActionPerformed

    private void textNameOfTestCaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNameOfTestCaseKeyReleased
        if (textNameOfTestCase.getText().length() > 0 && !validator.isEnOrDigit(textNameOfTestCase.getText())) {
            textNameOfTestCase.setText(textNameOfTestCase.getText().substring(0, textNameOfTestCase.getText().length() - 1));
        }
    }//GEN-LAST:event_textNameOfTestCaseKeyReleased

    private void ComboBoxDbTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxDbTypesActionPerformed
        if (ComboBoxDbTypes.getSelectedItem() != null && ComboBoxDbTypes.getSelectedIndex() != -1) {
            LoadExistingTestCases(ComboBoxDbTypes.getSelectedItem().toString());
        }
    }//GEN-LAST:event_ComboBoxDbTypesActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBoxDbTypes;
    private javax.swing.JTextArea area_DescriptionOfTestCase;
    private javax.swing.JComboBox comboBoxExistingTests;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField textNameOfTestCase;
    // End of variables declaration//GEN-END:variables

    private boolean LoadExistingTestCases(String dbType) {
        if (comboModel_ExistingTests == null) {
            return false;
        }
        try {
            File f = new File(appSettings.TEST_CASES_MAIN_PATH + dbType);
            if (comboModel_ExistingTests.getSize() > 0) {
                comboModel_ExistingTests.removeAllElements();
            }
            comboModel_ExistingTests.addElement("-----Создать новый-----");
            if (f.isDirectory()) {
                File[] testCases = f.listFiles();
                for (int i = 0; i < testCases.length; ++i) {
                    File cur = new File(testCases[i].getPath());
                    if (cur.isDirectory() && (new File(cur.getPath() + "/" + appSettings.SETTINGS_TEST_CASE_PROP_FILE).isFile())) {
                        comboModel_ExistingTests.addElement(testCases[i].getName());
                    }
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public JComboBox getComboBoxDbTypes() {
        return ComboBoxDbTypes;
    }

    public JTextArea getArea_DescriptionOfTestCase() {
        return area_DescriptionOfTestCase;
    }

    public DefaultComboBoxModel getComboModel_ExistingTests() {
        return comboModel_ExistingTests;
    }

    public JTextField getJtextNameOfTestCase() {
        return textNameOfTestCase;
    }

    public JComboBox getComboBoxExistingTests() {
        return comboBoxExistingTests;
    }
}
