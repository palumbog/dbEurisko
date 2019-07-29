package com.mycompany.dbeurisko;

import com.mycompany.dbeurisko.Commento;
import com.mycompany.dbeurisko.Evento;
import com.mycompany.dbeurisko.Foto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-28T18:01:37")
@StaticMetamodel(Utente.class)
public class Utente_ { 

    public static volatile SingularAttribute<Utente, String> password;
    public static volatile SingularAttribute<Utente, String> mail;
    public static volatile CollectionAttribute<Utente, Commento> commentoCollection;
    public static volatile CollectionAttribute<Utente, Foto> fotoCollection;
    public static volatile SingularAttribute<Utente, String> username;
    public static volatile CollectionAttribute<Utente, Evento> eventoCollection;

}