/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester.gui;

import org.apache.log4j.Logger;
import sdbtester.STestCaseHelper;

/**
 *
 * @author boris
 */
public class ProgressThread implements Runnable {

    CardTestRunning card = null;
    STestCaseHelper appSettings = STestCaseHelper.getInstance();
    private static Logger logger = Logger.getLogger(ProgressThread.class);

    public ProgressThread(CardTestRunning ctr) {
        this.card = ctr;
    }

    @Override
    public void run() {
        try {
            while (true) {
                logger.info("test started thread");
                while (!appSettings.isCurrentTestStarted()) {
                    logger.info("test !started thread");
                }
                while (appSettings.isCurrentTestStarted()) {
                    logger.info(" thread works");
                    try {
                        card.getjProgressBar3().setValue(appSettings.getProgressBarPosition());
                        card.getjProgressBar3().setStringPainted(true);
                        if (!card.getAreaTestLog().getText().equals(appSettings.getLogTestContent())) {
                            card.getAreaTestLog().setText(appSettings.getLogTestContent());
                        }
                        logger.info("test started thread");
                    } catch (Exception e) {
                        logger.error(e);
                    }
                }
                if (!card.getAreaTestLog().getText().equals(appSettings.getLogTestContent())) {
                    card.getAreaTestLog().setText(appSettings.getLogTestContent());
                }
//                Thread.sleep(2000);
//                SAnalysisHelper sah = new SAnalysisHelper();
//                sah.loadTestFromTemplate(appSettings.getTestCaseName(), appSettings.getTestDbType());
//                appSettings.setAnalysisTest1(sah);
//                JFrame analysisForm = new AnalysisForm();
//                analysisForm.setVisible(true);

            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
