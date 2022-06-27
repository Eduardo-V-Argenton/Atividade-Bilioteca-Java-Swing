
package trabalho;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;

public class Login extends Tabela{
    
    public Login() {
    }

    @Override
    public String getTable() {
        return "Login";
    }

    @Override
    public boolean isAllParameterNotNull(List<String> parameters) {
         return !((parameters.size() < 2) || (parameters.get(0).isBlank() || 
                 parameters.get(1).isBlank()));
    }

    
    public String insertQuery(String user, String password) {
        return String.format("INSERT INTO Login(Usuario, Senha)"
                + " VALUES('%s', SHA1('%s'))",user, password);
    }
    
    public void executeInsert(JFrame jf, String user, String password){
        if(this.isAllParameterNotNull(Arrays.asList(user, password))){
            if(this.DBConnectionUpdate(jf, this.insertQuery(user, password)))
                JOptionPaneDialogMessages.getInstance().infoMessage(jf, 
                        "Usuário Cadastrado com sucesso");
        }else{
            JOptionPaneDialogMessages.getInstance().errorMessage(jf, 
                "Preencha todos os campos!");
        }
    }

    @Override
    public String[] loadValuesArray(ResultSet result) throws SQLException {
        String values[] = {
            result.getString(1),
            result.getString(2)
        };
        return values;
    }
    
    public boolean verifyLoginAndPassword(JFrame jf, String user, String password){
        String query = String.format("SELECT * FROM Login WHERE Usuario= '%s'"
                + " AND Senha=SHA1('%s')", user, password);
        ResultSet result = this.DBConnectionSelect(jf, query);
        try{
            if(result.next() != false){
                return true;
            }
            else
                JOptionPaneDialogMessages.getInstance().errorMessage(jf, 
                        "Usuário ou senha incorretos");
        }catch(SQLException e){
                JOptionPaneDialogMessages.getInstance().errorMessage(jf, 
                        e.getMessage());
        }
        return false;
    }
}

