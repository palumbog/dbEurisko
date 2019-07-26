/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbeurisko;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Giuseppe
 */
@Entity
@Table(name = "commento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commento.findAll", query = "SELECT c FROM Commento c")
    , @NamedQuery(name = "Commento.findByIdcommento", query = "SELECT c FROM Commento c WHERE c.idcommento = :idcommento")
    , @NamedQuery(name = "Commento.findByCommento", query = "SELECT c FROM Commento c WHERE c.commento = :commento")})
public class Commento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcommento")
    private Integer idcommento;
    @Size(max = 100)
    @Column(name = "commento")
    private String commento;
    @JoinColumn(name = "creatore", referencedColumnName = "username")
    @ManyToOne
    private Utente creatore;
    @JoinColumn(name = "idevento", referencedColumnName = "idevento")
    @ManyToOne
    private Evento idevento;

    public Commento() {
    }

    public Commento(Integer idcommento) {
        this.idcommento = idcommento;
    }

    public Integer getIdcommento() {
        return idcommento;
    }

    public void setIdcommento(Integer idcommento) {
        this.idcommento = idcommento;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public Utente getCreatore() {
        return creatore;
    }

    public void setCreatore(Utente creatore) {
        this.creatore = creatore;
    }

    public Evento getIdevento() {
        return idevento;
    }

    public void setIdevento(Evento idevento) {
        this.idevento = idevento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcommento != null ? idcommento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commento)) {
            return false;
        }
        Commento other = (Commento) object;
        if ((this.idcommento == null && other.idcommento != null) || (this.idcommento != null && !this.idcommento.equals(other.idcommento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dbeurisko.Commento[ idcommento=" + idcommento + " ]";
    }
    
}
