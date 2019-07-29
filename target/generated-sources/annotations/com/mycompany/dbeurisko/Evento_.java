package com.mycompany.dbeurisko;

import com.mycompany.dbeurisko.Commento;
import com.mycompany.dbeurisko.Foto;
import com.mycompany.dbeurisko.Utente;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-28T18:01:37")
@StaticMetamodel(Evento.class)
public class Evento_ { 

    public static volatile SingularAttribute<Evento, Integer> nonMiPiace;
    public static volatile SingularAttribute<Evento, Integer> idevento;
    public static volatile SingularAttribute<Evento, String> tipo;
    public static volatile SingularAttribute<Evento, Integer> miPiace;
    public static volatile SingularAttribute<Evento, String> nome;
    public static volatile SingularAttribute<Evento, String> oraFine;
    public static volatile SingularAttribute<Evento, String> descrizione;
    public static volatile SingularAttribute<Evento, String> dataInizio;
    public static volatile SingularAttribute<Evento, String> dataFine;
    public static volatile SingularAttribute<Evento, Utente> creatore;
    public static volatile CollectionAttribute<Evento, Commento> commentoCollection;
    public static volatile SingularAttribute<Evento, String> luogoX;
    public static volatile SingularAttribute<Evento, String> oraInizio;
    public static volatile SingularAttribute<Evento, String> luogoY;
    public static volatile CollectionAttribute<Evento, Foto> fotoCollection;
    public static volatile SingularAttribute<Evento, String> citta;

}