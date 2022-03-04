# 2122_5BHEL_pscholle_amaxner_dweinsta_manzinge

Polling Project Repository: [GIT](https://github.com/AutoMachtBrumm/2122_5BHEL_pscholle_amaxner_dweinsta_manzinge)

##Distribution of tasks

ANZINGER: Client & JavaFX 

WEINSTABEL: Server

SCHOLLER: Datenbank

MAXNER: Datenübersetzung (JSON to Java Object)


##Definition of the serial interface

There are 3 types of possible questions:

* Yes or No Questions
```Json
{
  "nr": 0,
  "type": "BOOL",
  "text": "...",
  "value": true
}
```

* Text Questions
```Json
{
  "nr": 0,
  "type": "TEXT",
  "text": "...",
  "value": "answer"
}
```

* Numeric Questions
```Json
{
  "nr": 0,
  "type": "NUMERIC",
  "text": "...",
  "min": 1,
  "max": 5,
  "value": 0
}
```