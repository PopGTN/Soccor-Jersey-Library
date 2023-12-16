# Soccer Jersey Library #

---
## Development Team

Business Client: Angelo<br/>
Lead Developer: Josh<br/>
Quality Control: Samuel<br/>

---
## Description
The project is for people looking for jerseys from different clubs. They should be able to find a desired jersey by selecting the corresponding Country > Name of the Club > Home or Away > Star Player from a database. Users can just see and pick what they want from the collection.  <br/><br/>

---
## Color
 main-color-light: #00cc00<br/>
 main-color-primary: #fcba03<br/>
 main-color-dark: #008000<br/>
 main-color-text: #fcba03<br/>
 main-color-bright: #ffffff<br/>

---
## Required Fields
Unique ID for every jersey as a PK, what country are they from, the name club/team, is it a Home Kit or an Away Kit, the name of the star player/s on the jersey.
<br/>
- id: int (primary key)
<br/>
- jerseyCode: int (primary key)
<br/>
- nameCountry: String
<br/>
- nameClub: String
<br/>
- nameType: String (Options: nameAway or nameHome)
<br/>
- namePlayer: String
<br/>
....

## Report Details
User should select a specific country and would show all the teams and jerseys from the database.