
# WEINSTABEL _ Working progress

08.03.2022

Erstellen der Hauptklassen Befragung, Frage (Abstrakt), FrageBool, FrageNum, FrageText inklusive Konstruktoren, Getter und Setter

Erstellen des TCPSockets, welcher die Clients annimmt
Erstellen des ClientThread, um parallel mit mehrerer Clients zu arbeiten

09.03.2022

Im Client Thread wird nun mit dem Client kommuniziert (Zuordnung zur Befragung)

15.03.2022

Konvertierung der Java Klassen in JSON
Sender der einzelnen Fragen an die Clients im ClientThread

16.03.2022

Eintragen der Antowrten die vom Client kommen in die Datenbank

22.03.2022

Umbau des Servers auf eine grafische Oberfläche mit Javafx

Erstellen der Hauptoberfläche

23.03.2022

Erstellen der Hauptoberfläche inklusive einer Liste aller Befragungen

29.03.2022

Anzeigen aller Fragen zu den Befragungen

30.03.2022

Erstellen einiger neuer Datenbankabfragen im DBController, welche für die grafische Oberfläche benötigt werden

02.04.2022

Erstellen neuer Befragungen durch die grafische Oberfläche, dafür wurde ein Dialog erstellt

Erstellen neuer Datenbankoperationen welche für das Einfügen und Löschen der Befragungen benötigt werden

Einfügen neuer Fragen durch die grafische Oberfläche, dafür wurde ein Dialog erstellt

Erstellen neuer Datenbankoperationen welche für das Einfügen und Löschen der Fragen benötigt werden

Umstellung der Authentifizierung der Clients durch Keys

Erstellen einer Liste aller Keys pro Befragung in der grafischen Oberfläche

Generieren von Keys in der grafischen Oberfläche

Erstellen neuer Datenbankoperationen welche für das Einfügen und Löschen der Keys benötigt werden

CLients melden sich nun mit Keys an, diese werden dann aus der Datenbank entfernt
