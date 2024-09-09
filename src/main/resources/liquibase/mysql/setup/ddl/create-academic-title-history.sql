create table tbl_academic_title_history (
    id bigint unsigned not null AUTO_INCREMENT,
    start_date datetime,
    end_date datetime,
    academic_title_id bigint unsigned,
    scientific_field_id bigint unsigned,
    member_id bigint unsigned,
    primary key (id, academic_title_id, scientific_field_id, member_id),
    constraint academic_title_fk FOREIGN KEY
    (academic_title_id) REFERENCES tbl_academic_title(id)
    on update cascade on delete cascade,
    constraint scientific_field_fk FOREIGN KEY
    (scientific_field_id) REFERENCES tbl_scientific_field(id)
    on update cascade on delete cascade,
    constraint member_fk FOREIGN KEY
    (member_id) REFERENCES tbl_member(id)
    on update cascade on delete cascade
);
