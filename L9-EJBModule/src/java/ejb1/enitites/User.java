/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb1.enitites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MkSavin
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByRemoteId", query = "SELECT u FROM User u WHERE u.remoteId = :remoteId")
    , @NamedQuery(name = "User.findByBalanceLocal", query = "SELECT u FROM User u WHERE u.balanceLocal = :balanceLocal")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "remote_id")
    private int remoteId;
    @Basic(optional = false)
    @Column(name = "balance_local")
    private int balanceLocal;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, int remoteId, int balanceLocal) {
        this.id = id;
        this.remoteId = remoteId;
        this.balanceLocal = balanceLocal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

    public int getBalanceLocal() {
        return balanceLocal;
    }

    public void setBalanceLocal(int balanceLocal) {
        this.balanceLocal = balanceLocal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb2.entities.User[ id=" + id + " ]";
    }
    
}
