# Varuautomat GUI Applikation

Detta projekt är en Java-baserad GUI-applikation som simulerar en varuautomat. Applikationen ger ett användargränssnitt där användare kan visa, välja och köpa varor från olika kategorier (Böcker, Snacks och Drycker). Bakomliggande lager hanteras av ett system som sparar och laddar varulistan från en lokal fil (`vending_inventory.csv`), vilket gör att lagret består mellan sessioner.


### Genomgång av klasser

#### 1. **UI.java** (Användargränssnittet)

`UI.java` är huvudklassen som ansvarar för hela användargränssnittet. Här hanteras fönstrets layout, menyval för olika kategorier och köp av produkter.

#### 2. **Varor.java** (Abstrakt klass för varor)

`Varor.java` är en abstrakt klass som representerar alla typer av produkter i systemet. Varje produkt har ett namn, ett pris, en kvantitet och en momssats.

#### 3. **Pocketbok.java** (Böcker)

`Pocketbok.java` är en klass som representerar böcker. Denna klass är en subklass till `Varor` och lägger till attributen författare och genre.

#### 4. **Dricka.java** (Drycker)

`Dricka.java` representerar dryckesprodukter. Denna klass är också en subklass till `Varor` och lägger till attributet temperatur.

#### 5. **Snacks.java** (Snacks)

`Snacks.java` representerar snacksprodukter och är en subklass till `Varor`. Förutom grundegenskaperna hos en produkt har snacks även information om de innehåller nötter eller inte samt antalet kalorier.

#### 6. **StorageManager.java** (Lagerhantering)

`StorageManager.java` ansvarar för att hantera lagring och laddning av produktlagret från en fil. Det använder en `Map` där nyckeln är produktkategorin (som en sträng) och värdet är en array av produkter (`Varor[]`).
x

### Funktioner i detalj

1. **Grafisk design**:
   - Varje kategori har egna färger för en visuell separation (t.ex. blått för böcker, grönt för snacks, rött för drycker).
   - Hover-effekter och kursorsymboler används för att göra interaktionen mer intuitiv.
   
2. **Lagerhantering**:
   - Lagret sparas till en fil i binärt format när programmet stängs. När programmet startar igen, laddas lagret från filen så att data består mellan sessioner.
   - `StorageManager` hanterar alla interaktioner med filsystemet och säkerställer att lagret är synkroniserat med vad som visas i gränssnittet.

3. **Interaktion och köplogik**:
   - När en användare väljer en produkt, visas en dialogruta som frågar om köpet ska bekräftas. Om användaren bekräftar, minskar produkten i lager och lagret uppdateras.
   - Om en produkt är slut, visas ett meddelande som informerar användaren om att artikeln inte finns i lager.


## Anpassa lagret

För att anpassa standardlagret (om inget sparat lager finns) kan du modifiera metoden `initializeDefaultInventory()` i `UI.java`. Denna metod sätter standardböcker, drycker och snacks som finns tillgängliga i varuautomaten.

### Exempel:
```java
Varor[] drinks = {
    new Dricka("Cola", 20, 10),
    new Dricka("Apelsinläsk", 20, 8),
