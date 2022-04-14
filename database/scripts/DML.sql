USE BDPAGOSERVICIOS;

insert into canal (CodCanal, Name, Status) values('CJ', 'Cajeros', 'Activo');
insert into canal (CodCanal, Name, Status) values('BM', 'Banca Móvil', 'Activo');
insert into canal (CodCanal, Name, Status) values('BI', 'Banca por internet', 'Activo');
insert into canal (CodCanal, Name, Status) values('AG', 'Agente', 'Activo');


insert into servicios (Name, CodCanal, Status) values('Recargas de telefonía móvil mayores a S/ 5.00​', 'CJ', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de luz', 'CJ', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de agua', 'CJ', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de telefonía móvil o fija​', 'CJ', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de gas​', 'CJ', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de educación (colegios y universidades)​', 'CJ', 'Activo');

insert into servicios (Name, CodCanal, Status) values('Recargas de telefonía móvil mayores a S/ 5.00​', 'BM', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de luz', 'BM', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de agua', 'BM', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de telefonía móvil o fija​', 'BM', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de gas​', 'BM', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de educación (colegios y universidades)​', 'BM', 'Activo');

insert into servicios (Name, CodCanal, Status) values('Recargas de telefonía móvil mayores a S/ 5.00​', 'BI', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de luz', 'BI', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de agua', 'BI', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de telefonía móvil o fija​', 'BI', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de gas​', 'BI', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de educación (colegios y universidades)​', 'BI', 'Activo');

insert into servicios (Name, CodCanal, Status) values('Recargas de telefonía móvil mayores a S/ 5.00​', 'AG', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de luz', 'AG', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de agua', 'AG', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de telefonía móvil o fija​', 'AG', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de gas​','AG', 'Activo');
insert into servicios (Name, CodCanal, Status) values('Pago de educación (colegios y universidades)​', 'AG', 'Activo');

COMMIT;