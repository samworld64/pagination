/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sam.paginate;

import static java.lang.Math.ceil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pc
 */
public class Pages {
    private int page = 1;
    private int limit = 20;
    private int offset;
    private int total = 0; // total pages
    private String query = "";
    
    PreparedStatement preparedStmt = null;
    Statement stmt = null;
    ResultSet result = null;
    
    public Pages(){
        
    }
    
    public Pages(String query){
        this.query = query;
        this.setOffSet();
    }
    
    public Pages(String query,int page){
        this.page = page;
        this.query = query;
        this.setOffSet();
    }
    public Pages(String query, int page, int limit){
        this.page = page;
        this.limit = limit;
        this.query = query;
        this.setOffSet();
    }
    
    private void setOffSet(){
        if(this.page <= 1){
            this.page = 1;
        }
        this.offset = (this.page - 1 ) * this.limit;
    }
    
    public void setLimit(int limit){
        this.limit = limit;
        this.setOffSet();
    }
    
    public void setPage(int page){
        this.page = page;
        this.setOffSet();
    }
    
    public void setQuery(String query){
        this.query = query;
    }
    
    public ResultSet getPageData() throws SQLException{
        String query_limit = this.query + " Limit ?,? ";
        preparedStmt = db.mycon().prepareStatement(query_limit);
        preparedStmt.setInt(1, this.offset);
        preparedStmt.setInt(2, limit);
        
        result = preparedStmt.executeQuery();
        
        return result;
    }
    
    public void nextPage(){
        if(this.page < this.total)
            this.page++;
        else 
            this.page = this.total;
        this.setOffSet();
    }
    
    public void prevPage(){
        if(this.page  > 1)
            this.page --;
        else 
            this.page = 1;
        
        this.setOffSet();
    }
    
    public void firstPage(){
        this.page = 1;
        this.setOffSet();
    }
    
    public void lastPate(){
        this.page = this.total;
        this.setOffSet();
    }
    
    public void totalRecords(String query_records) throws SQLException{
        stmt = db.mycon().createStatement();
        result = stmt.executeQuery(query_records);
        int total_records = 0;
        if(result.next()){
            total_records = result.getInt(1);
        }
        total = (int) ceil(total_records/limit);
    }

}
