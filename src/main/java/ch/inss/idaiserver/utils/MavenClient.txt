package ch.inss.idaiserver.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.maven.shared.invoker.*;


public class MavenClient {

    public static void doMaven() {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile( new File( "pom.xml" ) );
        LinkedList<String> list = new LinkedList<String>();
        list.add("clean");
        list.add("test");
        request.setGoals( list );
         
        Invoker invoker = new DefaultInvoker();
        try {
            invoker.execute( request );
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }
    
}
