drop database if exists `library`;
create database `library`;

use `library`;

create table `member` (
	member_id int primary key auto_increment,
	first_name varchar(100) not null,
    last_name varchar(100) not null
);

create table `book` (
	book_id int primary key auto_increment,
    title varchar(100) not null,
    author varchar(100) not null,
    genre varchar(50) not null
);

create table `borrowing_record` (
	borrowing_record_id int primary key auto_increment,
    member_id int not null,
    book_id int not null,
    checked_out_on date not null,
    returned_on date null,
    FOREIGN KEY (member_id) REFERENCES `member`(member_id),
    FOREIGN KEY (book_id) REFERENCES `book`(book_id)
);

insert into `book` (title, author, genre) values
	("At The Mountains Of Madness", "H.P. Lovecraft", "Cosmic Horror"),
    ("Beyond Good & Evil", "Friedrich Nietzsche", "Philosophy"),
    ("The Myth of Sisyphus", "Albet Camus", "Philosophy");
    
insert into `member` (first_name, last_name) values
	("Owen", "Ribera"),
    ("Jesus", "Christ"),
    ("Eric", "De Anda");
    