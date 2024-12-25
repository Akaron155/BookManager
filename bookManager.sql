create database book;
use book;
create table if not exists article
(
    id          int unsigned auto_increment comment 'ID'
        primary key,
    title       varchar(30)                 not null comment '图书标题',
    content     varchar(10000)              not null comment '图书简介',
    cover_img   varchar(128)                not null comment '图书封面',
    state       varchar(3) default '未借阅' null comment '图书状态: 只能是[未借阅] 或者 [已借阅]',
    category_id int unsigned                null comment '图书分类ID',
    create_user int unsigned                not null comment '创建人ID',
    create_time datetime                    not null comment '创建时间',
    update_time datetime                    not null comment '修改时间'
)
    comment '图书信息表';

create index fk_article_category
    on article (category_id);

create index fk_article_user
    on article (create_user);

create table if not exists category
(
    id             int unsigned auto_increment comment 'ID'
        primary key,
    category_name  varchar(32)   not null comment '分类名称',
    category_alias varchar(32)   not null comment '分类别名',
    book_number    int default 0 null,
    create_user    int unsigned  not null comment '创建人ID',
    create_time    datetime      not null comment '创建时间',
    update_time    datetime      not null comment '修改时间'
)
    comment '图书分类表';

create index fk_category_user
    on category (create_user);

create table if not exists operate_log
(
    id            int unsigned auto_increment comment 'ID'
        primary key,
    operate_user  varchar(10)   null comment '操作人',
    operate_time  datetime      null comment '操作时间',
    class_name    varchar(100)  null comment '操作的类名',
    method_name   varchar(100)  null comment '操作的方法名',
    method_params varchar(1000) null comment '方法参数',
    return_value  varchar(2000) null comment '返回值',
    cost_time     bigint        null comment '方法执行耗时, 单位:ms'
)
    comment '操作日志表';

create table if not exists user
(
    id          int unsigned auto_increment comment 'ID'
        primary key,
    username    varchar(20)             not null comment '用户名',
    password    varchar(32)             null comment '密码',
    nickname    varchar(10)  default '' null comment '昵称',
    email       varchar(128) default '' null comment '邮箱',
    user_pic    varchar(128) default '' null comment '头像',
    create_time datetime                not null comment '创建时间',
    update_time datetime                not null comment '修改时间',
    constraint username
        unique (username)
)
    comment '用户表';

CREATE table if not exists admin
(
    id          int unsigned auto_increment comment 'ID'
        primary key,
    username    varchar(20)             not null comment '用户名',
    password    varchar(32)             null comment '密码',
    email       varchar(128) default '' null comment '邮箱',
    user_pic    varchar(128) default '' null comment '头像',
    phone       varchar(11)             not null comment '手机号',
    usertype    varchar(2)              not null comment '用户类型',
    constraint username unique (username),
    constraint phone unique (phone)
)
    comment '管理员表';
    
-- 测试数据
INSERT INTO user (username, password, nickname, email, user_pic, create_time, update_time)
VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', 'admin@example.com', 'user_pic.jpg', NOW(), NOW());
-- 插入数据到category表
INSERT INTO category (category_name, category_alias, book_number, create_user, create_time, update_time)
VALUES 
('科幻', 'Sci-Fi', 0, 1, NOW(), NOW()),
('奇幻', 'Fantasy', 0, 1, NOW(), NOW()),
('非虚构', 'NonFiction', 0, 1, NOW(), NOW());

-- 插入数据到article表
-- 科幻分类的图书
INSERT INTO article (title, content, cover_img, state, category_id, create_user, create_time, update_time)
VALUES 
('三体', '刘慈欣著作的科幻小说', 'san_ti.jpg', '未借阅', 1, 1, NOW(), NOW()),
('基地', '阿西莫夫的科幻系列', 'ji_di.jpg', '未借阅', 1, 1, NOW(), NOW()),
('沙丘', '弗兰克·赫伯特的科幻小说', 'sha_qiu.jpg', '未借阅', 1, 1, NOW(), NOW());

-- 奇幻分类的图书
INSERT INTO article (title, content, cover_img, state, category_id, create_user, create_time, update_time)
VALUES 
('魔戒', '托尔金的奇幻小说', 'mo_jie.jpg', '未借阅', 2, 1, NOW(), NOW()),
('纳尼亚传奇', 'C.S.刘易斯的奇幻小说', 'na_ni_ya.jpg', '未借阅', 2, 1, NOW(), NOW()),
('哈利·波特', 'J.K.罗琳的奇幻小说', 'ha_li_bo_te.jpg', '未借阅', 2, 1, NOW(), NOW());

-- 非虚构分类的图书
INSERT INTO article (title, content, cover_img, state, category_id, create_user, create_time, update_time)
VALUES 
('人类简史', '尤瓦尔·赫拉利的非虚构著作', 'ren_lei_jian_shi.jpg', '未借阅', 3, 1, NOW(), NOW()),
('枪炮、病菌与钢铁', '贾雷德·戴蒙德的非虚构著作', 'qiang_pao_bing_jun_yu_gang_tie.jpg', '未借阅', 3, 1, NOW(), NOW()),
('自私的基因', '理查德·道金斯的非虚构著作', 'zi_si_de_ji_yin.jpg', '未借阅', 3, 1, NOW(), NOW());
