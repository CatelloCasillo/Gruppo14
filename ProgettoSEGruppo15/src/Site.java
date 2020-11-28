/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Enrico
 */
public class Site {
    String id;
    String factory;
    String area;

    public Site(String id, String factory, String area) {
        this.id = id;
        this.factory = factory;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public String getFactory() {
        return factory;
    }

    public String getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "SiTE: "+id+factory+area; //To change body of generated methods, choose Tools | Templates.
    }

 
    
}
