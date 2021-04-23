package com;
import model.Item; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Items") 
public class ItemService 
{ 
 Item itemObj = new Item(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readItems() 
  { 
  return itemObj.readItems(); 
  }
 
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertItem(@FormParam("itemCode") String itemCode, 
  @FormParam("itemName") String itemName, 
  @FormParam("itemType") String itemType,
  @FormParam("itemCatogory") String itemCatogory, 
  @FormParam("itemPrice") String itemPrice, 
  @FormParam("itemDesc") String itemDesc,
  @FormParam("Brand") String Brand,
  @FormParam("Color") String Color,
  @FormParam("Size") String Size,
  @FormParam("Meterial") String Meterial,
  @FormParam("ItemLocation") String ItemLocation) 
 { 
  String output = itemObj.insertItem(itemCode, itemName,itemType,itemCatogory, itemPrice, itemDesc,Brand,Color,Size,Meterial,ItemLocation); 
 return output; 
 }
 
 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateItem(String itemData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
 //Read the values from the JSON object
  String itemID = itemObject.get("itemID").getAsString(); 
  String itemCode = itemObject.get("itemCode").getAsString(); 
  String itemName = itemObject.get("itemName").getAsString(); 
  String itemType = itemObject.get("itemType").getAsString(); 
  String itemCatogory = itemObject.get("itemCatogory").getAsString(); 
  String itemPrice = itemObject.get("itemPrice").getAsString(); 
  String itemDesc = itemObject.get("itemDesc").getAsString(); 
  String Brand = itemObject.get("Brand").getAsString(); 
  String Color = itemObject.get("Color").getAsString(); 
  String Size = itemObject.get("Size").getAsString(); 
  String Meterial = itemObject.get("Meterial").getAsString(); 
  String ItemLocation = itemObject.get("ItemLocation").getAsString(); 
  String output = itemObj.updateItem(itemID, itemCode, itemName,itemType,itemCatogory, itemPrice, itemDesc,Brand,Color,Size,Meterial,ItemLocation); 
 return output; 
 }

 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteItem(String itemData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String itemID = doc.select("itemID").text(); 
  String output = itemObj.deleteItem(itemID); 
 return output; 
 }
    


}