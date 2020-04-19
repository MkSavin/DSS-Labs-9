/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb2.beans;

import ejb2.entities.RemoteUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MkSavin
 */
@Local
public interface RemoteUserFacadeLocal {

    void create(RemoteUser user);

    void edit(RemoteUser user);

    void remove(RemoteUser user);

    RemoteUser find(Object id);

    List<RemoteUser> findAll();

    List<RemoteUser> findRange(int[] range);

    int count();
    
}
