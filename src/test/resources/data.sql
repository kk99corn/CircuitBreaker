-- define tCacheData
drop table if exists tCacheData CASCADE;
create table tCacheData
(
    nSeq   int auto_increment,
    dtDate datetime,
    sKey   varchar(255),
    sValue text
);

-- insert test data
insert into tcachedata(dtDate, sKey, sValue) values (now(), 'test', 'test');
insert into tcachedata(dtDate, sKey, sValue) values (now(), 'test1', 'test1');