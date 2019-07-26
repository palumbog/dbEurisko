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
import javax.persistence.Lob;
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
@Table(name = "foto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foto.findAll", query = "SELECT f FROM Foto f")
    , @NamedQuery(name = "Foto.findByIdfoto", query = "SELECT f FROM Foto f WHERE f.idfoto = :idfoto")})
public class Foto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idfoto")
    private Integer idfoto;
    @Lob
    @Size(max = 65535)
    @Column(name = "foto")
    private String foto;
    @JoinColumn(name = "creatore", referencedColumnName = "username")
    @ManyToOne
    private Utente creatore;
    @JoinColumn(name = "idevento", referencedColumnName = "idevento")
    @ManyToOne
    private Evento idevento;

    public Foto() {
    }

    public Foto(Integer idfoto) {
        this.idfoto = idfoto;
    }

    public Integer getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(Integer idfoto) {
        this.idfoto = idfoto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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
        hash += (idfoto != null ? idfoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foto)) {
            return false;
        }
        Foto other = (Foto) object;
        if ((this.idfoto == null && other.idfoto != null) || (this.idfoto != null && !this.idfoto.equals(other.idfoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dbeurisko.Foto[ idfoto=" + idfoto + " ]";
    }
    
}
