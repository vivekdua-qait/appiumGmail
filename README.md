Upload Image in Google Photos and Send as an attachment through Gmail

Setting up your machine
Prerequisites before running the script for Appium setup
Set JAVA_HOME as an environment variable

Set ANDROID_HOME as an environment variable - pointing to the directory where Android SDK should be setup

Execute the following scripts to setup your Mac setup_mac.sh or Ubuntu setup_linux.sh machine automatically

The above script will install all dependencies required for implementing / running tests on Android devices. To do the setup for iOS devices, run appium-doctor and see the list of dependencies that are missing, and install the same.

You may be prompted for password or confirmations along the way

Running the tests

Execute the command from terminal:
 mvn clean test
 
 OR

Run the class:
 UploadImageAndSendEmail.java as TestNG Test
 
 
