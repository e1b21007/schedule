INSERT INTO sUser (username) VALUES ('user1');
INSERT INTO sUser (username) VALUES ('user2');
INSERT INTO sUser (username) VALUES ('user3');
INSERT INTO sUser (username) VALUES ('user4');

INSERT INTO sGroup (GroupName) VALUES ('Group1');
INSERT INTO sGroup (GroupName) VALUES ('Group2');
INSERT INTO sGroup (GroupName) VALUES ('Group3');
INSERT INTO sGroup (GroupName) VALUES ('Group4');

INSERT INTO Entry (userid,Groupid) VALUES (1,1);
INSERT INTO Entry (userid,Groupid) VALUES (2,2);
INSERT INTO Entry (userid,Groupid) VALUES (2,3);
INSERT INTO Entry (userid,Groupid) VALUES (3,3);
INSERT INTO Entry (userid,Groupid) VALUES (4,4);

INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-10','10:30','12:30',1,'タイトルA','本文1');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-10','10:30','12:30',2,'タイトルB','本文2');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-15','10:30','12:30',3,'タイトルC','本文3');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-10','13:30','15:30',1,'タイトルD','本文4');
