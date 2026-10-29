# Automatic Server

A Script that executes nightly that restarts a computer and then turns on a server automaticaly.

## The Problem

I want to be able to host a home lab server on my personal computer that can do scheduled maintenance. I want it to restart every night and boot back up the servers without me having to log onto anything. I also want to be able to SSH into it to run or stop a server I have running.

## How It Works

A paragraph or a few bullets explaining what the software does at a
high level. If you have a diagram, embed it here:

![System diagram](docs/diagram.png)

## Tech Stack

- **Language:** Bash
- **Libraries:** 
- **External tools:** cron, tailscale, jellyfin

## Setup

Step-by-step: how does someone who just cloned your repo get it
running?

```bash
git clone https://github.com/you/your-project
cd your-project
./install.sh   # or whatever
```

## Usage

How to actually run the thing, with examples.

```bash
./backup.sh ~/Documents
```

## Roadmap

- [ ] Control Server Remotely with SSH
- [ ] Print out a summary of what servers are runnung

## Author

Derek Nash, [link text]([https://url.com](https://github.com/dnash27))
