/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdbtester.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import sdbtester.TestCaseSettings;

/**
 *
 * @author boris
 */
public class DbTestValidator {

    private Pattern PatInt = Pattern.compile("[1,2,3,4,5]{1}");
    private Pattern PatFloat = Pattern.compile("[0]{1}[\u002E]{1}\\d{1,10}?");
    private Pattern PatEnDigit = Pattern.compile("^\\w+$");
    private static Logger logger = Logger.getLogger(TestCaseSettings.class);

    /**
     * method check is str == integer or not
     */
    public boolean isInt(String str) {
        try {
            Matcher matcher = PatInt.matcher(str);
            return matcher.matches();
        } catch (Exception e) {
            logger.error("isInt  ->  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean isFloat(String str) {
        try {
            Matcher matcher = PatFloat.matcher(str);
            return matcher.matches();
        } catch (Exception e) {
            logger.error("isFloat  ->  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean isEnOrDigit(String str) {
        try {
            Matcher matcher = PatEnDigit.matcher(str);
            return matcher.matches();
        } catch (Exception e) {
            logger.error("isEnOrDigit  ->  \n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage() + e.getStackTrace(), "Not enough mana!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
