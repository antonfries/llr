# Lager-Leistung-Rechner
### Feature-Requests
- Datenbank-Persistierung
- Zusätzlich zur ProgressBar einen InputMonitor mit lustigen Sprüchen oder Informationen zu Summanden
- Beliebiges (auch kein) Trennzeichen für Spaltenangaben erlauben,
wenn innerhalb des Feldes und über die Felder hinaus das gleiche Trennzeichen verwendet wird
- Handling von invaliden Einstellungen in der Windows-Registry bei manueller Bearbeitung des Nutzers
- Grid- bzw. GridBagLayout implementieren mit Abständen zur Außenkomponente
### Codestyle
- Anzeige jeweder Fehlermeldung in Dialog der jeweiligen aktiven GUI,
bzw. Evaluation, wo überhaupt welche Fehler passieren können
- Evaluation, ob eine Fehlermeldung, automatische Verbesserung der Fehleingabe
oder ein Mix von beidem pro Feld passieren soll, Idealfall:
```java
public class Test {
    public Test() {
        zeileAnfang = Math.max(1, zeileAnfang);
    }
}
```
### Bei Finalisierung
```java
public class Datei {
    public Datei() {
        File homeDirectoryFile = FileSystemView.getFileSystemView().getHomeDirectory();
        JFileChooser jFileChooser = new JFileChooser(homeDirectoryFile);
    }
}
```
