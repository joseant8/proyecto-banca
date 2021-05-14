INSERT INTO cuenta (iban, saldo, fecha_creacion) values
    ('ES00 0000 0000 0000', 3000.0, '2020-02-24')
    ;

INSERT INTO categoria (nombre) values
    ('Ocio'),
    ('Bares y Restaurantes'),
    ('Deporte'),
    ('Viajes')
    ;

INSERT INTO movimiento (cantidad, tipo, categoria_id, cuenta_id, tarjeta_id, fecha) values
    (35.04, 0, 1, 1, null, '2021-03-10'),
    (20.2, 0, 2, 1, null, '2021-05-10'),
    (60.0, 1, 3, 1, null, '2021-05-12')
    ;
