BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO classes (class_name)
VALUES ('artificer'),('barbarian'), ('bard'), ('cleric'), ('druid'), ('fighter'), ('monk'),
	   ('paladin'), ('ranger'), ('rogue'), ('sorcerer'), ('warlock'), ('wizard');

INSERT INTO subclasses (subclass_name, class_id)
VALUES ('Alchemist', 1), ('Armorer', 1), ('Artillerist', 1), ('Battle Smith', 1),
('Path of the Ancestral Guardian', 2), ('Path of the Battlerager', 2),
('Path of the Beast', 2), ('Path of the Berserker', 2), ('Path of the Giant', 2),
('Path of the Storm Herald', 2), ('Path of the Totem Warrior', 2), ('Path of the Zealot', 2), ('Path of Wild Magic', 2),
('College of Creation', 3), ('College of Eloquence', 3), ('College of Glamour', 3), ('College of Lore', 3),
('College of Spirits', 3), ('College of Swords', 3), ('College of Valor', 3), ('College of Whispers', 3),
('Arcana Domain', 4), ('Death Domain', 4), ('Forge Domain', 4), ('Grave Domain', 4),
('Knowledge Domain', 4), ('Life Domain', 4), ('Light Domain', 4), ('Nature Domain', 4),
('Order Domain', 4), ('Peace Domain', 4), ('Tempest Domain', 4), ('Trickery Domain', 4),
('Twilight Domain', 4), ('War Domain', 4), ('Circle of Dreams', 5), ('Circle of Spores', 5),
('Circle of Stars', 5), ('Circle of Wildfire', 5), ('Circle of the Land', 5), ('Circle of the Moon', 5),
('Circle of the Shepherd', 5), ('Arcane Archer', 6), ('Battle Master', 6), ('Cavalier', 6), ('Champion', 6),
('Echo Knight', 6), ('Eldritch Knight', 6), ('Psi Warrior', 6), ('Purple Dragon Knight', 6),
('Rune Knight', 6), ('Samurai', 6), ('Way of Mercy', 7), ('Way of Shadow', 7), ('Way of the Ascendant Dragon', 7),
('Way of the Astral Self', 7), ('Way of the Drunken Master', 7), ('Way of the Four Elements', 7),
('Way of the Kensei', 7), ('Way of the Long Death', 7), ('Way of the Open Hand', 7),
('Way of the Sun Soul', 7), ('Oath of Conquest', 8), ('Oath of Devotion', 8), ('Oath of Glory', 8),
('Oath of Redemption', 8), ('Oath of the Ancients', 8), ('Oath of the Crown', 8), ('Oath of the Watchers', 8),
('Oath of Vengeance', 8), ('Oathbreaker', 8), ('Beast Master', 9), ('Drakewarden', 9), ('Fey Wanderer', 9),
('Gloom Stalker', 9), ('Horizon Walker', 9), ('Hunter', 9), ('Monster Slayer', 9), ('Swarmkeeper', 9),
('Arcane Trickster', 10), ('Assassin', 10), ('Inquisitive', 10), ('Phantom', 10), ('Mastermind', 10), ('Scout', 10), ('Soulknife', 10),
('Swashbuckler', 10), ('Thief', 10), ('Aberrant Mind', 11), ('Clockwork Soul', 11), ('Divine Soul', 11), ('Draconic Bloodline', 11),
('Lunar Sorcery', 11), ('Shadow Magic', 11), ('Storm Sorcery', 11), ('Wild Magic', 11),
('The Archfey', 12), ('The Celestial', 12), ('The Fathomless', 12), ('The Fiend', 12), ('The Genie', 12),
('The Great Old One', 12), ('The Hexblade', 12), ('The Undead', 12), ('The Undying', 12),
('Bladesinging', 13), ('Chronurgy Magic', 13), ('Graviturgy Magic', 13), ('Order of Scribes', 13), ('School of Abjuration', 13), ('School of Conjuration', 13),
('School of Divination', 13), ('School of Enchantment', 13), ('School of Evocation', 13), ('School of Illusion', 13), ('School of Necromancy', 13), ('School of Transmutation', 13), ('War Magic', 13);

INSERT INTO characters (user_id, character_name, character_race, alignment, profile_pic)
VALUES (2, 'Neme', 'Satyr', 'Chaotic Neutral', 'https://res.cloudinary.com/dccsx3iht/image/upload/v1714150573/x3hhbbomsbf1pa4ybw60.jpg'),
(2, 'Rhywyn', 'Halfling', 'None', 'https://res.cloudinary.com/dccsx3iht/image/upload/v1714150698/qmzaf08mchmqsoeajnif.jpg'),
(2, 'Chicken', 'Tiefling', 'Chaotic Good', 'https://res.cloudinary.com/dccsx3iht/image/upload/v1714153760/dswrsvil5g9i65xhakof.jpg');

INSERT INTO character_classes (character_id, class_id, subclass_id, class_level)
VALUES (1, 11, 91, 5), (2, 10, 87, 2), (2, 3, 19, 3),
(3, 5, 37, 3);

COMMIT TRANSACTION;
