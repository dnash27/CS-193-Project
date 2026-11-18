# Automatic Server

A Script that executes nightly that restarts a computer and then turns on a server automaticaly.

## The Problem

I want to be able to host a home lab server on my personal computer that can do scheduled maintenance. I want it to restart every night and boot back up the servers without me having to log onto anything. I also want to be able to SSH into it to run or stop a server I have running.

## How It Works

It uses a Windows Task Scheduler task to run a powershell .ps1 file that restarts the laptop every night at whatever time you choose. Eventually this task will also be able to safely stop any servers running and start them back up.

![System diagram](docs/diagram.png)

## Tech Stack

- **Language:** Microsoft .ps1
- **Libraries:** 
- **External tools:** WindowsTaskScheduler, tailscale

## Setup
Copy the files and place them onto your root Directory "C:"

create a WindowsTaskScheduler by:
- pressing WIN + R and running taskschd.msc
- click "create task" on the right (not basic task)
- name it "Nightly Laptop Restart"
- under the General tab choose "Run whether user is logged on or not" and "Run with highest privileges."
- under the Triggers tab click new and begin the task on a scheduale (daily)
- pick a time such as 5:00 AM
- under the actions tab click new and set action to start a program
- set the Program to: Powershell.exe
- set the argument to: (Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass

& "C:\ServerMaintenance\scripts\restart.ps1") copy this whole thing


## Usage

Watch as your PC automatically restarts every night.

## Roadmap

- [ ] Add scripts to stop running Minecraft server and restart once PC boots
- [ ] Create Backups for the Minecraft server world

## Author

Derek Nash, [link text]([https://url.com](https://github.com/dnash27))
