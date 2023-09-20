

# recuperer la liste de tout les materiels

    select*from materiel;
-- recuperer tout les materiels dont la marque est telephone

select*from materiel where materiel_marque = 'telephone';

# recuperer la marque du materiel dont l'identifiant est egale à 10
select materiel_marque from materiel where materiel_id =10;

# on affiche l'identifiant, la marque et la quantité du materiel ainsi que l'identifiant du pret dont la qté est inferieur à 5 par exemple
select materiel_id, materiel_marque,  materiel_qte, pret_id
from materiel join pret on materiel.materiel_id = pret.pret_id
where pret_qte <5;

-- recuperer la liste de tout les pret de materiels avec les dates de debut et de fin

select m.*, p.pret_debut, p.pret_fin
from materiel m
         join pret p on m.materiel_id
where year( p.pret_fin)=2023;


# on affiche la liste des materiels dont la date de fin se termine en juillet

select m.materiel_id,  m.materiel_marque, p.pret_debut, p.pret_fin
from materiel m
         join pret p on m.materiel_id
where month(p.pret_fin) = 07;


# rajout de order by

select m.materiel_id,  m.materiel_marque, m.materiel_qte,  p.pret_debut, p.pret_fin
from materiel m
         join pret p on m.materiel_id
where month(p.pret_fin)
order by m.materiel_qte asc;

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



-- les prets dont la date de fin est prevu en juillet

select pret_id, pret_description, pret_fin
from pret where month(pret_fin) = 07;