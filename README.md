# Server-Connection-Checker

Checks the connections of servers listed within a text file.

---


# Installation
Head on over to the releases tab (when I make a release) and download the recent .jar.


---


# Usage
### All distros (should) run the same.

Run the jar file with ```java -jar serverconnectionchecker-<RECENT-VERSION>.jar```. Of course, replacing <RECENT-VERSION> with the recent version number (if it's something like "4.2-SNAPSHOT" then put "serverconnectionchecker-4.2-SNAPSHOT.jar")

This will run the jar file and create a text file, upon first run, asking for IPs and Ports (IP:PORT) to search. After this, if that text file is empty, it will redo the first-time launch.

If you want to use a custom text file (which follows the format of IP:PORT) then proceed to do ```java -jar serverconnectionchecker-<RECENT-VERSION>.jar --file="<Insert File Name Here>.txt"```. This should try find that file and read the values within it.


---


# License


<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>.
