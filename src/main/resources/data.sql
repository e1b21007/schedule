INSERT INTO Users (username) VALUES ('user1');
INSERT INTO Users (username) VALUES ('user2');
INSERT INTO Users (username) VALUES ('user3');
INSERT INTO Users (username) VALUES ('user4');

INSERT INTO Groups (GroupName) VALUES ('Group1');
INSERT INTO Groups (GroupName) VALUES ('Group2');
INSERT INTO Groups (GroupName) VALUES ('Group3');
INSERT INTO Groups (GroupName) VALUES ('リマインダーテスト用');

INSERT INTO Entry (userid,Groupid) VALUES (1,1);
INSERT INTO Entry (userid,Groupid) VALUES (2,2);
INSERT INTO Entry (userid,Groupid) VALUES (2,3);
INSERT INTO Entry (userid,Groupid) VALUES (3,3);
INSERT INTO Entry (userid,Groupid) VALUES (4,4);

INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-10','10:30','12:30',1,'タイトルA','本文1');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-10','10:30','12:30',2,'タイトルB','本文2');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-15','10:30','12:30',3,'タイトルC','本文3');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-08-10','13:30','15:30',1,'タイトルD','本文4');

INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-11-28','20:30','23:30',4,'11月28日の予定','AAA');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-11-29','20:30','23:30',4,'11月29日の予定','BBB');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-11-30','20:30','23:30',4,'11月30日の予定','CCC');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-01','20:30','23:30',4,'12月1日の予定','DDD');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-02','20:30','23:30',4,'12月2日の予定','EEE');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-03','20:30','23:30',4,'12月3日の予定','FFF');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-04','20:30','23:30',4,'12月4日の予定','GGG');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-05','20:30','23:30',4,'12月5日の予定','HHH');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-06','20:30','23:30',4,'12月6日の予定','III');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-07','20:30','23:30',4,'12月7日の予定','JJJ');
INSERT INTO GroupSchedule (hizuke,kaisi,owari,Groupid,title,content) VALUES ('2023-12-08','20:30','23:30',4,'12月8日の予定','KKK');
