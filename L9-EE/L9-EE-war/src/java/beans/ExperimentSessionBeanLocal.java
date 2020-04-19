/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Local;

/**
 *
 * @author MkSavin
 */
@Local
public interface ExperimentSessionBeanLocal {
    
    public void addLocalUser(ejb1.enitites.User u);
    public void addRemoteUser(ejb2.entities.RemoteUser u);
    
    public void deleteLocalUser(int id);

    public boolean addUsers();
    public boolean deleteUsers();
    
}
