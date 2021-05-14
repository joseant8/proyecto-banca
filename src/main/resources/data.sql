INSERT INTO usuario (nombre, apellido1, username) values
    ('Jose Antonio', 'Marí', 'jose@email.com'),
    ('Sara', 'Martínez', 'sara@email.com'),
    ('Alejandro', 'Tur', 'al@email.com'),
    ('Marta', 'Carrasco', 'marta@email.com')
    ;

INSERT INTO cuenta (iban, saldo, fecha_creacion) values
    ('ES00 0000 2000 00 0000000000', 3000.0, '2020-02-24'),
    ('ES00 0000 3000 10 0000000001', 6000.0, '2020-03-24'),
    ('ES00 0000 3000 20 0000000002', 6000.0, '2020-03-24')
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

-- relaciones usuario_cuenta
INSERT INTO usuario_cuenta (usuario_id, cuenta_id) values
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 3)
    ;