/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb2.beans;

import ejb2.entities.RemoteUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MkSavin
 */
@Stateless
public class RemoteUserFacade extends AbstractFacade<RemoteUser> implements RemoteUserFacadeLocal {

    @PersistenceContext(unitName = "L9-EJBModule-2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RemoteUserFacade() {
        super(RemoteUser.class);
    }
    
}
