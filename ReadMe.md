# Movida
**Di Pasquale Alessio, Colamonaco Stefano**

**Strutture Dati: Hash e AVL**

**Algoritmi: SelectionSort e QuickSort**

## MOVIDA (MOVIes Data and Algorithms) è un'applicazione Java per interagire con una knowledge-base a tema cinema

MOVIDA permette di:
-  importare una knowledge-base
-  visualizzare informazioni su film e attori
-  cercare informazioni in base a criteri diversi

## MovidaCore
MovidaCore è la classe entry point dell'applicazione e sarà testata in modo automatico

Un'istanza della classe MovidaCore permette di caricare e successivamente recuperare le informazioni relative a film, attori e registi
- La classe MovidaCore implementa alcune interfacce, che descrivono tre gruppi di operazioni:
    -  IMovidaDB: Caricamento in memoria e lettura dei dati
    -  IMovidaConfig: Scelta degli algoritmi e strutture dati da
    usare
    -  IMovidaSearch: Ricerca delle informazioni

## Dati in MOVIDA
  Il modello dei dati è (molto) semplificato e prevede due classi:
- Movie: informazioni relative ad un film 
  - Titolo
  - Anno
  - Regista
  - Lista di Attori •  Voti su IMDb
- Person: informazioni relative ad un attore o un regista 
  - Nome
  
## Caricamento di dati da file
  -  MOVIDA definisce un formato testuale per il salvataggio dei dati su file
  -  Ogni film è descritto da un record separato dal record successivo con una riga vuota
  -  Ogni record è composto da diversi campi (coppie chiave:valore), uno per riga
  -  MovidaCore espone i metodi per caricare/salvare i dati in questo formato
  -  I dettagli delle operazioni di caricamento e salvataggio sono descritti nell'interfaccia IMovidaDB
  
## Scelta algoritmi e strutture dati
  -  MovidaCore espone i metodi per selezionare
  l'algoritmo e il dizionario da usare a run-time, descritti
  nell'interfaccia IMovidaConfig •  setSort(...) e setMap(...)
  - Le possibili scelte sono espresse tramite le enumeration MapImplementation e SortingAlgorithm
  
## Ricerche
  -  Dopo aver caricato le informazioni in un'istanza di MovidaCore è possibile interrogare la knowledge-base invocando i metodi dell'interfaccia IMovidaSearch
  -  MovidaCore usa l'algoritmo e il dizionario attivo (ultima configurazione)
  -  Il gruppo è libero di scegliere come integrare le parti sviluppate, quando invocare i metodi, e come strutturare e memorizzare le informazioni all'interno della classe MovidaCore

## Come organizzare i file del progetto
  -  Il package movida.commons contiene le interfacce ed enumerazioni comuni
  -  Il progetto deve essere contenuto in un package separato che include la classe MovidaCore
  -  Usare come nome del package la concatenazione dei cognomi dei membri del gruppo, tutto in minuscolo, senza spazi, apostrofi, accenti

## Collaborazioni in MOVIDA
  -  MOVIDA permette di osservare le collaborazioni tra attori, intese come partecipazioni agli stessi film

  **Classe Collaboration**
  -  Introduciamo una classe Collaboration che rappresenta
  appunto la collaborazione diretta tra due attori
  -  La classe memorizza i due attori e l'insieme di tutti i film in cui hanno collaborato
  -  Nella realtà si potrebbe estendere con ulteriori informazioni sulla collaborazione, ad esempio premi vinti dagli attori, etc.
  -  Per ogni coppia di attori esiste quindi una sola Collaboration
  -  Ogni collaborazione è caratterizzata da un punteggio (score)
  -  Per semplicità il punteggio è la media tra i voti di tutti i film in cui la coppia di attori ha collaborato
  -  Anche qui si potrebbe usare uno score diverso ma l'impostazione generale non cambia
  -  E' possibile identificare gruppi di attori che hanno collaborato tra loro
  -  Possiamo assumere, per semplicità, che tutti gli attori di un gruppo abbiano lo stesso produttore cinematografico
  -  Un produttore ha la necessità di organizzare al meglio le collaborazioni in un gruppo di attori per massimizzarne il successo
  
  **Collaborazioni caratteristiche di un team**
  -  Vogliamo fornire al produttore un metodo per individuare un "insieme di collaborazioni caratteristiche di un team" (ICC) cioè un insieme minimale di collaborazioni che coinvolgono tutti i membri del team
  -  Per definizione ICC(T) soddisfa le seguenti proprietà:
  -  se si elimina una collaborazione da ICC(T) non è possibile raggiungere,
  tramite collaborazione diretta o indiretta, tutti i membri del team T
  -  non è possibile aggiungere una collaborazione a ICC(T) che collega due attori già presenti in collaborazioni di ICC(T)
  -  Si noti che se T include K attori allora ICC(T) ha K-1 collaborazioni. Inoltre T può avere diversi insiemi di collaborazioni caratteristiche.
  -  MovidaCore permette di identificare l'insieme di collaborazioni caratteristiche di un team con lo score complessivo più alto
  
  MovidaCore deve implementare anche l'interfaccia IMovidaCollaborations per:
  -  Identificare i collaboratori diretti di un attore
  -  Ricostruire il team di un attore
  -  Identificare l'insieme di collaborazioni caratteristiche del team di un attore, che massimizza lo score complessivo
