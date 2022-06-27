
package trabalho;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduardo
 */
public abstract class Tabela {
    
    public abstract String getTable();
    public abstract boolean isAllParameterNotNull(List<String> parameters);
    public abstract String[] loadValuesArray(ResultSet result) throws SQLException;

    public Tabela() {
    }
    
    public String selectQuery(){
        String table = this.getTable();
        return String.format("SELECT * FROM %s", table);
    }
    
    public String selectQuery(String column, String search){
        String table = this.getTable();
        return String.format("SELECT * FROM %s"
                + " WHERE %s LIKE '%s%%'",table, column, search);
    }
    
    public String deleteQuery(String column, String search){
        String table = this.getTable();
        return String.format("DELETE FROM %s"
                    + " WHERE %s = '%s'", table, column, search); 
    }
    
    public String updateQuery(List<String> updateParameters, String whereColumn,
            String whereCondition){
        String table = this.getTable();
        String query = String.format("UPDATE %s SET ", table);
        for(Integer i = 0; i < updateParameters.size();i += 2){
            query += String.format("%s = '%s'", updateParameters.get(i), 
                    updateParameters.get(i+1));
            if(i != updateParameters.size() - 2)
                query += ", ";
        } 
        query += String.format(" WHERE %s = '%s'", whereColumn, whereCondition);
        return query;
    }
    
    public boolean DBConnectionUpdate(JFrame jf, String query){
        try{
            DBConnection.getInstance().statement.executeUpdate(query);
            return true;
            
        }catch (SQLException e) {
            JOptionPaneDialogMessages.getInstance().errorMessage(jf, 
                    e.getMessage());
            return false;
        }
    }
    
    public ResultSet DBConnectionSelect(JFrame jf, String query){
        try{
            return DBConnection.getInstance().statement.executeQuery(query);
        }catch (SQLException e) {
            JOptionPaneDialogMessages.getInstance().errorMessage(jf, 
                    e.getMessage());
        }
        return null;
    }
    
    public List loadQueriesList(ArrayList<ArrayList<String>> 
            searchParametersMatrix){
        List<String> queries = new ArrayList<>();
        for(int i = 0; i <= searchParametersMatrix.size(); i++){
            queries.add(this.selectQuery(searchParametersMatrix.get(i).get(0), 
                    searchParametersMatrix.get(i).get(1)));
        }
        return queries;
    }
    public void printTable(ResultSet result, javax.swing.JTable jTable,
            JFrame jf){
        try{
            while(result.next()){
                String[] values = loadValuesArray(result);
                ((DefaultTableModel)jTable.getModel()).addRow(values);
            }
        }catch(SQLException e){
            JOptionPaneDialogMessages.getInstance().errorMessage(jf, 
                    e.getMessage());
        }
    }
    
    public ResultSet executeSelect(JFrame jf){
        return DBConnectionSelect(jf, this.selectQuery());
    }
    
    public ResultSet executeSelect(JFrame jf,String column, String search){
        return DBConnectionSelect(jf, this.selectQuery(column, search));
    } 
    
    public void executeDelete(JFrame jf, String column, String search){
        if(JOptionPaneDialogMessages.getInstance().confirmChanges(jf)){
            if(DBConnectionUpdate(jf, this.deleteQuery(column, search)))
                JOptionPaneDialogMessages.getInstance().infoMessage(jf, "Livro "
                        + "removido com sucesso");
        }
    }
        
    public void executeUpdate(JFrame jf, List<String> updateParameters, 
            String whereColumn, String whereCondition){
        if(JOptionPaneDialogMessages.getInstance().confirmChanges(jf)){
            if(DBConnectionUpdate(jf, this.updateQuery( updateParameters, 
                    whereColumn, whereCondition)))
                JOptionPaneDialogMessages.getInstance().infoMessage(jf, "Livro "
                        + "alterado com sucesso"); 
        }
    }
}