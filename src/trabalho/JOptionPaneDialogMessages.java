/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class JOptionPaneDialogMessages {
    
    private static JOptionPaneDialogMessages instance = null;
    
    public static JOptionPaneDialogMessages getInstance() {
        if (instance == null) {
            instance = new JOptionPaneDialogMessages();
        }
        return instance;
    }
    
    public boolean confirmChanges(JFrame jf){
        return JOptionPane.showConfirmDialog(jf, "Tem certeza?") 
                == JOptionPane.OK_OPTION;
    }
    
    public void errorMessage(JFrame jf, String message){
        JOptionPane.showMessageDialog(
                jf,
                message,
                "ERRO",
                JOptionPane.ERROR_MESSAGE);
    }
    public void infoMessage(JFrame jf, String message){
        JOptionPane.showMessageDialog(
                jf,
                message,
                "INFO",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
