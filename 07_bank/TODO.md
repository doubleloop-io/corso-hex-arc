## TODO:

Obiettivo: implementa alcuni dei seguenti scenari con particolare attenzione ai building block che compongono
l'Hexagonal Architecture.

Apertura conto:

- [ ] Apertura avvenuto con successo.
- [ ] Apertura fallita per dati anagrafici invalidi.
- [ ] Apertura fallita perché minorenne.

Versamento soldi:

- [ ] Versamento importo avvenuto con successo.
- [ ] Versamento fallito per conto inesistente.
- [ ] Versamento fallito per superamento limite di legge (1000 euro).

Prelievo soldi:

- [ ] Prelievo avvenuto con successo.
- [ ] Prelievo fallito per conto inesistente.
- [ ] Prelievo fallito per mancanza fondi.
- [ ] Prelievo fallito per superamento limite giornaliero.

Chiusura conto:

- [ ] Chiusura avvenuto con successo.
- [ ] Chiusura fallito per conto inesistente.

Abbonamento conto:

- [ ] Ogni primo del mese addebitare 10 euro sul conto.
- [ ] Addebitare 10 euro anche se supera il bilancio dispobile.
- [ ] Addebitare 5 euro se il cliente ha meno di 30 anni.

Bonifico:

- [ ] Trasferimento importo avvenuto con successo.
- [ ] Trasferimento fallito per conto sorgente inesistente.
- [ ] Trasferimento fallito per conto destinatario inesistente.
- [ ] Trasferimento fallito per mancanza fondi.
- [ ] Trasferimento fallito per superamento limite operazione (10.000 euro).