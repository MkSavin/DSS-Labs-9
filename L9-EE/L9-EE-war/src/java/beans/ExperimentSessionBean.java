/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb1.enitites.User;
import ejb2.entities.RemoteUser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author MkSavin
 */
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class ExperimentSessionBean implements ExperimentSessionBeanLocal {

    @Resource 
    javax.transaction.UserTransaction ut;
    
    @Resource 
    SessionContext sc;
    
    @EJB
    ejb1.beans.UserFacadeLocal usersLocal;
    @EJB
    ejb2.beans.RemoteUserFacadeLocal usersRemote;
    
    
    List<User> addUsers = new ArrayList<>();
    List<RemoteUser> addRemoteUsers = new ArrayList<>();
    
    List<Integer> deleteUsers = new ArrayList<>();
    
    @Override
    public void addLocalUser(User u) {
        addUsers.add(u);
    }

    @Override
    public void addRemoteUser(RemoteUser u) {
        addRemoteUsers.add(u);
    }

    @Override
    public void deleteLocalUser(int id) {
        deleteUsers.add(id);
    }
    
    @Override
    public boolean addUsers() {
        try {
            ut.begin();
            for (RemoteUser u: addRemoteUsers) {
                usersRemote.create(u);
            }
            for (User u: addUsers) {
                usersLocal.create(u);
            }
            ut.commit();
            return true;
        } catch (Exception e) {
            // some logging code i wanna c here
            return false;
        } finally {
            addUsers.clear();
            addRemoteUsers.clear();
        }
    }
    
    @Override
    public boolean deleteUsers() {
        try {
            ut.begin();
            for (Integer i: deleteUsers) {
                if (i == 6) {
                    throw new EJBException();
                }
                User u = usersLocal.find(i);
                if (u == null) {
                    return false;
                }
                
                usersLocal.remove(u);
            ut.commit();
            // Different transaction pools
            ut.begin();
                RemoteUser ur = usersRemote.find(u.getRemoteId());
                
                if (ur != null) {
                    usersRemote.remove(ur);
                }
            }
            ut.commit();
            return true;
        } catch (Exception e) {
            // some logging code i wanna c here
            return false;
        } finally {
            deleteUsers.clear();
        }
    }
}
