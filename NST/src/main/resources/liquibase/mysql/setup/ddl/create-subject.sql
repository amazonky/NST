create table tbl_subject(
	id bigint unsigned not null AUTO_INCREMENT,
	name varchar(100) not null,
        espb int,
        department_id bigint unsigned,
	primary key (id),
        constraint department_fk5 FOREIGN KEY (department_id) REFERENCES tbl_department(id)
        on update cascade on delete cascade
)
