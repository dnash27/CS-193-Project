$serverFolder = "C:\MinecraftServer"

Start-Process cmd.exe -ArgumentList "/k cd /d `"$serverFolder`" && java -Xms4G -Xmx4G -jar fabric-server-mc.26.3-loader.0.20.1-launcher.1.1.2.jar nogui"