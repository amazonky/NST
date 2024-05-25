create table tbl_member_history (
    id bigint unsigned not null AUTO_INCREMENT,
    start_date datetime,
    end_date datetime,
    role varchar(10),
    department_id bigint unsigned,
    member_id bigint unsigned,
    primary key (id, department_id, member_id),
    constraint department_fk4 FOREIGN KEY (department_id)
    REFERENCES tbl_department(id) on update cascade
    on delete cascade,
    constraint member_fk1 FOREIGN KEY (member_id)
    REFERENCES tbl_member(id) on update cascade
    on delete cascade
)

