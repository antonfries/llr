#Lager-Leistung-Rechner
### Feature-Requests
- -1 als maximale Menge erlauben
- Beliebiges (auch kein) Trennzeichen für Spaltenangaben erlauben,
wenn innerhalb des Feldes und über die Felder hinaus das gleiche Trennzeichen verwendet wird
- Handling von invaliden Einstellungen in der Windows-Registry bei manueller Bearbeitung des Nutzers
- Grid- bzw. GridBagLayout implementieren mit Abständen zur Außenkomponente
### Codestyle
- Auslagerung der JProgressBar in eigene Komponente mit Schnittstellen
- Auslagerung bzw. Kapselung der individuellen Breiten der J-Textfelder
- Anzeige jeweder Fehlermeldung in Dialog der jeweiligen aktiven GUI,
bzw. Evaluation, wo überhaupt welche Fehler passieren können
- Evaluation, ob eine Fehlermeldung, automatische Verbesserung der Fehleingabe
oder ein Mix von beidem pro Feld passieren soll
- Else-Zweige vermeiden und dadurch Verschachtelungen vermeiden (md-Tool)
### Interesse
- Reihenfolge des Auslesens der Spalten in Tooltips klarstellen
- Handling mit Optionals, Kettenoperationen und Default-Werten
- Übersicht zur Erstellung jeweiliger J-Komponenten und abstrakten Typen erstellen
### Bug-Vermutungen
- Letzte Bedingung zur Anpassung der Grenzen (Utility Zeile 50)
- Initialisiertes Setzen der ProgressBar auf 0 notwendig? (Rechner 43)
- +1 unklar bei zeilenanzahl - min (Rechner 34)
### Bei Finalisierung
```java
public class Datei {
    public Datei() {
        File homeDirectoryFile = FileSystemView.getFileSystemView().getHomeDirectory();
        JFileChooser jFileChooser = new JFileChooser(homeDirectoryFile);
    }
}
```