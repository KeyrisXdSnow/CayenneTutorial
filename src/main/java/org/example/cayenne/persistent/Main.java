package org.example.cayenne.persistent;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.ObjectSelect;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] arg) {
        ServerRuntime serverRuntime = ServerRuntime.builder()
                .addConfig("cayenne-project.xml")
                .build();
        ObjectContext context = serverRuntime.newContext();

        insertToDateBase(context);
        selectAllPaintings(context);
        selectStartGiPaintings(context);
        selectPaintingsByArtistAge(context);


    }

    static void insertToDateBase(ObjectContext context) {
        Artist picasso = context.newObject(Artist.class);
        picasso.setName("Pablo Picasso");
        picasso.setDateIfBirthString("18811025");

        Gallary metropolitan = context.newObject(Gallary.class);
        metropolitan.setName("Metropolitan Museum of Art");

        Painting girl = context.newObject(Painting.class);
        girl.setName("Girl Reading at a Table");
        Painting stein = context.newObject(Painting.class);
        stein.setName("Gertrude Stein");

        picasso.addToPaintings(girl);
        picasso.addToPaintings(stein);

        girl.setGallary(metropolitan);
        stein.setGallary(metropolitan);

        context.commitChanges();

    }

    static void selectAllPaintings(ObjectContext context) {
        List<Painting> paintings = ObjectSelect.query(Painting.class).select(context);
    }
    static void selectStartGiPaintings(ObjectContext context) {
        List<Painting> paintings = ObjectSelect.query(Painting.class)
                .where(Painting.NAME.likeIgnoreCase("gi%"))
                .select(context);
    }
    static void selectPaintingsByArtistAge(ObjectContext context) {
        List<Painting> paintings = ObjectSelect.query(Painting.class)
                .where(Artist.DATE_OF_BIRTH_DATE.lt(LocalDate.of(1900,1,1)))
                .select(context);
    }
}
