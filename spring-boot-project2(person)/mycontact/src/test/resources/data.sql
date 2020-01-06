insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)values (1,'martin',10,'A',1991,8,15);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)values (2,'david',9,'AB',1992,7,25);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)values (3,'wonku',24,'O',1995,10,27);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)values (4,'minwoo',25,'A',1995,8,27);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)values (5,'JongYeon',26,'B',1995,7,7);

insert into block(`id`,`name`) values (1,'wonku');
insert into block(`id`,`name`) values (2,'minwoo');

update person set block_id=1 where id=3;
update person set block_id=2 where id=4;