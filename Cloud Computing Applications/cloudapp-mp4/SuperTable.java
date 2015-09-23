import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;

import org.apache.hadoop.hbase.TableName;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import org.apache.hadoop.hbase.util.Bytes;

public class SuperTable{

   public static void main(String[] args) throws IOException {

      // Instantiate Configuration class
	  Configuration config = HBaseConfiguration.create();

      // Instaniate HBaseAdmin class
	  HBaseAdmin admin = new HBaseAdmin(config);
      
      // Instantiate table descriptor class
	  HTableDescriptor tableDescriptor = new
      HTableDescriptor(TableName.valueOf("powers"));

      // Add column families to table descriptor
      tableDescriptor.addFamily(new HColumnDescriptor("personal"));
      tableDescriptor.addFamily(new HColumnDescriptor("professional"));
	  
      // Execute the table through admin
      admin.createTable(tableDescriptor);
      System.out.println(" Table created ");
	  
      // Instantiating HTable class
	  HTable hTable = new HTable(config, "powers");
     
      // Repeat these steps as many times as necessary

	      // Instantiating Put class
              // Hint: Accepts a row name

      	      // Add values using add() method
              // Hints: Accepts column family name, qualifier/row name ,value

      // Save the table			  
			  
	  Put p = new Put(Bytes.toBytes("row1"));
		
	  p.add(Bytes.toBytes("personal"),
      Bytes.toBytes("hero"), Bytes.toBytes("superman"));

      p.add(Bytes.toBytes("personal"),
      Bytes.toBytes("power"), Bytes.toBytes("strength"));

      p.add(Bytes.toBytes("professional"),
	  Bytes.toBytes("name"), Bytes.toBytes("clark"));

      p.add(Bytes.toBytes("professional"),
	  Bytes.toBytes("xp"), Bytes.toBytes("100"));
	  
	  hTable.put(p);
	  
	  p = new Put(Bytes.toBytes("row2"));
		
	  p.add(Bytes.toBytes("personal"),
      Bytes.toBytes("hero"), Bytes.toBytes("batman"));

      p.add(Bytes.toBytes("personal"),
      Bytes.toBytes("power"), Bytes.toBytes("money"));

      p.add(Bytes.toBytes("professional"),
	  Bytes.toBytes("name"), Bytes.toBytes("bruce"));

      p.add(Bytes.toBytes("professional"),
	  Bytes.toBytes("xp"), Bytes.toBytes("50"));
	  
	  hTable.put(p);
	  
	  p = new Put(Bytes.toBytes("row3"));
		
	  p.add(Bytes.toBytes("personal"),
      Bytes.toBytes("hero"), Bytes.toBytes("wolverine"));

      p.add(Bytes.toBytes("personal"),
      Bytes.toBytes("power"), Bytes.toBytes("healing"));

      p.add(Bytes.toBytes("professional"),
	  Bytes.toBytes("name"), Bytes.toBytes("logan"));

      p.add(Bytes.toBytes("professional"),
	  Bytes.toBytes("xp"), Bytes.toBytes("75"));
	  
	  hTable.put(p);
	
      // Close table
	  //hTable.close();

      // Instantiate the Scan class
	  Scan scan = new Scan();
     
      // Scan the required columns
	  scan.addColumn(Bytes.toBytes("personal"), Bytes.toBytes("hero"));

      // Get the scan result
	  ResultScanner scanner = hTable.getScanner(scan);

      // Read values from scan result
      // Print scan result
	  for (Result result = scanner.next(); result != null; result = scanner.next())
		  System.out.println(result);
      // Close the scanner
	  scanner.close();
   
      // Htable closer
	  hTable.close();
   }
}

