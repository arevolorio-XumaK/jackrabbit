package com.mycompany.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Repository; 
import javax.jcr.RepositoryException;
import javax.jcr.Session; 
import javax.jcr.SimpleCredentials;
import javax.jcr.Value;
import org.apache.jackrabbit.core.TransientRepository; 

public class App 
{
    
    	
    public static void main( String[] args ) throws RepositoryException, FileNotFoundException, IOException {
        Repository repository = new TransientRepository();
        Session session = repository.login(
                                           new SimpleCredentials("admin","admin".toCharArray()));

         try{

                Node root = session.getRootNode();
                root.addNode("nodeA").addNode("nodeB")
                                     .setProperty("message", "Alan!");
                root.addNode("nodeC").setProperty("test", "rama");
                session.save();

                Node ourNode = root.getNode("nodeA/nodeB");
                System.out.println(ourNode.getPath());
                System.out.println("-------------------------------------------------");
                System.out.println(ourNode.getProperty("message").getString());
                Node otherNode = root.getNode("nodeC");
                otherNode.addNode("nodeD");
                Node otherNode2 = root.getNode("nodeC/nodeD");
                InputStream is = new FileInputStream("/home/xumakgt6/Pictures/index.jpeg");
                otherNode2.setProperty("message", is);
                System.out.println(otherNode2.getPath());
                //System.out.println("<<<"+ otherNode2.getProperty("message").getString());
                session.save();
                Property h2 = otherNode2.getProperty("message");
                InputStream h2s = h2.getStream();
                /*BufferedReader in = new BufferedReader(new InputStreamReader( h2s));
                    String sCadena;
                while ((sCadena = in.readLine())!=null) {
                        System.out.println(sCadena);
                } */
                
                
                 OutputStream to = new FileOutputStream("/home/xumakgt6/Pictures/NuevaImagen.jpeg");//path a donde crea la imagen 
                 
                 int inte; 
                  while ((inte = h2s.read()) != -1)
                // Read until EOF
                  {
                    to.write(inte); // write
                  }
                  to.close();
                  h2s.close();
                /*File f = new File("/home/xumakgt6/Pictures/index.jpeg");
                FileOutputStream fos = new FileOutputStream(f);
                session.exportDocSS("", fos, false, false);
                 fos.close();*/
                
               

               

                //root.getNode("nodeA").remove();
                session.save();

        }finally{
            session.logout();
        }
}
    
    public static void dumpToConsole(Node node) throws RepositoryException {

    System.out.println(node.getPath());

    if(node.hasProperties()){
        PropertyIterator props = node.getProperties();
        while(props.hasNext()){
            Property property = (Property) props.next();

            if(property.isMultiple()){
                Value[] values = property.getValues();
                System.out.print(property.getPath()+"="+"[");

                for(Value value : values){
                    System.out.print(value.getString()+",");
                }
                System.out.println("]");
            } else {
                System.out.println(property.getPath()+ "="+property.getString());
            }
        }
    }

    NodeIterator iterator = node.getNodes();

    while(iterator.hasNext()){
        Node next = (Node) iterator.next();

        dumpToConsole(next);
    }
}

}

