package PrimoPackege;

import java.util.Objects;

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
    private String id;
    private String factory;
    private String area;

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
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Site other = (Site) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.factory, other.factory)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        return true;
    }
  
}
