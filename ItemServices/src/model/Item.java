package model; 
import java.sql.*; 
public class Item 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/product", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String insertItem(String code, String name,String type,String catogory, String price, String desc, String brand, String color, String size, String meterial, String location) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into items (`itemID`,`itemCode`,`itemName`,`itemType`,`itemCatogory`,`itemPrice`,`itemDesc`,`Brand`,`Color`,`Size`,`Meterial`,`ItemLocation`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, code); 
 preparedStmt.setString(3, name); 
 preparedStmt.setString(4, type); 
 preparedStmt.setString(5, catogory); 
 preparedStmt.setDouble(6, Double.parseDouble(price)); 
 preparedStmt.setString(7, desc); 
 preparedStmt.setString(8, brand); 
 preparedStmt.setString(9, color); 
 preparedStmt.setString(10, size); 
 preparedStmt.setString(11, meterial); 
 preparedStmt.setString(12, location); 
// execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String readItems() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='0'><tr><th>Item Code</th><th>Item Name</th>" +
		 "<th>Item Type</th>" + 
		 "<th>Item Category</th>" + 
 "<th>Item Price</th>" + 
 "<th>Item Description</th>"
 +"<th>Brand</th>" 
 +"<th>Color</th>"
 +"<th>Size</th>"
 +"<th>Meterial</th>"
 +"<th>Location</th>"; 
 
 String query = "select * from items"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String itemID = Integer.toString(rs.getInt("itemID")); 
 String itemCode = rs.getString("itemCode"); 
 String itemName = rs.getString("itemName");
 String itemType = rs.getString("itemType"); 
 String itemCatogory = rs.getString("itemCatogory"); 
 String itemPrice = Double.toString(rs.getDouble("itemPrice")); 
 String itemDesc = rs.getString("itemDesc"); 
 String Brand = rs.getString("Brand"); 
 String Color = rs.getString("Color"); 
 String Size = rs.getString("Size"); 
 String Meterial = rs.getString("Meterial"); 
 String ItemLocation = rs.getString("ItemLocation"); 
 // Add into the html table
 output += "<tr><td>" + itemCode + "</td>"; 
 output += "<td>" + itemName + "</td>"; 
 output += "<td>" + itemType + "</td>"; 
 output += "<td>" + itemCatogory + "</td>"; 
 output += "<td>" + itemPrice + "</td>"; 
 output += "<td>" + itemDesc + "</td>"; 
 output += "<td>" + Brand + "</td>"; 
 output += "<td>" + Color + "</td>"; 
 output += "<td>" + Size + "</td>"; 
 output += "<td>" + Meterial + "</td>"; 
 output += "<td>" + ItemLocation + "</td>"; 
 // buttons
  
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the items."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String updateItem(String ID, String code, String name, String type, String catogory, String price, String desc, String brand, String color, String size, String meterial, String location)
{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE items SET itemCode=?,itemName=?,itemType=?,itemCatogory=?,itemPrice=?,itemDesc=?,Brand=?,Color=?,Size=?,Meterial=?,ItemLocation=? WHERE itemID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, code); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, type); 
	 preparedStmt.setString(4, catogory); 
	 preparedStmt.setDouble(5, Double.parseDouble(price)); 
	 preparedStmt.setString(6, desc);
	 preparedStmt.setString(7, brand); 
	 preparedStmt.setString(8, color); 
	 preparedStmt.setString(9, size); 
	 preparedStmt.setString(10, meterial); 
	 preparedStmt.setString(11, location); 
	 preparedStmt.setInt(12, Integer.parseInt(ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String deleteItem(String itemID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from items where itemID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(itemID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	} 