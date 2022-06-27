package trabalho;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;

public class Livro extends Tabela{  

    public Livro() {
    }

    public String insertQuery(List<String> parameters) {
        return String.format("INSERT INTO Livro(Nome, Escritor, "
                + "Editora, AnoLancamento, Genero, Localizacao)"
                + " VALUES('%s', '%s', '%s', '%s', '%s', '%s')", 
                parameters.get(0), parameters.get(1), 
                parameters.get(2), parameters.get(3), 
                parameters.get(4), parameters.get(5));
    }
    
    public void executeInsert(JFrame jf, List<String> insertParameters){
        if(isAllParameterNotNull(insertParameters)){
            if(DBConnectionUpdate(jf, this.insertQuery(insertParameters)))
                JOptionPaneDialogMessages.getInstance().infoMessage(jf, "Livro "
                    + "inserido com sucesso");
        }else{
            JOptionPaneDialogMessages.getInstance().errorMessage(jf, 
                "Preencha todos os campos!");
        }
    }
    
    @Override
    public String getTable() {
        return "Livro";
    }

    @Override
    public boolean isAllParameterNotNull(List<String> parameters) {
        return !(parameters.size() < 6);
    }

    @Override
    public String[] loadValuesArray(ResultSet result) throws SQLException{
       String values[] = {
            String.valueOf(result.getInt(1)),
            result.getString(2),
            result.getString(3),
            result.getString(4),
            String.valueOf(result.getInt(5)),
            result.getString(6),
            result.getString(7)        
        };
       return values;
    }
        
}

