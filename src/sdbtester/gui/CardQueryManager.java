/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester.gui;

import java.awt.Component;
import javax.swing.*;
import org.apache.log4j.Logger;
import sdbtester.STestCaseHelper;
import sdbtester.factory_beans.SDataBaseMysql;

/**
 *
 * @author boris
 */
public class CardQueryManager extends javax.swing.JPanel {

//    private DefaultComboBoxModel queryListModel;
    private STestCaseHelper appSettings = null;
    private static Logger logger = Logger.getLogger(CardQueryManager.class);
    private SDataBaseMysql mysqlDbHelper = new SDataBaseMysql();
    private int editingQuery = -1;

    /**
     * Creates new form CardQueryManager
     */
    public CardQueryManager() {
        initComponents();
        appSettings = STestCaseHelper.getInstance();
        groupQueryType.add(radioInsert);
        groupQueryType.add(radioSelect);
        groupQueryType.add(radioUpdate);
        groupQueryType.add(radioDelete);
        groupQueryType.setSelected(radioInsert.getModel(), true);
        comboQueries.setRenderer(new ListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList jlist, Object value, int i, boolean isSelected, boolean cellHasFocus) {
                final JTextArea item = new JTextArea((String) value);
                if (isSelected) {
                    item.setEditable(false);
                    item.setCursor(null);
                    item.setOpaque(false);
                    item.setFocusable(false);
                }
                return item;

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupQueryType = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        radioInsert = new javax.swing.JRadioButton();
        radioSelect = new javax.swing.JRadioButton();
        radioUpdate = new javax.swing.JRadioButton();
        radioDelete = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        comboQueries = new javax.swing.JComboBox();
        btnAddQueryToList = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaQueryEditor = new javax.swing.JTextArea();
        btnEditSelectedQuery = new javax.swing.JButton();
        btnRemoveSelectedQuery = new javax.swing.JButton();
        btnMakeQueryTest = new javax.swing.JButton();
        labelTimeoutIndicator = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelStatusEditOrNew = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Управление запросами теста");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Категория"));

        radioInsert.setText("INSERT");
        radioInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioInsertActionPerformed(evt);
            }
        });

        radioSelect.setText("SELECT");
        radioSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSelectActionPerformed(evt);
            }
        });

        radioUpdate.setText("UPDATE");
        radioUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioUpdateActionPerformed(evt);
            }
        });

        radioDelete.setText("DELETE");
        radioDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioInsert)
                    .addComponent(radioSelect)
                    .addComponent(radioUpdate)
                    .addComponent(radioDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioInsert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Запросы:");

        comboQueries.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboQueries.setSelectedIndex(-1);
        comboQueries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboQueriesActionPerformed(evt);
            }
        });

        btnAddQueryToList.setText("Сохранить");
        btnAddQueryToList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddQueryToListActionPerformed(evt);
            }
        });

        areaQueryEditor.setColumns(20);
        areaQueryEditor.setRows(5);
        jScrollPane1.setViewportView(areaQueryEditor);

        btnEditSelectedQuery.setText("Редактировать");
        btnEditSelectedQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSelectedQueryActionPerformed(evt);
            }
        });

        btnRemoveSelectedQuery.setText("Удалить");
        btnRemoveSelectedQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveSelectedQueryActionPerformed(evt);
            }
        });

        btnMakeQueryTest.setText("Тест");
        btnMakeQueryTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMakeQueryTestActionPerformed(evt);
            }
        });

        labelTimeoutIndicator.setFont(new java.awt.Font("Liberation Mono", 1, 18)); // NOI18N
        labelTimeoutIndicator.setText("0 сек");

        jLabel3.setText("Режим:");

        labelStatusEditOrNew.setForeground(new java.awt.Color(255, 102, 102));
        labelStatusEditOrNew.setText("новый запрос");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelStatusEditOrNew, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                                .addComponent(btnEditSelectedQuery)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoveSelectedQuery))
                            .addComponent(comboQueries, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelTimeoutIndicator)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMakeQueryTest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddQueryToList)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddQueryToList, btnEditSelectedQuery, btnMakeQueryTest, btnRemoveSelectedQuery});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(comboQueries, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnRemoveSelectedQuery)
                                    .addComponent(btnEditSelectedQuery)
                                    .addComponent(jLabel3)
                                    .addComponent(labelStatusEditOrNew)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddQueryToList)
                    .addComponent(btnMakeQueryTest)
                    .addComponent(labelTimeoutIndicator))
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleName("Список \\nзапросов:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void comboQueriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboQueriesActionPerformed
        if (comboQueries.getSelectedItem() != null) {
            areaQueryEditor.setText(comboQueries.getSelectedItem().toString());
            editingQuery = -1;
            btnEditSelectedQuery.setText("Редактировать");
            labelStatusEditOrNew.setText("новый запрос");
        }
    }//GEN-LAST:event_comboQueriesActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        loadQueryList();
    }//GEN-LAST:event_formComponentShown

    private void radioInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioInsertActionPerformed
        loadQueryList();
    }//GEN-LAST:event_radioInsertActionPerformed

    private void radioSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSelectActionPerformed
        radioInsertActionPerformed(evt);
    }//GEN-LAST:event_radioSelectActionPerformed

    private void radioUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioUpdateActionPerformed
        radioInsertActionPerformed(evt);
    }//GEN-LAST:event_radioUpdateActionPerformed

    private void radioDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDeleteActionPerformed
        radioInsertActionPerformed(evt);
    }//GEN-LAST:event_radioDeleteActionPerformed

    private void btnAddQueryToListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddQueryToListActionPerformed
        long res = 0;
        if (radioInsert.isSelected()) {
            res = mysqlDbHelper.executeDataBaseQuery(areaQueryEditor.getText(), "insert");
            if (res >= 0) {
                if (editingQuery == -1) {
                    appSettings.getQueriesInsert().add(areaQueryEditor.getText());
                    comboQueries.addItem(areaQueryEditor.getText());
                } else {
                    appSettings.getQueriesInsert().set(editingQuery, areaQueryEditor.getText());
                    loadQueryList();
                }
            }
        } else if (radioSelect.isSelected()) {
            res = mysqlDbHelper.executeDataBaseQuery(areaQueryEditor.getText(), "select");
            if (res >= 0) {
                if (editingQuery == -1) {
                    appSettings.getQueriesSelect().add(areaQueryEditor.getText());
                    comboQueries.addItem(areaQueryEditor.getText());
                } else {
                    appSettings.getQueriesSelect().set(editingQuery, areaQueryEditor.getText());
                    loadQueryList();
                }
            }
        } else if (radioUpdate.isSelected()) {
            res = mysqlDbHelper.executeDataBaseQuery(areaQueryEditor.getText(), "update");
            if (res >= 0) {
                if (editingQuery == -1) {
                    appSettings.getQueriesUpdate().add(areaQueryEditor.getText());
                    comboQueries.addItem(areaQueryEditor.getText());
                } else {
                    appSettings.getQueriesUpdate().set(editingQuery, areaQueryEditor.getText());
                    loadQueryList();
                }
            }
        } else {
            res = mysqlDbHelper.executeDataBaseQuery(areaQueryEditor.getText(), "delete");
            if (res >= 0) {
                if (editingQuery == -1) {
                    appSettings.getQueriesDelete().add(areaQueryEditor.getText());
                    comboQueries.addItem(areaQueryEditor.getText());
                } else {
                    appSettings.getQueriesDelete().set(editingQuery, areaQueryEditor.getText());
                    loadQueryList();
                }
            }
        }
        if (res >= 0) {
            appSettings.storeTestCaseSettings(appSettings.getTestCaseName(), appSettings.getTestDbType());
        }
        labelTimeoutIndicator.setText("" + res + " милисек.");
    }//GEN-LAST:event_btnAddQueryToListActionPerformed

    private void btnMakeQueryTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMakeQueryTestActionPerformed
        if (radioInsert.isSelected()) {
            labelTimeoutIndicator.setText("" + mysqlDbHelper.executeDataBaseQuery(areaQueryEditor.getText(), "insert") + " милисек.");
        } else if (radioSelect.isSelected()) {
            labelTimeoutIndicator.setText("" + mysqlDbHelper.executeDataBaseQuery(areaQueryEditor.getText(), "select") + " милисек.");
        } else if (radioUpdate.isSelected()) {
            labelTimeoutIndicator.setText("" + mysqlDbHelper.executeDataBaseQuery(areaQueryEditor.getText(), "update") + " милисек.");
        } else {
            labelTimeoutIndicator.setText("" + mysqlDbHelper.executeDataBaseQuery(areaQueryEditor.getText(), "delete") + " милисек.");
        }
    }//GEN-LAST:event_btnMakeQueryTestActionPerformed

    private void btnEditSelectedQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSelectedQueryActionPerformed
        if (comboQueries.getSelectedItem() != null) {
            if (editingQuery == -1) {
                editingQuery = comboQueries.getSelectedIndex();
                btnEditSelectedQuery.setText("Добавить как новый");
                areaQueryEditor.setText(comboQueries.getSelectedItem().toString());
                labelStatusEditOrNew.setText("редактирование");
            } else {
                editingQuery = -1;
                btnEditSelectedQuery.setText("Редактировать");
                labelStatusEditOrNew.setText("новый запрос");
            }
        }
    }//GEN-LAST:event_btnEditSelectedQueryActionPerformed

    private void btnRemoveSelectedQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveSelectedQueryActionPerformed
        if (comboQueries.getSelectedItem() != null
                && JOptionPane.showConfirmDialog(null, "Выполнить удаление?", "Подтверждение", JOptionPane.OK_CANCEL_OPTION) == 0) {
            if (radioInsert.isSelected()) {
                appSettings.getQueriesInsert().remove(comboQueries.getSelectedIndex());
                loadQueryList();
            } else if (radioSelect.isSelected()) {
                appSettings.getQueriesSelect().remove(comboQueries.getSelectedIndex());
                loadQueryList();
            } else if (radioUpdate.isSelected()) {
                appSettings.getQueriesUpdate().remove(comboQueries.getSelectedIndex());
                loadQueryList();
            } else {
                appSettings.getQueriesDelete().remove(comboQueries.getSelectedIndex());
                loadQueryList();
            }
            appSettings.storeTestCaseSettings(appSettings.getTestCaseName(), appSettings.getTestDbType());
        }
    }//GEN-LAST:event_btnRemoveSelectedQueryActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaQueryEditor;
    private javax.swing.JButton btnAddQueryToList;
    private javax.swing.JButton btnEditSelectedQuery;
    private javax.swing.JButton btnMakeQueryTest;
    private javax.swing.JButton btnRemoveSelectedQuery;
    private javax.swing.JComboBox comboQueries;
    private javax.swing.ButtonGroup groupQueryType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelStatusEditOrNew;
    private javax.swing.JLabel labelTimeoutIndicator;
    private javax.swing.JRadioButton radioDelete;
    private javax.swing.JRadioButton radioInsert;
    private javax.swing.JRadioButton radioSelect;
    private javax.swing.JRadioButton radioUpdate;
    // End of variables declaration//GEN-END:variables

    private void loadQueryList() {
        comboQueries.removeAllItems();
        if (radioInsert.isSelected()) {
            for (int i = 0; i < appSettings.getQueriesInsert().size(); ++i) {
                comboQueries.addItem(appSettings.getQueriesInsert().get(i));
            }
        } else if (radioSelect.isSelected()) {
            for (int i = 0; i < appSettings.getQueriesSelect().size(); ++i) {
                comboQueries.addItem(appSettings.getQueriesSelect().get(i));
            }
        } else if (radioUpdate.isSelected()) {
            for (int i = 0; i < appSettings.getQueriesInsert().size(); ++i) {
                comboQueries.addItem(appSettings.getQueriesUpdate().get(i));
            }
        } else {
            for (int i = 0; i < appSettings.getQueriesInsert().size(); ++i) {
                comboQueries.addItem(appSettings.getQueriesDelete().get(i));
            }
        }
        if (comboQueries.getSelectedItem() != null) {
            areaQueryEditor.setText(comboQueries.getSelectedItem().toString());
        }
    }

    public JTextArea getAreaQueryEditor() {
        return areaQueryEditor;
    }

    public void setAreaQueryEditor(JTextArea areaQueryEditor) {
        this.areaQueryEditor = areaQueryEditor;
    }

    public JComboBox getComboQueries() {
        return comboQueries;
    }

    public void setComboQueries(JComboBox comboQueries) {
        this.comboQueries = comboQueries;
    }
}
