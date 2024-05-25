insert into tbl_member_history(start_date, end_date, role,
                                member_id, department_id)
values("2010-01-07", "2011-01-07", "DIRECTOR", (select(id)
                                    from tbl_member
                                    where first_name = "Pera"
                                    and last_name = "Peric"),
                                    (select(id)
                                    from tbl_department
                                    where short_name = "SILAB"));
insert into tbl_member_history(start_date, end_date, role,
                                member_id, department_id)
values("2011-01-07", "2020-03-04", "SECRETARY", (select(id)
                                    from tbl_member
                                    where first_name = "Pera"
                                    and last_name = "Peric"),
                                    (select(id)
                                    from tbl_department
                                    where short_name = "SILAB"));
insert into tbl_member_history(start_date, end_date, role,
                                member_id, department_id)
values("2021-10-07", "2022-03-04", "DIRECTOR", (select(id)
                                    from tbl_member
                                    where first_name = "Stevan"
                                    and last_name = "Markovic"),
                                    (select(id)
                                    from tbl_department
                                    where short_name = "MMKLAB"));
insert into tbl_member_history(start_date, end_date, role,
                                member_id, department_id)
values("2010-01-30", "2020-01-04", "REGULAR", (select(id)
                                    from tbl_member
                                    where first_name = "Jovana"
                                    and last_name = "Turcic"),
                                    (select(id)
                                    from tbl_department
                                    where short_name = "IS"));