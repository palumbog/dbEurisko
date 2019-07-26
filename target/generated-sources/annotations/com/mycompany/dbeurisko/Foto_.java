package com.mycompany.dbeurisko;

import com.mycompany.dbeurisko.Evento;
import com.mycompany.dbeurisko.Utente;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-24T15:15:30")
@StaticMetamodel(Foto.class)
public class Foto_ { 

    public static volatile SingularAttribute<Foto, Integer> idfoto;
    public static volatile SingularAttribute<Foto, Evento> idevento;
    public static volatile SingularAttribute<Foto, String> foto;
    public static volatile SingularAttribute<Foto, Utente> creatore;

}