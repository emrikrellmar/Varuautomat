# Varuautomat

## Översikt
Projektet varuautomat är en grafisk användargränssnitt är en inlämmning för kursen programmering 2, en (GUI)-applikation som simulerar en varuautomat. Användare kan köpa böcker, snacks och drycker. Applikationen hanterar lager, priser och momsberäkningar.

## Funktioner
- **Kategorier av produkter**: Användare kan välja mellan böcker, snacks och drycker.
- **Lagerhantering**: Produkten minskar i lager efter ett köp.
- **Priser och moms**: Varor har olika priser och momsberäkningar.
- **Responsiv GUI**: Enkla och användarvänliga knappar för att navigera genom applikationen.

## Teknologier
- Java
- Swing (för GUI)
- Objektorienterad programmering (OOP) för produktklasser

## Struktur
- **`gui`**: Innehåller GUI-komponenten och logik för användargränssnittet.
- **`varuautomat`**: Innehåller abstrakt klass `Varor` och dess subklasser för specifika produkter (Snacks, Dricka, Pocketbok).
