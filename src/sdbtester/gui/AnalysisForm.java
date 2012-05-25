/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester.gui;

import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import sdbtester.STestCaseHelper;

/**
 *
 * @author boris
 */
public class AnalysisForm extends javax.swing.JFrame {

    private STestCaseHelper appSettings = STestCaseHelper.getInstance();

    /**
     * Creates new form AnalysisForm
     */
    public AnalysisForm() {
        initComponents();
        repaintAllDiagrams();
    }

    private void repaintAllDiagrams() {
        switch (jTabbedPane1.getSelectedIndex()) {
            //insert
            case 0: {
                DiagramViewer dvInsertTime = new DiagramViewer(appSettings.getAnalysisTest1().getResultInsert(), appSettings.getAnalysisTest2().getResultInsert(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_TIME);
                setDiagramToForm(panelInsertTime, dvInsertTime);
                DiagramViewer dvInsertCPU = new DiagramViewer(appSettings.getAnalysisTest1().getResultInsertCPU(), appSettings.getAnalysisTest2().getResultInsertCPU(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_CPU);
                setDiagramToForm(panelInsertCPU, dvInsertCPU);
                DiagramViewer dvInsertRAM = new DiagramViewer(appSettings.getAnalysisTest1().getResultInsertRAM(), appSettings.getAnalysisTest2().getResultInsertRAM(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_RAM);
                setDiagramToForm(panelInsertRAM, dvInsertRAM);
                //select
                DiagramViewer dvSelectTime = new DiagramViewer(appSettings.getAnalysisTest1().getResultSelect(), appSettings.getAnalysisTest2().getResultSelect(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_TIME);
                setDiagramToForm(panelSelectTime, dvSelectTime);
                DiagramViewer dvSelectCPU = new DiagramViewer(appSettings.getAnalysisTest1().getResultSelectCPU(), appSettings.getAnalysisTest2().getResultSelectCPU(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_CPU);
                setDiagramToForm(panelSelectCPU, dvSelectCPU);
                DiagramViewer dvSelectRAM = new DiagramViewer(appSettings.getAnalysisTest1().getResultSelectRAM(), appSettings.getAnalysisTest2().getResultSelectRAM(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_RAM);
                setDiagramToForm(panelSelectRAM, dvSelectRAM);
                //update
                DiagramViewer dvUpdateTime = new DiagramViewer(appSettings.getAnalysisTest1().getResultUpdate(), appSettings.getAnalysisTest2().getResultUpdate(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_TIME);
                setDiagramToForm(panelUpdateTime, dvUpdateTime);
                DiagramViewer dvUpdateCPU = new DiagramViewer(appSettings.getAnalysisTest1().getResultUpdateCPU(), appSettings.getAnalysisTest2().getResultUpdateCPU(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_CPU);
                setDiagramToForm(panelUpdateCPU, dvUpdateCPU);
                DiagramViewer dvUpdateRAM = new DiagramViewer(appSettings.getAnalysisTest1().getResultUpdateRAM(), appSettings.getAnalysisTest2().getResultUpdateRAM(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_RAM);
                setDiagramToForm(panelUpdateRAM, dvUpdateRAM);
                //delete
                DiagramViewer dvDeleteTime = new DiagramViewer(appSettings.getAnalysisTest1().getResultDelete(), appSettings.getAnalysisTest2().getResultDelete(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_TIME);
                setDiagramToForm(panelDeleteTime, dvDeleteTime);
                DiagramViewer dvDeleteCPU = new DiagramViewer(appSettings.getAnalysisTest1().getResultDeleteCPU(), appSettings.getAnalysisTest2().getResultDeleteCPU(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_CPU);
                setDiagramToForm(panelDeleteCPU, dvDeleteCPU);
                DiagramViewer dvDeleteRAM = new DiagramViewer(appSettings.getAnalysisTest1().getResultDeleteRAM(), appSettings.getAnalysisTest2().getResultDeleteRAM(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_RAM);
                setDiagramToForm(panelDeleteRAM, dvDeleteRAM);
                break;
            }
            /////////////////////////////
            //separated insert
            case 1: {
                DiagramViewer dvInsertTimeSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultInsert(), appSettings.getAnalysisTest2().getResultInsert(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_TIME);
                setDiagramToForm(panelSepInsertTime, dvInsertTimeSep);
                DiagramViewer dvInsertCPUSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultInsertCPU(), appSettings.getAnalysisTest2().getResultInsertCPU(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_CPU);
                setDiagramToForm(panelSepInsertCPU, dvInsertCPUSep);
                DiagramViewer dvInsertRAMSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultInsertRAM(), appSettings.getAnalysisTest2().getResultInsertRAM(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_RAM);
                setDiagramToForm(panelSepInsertRAM, dvInsertRAMSep);
                break;
            }
            //select
            case 2: {
                DiagramViewer dvSelectTimeSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultSelect(), appSettings.getAnalysisTest2().getResultSelect(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_TIME);
                setDiagramToForm(panelSepSelectTime, dvSelectTimeSep);
                DiagramViewer dvSelectCPUSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultSelectCPU(), appSettings.getAnalysisTest2().getResultSelectCPU(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_CPU);
                setDiagramToForm(panelSepSelectCPU, dvSelectCPUSep);
                DiagramViewer dvSelectRAMSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultSelectRAM(), appSettings.getAnalysisTest2().getResultSelectRAM(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_RAM);
                setDiagramToForm(panelSepSelectRAM, dvSelectRAMSep);
                break;
            }
            //update
            case 3: {
                DiagramViewer dvUpdateTimeSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultUpdate(), appSettings.getAnalysisTest2().getResultUpdate(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_TIME);
                setDiagramToForm(panelSepUpdateTime, dvUpdateTimeSep);
                DiagramViewer dvUpdateCPUSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultUpdateCPU(), appSettings.getAnalysisTest2().getResultUpdateCPU(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_CPU);
                setDiagramToForm(panelSepUpdateCPU, dvUpdateCPUSep);
                DiagramViewer dvUpdateRAMSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultUpdateRAM(), appSettings.getAnalysisTest2().getResultUpdateRAM(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_RAM);
                setDiagramToForm(panelSepUpdateRAM, dvUpdateRAMSep);
                break;
            }
            //delete
            case 4: {
                DiagramViewer dvDeleteTimeSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultDelete(), appSettings.getAnalysisTest2().getResultDelete(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_TIME);
                setDiagramToForm(panelSepDeleteTime, dvDeleteTimeSep);
                DiagramViewer dvDeleteCPUSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultDeleteCPU(), appSettings.getAnalysisTest2().getResultDeleteCPU(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_CPU);
                setDiagramToForm(panelSepDeleteCPU, dvDeleteCPUSep);
                DiagramViewer dvDeleteRAMSep = new DiagramViewer(appSettings.getAnalysisTest1().getResultDeleteRAM(), appSettings.getAnalysisTest2().getResultDeleteRAM(), appSettings.getAnalysisTest1().getTestCaseName(), appSettings.getAnalysisTest2().getTestCaseName(), DiagramViewer.DIAGRAM_TYPE_RAM);
                setDiagramToForm(panelSepDeleteRAM, dvDeleteRAMSep);
                break;
            }
        }
    }

    private void setDiagramToForm(JPanel panel, DiagramViewer dv) {
        ChartPanel cp = new ChartPanel(dv.getChart());
        cp.setSize(panel.getSize());
        cp.setVisible(true);
        cp.setOpaque(false);
        cp.setMouseZoomable(false);
        panel.removeAll();
        panel.add(cp);
        cp.invalidate();
        cp.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        panelSelectTime = new javax.swing.JPanel();
        panelSelectCPU = new javax.swing.JPanel();
        panelSelectRAM = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelInsertTime = new javax.swing.JPanel();
        panelInsertCPU = new javax.swing.JPanel();
        panelInsertRAM = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        panelUpdateTime = new javax.swing.JPanel();
        panelUpdateCPU = new javax.swing.JPanel();
        panelUpdateRAM = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        panelDeleteTime = new javax.swing.JPanel();
        panelDeleteCPU = new javax.swing.JPanel();
        panelDeleteRAM = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelSepInsertTime = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        panelSepInsertCPU = new javax.swing.JPanel();
        panelSepInsertRAM = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        panelSepSelectTime = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        panelSepSelectCPU = new javax.swing.JPanel();
        panelSepSelectRAM = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jSplitPane5 = new javax.swing.JSplitPane();
        panelSepUpdateTime = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jSplitPane6 = new javax.swing.JSplitPane();
        panelSepUpdateCPU = new javax.swing.JPanel();
        panelSepUpdateRAM = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jSplitPane7 = new javax.swing.JSplitPane();
        panelSepDeleteTime = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jSplitPane8 = new javax.swing.JSplitPane();
        panelSepDeleteCPU = new javax.swing.JPanel();
        panelSepDeleteRAM = new javax.swing.JPanel();

        setTitle("Сравнение тестов");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(0, 0));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SELECT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_BOTTOM));

        panelSelectTime.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelSelectTimeLayout = new javax.swing.GroupLayout(panelSelectTime);
        panelSelectTime.setLayout(panelSelectTimeLayout);
        panelSelectTimeLayout.setHorizontalGroup(
            panelSelectTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        panelSelectTimeLayout.setVerticalGroup(
            panelSelectTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        panelSelectCPU.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelSelectCPULayout = new javax.swing.GroupLayout(panelSelectCPU);
        panelSelectCPU.setLayout(panelSelectCPULayout);
        panelSelectCPULayout.setHorizontalGroup(
            panelSelectCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        panelSelectCPULayout.setVerticalGroup(
            panelSelectCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelSelectRAM.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelSelectRAMLayout = new javax.swing.GroupLayout(panelSelectRAM);
        panelSelectRAM.setLayout(panelSelectRAMLayout);
        panelSelectRAMLayout.setHorizontalGroup(
            panelSelectRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        panelSelectRAMLayout.setVerticalGroup(
            panelSelectRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(panelSelectTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSelectCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSelectRAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSelectTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelSelectCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelSelectRAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INSERT", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_BOTTOM));

        panelInsertTime.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelInsertTimeLayout = new javax.swing.GroupLayout(panelInsertTime);
        panelInsertTime.setLayout(panelInsertTimeLayout);
        panelInsertTimeLayout.setHorizontalGroup(
            panelInsertTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelInsertTimeLayout.setVerticalGroup(
            panelInsertTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        panelInsertCPU.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelInsertCPULayout = new javax.swing.GroupLayout(panelInsertCPU);
        panelInsertCPU.setLayout(panelInsertCPULayout);
        panelInsertCPULayout.setHorizontalGroup(
            panelInsertCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelInsertCPULayout.setVerticalGroup(
            panelInsertCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelInsertRAM.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelInsertRAMLayout = new javax.swing.GroupLayout(panelInsertRAM);
        panelInsertRAM.setLayout(panelInsertRAMLayout);
        panelInsertRAMLayout.setHorizontalGroup(
            panelInsertRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelInsertRAMLayout.setVerticalGroup(
            panelInsertRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelInsertTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInsertCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInsertRAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInsertTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelInsertCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelInsertRAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "UPDATE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_BOTTOM));

        panelUpdateTime.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelUpdateTimeLayout = new javax.swing.GroupLayout(panelUpdateTime);
        panelUpdateTime.setLayout(panelUpdateTimeLayout);
        panelUpdateTimeLayout.setHorizontalGroup(
            panelUpdateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelUpdateTimeLayout.setVerticalGroup(
            panelUpdateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );

        panelUpdateCPU.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelUpdateCPULayout = new javax.swing.GroupLayout(panelUpdateCPU);
        panelUpdateCPU.setLayout(panelUpdateCPULayout);
        panelUpdateCPULayout.setHorizontalGroup(
            panelUpdateCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelUpdateCPULayout.setVerticalGroup(
            panelUpdateCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelUpdateRAM.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelUpdateRAMLayout = new javax.swing.GroupLayout(panelUpdateRAM);
        panelUpdateRAM.setLayout(panelUpdateRAMLayout);
        panelUpdateRAMLayout.setHorizontalGroup(
            panelUpdateRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelUpdateRAMLayout.setVerticalGroup(
            panelUpdateRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(panelUpdateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelUpdateCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelUpdateRAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUpdateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelUpdateCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelUpdateRAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DELETE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_BOTTOM));

        panelDeleteTime.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelDeleteTimeLayout = new javax.swing.GroupLayout(panelDeleteTime);
        panelDeleteTime.setLayout(panelDeleteTimeLayout);
        panelDeleteTimeLayout.setHorizontalGroup(
            panelDeleteTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDeleteTimeLayout.setVerticalGroup(
            panelDeleteTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );

        panelDeleteCPU.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelDeleteCPULayout = new javax.swing.GroupLayout(panelDeleteCPU);
        panelDeleteCPU.setLayout(panelDeleteCPULayout);
        panelDeleteCPULayout.setHorizontalGroup(
            panelDeleteCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDeleteCPULayout.setVerticalGroup(
            panelDeleteCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelDeleteRAM.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout panelDeleteRAMLayout = new javax.swing.GroupLayout(panelDeleteRAM);
        panelDeleteRAM.setLayout(panelDeleteRAMLayout);
        panelDeleteRAMLayout.setHorizontalGroup(
            panelDeleteRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDeleteRAMLayout.setVerticalGroup(
            panelDeleteRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(panelDeleteTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDeleteCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDeleteRAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDeleteTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDeleteCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDeleteRAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Все", jPanel2);

        jSplitPane1.setDividerLocation(350);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        panelSepInsertTime.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepInsertTimeComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepInsertTimeLayout = new javax.swing.GroupLayout(panelSepInsertTime);
        panelSepInsertTime.setLayout(panelSepInsertTimeLayout);
        panelSepInsertTimeLayout.setHorizontalGroup(
            panelSepInsertTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1136, Short.MAX_VALUE)
        );
        panelSepInsertTimeLayout.setVerticalGroup(
            panelSepInsertTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        jSplitPane1.setTopComponent(panelSepInsertTime);

        jSplitPane2.setDividerLocation(600);

        panelSepInsertCPU.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepInsertCPUComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepInsertCPULayout = new javax.swing.GroupLayout(panelSepInsertCPU);
        panelSepInsertCPU.setLayout(panelSepInsertCPULayout);
        panelSepInsertCPULayout.setHorizontalGroup(
            panelSepInsertCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        panelSepInsertCPULayout.setVerticalGroup(
            panelSepInsertCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        jSplitPane2.setLeftComponent(panelSepInsertCPU);

        panelSepInsertRAM.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepInsertRAMComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepInsertRAMLayout = new javax.swing.GroupLayout(panelSepInsertRAM);
        panelSepInsertRAM.setLayout(panelSepInsertRAMLayout);
        panelSepInsertRAMLayout.setHorizontalGroup(
            panelSepInsertRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );
        panelSepInsertRAMLayout.setVerticalGroup(
            panelSepInsertRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(panelSepInsertRAM);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel16);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("INSERT", jPanel3);

        jSplitPane3.setDividerLocation(350);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        panelSepSelectTime.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepSelectTimeComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepSelectTimeLayout = new javax.swing.GroupLayout(panelSepSelectTime);
        panelSepSelectTime.setLayout(panelSepSelectTimeLayout);
        panelSepSelectTimeLayout.setHorizontalGroup(
            panelSepSelectTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1136, Short.MAX_VALUE)
        );
        panelSepSelectTimeLayout.setVerticalGroup(
            panelSepSelectTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        jSplitPane3.setTopComponent(panelSepSelectTime);

        jSplitPane4.setDividerLocation(600);

        panelSepSelectCPU.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepSelectCPUComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepSelectCPULayout = new javax.swing.GroupLayout(panelSepSelectCPU);
        panelSepSelectCPU.setLayout(panelSepSelectCPULayout);
        panelSepSelectCPULayout.setHorizontalGroup(
            panelSepSelectCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        panelSepSelectCPULayout.setVerticalGroup(
            panelSepSelectCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        jSplitPane4.setLeftComponent(panelSepSelectCPU);

        panelSepSelectRAM.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepSelectRAMComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepSelectRAMLayout = new javax.swing.GroupLayout(panelSepSelectRAM);
        panelSepSelectRAM.setLayout(panelSepSelectRAMLayout);
        panelSepSelectRAMLayout.setHorizontalGroup(
            panelSepSelectRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );
        panelSepSelectRAMLayout.setVerticalGroup(
            panelSepSelectRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        jSplitPane4.setRightComponent(panelSepSelectRAM);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );

        jSplitPane3.setRightComponent(jPanel17);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("SELECT", jPanel4);

        jSplitPane5.setDividerLocation(350);
        jSplitPane5.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        panelSepUpdateTime.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepUpdateTimeComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepUpdateTimeLayout = new javax.swing.GroupLayout(panelSepUpdateTime);
        panelSepUpdateTime.setLayout(panelSepUpdateTimeLayout);
        panelSepUpdateTimeLayout.setHorizontalGroup(
            panelSepUpdateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1136, Short.MAX_VALUE)
        );
        panelSepUpdateTimeLayout.setVerticalGroup(
            panelSepUpdateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        jSplitPane5.setTopComponent(panelSepUpdateTime);

        jSplitPane6.setDividerLocation(600);

        panelSepUpdateCPU.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepUpdateCPUComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepUpdateCPULayout = new javax.swing.GroupLayout(panelSepUpdateCPU);
        panelSepUpdateCPU.setLayout(panelSepUpdateCPULayout);
        panelSepUpdateCPULayout.setHorizontalGroup(
            panelSepUpdateCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        panelSepUpdateCPULayout.setVerticalGroup(
            panelSepUpdateCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        jSplitPane6.setLeftComponent(panelSepUpdateCPU);

        panelSepUpdateRAM.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepUpdateRAMComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepUpdateRAMLayout = new javax.swing.GroupLayout(panelSepUpdateRAM);
        panelSepUpdateRAM.setLayout(panelSepUpdateRAMLayout);
        panelSepUpdateRAMLayout.setHorizontalGroup(
            panelSepUpdateRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );
        panelSepUpdateRAMLayout.setVerticalGroup(
            panelSepUpdateRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        jSplitPane6.setRightComponent(panelSepUpdateRAM);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );

        jSplitPane5.setRightComponent(jPanel18);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("UPDATE", jPanel6);

        jSplitPane7.setDividerLocation(350);
        jSplitPane7.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        panelSepDeleteTime.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepDeleteTimeComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepDeleteTimeLayout = new javax.swing.GroupLayout(panelSepDeleteTime);
        panelSepDeleteTime.setLayout(panelSepDeleteTimeLayout);
        panelSepDeleteTimeLayout.setHorizontalGroup(
            panelSepDeleteTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1136, Short.MAX_VALUE)
        );
        panelSepDeleteTimeLayout.setVerticalGroup(
            panelSepDeleteTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        jSplitPane7.setTopComponent(panelSepDeleteTime);

        jSplitPane8.setDividerLocation(600);

        panelSepDeleteCPU.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepDeleteCPUComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepDeleteCPULayout = new javax.swing.GroupLayout(panelSepDeleteCPU);
        panelSepDeleteCPU.setLayout(panelSepDeleteCPULayout);
        panelSepDeleteCPULayout.setHorizontalGroup(
            panelSepDeleteCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        panelSepDeleteCPULayout.setVerticalGroup(
            panelSepDeleteCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        jSplitPane8.setLeftComponent(panelSepDeleteCPU);

        panelSepDeleteRAM.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelSepDeleteRAMComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panelSepDeleteRAMLayout = new javax.swing.GroupLayout(panelSepDeleteRAM);
        panelSepDeleteRAM.setLayout(panelSepDeleteRAMLayout);
        panelSepDeleteRAMLayout.setHorizontalGroup(
            panelSepDeleteRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );
        panelSepDeleteRAMLayout.setVerticalGroup(
            panelSepDeleteRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        jSplitPane8.setRightComponent(panelSepDeleteRAM);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );

        jSplitPane7.setRightComponent(jPanel19);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("DELETE", jPanel7);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        repaintAllDiagrams();
    }//GEN-LAST:event_formWindowActivated

    private void panelSepInsertCPUComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepInsertCPUComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepInsertCPUComponentResized

    private void panelSepInsertRAMComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepInsertRAMComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepInsertRAMComponentResized

    private void panelSepInsertTimeComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepInsertTimeComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepInsertTimeComponentResized

    private void panelSepSelectTimeComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepSelectTimeComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepSelectTimeComponentResized

    private void panelSepSelectCPUComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepSelectCPUComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepSelectCPUComponentResized

    private void panelSepSelectRAMComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepSelectRAMComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepSelectRAMComponentResized

    private void panelSepUpdateTimeComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepUpdateTimeComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepUpdateTimeComponentResized

    private void panelSepUpdateCPUComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepUpdateCPUComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepUpdateCPUComponentResized

    private void panelSepUpdateRAMComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepUpdateRAMComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepUpdateRAMComponentResized

    private void panelSepDeleteTimeComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepDeleteTimeComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepDeleteTimeComponentResized

    private void panelSepDeleteCPUComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepDeleteCPUComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepDeleteCPUComponentResized

    private void panelSepDeleteRAMComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelSepDeleteRAMComponentResized
        repaintAllDiagrams();
    }//GEN-LAST:event_panelSepDeleteRAMComponentResized

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        repaintAllDiagrams();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AnalysisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnalysisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnalysisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnalysisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AnalysisForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JSplitPane jSplitPane6;
    private javax.swing.JSplitPane jSplitPane7;
    private javax.swing.JSplitPane jSplitPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelDeleteCPU;
    private javax.swing.JPanel panelDeleteRAM;
    private javax.swing.JPanel panelDeleteTime;
    private javax.swing.JPanel panelInsertCPU;
    private javax.swing.JPanel panelInsertRAM;
    private javax.swing.JPanel panelInsertTime;
    private javax.swing.JPanel panelSelectCPU;
    private javax.swing.JPanel panelSelectRAM;
    private javax.swing.JPanel panelSelectTime;
    private javax.swing.JPanel panelSepDeleteCPU;
    private javax.swing.JPanel panelSepDeleteRAM;
    private javax.swing.JPanel panelSepDeleteTime;
    private javax.swing.JPanel panelSepInsertCPU;
    private javax.swing.JPanel panelSepInsertRAM;
    private javax.swing.JPanel panelSepInsertTime;
    private javax.swing.JPanel panelSepSelectCPU;
    private javax.swing.JPanel panelSepSelectRAM;
    private javax.swing.JPanel panelSepSelectTime;
    private javax.swing.JPanel panelSepUpdateCPU;
    private javax.swing.JPanel panelSepUpdateRAM;
    private javax.swing.JPanel panelSepUpdateTime;
    private javax.swing.JPanel panelUpdateCPU;
    private javax.swing.JPanel panelUpdateRAM;
    private javax.swing.JPanel panelUpdateTime;
    // End of variables declaration//GEN-END:variables
}
