/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

/**
 *
 * @author DIENMAYXANH
 */
public class StudentClass {
    private String id;
    private String className;

    public StudentClass(String id, String className) {
        this.id = id;
        this.className = className;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    
}
