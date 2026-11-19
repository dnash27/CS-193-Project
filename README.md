# Automatic Server

A Script that executes nightly that restarts a computer at a specified time.

## The Problem

I want to be able to host a home lab server on my personal computer that can do scheduled maintenance. I want it to restart every night and boot back up the servers without me having to log onto anything. I also want to be able to SSH into it to run or stop a server I have running.

## How It Works

It uses a Windows Task Scheduler task to run a powershell .ps1 file that restarts the laptop every night at whatever time you choose. Eventually this task will also be able to safely stop any servers running and start them back up.

![System diagram](docs/diagram.png)

## Tech Stack

- **Language:** Microsoft .ps1, Java
- **Libraries:** Javax.Swing
- **External tools:** WindowsTaskScheduler

## Setup
1. Copy the files and place them onto your root Directory "C:"
2. run the Install.java file in the terminal by running "java Install.java"
3. enter the time you want the PC to be restarted at into the GUI (Example: 5:00)
4. run the command given in the command.text file in powershell as an adminastrator
5. you have now set up a scheduled task that restarts your laptop at a given time everyday.

## Usage

Watch as your PC automatically restarts every night.

## Roadmap

- [ ] Add scripts to stop running Minecraft server and restart once PC boots
- [ ] Create Backups for the Minecraft server world

## Author

Derek Nash, [link text]([https://url.com](https://github.com/dnash27))
