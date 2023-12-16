package info.hccis.SoccorJersey.soap;

import javax.xml.ws.Endpoint;

public class SoccorJerseyLibraryPublisher {
    public static void main(String[] args) {
        Endpoint.publish(
          "http://localhost:8081/soccorjerseylibrary",
           new SoccorJerseyLibraryImpl()
        );

    }
}