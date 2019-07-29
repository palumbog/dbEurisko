/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbeurisko;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Giuseppe
 */
@Entity
@Table(name = "evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
    , @NamedQuery(name = "Evento.findByIdevento", query = "SELECT e FROM Evento e WHERE e.idevento = :idevento")
    , @NamedQuery(name = "Evento.findByNome", query = "SELECT e FROM Evento e WHERE e.nome = :nome")
    , @NamedQuery(name = "Evento.findByTipo", query = "SELECT e FROM Evento e WHERE e.tipo = :tipo")
    , @NamedQuery(name = "Evento.findByDataInizio", query = "SELECT e FROM Evento e WHERE e.dataInizio = :dataInizio")
    , @NamedQuery(name = "Evento.findByDataFine", query = "SELECT e FROM Evento e WHERE e.dataFine = :dataFine")
    , @NamedQuery(name = "Evento.findByLuogoX", query = "SELECT e FROM Evento e WHERE e.luogoX = :luogoX")
    , @NamedQuery(name = "Evento.findByLuogoY", query = "SELECT e FROM Evento e WHERE e.luogoY = :luogoY")
    , @NamedQuery(name = "Evento.findByMiPiace", query = "SELECT e FROM Evento e WHERE e.miPiace = :miPiace")
    , @NamedQuery(name = "Evento.findByNonMiPiace", query = "SELECT e FROM Evento e WHERE e.nonMiPiace = :nonMiPiace")
    , @NamedQuery(name = "Evento.findByCitta", query = "SELECT e FROM Evento e WHERE e.citta = :citta")
    , @NamedQuery(name = "Evento.findByOraInizio", query = "SELECT e FROM Evento e WHERE e.oraInizio = :oraInizio")
    , @NamedQuery(name = "Evento.findByOraFine", query = "SELECT e FROM Evento e WHERE e.oraFine = :oraFine")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idevento")
    private Integer idevento;
    @Lob
    @Size(max = 65535)
    @Column(name = "descrizione")
    private String descrizione;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 1)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "data_inizio")
    private String dataInizio;
    @Size(max = 45)
    @Column(name = "data_fine")
    private String dataFine;
    @Size(max = 100)
    @Column(name = "luogo_x")
    private String luogoX;
    @Size(max = 100)
    @Column(name = "luogo_y")
    private String luogoY;
    @Column(name = "miPiace")
    private Integer miPiace;
    @Column(name = "nonMiPiace")
    private Integer nonMiPiace;
    @Size(max = 100)
    @Column(name = "citta")
    private String citta;
    @Size(max = 45)
    @Column(name = "ora_inizio")
    private String oraInizio;
    @Size(max = 45)
    @Column(name = "ora_fine")
    private String oraFine;
    @JoinColumn(name = "creatore", referencedColumnName = "username")
    @ManyToOne
    private Utente creatore;
    @OneToMany(mappedBy = "idevento")
    private Collection<Foto> fotoCollection;
    @OneToMany(mappedBy = "idevento")
    private Collection<Commento> commentoCollection;

    public Evento() {
    }

    public Evento(Integer idevento) {
        this.idevento = idevento;
    }

    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public String getLuogoX() {
        return luogoX;
    }

    public void setLuogoX(String luogoX) {
        this.luogoX = luogoX;
    }

    public String getLuogoY() {
        return luogoY;
    }

    public void setLuogoY(String luogoY) {
        this.luogoY = luogoY;
    }

    public Integer getMiPiace() {
        return miPiace;
    }

    public void setMiPiace(Integer miPiace) {
        this.miPiace = miPiace;
    }

    public Integer getNonMiPiace() {
        return nonMiPiace;
    }

    public void setNonMiPiace(Integer nonMiPiace) {
        this.nonMiPiace = nonMiPiace;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(String oraInizio) {
        this.oraInizio = oraInizio;
    }

    public String getOraFine() {
        return oraFine;
    }

    public void setOraFine(String oraFine) {
        this.oraFine = oraFine;
    }

    public Utente getCreatore() {
        return creatore;
    }

    public void setCreatore(Utente creatore) {
        this.creatore = creatore;
    }

    @XmlTransient
    public Collection<Foto> getFotoCollection() {
        return fotoCollection;
    }

    public void setFotoCollection(Collection<Foto> fotoCollection) {
        this.fotoCollection = fotoCollection;
    }

    @XmlTransient
    public Collection<Commento> getCommentoCollection() {
        return commentoCollection;
    }

    public void setCommentoCollection(Collection<Commento> commentoCollection) {
        this.commentoCollection = commentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevento != null ? idevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idevento == null && other.idevento != null) || (this.idevento != null && !this.idevento.equals(other.idevento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dbeurisko.Evento[ idevento=" + idevento + " ]";
    }
    
}
