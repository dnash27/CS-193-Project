import javax.swing.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Install {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Install PC Auto Restart");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            JTextField restartTimeField = new JTextField(20);
            restartTimeField.setText("Enter A Restart Time");

            JButton enter = new JButton("Enter");

            JLabel timeLabel = new JLabel("Time: ");

            JRadioButton pm = new JRadioButton("PM");
            JRadioButton am = new JRadioButton("AM");
            ButtonGroup ampm = new ButtonGroup();
            ampm.add(pm);
            ampm.add(am);
            am.setSelected(true);

            panel.add(restartTimeField);
            panel.add(enter);
            panel.add(am);
            panel.add(pm);
            panel.add(timeLabel);
            frame.add(panel);

            enter.addActionListener(e -> {
                boolean valid = true;
                String time = restartTimeField.getText();
                int length = time.length();
                String[] characters = new String[length];
                for (int i = 0; i < length; i++) {
                    characters[i] = "" + time.charAt(i);
                }
                if (length < 4 || length > 5) {
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
                    try (PrintWriter pw = new PrintWriter(new FileOutputStream("C:\\ServerMaintinance\\Scripts\\install.ps1"))) {
                        pw.println("$action = New-ScheduledTaskAction `");
                        pw.println("    -Execute \"powershell.exe\" `");
                        pw.println("    -Argument \"-ExecutionPolicy Bypass -Command `\"& 'C:\\ServerMaintinance\\Scripts\\shutdown.ps1'`\"\"");
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
                    ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-NoProfile", "-Command",
                            "Start-Process powershell.exe -Verb RunAs -ArgumentList '-NoProfile -ExecutionPolicy Bypass -File C:\\ServerMaintinance\\Scripts\\install.ps1'"
                    );
                    try {
                        pb.start();
                    } catch (Exception ex) {
                        timeLabel.setText("Could not start command");
                    }
                }
            });

            frame.setVisible((true));
        });
    }
}
