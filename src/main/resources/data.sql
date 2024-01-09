INSERT INTO Users (userName, userPass) VALUES ('user1','$2y$10$DPZvoBEzZrecXwgAhq/69OoHbWam2Kq0taE9gXsEHJ4q9yRzGtvHO');
INSERT INTO Users (userName, userPass) VALUES ('user2','$2y$10$SbWZIjsMBTtJ6SaG34GxauRrl7rD7YBKDQihnvmwaoEU/Q0kYy4ae');
INSERT INTO Users (userName, userPass) VALUES ('user3','$2y$10$bifSQsD6CxLgDfbvmdJi5.MArojBdtczVtBMqwr6F3PaCFoXO7s26');
INSERT INTO Users (userName, userPass) VALUES ('user4','$2y$10$qBTq8Ku0XpEA.MlHdbgMz.AocTZOc53q1TMb1mUP.vmK73vgkPTkO');
INSERT INTO Users (userName, userPass) VALUES ('user5','$2y$10$3/HBeTCql3HhnDFTUCbPNu/1RsqZMUqah9jEYifS32sbgcYlpUJEu');

INSERT INTO Groups (GroupName,userid) VALUES ('Group1',1);
INSERT INTO Groups (GroupName,userid) VALUES ('Group2',2);
INSERT INTO Groups (GroupName,userid) VALUES ('Group3',2);
INSERT INTO Groups (GroupName,userid) VALUES ('リマインダーテスト用',4);

INSERT INTO Entry (userid,Groupid) VALUES (1,1);
INSERT INTO Entry (userid,Groupid) VALUES (2,2);
INSERT INTO Entry (userid,Groupid) VALUES (2,3);
INSERT INTO Entry (userid,Groupid) VALUES (3,3);
INSERT INTO Entry (userid,Groupid) VALUES (4,4);

INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-10','10:30','12:30',1,'タイトルA','本文1');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-10','10:30','12:30',2,'タイトルB','本文2');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-15','10:30','12:30',3,'タイトルC','本文3');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-10','13:30','15:30',1,'タイトルD','本文4');

INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-09','20:30','23:30',4,'12月9日の予定','AAA');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-10','20:30','23:30',4,'12月10日の予定','BBB');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-11','20:30','23:30',4,'12月11日の予定','CCC');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-12','20:30','23:30',4,'12月12日の予定','DDD');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-13','20:30','23:30',4,'12月13日の予定','EEE');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-14','20:30','23:30',4,'12月14日の予定','FFF');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-15','20:30','23:30',4,'12月15日の予定','GGG');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-16','20:30','23:30',4,'12月16日の予定','HHH');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-17','20:30','23:30',4,'12月17日の予定','III');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-07','20:30','23:30',4,'12月7日の予定','JJJ');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-08','20:30','23:30',4,'12月8日の予定','KKK');
