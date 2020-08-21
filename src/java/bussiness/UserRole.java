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
public enum UserRole {
    ADMIN,
    GIAOVU,
    GIAOVIEN;
    @Override
    public String toString() {
        String output = "";
        switch (this.ordinal()) {
            case 0:
                output = "Quản trị";
                break;
            case 1:
                output = "Giáo vụ";
                break;
            case 2:
                output = "Giáo viên";
                break;
        }
        return output;
    }
}
