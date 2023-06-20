
-- recuperer la liste de tout les pret de materiels avec les dates de debut et de fin

select m.*, p.pret_debut, p.pret_fin
from materiel m
         join pret p on m.materiel_id
where year( p.pret_fin)=2023;

-- afficher le nom, prenom et la quantité de materiel emprunté par les utilisateur

SELECT u.user_first_name, u.user_last_name, p.pret_qte
FROM user u
         INNER JOIN pret p ON u.user_name = p.user_name
WHERE p.pret_qte = 10;

-- afficher le nom, prenom des utilisateurs, la quatité de materiels, la marque ainsi que la date de de debut pret

SELECT u.user_first_name, u.user_last_name, p.pret_qte, m.materiel_marque, p.pret_debut
FROM user u
         INNER JOIN pret p ON u.user_name = p.user_name
         INNER JOIN materiel m ON p.pret_id= m.materiel_id;


-- afficher le nom, prenom des utilisateurs, la quatité de materiels, la marque ainsi que la date de de fin pret
SELECT u.user_first_name, u.user_last_name, p.pret_qte, m.materiel_marque, p.pret_fin
FROM user u
         INNER JOIN pret p ON u.user_name = p.user_name
         INNER JOIN materiel m ON p.pret_id = m.materiel_id;



-- les prets dont la date de fin est prevu en mai
SELECT *
FROM pret
WHERE MONTH(pret_fin) = 5;
