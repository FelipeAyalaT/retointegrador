IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'BDPAGOSERVICIOS')
  BEGIN
    CREATE DATABASE [BDPAGOSERVICIOS]
  END
GO

USE BDPAGOSERVICIOS

GO 

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='canal' and xtype='U')
BEGIN
	CREATE TABLE canal(
		CodCanal varchar(2),
		Name varchar(250),
		Status varchar(10)
	)

	insert into [dbo].[canal](CodCanal, Name, Status) values('CJ', 'Cajeros', 'Activo')
	insert into [dbo].[canal](CodCanal, Name, Status) values('BM', 'Banca Móvil', 'Activo')
	insert into [dbo].[canal](CodCanal, Name, Status) values('BI', 'Banca por internet', 'Activo')
	insert into [dbo].[canal](CodCanal, Name, Status) values('AG', 'Agente', 'Activo')
END

GO
 
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='servicios' and xtype='U')
BEGIN
	CREATE TABLE servicios(
		Id int identity(1,1) primary key,
		Name varchar(250),
		CodCanal varchar(2),
		Status varchar(10)
	)

	insert into [dbo].[servicios](Name, CodCanal, Status) values('Recargas de telefonía móvil mayores a S/ 5.00​', 'CJ', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de luz', 'CJ', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de agua', 'CJ', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de telefonía móvil o fija​', 'CJ', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de gas​', 'CJ', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de educación (colegios y universidades)​', 'CJ', 'Activo')

	insert into [dbo].[servicios](Name, CodCanal, Status) values('Recargas de telefonía móvil mayores a S/ 5.00​', 'BM', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de luz', 'BM', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de agua', 'BM', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de telefonía móvil o fija​', 'BM', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de gas​', 'BM', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de educación (colegios y universidades)​', 'BM', 'Activo')

	insert into [dbo].[servicios](Name, CodCanal, Status) values('Recargas de telefonía móvil mayores a S/ 5.00​', 'BI', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de luz', 'BI', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de agua', 'BI', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de telefonía móvil o fija​', 'BI', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de gas​', 'BI', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de educación (colegios y universidades)​', 'BI', 'Activo')

	insert into [dbo].[servicios](Name, CodCanal, Status) values('Recargas de telefonía móvil mayores a S/ 5.00​', 'AG', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de luz', 'AG', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de agua', 'AG', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de telefonía móvil o fija​', 'AG', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de gas​','AG', 'Activo')
	insert into [dbo].[servicios](Name, CodCanal, Status) values('Pago de educación (colegios y universidades)​', 'AG', 'Activo')
END

 
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='pagos' and xtype='U')
BEGIN
	CREATE TABLE pagos(
		Id int identity(1,1) primary key,
		Suministro varchar(50),
		Monto decimal(14,2),
		ServicioId int,
		Username varchar(150),
		Fecha datetime
	)
END
GO