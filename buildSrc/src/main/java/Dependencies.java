public class Dependencies {
    public static class Versions {
        public static final String junitVersion = "5.8.1";
    }

    public static final String junitApi = String.format("org.junit.jupiter:junit-jupiter-api:%s", Versions.junitVersion);
    public static final String junitEngine = String.format("org.junit.jupiter:junit-jupiter-engine:%s", Versions.junitVersion);

}