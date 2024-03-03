To run the jar file, you have to have installed the javafx sdk (We used javafx 21.0.1) and the Java Development Kit (Various versions on the team,
but the .jar file was generated with JDK version 21.0.1).

Additionally, to run the jar file in the command line (has to be run from the command line, needs the arguments given),
you have to enter this command:

java --module-path C:/<path to sdk>/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml -jar SFWE-301Project.jar
