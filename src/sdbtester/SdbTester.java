/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import org.apache.log4j.Logger;
import sdbtester.gui.StartForm;

/**
 *
 * @author boris
 */
public class SdbTester {

    public static StartForm startF;

    private static Logger logger = Logger.getLogger(SdbTester.class);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        startF = new StartForm();
        setFramePositionCenter(startF);
        logger.info("Show start window");
        
        startF.setVisible(true);
    }

    public static void setFramePositionCenter(JFrame window) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = window.getSize().width;
        int h = window.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        window.setLocation(x, y);
    }
}
