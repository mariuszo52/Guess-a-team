-- Dodawanie poziomów (Levels)
INSERT INTO Level (id, answer, shortAnswer, isCompleted, league)
VALUES (1, 'Answer 1', 'Short1', 0, 'League 1');

INSERT INTO Level (id, answer, shortAnswer, isCompleted, league)
VALUES (2, 'Answer 2', 'Short2', 0, 'League 2');

-- Dodawanie graczy (Players) do poziomu 1
INSERT INTO Player (id, name, countryUrl, isShowed, position, levelId)
VALUES (1, 'Player 1', 'url1', 0, 'FORWARD', 1);

INSERT INTO Player (id, name, countryUrl, isShowed, position, levelId)
VALUES (2, 'Player 2', 'url2', 0, 'DEFENDER', 1);

-- Dodawanie graczy (Players) do poziomu 2
INSERT INTO Player (id, name, countryUrl, isShowed, position, levelId)
VALUES (3, 'Player 3', 'url3', 0, 'GOALKEEPER', 2);

INSERT INTO Player (id, name, countryUrl, isShowed, position, levelId)
VALUES (4, 'Player 4', 'url4', 0, 'MIDFIELDER', 2);
