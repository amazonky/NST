create table tbl_member(
    id bigint unsigned not null AUTO_INCREMENT,
    first_name varchar(25) not null,
    last_name varchar(25) not null,
    start_date datetime,
    role varchar(10),
    academic_title_id bigint unsigned,
    department_id bigint unsigned,
    education_title_id bigint unsigned,
    scientific_field_id bigint unsigned,
    primary key (id),
    constraint academic_title_fk1 FOREIGN KEY
    (academic_title_id) REFERENCES tbl_academic_title(id)
    on update cascade on delete cascade,
    constraint department_fk6 FOREIGN KEY
    (department_id) REFERENCES tbl_department(id)
    on update cascade on delete cascade,
    constraint education_title_fk2 FOREIGN KEY
    (education_title_id) REFERENCES tbl_education_title(id)
    on update cascade on delete cascade,
    constraint scientific_field_fk1 FOREIGN KEY
    (scientific_field_id) REFERENCES tbl_scientific_field(id)
    on update cascade on delete cascade
)