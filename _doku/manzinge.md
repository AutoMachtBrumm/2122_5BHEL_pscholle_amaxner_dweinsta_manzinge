
# ANZINGER _ Working progress

* 8.3.2022: Client Class erstellt
* 15.3.2022: JSON Handling & Client communication with Server
* 22.3.2022: JavaFX Implementation and Runnable Client


* 24.3.2022:
  * Der Client kann nach dem Senden eines Key an den Server mit der Befragung start.
    Der Server sendet jeweils eine Frage. Passend zum Fragetype wird das richtige FXML-File geöffnet
    und über den ViewController werden alle Antworten eingelesen an den QuestionHandler weitergegeben und
    schließlich an den Server gesendet. Dieser Vorgang wird so oft wiederholt bis der Server "ENDE" sendet.


* 25.3.2022:
  * Der Client wurde in einem Singleton - Pattern geändert, damit das Client-Object von jedem FXML-Controller
    aufgerufen werden kann. Zudem wurde die Aufgabe des QuestionHandler der Klasse ViewControl übergeben
  * ViewControl: Verbindungsmodule von Frontend zu Backend
    * Zuständig für den Scene wechsel (je nach Fragen Typ)
    * Verwaltung des JSON-Object (von Server) und der Antworten