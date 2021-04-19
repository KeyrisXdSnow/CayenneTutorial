package org.example.cayenne.persistent;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

public class Main {
    public static void main(String[] arg) {
        ServerRuntime serverRuntime = ServerRuntime.builder()
                .addConfig("cayenne-project.xml")
                .build();
        ObjectContext objectContext = serverRuntime.newContext();
    }
}
