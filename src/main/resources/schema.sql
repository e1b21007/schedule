CREATE TABLE Users (
    userid IDENTITY,
    userName VARCHAR NOT NULL
);

CREATE TABLE Groups (
    Groupid IDENTITY,
    GroupName VARCHAR NOT NULL
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
