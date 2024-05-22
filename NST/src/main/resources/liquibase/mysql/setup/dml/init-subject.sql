insert into tbl_subject(name,espb,department_id)
values ("Programming 1", 4, (select (id)
                              from tbl_department
                              WHERE short_name="SILAB"));
insert into tbl_subject(name,espb,department_id)
values ("Programming 2", 6, (select (id)
                              from tbl_department
                              WHERE short_name="SILAB"));
insert into tbl_subject(name,espb,department_id)
values ("E-business", 6, (select (id)
                              from tbl_department
                              WHERE short_name="ELAB"));
insert into tbl_subject(name,espb,department_id)
values ("Databases 1", 5, (select (id)
                              from tbl_department
                              WHERE short_name="IS"));

insert into tbl_subject(name,espb,department_id)
values ("Programming languages and compilers", 5, (select (id)
                              from tbl_department
                              WHERE short_name="IS"));
insert into tbl_subject(name,espb,department_id)
values ("Computer architecture", 6, (select (id)
                              from tbl_department
                              WHERE short_name="MMKLAB"));
insert into tbl_subject(name,espb,department_id)
values ("Internet technologies", 6, (select (id)
                              from tbl_department
                              WHERE short_name="ELAB"));
insert into tbl_subject(name,espb,department_id)
values ("Advanced Java technologies", 5, (select (id)
                              from tbl_department
                              WHERE short_name="SILAB"));
insert into tbl_subject(name,espb,department_id)
values ("Advanced programming", 4, (select (id)
                              from tbl_department
                              WHERE short_name="SILAB"));
