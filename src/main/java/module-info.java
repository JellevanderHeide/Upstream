module Upstream {
    requires hanyaeger;

    exports org.example;
    exports org.example.entities.tilemaps;

    opens audio;
    opens backdrops;
    opens tiles;
    opens sprites;
}