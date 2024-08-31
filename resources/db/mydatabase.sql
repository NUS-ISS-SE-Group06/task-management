PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE task_audit(
   task_audit_id INTEGER NOT NULL,
   audit_comment  TEXT,
   audit_by TEXT,
   PRIMARY KEY (task_audit_id)

);
CREATE TABLE task_category(
   task_category_id INTEGER NOT NULL,
   category_name  TEXT,
   PRIMARY KEY (task_category_id)

);
CREATE TABLE task_info (task_id integer, task_due_date timestamp, created_by varchar(255), created_date timestamp, delete_flag varchar(255), modified_by varchar(255), modified_date timestamp, task_assignee integer not null, task_category_id integer not null, task_description varchar(255), task_name varchar(255), task_priority varchar(255), task_reward_point integer not null, task_status varchar(255), primary key (task_id));
INSERT INTO task_info VALUES(1,1713744000000,'ADMIN',1711592453529,'FALSE','ADMIN',1713524315914,1,1,'test-01-01','test-01','1',100,'Pending');
INSERT INTO task_info VALUES(2,1711584000000,'ADMIN',1712313659367,'FALSE','ADMIN',1713525004881,2,2,'test43','test43','1',100,'Complete');
INSERT INTO task_info VALUES(3,1712145600000,'ADMIN',1712313922169,'FALSE','ADMIN',1712313922170,3,3,'testx3x','testx3x','3',100,'Progress');
INSERT INTO task_info VALUES(4,1712145600000,'ADMIN',1712313953211,'FALSE','ADMIN',1712313953211,1,3,'testx3x','999999','3',100,'Complete');
INSERT INTO task_info VALUES(5,1711627200000,'ADMIN',1712314463276,'FALSE','ADMIN',1712314463276,2,2,'test43','test43','1',100,'Complete');
INSERT INTO task_info VALUES(6,1711627200000,'ADMIN',1712314468428,'FALSE','ADMIN',1712314468428,3,1,'test43','test43','1',100,'Pending');
INSERT INTO task_info VALUES(7,1711972800000,'ADMIN',1712314797448,'FALSE','ADMIN',1712314797448,1,1,'test07','test07','2',100,'Progress');
INSERT INTO task_info VALUES(8,1711929600000,'ADMIN',1711614854663,'FALSE','ADMIN',1713069357394,2,2,'test-02','test-02','1',100,'Complete');
INSERT INTO task_info VALUES(9,1712145600000,'ADMIN',1712314810816,'FALSE','ADMIN',1712314810816,3,3,'testx3x','999999','3',100,'Progress');
INSERT INTO task_info VALUES(10,1712707200000,'ADMIN',1711713421788,'FALSE','ADMIN',1712321087535,1,1,'test-03','test-03','1',100,'Complete');
INSERT INTO task_info VALUES(11,1711972800000,'ADMIN',1712314836233,'FALSE','ADMIN',1712314836233,2,2,'test07','test07','2',100,'Pending');
INSERT INTO task_info VALUES(12,1713652080000,'ADMIN',1713623345714,'FALSE','ADMIN',1713623345714,3,3,'test again11','test again11','2',100,'Complete');
INSERT INTO task_info VALUES(13,1717106400000,'ADMIN',1714797998484,'FALSE','ADMIN',1714797998484,1,2,'test again11','test again11','2',0,'Pending');
INSERT INTO task_info VALUES(14,1711972800000,'ADMIN',1714798022047,'FALSE','ADMIN',1724765685901,2,2,'test07-test','test07-test111','2',0,'Progress');
INSERT INTO task_info VALUES(15,1717106400000,'ADMIN',1714798037356,'FALSE','ADMIN',1714798037356,1,2,'test again11','test again11','2',0,'Pending');
INSERT INTO task_info VALUES(16,1717027200000,'ADMIN',1714798043155,'FALSE','ADMIN',1714798043155,1,2,'test again11','test again11','2',0,'Pending');
INSERT INTO task_info VALUES(17,1717027200000,'ADMIN',1714798057979,'FALSE','ADMIN',1714798057979,1,2,'test again11','test again11','2',0,'Pending');
INSERT INTO task_info VALUES(18,1715904000000,'ADMIN',1714798114687,'FALSE','ADMIN',1714798114687,1,2,'test again11','test again11','2',0,'Pending');
INSERT INTO task_info VALUES(19,1715904000000,'ADMIN',1714798118966,'FALSE','ADMIN',1714798118966,1,2,'test again11','test again11','2',0,'Pending');
INSERT INTO task_info VALUES(20,1715904000000,'ADMIN',1714798130410,'FALSE','ADMIN',1714798130410,1,2,'test again11','test again11','2',0,'Pending');
INSERT INTO task_info VALUES(21,1724882400000,'1',1724852384279,'FALSE','1',1724852384279,1,2,'test again11','test again11','2',100,'Pending');
INSERT INTO task_info VALUES(22,1724882400000,'1',1724853834438,'FALSE','1',1724853834438,1,2,'test again11','test again11','2',100,'Pending');
INSERT INTO task_info VALUES(23,1724882400000,'1',1724853985347,'FALSE','1',1724853985347,1,2,'test again11','test again11','2',100,'Pending');
CREATE TABLE group_info (group_id integer, created_by varchar(255), created_date varchar(255), delete_flag varchar(255), group_description varchar(255), group_name varchar(255), modified_by varchar(255), modified_date varchar(255), primary key (group_id));
INSERT INTO group_info VALUES(1,'SiBa',NULL,'FALSE','Description for Group Test','Group Test','SiBa',NULL);
INSERT INTO group_info VALUES(2,'ADMIN',NULL,'FALSE','Group Two','Group 2','ADMIN',NULL);
INSERT INTO group_info VALUES(3,'ADMIN',NULL,'FALSE','Group 3','Group 3','ADMIN',NULL);
INSERT INTO group_info VALUES(4,'SiBa',NULL,'FALSE','Description for Group TestABC','Group TestABC',NULL,NULL);
INSERT INTO group_info VALUES(5,'SiBa',NULL,'FALSE','Description for Group Test','Group Test','SiBa',NULL);
INSERT INTO group_info VALUES(6,'SiBa',NULL,'FALSE','Description for Group Test','Group Test','SiBa',NULL);
INSERT INTO group_info VALUES(7,'SiBa',NULL,'FALSE','Description for Group Test','Group Test2','SiBa',NULL);
INSERT INTO group_info VALUES(8,'SiBa',NULL,'FALSE','Description for Group Test','Group Test2','SiBa',NULL);
CREATE TABLE IF NOT EXISTS "user_info"
(
    user_id                   integer
        primary key,
    created_by                varchar(255),
    created_date              timestamp,
    delete_flag               varchar(255) default FALSE,
    email                     varchar(255),
    group_id                  integer,
    modified_by               varchar(255),
    modified_date             timestamp,
    name                      varchar(255),
    password                  varchar(255),
    password_change_mandatory varchar(255),
    role                      varchar(255),
    username                  varchar(255)
);
INSERT INTO user_info VALUES(1,'ADMIN',1711279662626,'FALSE','test@test.com',1,'ADMIN',1711279662626,'Bruce Wilson','password1','FALSE','ROLE_ADMIN','user1');
INSERT INTO user_info VALUES(2,'ADMIN',1711280777201,'FALSE','test@test.com',1,'ADMIN',1711280777201,'Kelly Young','password1','FALSE','ROLE_USER','user2');
INSERT INTO user_info VALUES(3,'ADMIN',1711724019271,'FALSE','testuser1@gmail.com',1,'ADMIN',1711724019271,'testuser1','NUSISS2024','FALSE','ROLE_USER','testuser1');
CREATE TABLE task_comments (task_comment_id integer, created_by varchar(255), created_date timestamp, task_comment varchar(255), task_id integer, primary key (task_comment_id));
INSERT INTO task_comments VALUES(1,'user1',NULL,'test',1);
INSERT INTO task_comments VALUES(2,'user1',NULL,'new comment',1);
INSERT INTO task_comments VALUES(3,'user1',NULL,'four',1);
INSERT INTO task_comments VALUES(4,'user1',NULL,'6dasdfa',1);
INSERT INTO task_comments VALUES(5,'user1',NULL,'adfads',1);
INSERT INTO task_comments VALUES(6,'user1',NULL,'testing 123',1);
INSERT INTO task_comments VALUES(7,'user1',NULL,'1111',1);
INSERT INTO task_comments VALUES(8,'user1',NULL,'ddd',2);
INSERT INTO task_comments VALUES(9,'user1',NULL,'Test Comment1',1);
DELETE FROM sqlite_sequence;
COMMIT;
