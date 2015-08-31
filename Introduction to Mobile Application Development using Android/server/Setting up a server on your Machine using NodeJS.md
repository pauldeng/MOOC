### Textual Instructions

## Step 1: Installing NodeJS

To install NodeJS on your machine, go to [https://nodejs.org](https://nodejs.org) and click on the Install button. Depending on your computer's platform (Windows, MacOS or Linux), the appropriate installation package is downloaded. Follow along the instructions to install NodeJS on your machine.

## Step 2: Downloading Server Configuration Files

Download server.zip and unzip it at a convenient location on your machine. This will create a sub-folder named server on your machine that contains the configuration files for running a server on your machine. Move to this folder on your machine.

## Step 3: Setting up JSON-Server

Open a terminal window on your machine. If you are using a Windows machine, open a cmd window or PowerShell window with admin privileges.
To ensure that your NodeJS setup is working correctly, type the following at the command prompt to check for the version of Node and NPM.

```
node -v
```

```
npm -v
```


At the command prompt type the following to install a simple JSON server package in NodeJS.

```
npm install -g json-server
```


Note: On MacOS and Linux you may need to add sudo in front of the command. When you are prompted by sudo, just type in your password in the window to proceed with the installation.
Step 4: Starting your Server

To start your server, move to the server folder that you created by unzipping the configuration files in Step 2 above, then type the following at the command prompt.

```
json-server --watch db.json
```


To check that your server is running correctly and serving up the JSON string, type the url below into any browser window.

http://localhost:3000/people

Note down the IP address of your computer because you need to configure this in the code of the next exercise.
You can also cross-check by typing the url below into a browser window to ensure that your server is running correctly. This should show the JSON string in your browser window.

http://<Your machine's IP address>:3000/people

Finding your Machine's IP address

If you are using a Microsoft Windows based machine, you can use the instructions available at [http://windows.microsoft.com/en-us/windows/find-computers-ip-address#1TC=windows-7](http://windows.microsoft.com/en-us/windows/find-computers-ip-address#1TC=windows-7) to find your machine's IP address.
On a Mac, go to System Preferences->Network to find the IP address of your network adapter.