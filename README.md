telnet-server
=============

A simple telnet-like server.

INSTALLATION

1. Clone the repository or get the code somehow.
2. Ensure you have Java and Maven (minimum 2.2.1) setup correctly.
3. Run 'mvn clean install'.
4. That's it.

RUNNING THE SERVER

1. Use the following command:

java -jar {name}-jar-with-dependencies.jar -port {port-number}

2. The server will start listening on the given port if it's empty.
3. Now you can connect to the server and start using the commands.

STOPPING THE SERVER

Use Ctrl+C or the 'stop' command.


CAPABILITIES

The server provides the following basic commands:
1. ls - list the contents of a directory.
2. pwd - prints the working directory.
3. cd - changes to a directory for navigation.
4. mkdir - creates a directory.

The usage of the commands is shown in the greeting message from the server and every time after a wrong command.


FUTURE PLANS

1. Implement proper stopping of the server, e. g. using a stop.jar.
