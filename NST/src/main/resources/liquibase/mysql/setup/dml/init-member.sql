insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Pera", "Peric", "2020-03-04", "REGULAR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Lecturer"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="SILAB"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Professor"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "HCI"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Stefan", "Veljkovic", "2010-01-01", "REGULAR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Senior Lecturer"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="SILAB"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Professor"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "Security in tech"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Todor", "Toljaga", "2011-09-10", "REGULAR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Senior Lecturer"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="SILAB"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Admissions assistant"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "Data structures and algorithms"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Zoran", "Zoric", "2019-01-04", "SUPERVISOR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Emeritus"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="SILAB"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Assistant dean"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "HCI"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Stevan", "Markovic", "2022-03-04", "SECRETARY",
                        (select (id)
                        from tbl_academic_title
                        where name = "Senior lecturer"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="SILAB"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Assistant dean"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "HCI"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Sara", "Markovic", "2003-11-04", "REGULAR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Emeritus"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="MMKLAB"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Professor"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "HCI"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Petar", "Zoric", "2010-11-04", "REGULAR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Senior lecturer"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="MMKLAB"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Professor"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "Programming languages"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Zoran", "Zoric", "2020-01-01", "SUPERVISOR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Emeritus"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="MMKLAB"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Assistant dean"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "HCI"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Goran", "Goric", "2022-07-04", "SECRETARY",
                        (select (id)
                        from tbl_academic_title
                        where name = "Adjunct professor"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="MMKLAB"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Professor"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "HCI"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Pera", "Sipka", "2020-03-04", "REGULAR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Lecturer"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="IS"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Professor"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "Data structures and algorithms"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Jana", "Stankovic", "2020-10-19", "REGULAR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Distinguished professor"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="IS"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Professor"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "Database management"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Jovana", "Jovicic", "2019-09-19", "REGULAR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Distinguished professor"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="IS"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Professor"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "Database management"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Jovana", "Turcic", "2020-01-04", "SUPERVISOR",
                        (select (id)
                        from tbl_academic_title
                        where name = "Senior lecturer"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="IS"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Assistant dean"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "HCI"));

insert into tbl_member(first_name, last_name, start_date, role,
        academic_title_id, department_id, education_title_id,
        scientific_field_id)
values ("Marko", "Stankovic", "2008-09-09", "SECRETARY",
                        (select (id)
                        from tbl_academic_title
                        where name = "Senior lecturer"),
                        (select (id)
                        from tbl_department
                        WHERE short_name="IS"),
                        (select (id)
                        from tbl_education_title
                        where title_name = "Assistant dean"),
                        (select (id)
                        from tbl_scientific_field
                        where field_name = "Database management"));