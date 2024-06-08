use concesionarioDB;
-- CRUD Sucursales

DELIMITER $$
create procedure sp_agregarSucursales(dir varchar(50), tel varchar(50))
BEGIN
	insert into Sucursales(direccion, telefono)values
    (dir,tel);
END $$
DELIMITER ;

-- Listar: Sucursales

DELIMITER $$
	CREATE PROCEDURE sp_ListarSucursales()
		BEGIN
			SELECT 
				Sucursales.sucursalId,
                Sucursales.direccion,
                Sucursales.telefono
					FROM Sucursales;
		END$$
DELIMITER ;

CALL sp_ListarSucursales();


-- Eliminar: Sucursales
DELIMITER $$
	CREATE PROCEDURE sp_EliminarSucursales(IN sucId INT)
		BEGIN
			DELETE
				FROM Sucursales
                WHERE sucursalId = sucId;
		END $$
DELIMITER ;

-- CALL sp_EliminarSucursales(8);


-- Buscar: Sucursales
DELIMITER $$
	CREATE PROCEDURE sp_BuscarSucursales(IN sucId INT)
		BEGIN
			SELECT
				Sucursales.sucursalId,
                Sucursales.direccion,
                Sucursales.telefono
					FROM Sucursales
                    WHERE sucursalId = sucId;
		END $$
DELIMITER ;

CALL sp_BuscarSucursales(2);

-- Editar: Sucursales

DELIMITER $$
	CREATE PROCEDURE sp_EditarSucursales(IN sucId INT, IN dir VARCHAR(50), IN tel VARCHAR(50))
		BEGIN  
        UPDATE Sucursales
			SET
				direccion = dir,
                telefono = tel
                WHERE sucursalId = sucId;
		END$$
DELIMITER ;

-- DROP PROCEDURE sp_EditarSucursales;

CALL sp_EditarSucursales(2, 'quiche', '1789-3648');

select * from Sucursales;

-- ***********************************************************************************

-- CRUD Vendedores

DELIMITER $$
create procedure sp_agregarVendedores(nom varchar(50), ape varchar(50), tel varchar(50), cor varchar(50), sucId int)
BEGIN
	insert into Vendedores(nombreVendedor, apellidoVendedor, telefono, correo, sucursalId)values
    (nom,ape,tel,cor, sucId);
END $$
DELIMITER ;


-- Listar: Vendedores

DELIMITER $$
	CREATE PROCEDURE sp_ListarVendedores()
		BEGIN
			SELECT 
				Vendedores.vendedorId,
                Vendedores.nombreVendedor,
                Vendedores.apellidoVendedor,
                Vendedores.telefono,
                Vendedores.correo,
                Vendedores.sucursalId
					FROM Vendedores;
		END $$
DELIMITER ;

CALL sp_ListarVendedores();


-- Eliminar: Vendedores
DELIMITER $$
	CREATE PROCEDURE sp_EliminarVendedores(IN venId INT)
		BEGIN
			DELETE
				FROM Vendedores
                WHERE vendedorId = venId;
		END $$
DELIMITER ;

-- CALL sp_EliminarVendedores(8);


-- Buscar: Vendedores
DELIMITER $$
	CREATE PROCEDURE sp_BuscarVendedores(IN venId INT)
		BEGIN
			SELECT
				Vendedores.vendedorId,
                Vendedores.nombreVendedor,
                Vendedores.apellidoVendedor,
                Vendedores.telefono,
                Vendedores.correo,
                Vendedores.sucursalId
					FROM Vendedores
                    WHERE vendedorId = venId;
		END $$
DELIMITER ;

CALL sp_BuscarVendedores(6);

-- Editar: Vendedores

DELIMITER $$
	CREATE PROCEDURE sp_EditarVendedores(IN venId INT, IN nom VARCHAR(50), IN ape VARCHAR(50), IN tel VARCHAR(50), IN cor VARCHAR(50), IN sucId int)
		BEGIN
        UPDATE Vendedores
			SET
				nombreVendedor = nom,
                apellidoVendedor = ape,
                telefono = tel,
                correo = cor,
                sucursalId = sucId
                WHERE vendedorId = venId;
		END$$
DELIMITER ;

-- DROP PROCEDURE sp_EditarVendedores;

CALL sp_EditarVendedores(2, 'PIchiyaWarzon', 'luis', '7896-3456', 'kinal@gmail.com', 3);

select * from Vendedores;

-- ***********************************************************************************

-- CRUD CLIENTES

DELIMITER $$
create procedure sp_agregarCliente(nom varchar(50), ape varchar(50), tel varchar(50), n varchar(50), dir varchar (50))
BEGIN
	insert into Clientes(nombreCliente, apellidoCliente, telefono, nit, direccion)values
    (nom,ape,tel,n, dir);
END $$
DELIMITER ;

call sp_agregarCliente('Robin','Sisimit','2222-2222','5549594', 'Totonicapan');

-- Listar: Clientes

DELIMITER $$
	CREATE PROCEDURE sp_ListarClientes()
		BEGIN
			SELECT 
				Clientes.clienteId,
                Clientes.nombreCliente,
                Clientes.apellidoCliente,
                Clientes.telefono,
                Clientes.nit,
                Clientes.direccion
					FROM Clientes;
		END $$
DELIMITER ;

CALL sp_ListarClientes();


-- Eliminar: Clientes
DELIMITER //
	CREATE PROCEDURE sp_EliminarCliente(IN cliId INT)
		BEGIN
			DELETE
				FROM Clientes
                WHERE clienteId = cliId;
		END//
DELIMITER ;

-- CALL sp_EliminarCliente(8);


-- Buscar: Clientes
DELIMITER //
	CREATE PROCEDURE sp_BuscarClientes(IN cliId INT)
		BEGIN
			SELECT
				Clientes.clienteId,
                Clientes.nombreCliente,
                Clientes.apellidoCliente,
                Clientes.telefono,
                Clientes.nit,
                Clientes.direccion
					FROM Clientes
                    WHERE clienteId = cliId;
		END//
DELIMITER ;

CALL sp_BuscarClientes(2);

-- Editar: Clientes

DELIMITER $$
	CREATE PROCEDURE sp_EditarCliente(IN cliId INT, IN nom VARCHAR(50), IN ape VARCHAR(50), IN tel VARCHAR(50), IN dir VARCHAR(50), IN n VARCHAR (50))
		BEGIN
        UPDATE Clientes
			SET
				nombreCliente = nom,
                apellidoCliente = ape,
                telefono = tel,
                nit = nit,
                direccion = dir
                WHERE clienteId = cliId;
		END$$
DELIMITER ;

-- DROP PROCEDURE sp_EditarCliente;

CALL sp_EditarCliente(3, 'Juan', 'Araujo', '454548124', '52524567', 'mazate');

select * from clientes;

-- ***********************************************************************************

-- CRUD Carros

DELIMITER $$
create procedure sp_agregarCarros(mar varchar(50), mol varchar(50), pre decimal(10,2), cliId int)							
BEGIN
	insert into Carros(marca, modelo, precio, clienteId) values
    (mar,mol,pre,cliId);
END $$
DELIMITER ;

call sp_agregarCarros('toyota','2006', 255.00,  1);

-- Listar: Carros

DELIMITER $$
	CREATE PROCEDURE sp_ListarCarros()
		BEGIN
			SELECT 
				Carros.carroId,
                Carros.marca,
                Carros.modelo,
                Carros.precio,
                Carros.clienteId
					FROM Carros;
		END $$
DELIMITER ;

CALL sp_ListarCarros();


-- Eliminar: Carros
DELIMITER $$
	CREATE PROCEDURE sp_EliminarCarros(IN carId INT)
		BEGIN
			DELETE
				FROM Carros
                WHERE carroId = carId;
		END $$
DELIMITER ;

-- CALL sp_EliminarCarros(8);


-- Buscar: Carros
DELIMITER $$
	CREATE PROCEDURE sp_BuscarCarros(IN carId INT)
		BEGIN
			SELECT
				Carros.carroId,
                Carros.marca,
                Carros.modelo,
                Carros.precio,
                Carros.clienteId
					FROM Carros
                    WHERE carroId = carId;
		END $$
DELIMITER ; 

CALL sp_BuscarCarros(2);

-- Editar: Carros

DELIMITER $$
	CREATE PROCEDURE sp_EditarCarros(IN carId INT, IN mar VARCHAR(50), IN mol VARCHAR(50), IN pre decimal(10,2), IN cliId int)
		BEGIN
        UPDATE Carros
			SET
				marca = mar,
                modelo = mol,
                precio = pre,
                clienteId = cliId
                WHERE carroId = carId;
		END$$
DELIMITER ;

-- DROP PROCEDURE sp_EditarCliente;


select * from Carros;

-- ***********************************************************************************

-- CRUD Facturas

DELIMITER $$
create procedure sp_agregarFacturas(fec date, hor time, tot decimal(10,2), cliId INT, venId INT, carId INT)
BEGIN
	insert into Facturas(fecha, hora, total, clienteId, vendedorId, carroId)values
    (fec,hor, tot, cliId,venId, carId);
END $$
DELIMITER ;


-- Listar: Facturas

DELIMITER $$
	CREATE PROCEDURE sp_ListarFacturas()
		BEGIN
			SELECT 
				F.facturaId,F.fecha, F.hora, F.total,
				concat('Id: ', C.clienteId, '|', C.nombreCliente) as 'Cliente',
				concat('Id: ', V.vendedorId, '|', V.nombreVendedor) as 'Vendedor',
				concat('Id: ', CA.carroId, '|', CA.marca) as 'Carro'
				FROM Facturas F
				Join Carros CA on F.carroId = CA.carroId
				Join Clientes C on F.clienteId = C.clienteId
				Join Vendedores V on F.vendedorId = V.vendedorId;
		END $$
DELIMITER ;

CALL sp_ListarFacturas();


-- Eliminar: Facturas
DELIMITER $$
	CREATE PROCEDURE sp_EliminarFacturas(IN facId INT)
		BEGIN
			DELETE
				FROM Facturas
                WHERE facturaId = facId;
		END $$
DELIMITER ;

-- CALL sp_EliminarFacturas(8);

-- DROP PROCEDURE sp_EliminarFacturas;

-- Buscar: Facturas
DELIMITER $$
	CREATE PROCEDURE sp_BuscarFacturas(IN facId INT)
		BEGIN
			SELECT
				Facturas.facturaId,
                Facturas.fecha,
                Facturas.hora,
                Facturas.clienteId,
                Facturas.vendedorId,
                Facturas.carroId
					FROM Facturas
                    WHERE facturaId = facId;
		END $$
DELIMITER ;

CALL sp_BuscarFacturas(2);

-- Editar: Facturas

DELIMITER $$
	CREATE PROCEDURE sp_EditarFacturas(in fec date,in  hor time,in cliId INT,in venId INT,in carId INT)
		BEGIN
        UPDATE Facturas
			SET
				fecha = fec,
                hora = hor,
                clienteId = cliId,
                vendedorId = venId,
                carroId = carId
                WHERE clienteId = cliId;
		END$$
DELIMITER ;

-- DROP PROCEDURE sp_EditarFacturas;


select * from Facturas;
