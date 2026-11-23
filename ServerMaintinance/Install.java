import javax.swing.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InstallGUI {
    public static void main(String[] args) {
        String folder = System.getProperty("user.dir");

        String scriptsFolder = folder + "\\Scripts";

        String installPath = scriptsFolder + "\\install.ps1";
        String shutdownPath = scriptsFolder + "\\shutdown.ps1";
        String uninstallPath = scriptsFolder + "\\uninstallTask.ps1";

        SwingUtilities.invokeLater(() -> {
            AtomicBoolean installed = new AtomicBoolean(true);
            JFrame frame = new JFrame("Install PC Auto Restart");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            JTextField restartTimeField = new JTextField(20);
            restartTimeField.setText("Enter A Restart Time");

            JButton enter = new JButton("Install");

            JLabel timeLabel = new JLabel("Time: ");

            JRadioButton pm = new JRadioButton("PM");
            JRadioButton am = new JRadioButton("AM");
            ButtonGroup ampm = new ButtonGroup();
            ampm.add(pm);
            ampm.add(am);
            am.setSelected(true);

            JButton uninstall = new JButton("uninstall");

            panel.add(restartTimeField);
            panel.add(am);
            panel.add(pm);
            panel.add(enter);
            panel.add(uninstall);
            panel.add(timeLabel);
            frame.add(panel);

            uninstall.addActionListener(e -> {
                if (installed.get()) {
                    timeLabel.setText("Uninstalling . . .");
                    ProcessBuilder pb = new ProcessBuilder(
                            "powershell.exe",
                            "-NoProfile",
                            "-Command",
                            "Start-Process powershell.exe -Verb RunAs -ArgumentList '-NoProfile -ExecutionPolicy Bypass -File \""+ uninstallPath + "\"'"
                    );
                    try {
                        pb.start();
                        installed.set(true);
                    } catch (Exception ex) {
                        IO.println("Could not start command");
                    }
                    timeLabel.setText("Uninstalled successfully");
                    installed.set(false);

                } else {
                    timeLabel.setText("Already Uninstalled");
                }
            });

            enter.addActionListener(e -> {
                boolean valid = true;
                String time = restartTimeField.getText().trim();
                String suffix = am.isSelected() ? "AM" : "PM";
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm a", Locale.US);
                    LocalTime.parse(time + " " + suffix, dtf);
                } catch (Exception ex) {
                    valid = false;
                }
                String restartTime = "";

                if (valid && am.isSelected()) {
                    restartTime = restartTimeField.getText() + "AM";
                    timeLabel.setText("Time: " + restartTime);
                } else if (valid && pm.isSelected()){
                    restartTime = restartTimeField.getText() + "PM";
                    timeLabel.setText("Time: " + restartTime);
                } else {
                    timeLabel.setText("Please Enter a valid time");
                }
                if (valid) {
                    try (PrintWriter pw = new PrintWriter(new FileOutputStream(installPath))) {
                        pw.println("$action = New-ScheduledTaskAction `");
                        pw.println("    -Execute \"powershell.exe\" `");
                        pw.println("    -Argument \"-ExecutionPolicy Bypass -Command `\"& '" + shutdownPath + "'`\"\"");
                        pw.println();
                        pw.println("$trigger = New-ScheduledTaskTrigger -Daily -At \""
                                + restartTime + "\"");
                        pw.println();
                        pw.println("Register-ScheduledTask `");
                        pw.println("    -TaskName \"Scheduled Server Restart\" `");
                        pw.println("    -Action $action `");
                        pw.println("    -Trigger $trigger `");
                        pw.println("    -RunLevel Highest `");
                        pw.println("    -Force");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        timeLabel.setText("Writing to file failed");
                    }
                    ProcessBuilder pb = new ProcessBuilder(
                            "powershell.exe",
                            "-NoProfile",
                            "-Command",
                            "Start-Process powershell.exe -Verb RunAs -ArgumentList '-NoProfile -ExecutionPolicy Bypass -File \"" + installPath + "\"'"
                    );
                    try {
                        pb.start();
                        installed.set(true);
                    } catch (Exception ex) {
                        IO.println("Could not start command");
                    }
                    timeLabel.setText("Installed Successfully");
                }
            });

            frame.setVisible((true));
        });
    }
}
