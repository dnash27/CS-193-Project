public class McTaskInstall {
    public static void main(String[] args) {
        String folder = System.getProperty("user.dir");

        String scriptsFolder = folder + "\\Scripts";

        String MCPath = scriptsFolder + "\\McTask.ps1";
        ProcessBuilder pb = new ProcessBuilder(
                            "powershell.exe",
                            "-NoProfile",
                            "-Command",
                            "Start-Process powershell.exe -Verb RunAs -ArgumentList '-NoProfile -ExecutionPolicy Bypass -File \"" + MCPath + "\"'"
                    );
                    try {
                        pb.start();
                    } catch (Exception ex) {
                        IO.println("Could not start command");
                    }
    }
}