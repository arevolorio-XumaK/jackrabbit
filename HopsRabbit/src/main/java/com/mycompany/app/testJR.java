/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.app;
import javax.jcr.Node;
import javax.jcr.Repository; 
import javax.jcr.RepositoryException;
import javax.jcr.Session; 
import javax.jcr.SimpleCredentials;
import org.apache.jackrabbit.core.TransientRepository; 
/**
 *
 * @author xumakgt6 // AlanRev
 */
public class testJR {
     public static void main( String[] args ) throws RepositoryException {
        Repository repository = new TransientRepository();
        Session session = repository.login(
                                           new SimpleCredentials("admin","admin".toCharArray()));

         try{

                Node root = session.getRootNode();
                Node ourNode = root.getNode("nodeA/nodeB");
                System.out.println(ourNode.getPath());
                System.out.println("Message:"+ourNode.getProperty("message").getString());
                Node otherNode = root.getNode("nodeC");
                System.out.println(ourNode.getPath());
                System.out.println("Message:"+ourNode.getProperty("message").getString());
            
                
        }finally{
            session.logout();
        }
     }

}
