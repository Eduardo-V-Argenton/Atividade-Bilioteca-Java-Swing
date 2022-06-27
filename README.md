# Atividade-Bilioteca-Java-Swing
Projeto desenvolvido como uma atividade avaliativa da discipilna de Programação Orientada a Objetos 

-- Criação Banco

CREATE DATABASE Biblioteca;

-- Usar Biblioteca

USE Biblioteca;

-- Criação Tabelas

CREATE TABLE Livro(
	CodigoLivro INT PRIMARY KEY AUTO_INCREMENT,
	Nome VARCHAR(255) NOT NULL,
	Escritor VARCHAR(255) NOT NULL,
	Editora VARCHAR(255) NOT NULL,
	AnoLancamento INT NOT NULL,
	Genero VARCHAR(255) NOT NULL,
	Localizacao VARCHAR(150) NOT NULL
);


CREATE TABLE Login(
	LoginID INT PRIMARY KEY AUTO_INCREMENT,
        Usuario VARCHAR(150),
        Senha VARCHAR(255) NOT NULL
);

-- Criação de Usuário

CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON  Biblioteca. * TO 'user'@'localhost';
FLUSH PRIVILEGES;


------------É necessário instalar o mysql connector----------
