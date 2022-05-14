package com.electricgrid.billingmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.electricgrid.billingmanagement.Billingmanagement;



public class BillingmanagementRepository {
		
	Connection con = null;
	
		public BillingmanagementRepository() {
			
			String url = "Jdbc:mysql://localhost:3306/electric_grid";
			String username = "root";
			String password = "";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url,username,password);
		           System.out.println("Database is successfully Connected!!!");
			}catch (Exception e) {
				System.out.println(e);
		          System.out.println("Error while Connecting to database!!");
			}
			
		}
		
		public List<Billingmanagement> getBillingmanagements(){
			
			List<Billingmanagement> billingmanagements = new ArrayList<>();
			String sql = "select * from billingmanagement";
			
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					
					Billingmanagement b = new Billingmanagement();
					b.setBillig_id(rs.getInt(1));
					b.setMc_id(rs.getInt(2));
					b.setUser_acc_id(rs.getInt(3));
					b.setMonthly_unit_usage(rs.getFloat(4));
					b.setTotal_bill(rs.getInt(5));
					b.setBilling_status(rs.getString(6));
					
					billingmanagements.add(b);
				}
				
			}
			catch(Exception e) {
				System.out.print(e);
			}
			return billingmanagements;
		}
		
		public Billingmanagement getBillingmanagement(int billing_id) {
			String sql = "select * from billingmanagement where billing_id ="+billing_id;
			Billingmanagement b = new Billingmanagement();
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if(rs.next()) {
				
					b.setBillig_id(rs.getInt(1));
					b.setMc_id(rs.getInt(2));
					b.setUser_acc_id(rs.getInt(3));
					b.setMonthly_unit_usage(rs.getFloat(4));
					b.setTotal_bill(rs.getInt(5));
					b.setBilling_status(rs.getString(6));
					
				}
				
			}
			catch(Exception e) {
				System.out.print(e);
	            System.out.println("Error in Serching data!!!");
			}
			
			return b;
		}
		
		public String createbillingmanagement(Billingmanagement b1) {
			String sql = "INSERT INTO `billingmanagement`(`billing_id`, `mc_id`, `user_acc_id`, `monthly_unit_usage`, `total_bill`, `billing_status`) VALUES (?,?,?,?,?,?)";
	        String output ="";
			try {
				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1, b1.getBillig_id());
				st.setInt(2, b1.getMc_id());
				st.setInt(3, b1.getUser_acc_id());
				st.setFloat(4, b1.getMonthly_unit_usage());
				st.setInt(5, b1.getTotal_bill());
				st.setString(6, b1.getBilling_status());
				st.executeUpdate();
	            output = "Inserted Successfully !";
			}
			catch(Exception e) {
				System.out.print(e);
	            output = "adding data into database went wrong!";
	            System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		 //Implementing a method for Delete data for delete operation
	    public String deleteBillingmanagement(int billing_id) {

	        String sql = "DELETE FROM billingmanagement WHERE billing_id =?";
	        String output ="";
	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            st.setInt(1, billing_id);
	            st.executeUpdate();
	            //System.out.println("Successfully deleted the Power Consumption Entry!!!");
	            output = "Deleted Successfully !";
	        }
	        catch (Exception e) {
	            output = "Error While Deleting!";
	            System.err.println(e.getMessage());
	        }
	    return output;
	    }
		

	    //Implementing a method for Delete data for update operation
		
		public String updateBillingmanagement(Billingmanagement b1) {
			 String sql = "UPDATE billingmanagement SET mc_id=?, user_acc_id=?, monthly_unit_usage=?, total_bill=?, billing_status=? WHERE billing_id =?";
		        String output ="";
		        try {
		            PreparedStatement st = con.prepareStatement(sql);
		            st.setInt(1, b1.getMc_id());
		            st.setInt(2, b1.getUser_acc_id());
		            st.setFloat(3, b1.getMonthly_unit_usage());
		            st.setInt(4, b1.getTotal_bill());
		            st.setString(5, b1.getBilling_status());
		            st.setInt(6, b1.getBillig_id());


		            st.executeUpdate();
		            output = "Updated Successfully !";
		        }
		        catch (Exception e) {
		            output = "Database cannot update Power Consumption details !";
		            System.err.println(e.getMessage());
		        }
		        return output;
		    }
}