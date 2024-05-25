insert into tbl_academic_title_history (start_date, end_date,
                                        academic_title_id,
                                        scientific_field_id,
                                        member_id)
values ("2021-09-05", "2022-09-05",
        (select id from tbl_academic_title where name = "Emeritus"),
        (select id from tbl_scientific_field where field_name = "HCI"),
        (select id from tbl_member where first_name = "Pera" and last_name = "Peric"));
insert into tbl_academic_title_history (start_date, end_date,
                                        academic_title_id,
                                        scientific_field_id,
                                        member_id)
VALUES ("2020-11-20",
        "2021-11-20",
        (select id from tbl_academic_title where name = "Lecturer"),
        (select id from tbl_scientific_field where field_name = "Digital marketing"),
        (select id from tbl_member where first_name = "Pera" and last_name = "Sipka"));
