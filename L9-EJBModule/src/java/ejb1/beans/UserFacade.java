/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb1.beans;

import ejb1.enitites.User;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MkSavin
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "L9-EJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Resource 
    SessionContext sc;
    
    @Override
    @TransactionAttribute(REQUIRES_NEW)
    public void remove(User u) {
        super.remove(u);
        
        if (u.getId().equals(10)) {
            throw new EJBException();
        }
    }
    
    public UserFacade() {
        super(User.class);
    }
    
}
