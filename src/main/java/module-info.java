module Upstream {
    requires hanyaeger;

    exports org.example;

    opens audio;
    opens backdrops;
    opens tiles;
}