import myPackage.Version;

public class VersionExample {

    public static void main(String[] args) {
        Version version = new Version();
        int minorVersion = version.getMinor();
        System.out.println("Minor version: " + minorVersion);
    }
}