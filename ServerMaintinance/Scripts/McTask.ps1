$action = New-ScheduledTaskAction `
    -Execute "powershell.exe" `
    -Argument "-NoProfile -ExecutionPolicy Bypass -File `"C:\ServerMaintenance\Scripts\start-minecraft.ps1`""

$trigger = New-ScheduledTaskTrigger -AtLogOn

Register-ScheduledTask `
    -TaskName "MC Server Start" `
    -Action $action `
    -Trigger $trigger `
    -RunLevel Highest `
    -Force