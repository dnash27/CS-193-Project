# Automatic Server

A Script that executes nightly that restarts a computer at a specified time.

## The Problem

I want to be able to host a home lab server on my personal computer that can do scheduled maintenance. I want it to restart every night and boot back up the servers without me having to log onto anything.

## How It Works

It uses a Java Swing GUI to ask the user what time they would like to restart their computer daily. It then writes to a Microsoft powershell file (.ps1) that creates a Windows Task Scheduler task. The task then restarts the computer at the desired time.
![System diagram](docs/diagram.png)

## Tech Stack

- **Language:** Microsoft .ps1, Java
- **Libraries:** Javax.Swing
- **External tools:** WindowsTaskScheduler

## Setup
* NOTE: you must be on a windows device to run this

1. Copy the files and place them anywhere on your computer
2. go inside the ServerMaintinance folder and run the Install.java file in the terminal by running "java Install.java"
3. enter the time you want the PC to be restarted at into the GUI (Example: 5:00) and choose AM or PM
4. Allow the usage of Powershell as Adminastrator
5. you have now set up a scheduled task that restarts your laptop at a given time everyday.

*Uninstalling: press the uninstall button on the Java GUI and allow it to run as administrator

## Usage
- choose whenever you want your pc to restart daily
- uninstall whenever you want
- to check if it is working properly:
1. press windows key + r and run "taskschd.msc"
2. press enter and click on Task Scheduler Library on the left
3. scroll down and look for Scheduled Server Restart
4. from there you can run it now or delete it

## Roadmap

- [ ] Add scripts to stop running Minecraft server and restart once PC boots
- [ ] Create Backups for the Minecraft server world

## Author

Derek Nash, [link text]([https://url.com](https://github.com/dnash27))
