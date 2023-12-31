CREATE TABLE IF NOT EXISTS Users (
    userid IDENTITY,
    userName VARCHAR(128) NOT NULL,
    userPass VARCHAR(512)
);

CREATE TABLE Groups (
    Groupid IDENTITY,
    GroupName VARCHAR NOT NULL,
    userid int,
    FOREIGN KEY(userid) REFERENCES Users
);

CREATE TABLE Entry (
    userid int,
    Groupid int,
    PRIMARY KEY(userid,Groupid),
    FOREIGN KEY(userid) REFERENCES Users,
    FOREIGN KEY(Groupid) REFERENCES Groups
);

CREATE TABLE GroupSchedule (
    Scheduleid IDENTITY,
    hizuke DATE NOT NULL,
    kaisi TIME NOT NULL,
    owari TIME NOT NULL,
    Groupid int,
    title VARCHAR NOT NULL,
    content VARCHAR NOT NULL,
    FOREIGN KEY(Groupid) REFERENCES Groups
);
