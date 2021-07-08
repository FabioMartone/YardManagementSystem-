package com.sad.yardmanagementsystem.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RepositoryOrariDeposito {

	 public RepositoryOrariDeposito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void do_query (Long id_deposito, String fascia_oraria) {
	        try {
	            String url = "jdbc:mysql://localhost:3306/yardmanagementsystem?allowPublicKeyRetrieval=true&useSSL=false";
	            Connection conn = DriverManager.getConnection(url,"root","FabioMartone16@");
	            Statement stmt = conn.createStatement();
	            
	            String query="DELETE FROM orari_deposito WHERE (id_deposito = ? AND fascia_oraria = ?)";
	            
	            try(PreparedStatement pstmt = conn.prepareStatement(query)) {
	    			
	    			pstmt.setLong(1, id_deposito);
	    			pstmt.setString(2, fascia_oraria);	
	    			
	    			pstmt.executeUpdate();
	            }
	            
	            conn.close();
	        } catch (Exception e) {
	            System.err.println("Got an exception! ");
	            System.err.println(e.getMessage());
	        }
	    }
	
}
