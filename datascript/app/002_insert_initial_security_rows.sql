INSERT INTO app.acc_utente (
	  username,
	  nome,
	  cognome,
	  note,
    digest,
    salt,
    iterazioni,
    ultimo_login_ts,
    creazione_ts,
    ultima_attivazione_ts,
    ultima_disattivazione_ts,
    attivo
  )
  VALUES (
    'admin', 
    'Administrator', 
    'Administrator', 
    null,
    'bcb1e28282e7bab07fbe4191662f7c0bfca5426471f929b307e1353665466d0d0293437b9b2302678397bd0c5167049f79bf1facb0b652c73ab337f283c8f780',
    '8521903395d92a798dfe4972867e8aead2861a458abc5fb488bde37825890d6b2ac1300b7cfce53020a1dbb1f3bbacc74b7410fab45b38b14d8990d2b673d8a5',
    50000,
    null,
    current_timestamp,
    current_timestamp,
    null,
    true
  );

INSERT INTO app.acc_ruolo (nome, descrizione)
  VALUES ('admin', 'Ruolo di amministrazione');

INSERT INTO app.acc_permesso (permesso)
  VALUES ('*');

INSERT INTO app.acc_utente_ruolo (utente_id, ruolo_id)
  VALUES (
	(select id from app.acc_utente where username = 'admin'), 
	(select id from app.acc_ruolo where nome = 'admin')
);

INSERT INTO app.acc_ruolo_permesso (ruolo_id, permesso_id)
  VALUES (
	(select id from app.acc_ruolo where nome = 'admin'), 
	(select id from app.acc_permesso where permesso = '*')
);
