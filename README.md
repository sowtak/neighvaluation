# Neighvaluation (Website for reviewing your neighbor)
---This is a review site.

- When you move out of a room, you can post reviews about the people around the room you lived in.
- It can be used as a reference for the next person who moves in.
- ex1) It was comfortable to live in a room with no noise or voices.
- ex2) The people on the floor above me sometimes shut the door too tightly and made a lot of noise when things fell.
- ex3) I can hear multiple people making noise not from this building, but from the building next door.
Things like.

# Feature


- JWT authentication
- Review function (in the forum you created)
- Comment and Like functions

# architecture: domain driven design


- configuration: Configure SecuritiConfiguration
- controller: Returns responses to requests coming from the Angular side, retrieves values from the DTO, and returns them.
- dto: Holds the values converted by the mapper.
- exception: An exception class defined by you.
- mapper: Converts between DTO and repository
- model: Domain model definition
- repository: Define functions to manipulate the DB
- security: JWT
- service: Implement the model behavior



# future


- Deploy
- Add a search function

Translated with www.DeepL.com/Translator (free version)