CREATE TABLE IF NOT EXISTS Equipo (
                                      ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      Nombre VARCHAR(50),
                                      Liga VARCHAR(50),
                                      Pais VARCHAR(50)
);

INSERT INTO Equipo (Nombre, Liga, Pais) VALUES
                                            ('Real Madrid', 'La Liga', 'España'),
                                            ('FC Barcelona', 'La Liga', 'España'),
                                            ('Manchester United', 'Premier League', 'Inglaterra'),
                                            ('Liverpool FC', 'Premier League', 'Inglaterra'),
                                            ('Juventus FC', 'Serie A', 'Italia'),
                                            ('AC Milan', 'Serie A', 'Italia'),
                                            ('Bayern Munich', 'Bundesliga', 'Alemania'),
                                            ('Borussia Dortmund', 'Bundesliga', 'Alemania'),
                                            ('Paris Saint-Germain', 'Ligue 1', 'Francia'),
                                            ('Olympique de Marseille', 'Ligue 1', 'Francia'),
                                            ('FC Porto', 'Primeira Liga', 'Portugal'),
                                            ('Sporting CP', 'Primeira Liga', 'Portugal'),
                                            ('Ajax Amsterdam', 'Eredivisie', 'Países Bajos'),
                                            ('Feyenoord', 'Eredivisie', 'Países Bajos'),
                                            ('Celtic FC', 'Scottish Premiership', 'Escocia'),
                                            ('Rangers FC', 'Scottish Premiership', 'Escocia'),
                                            ('Galatasaray SK', 'Süper Lig', 'Turquía'),
                                            ('Fenerbahçe SK', 'Süper Lig', 'Turquía'),
                                            ('FC Zenit Saint Petersburg', 'Premier League Rusa', 'Rusia'),
                                            ('Spartak Moscow', 'Premier League Rusa', 'Rusia'),
                                            ('SL Benfica', 'Primeira Liga', 'Portugal'),
                                            ('Besiktas JK', 'Süper Lig', 'Turquía'),
                                            ('SSC Napoli', 'Serie A', 'Italia'),
                                            ('Atlético Madrid', 'La Liga', 'España');
