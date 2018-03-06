---sql工具 ---
--- mysql---

-------------------
-------权限表- ------
-------------------
create table t_permission (
  p_id int(11) not null auto_increment comment '权限id',
  p_code varchar(32) not null comment '权限代码',
  p_dtl varchar(64) not null comment '权限详情',
  p_name varchar(64) not null comment '权限名字',
  p_status int(11) not null default '0' comment '权限状态 0：可用   1：不可用   默认0',
  p_create_time datetime default current_timestamp on update current_timestamp comment '创建时间,禁止修改',
  p_modify_time datetime default null on update current_timestamp comment '权限被修改时间',
  primary key (p_id)
) engine=innodb auto_increment=6 default charset=utf8;

insert into t_permission values ('1', '*', '所有数据操作权限', '所有', '0', '2018-03-04 11:28:49', null);
insert into t_permission values ('2', 'create', '新增数据操作权限', '新增', '0', '2018-03-04 11:29:20', null);
insert into t_permission values ('3', 'delete', '删除数据操作权限', '删除', '0', '2018-03-04 11:29:58', null);
insert into t_permission values ('4', 'update', '更新数据操作权限', '更新', '0', '2018-03-04 11:32:05', null);
insert into t_permission values ('5', 'select', '查询数据操作权限', '查询', '0', '2018-03-04 11:31:28', null);

-------------------
-------角色表--------
-------------------
create table t_role (
  id int(11) not null auto_increment,
  role_code varchar(32) not null default '' comment '角色代码',
  role_name varchar(32) not null comment '角色名字',
  role_dtl varchar(64) not null comment '角色详情',
  role_permission_ids varchar(255) not null default '' comment '角色所拥有的权限，引用权限表中的id,多个权限以逗号分隔',
  role_status int(11) default '0' comment '角色状态，0：可用   1：不可用   默认0可用',
  role_create_time datetime default current_timestamp on update current_timestamp comment '角色创建时间',
  role_modify_time datetime default null on update current_timestamp comment '角色更新时间',
  primary key (id)
) engine=innodb auto_increment=2 default charset=utf8;
insert into t_role values ('1', 'admin', '系统管理员', '此角色能操作所有', '1', '0', '2018-03-04 11:51:09', null);


-------------------
-----博客分类表-------
-------------------
create table t_classify (
  id int(11) not null auto_increment,
  classify_name varchar(32) not null comment '分类名字，如mysql,java',
  classify_user_id int(11) default null comment '此分类归属哪个用户',
  classify_create_time datetime default null on update current_timestamp comment '分类创建时间',
  classify_modify_time datetime default null on update current_timestamp comment '修改时间',
  primary key (id),
  key classify_user_id (classify_user_id) using hash comment '分类归属哪个用户创建索引'
) engine=innodb default charset=utf8;


-------------------
-------博客表--------
-------------------
create table t_blog (
  id int(11) not null auto_increment,
  blog_author_id int(11) default null comment '博客作者id',
  blog_classify_id int(11) default null comment '博客分类id,引用分类表中的id,多个分类以逗号分隔',
  blog_name varchar(64) not null comment '博文名字',
  blog_keyword varchar(255) not null comment '博客关键字，写入html中keyword中，方便百度收录',
  blog_title varchar(64) not null comment '博客描述',
  blog_description varchar(255) not null comment '博客名称',
  blog_create_time datetime default null on update current_timestamp comment '博客创建时间',
  blog_modify_time datetime default null on update current_timestamp comment '博客修改时间',
  primary key (id),
  key blog_name_classify_id (blog_classify_id,blog_name) using hash comment '为博客名字和分类id创建hash索引，为以后博文多时，方便搜索'
) engine=innodb default charset=utf8;


-------------------
-----博客内容表------
-------------------
create table t_blog_text (
  id int(11) not null auto_increment,
  blog_id int(11) not null comment '博客id',
  blog_text mediumblob comment '博客内容，大文本存储，存储html文本',
  primary key (id)
) engine=innodb default charset=utf8;


------------------
------用户表-------
------------------
create table t_user (
  id int(11) not null auto_increment,
  user_name varchar(64) not null comment '用户名，库中唯一',
  user_password varchar(255) not null comment '用户密码',
  user_password_salt varchar(255) default null comment '用户盐值密码',
  user_real_name varchar(64) default null comment '用户真实姓名',
  user_address varchar(255) default null comment '用户地址',
  user_phone varchar(15) default null comment '用户手机号',
  user_email varchar(64) default null comment '用户邮箱',
  user_id_card varchar(18) default null comment '用户身份证号',
  user_status int(11) not null default '0' comment '用户当前状态，0,可用，1,不可用，2,被锁定 默认0可用',
  user_create_time datetime not null default current_timestamp on update current_timestamp comment ' 用户创建时间',
  user_modify_time datetime default null on update current_timestamp comment '用户最后一次编辑时间',
  primary key (id),
  unique key user_name_phone_email (user_name,user_real_name,user_phone,user_email) comment '用户名，手机号，邮箱唯一'
) engine=innodb default charset=utf8;
