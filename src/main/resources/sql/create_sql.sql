-- sql工具 -- -
-- - mysql-- -
-- 测试
create table test_student (
  id int(11) not null auto_increment,
  name varchar(255) default null,
  age int(11) default null,
  primary key (id)
) engine=innodb auto_increment=14 default charset=utf8;


-- -- -- -- -- -- -- -- -- -
-- -- -- -权限表- -- -- -- 
-- -- -- -- -- -- -- -- -- -
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

-- -- -- -- -- -- -- -- -- -
-- -- -- -角色表-- -- -- -- 
-- -- -- -- -- -- -- -- -- -
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


-- -- -- -- -- -- -- -- -- -
-- -- -博客分类表-- -- -- -
-- -- -- -- -- -- -- -- -- -
create table t_classify (
  id int(11) not null auto_increment,
  classify_name varchar(32) not null comment '分类名字，如mysql,java',
  classify_parent_id int(11) default '0' comment '父类id，默认为0,为第一级分类',
  classify_description varchar(255) default null comment '分类描述',
  classify_user_id int(11) default null comment '此分类归属哪个用户',
  classify_create_time datetime default null on update current_timestamp comment '分类创建时间',
  classify_modify_time datetime default null on update current_timestamp comment '修改时间',
  primary key (id),
  key classify_user_id (classify_user_id) using hash comment '分类归属哪个用户创建索引'
) engine=innodb default charset=utf8;

-- -- -- -- -- -- -- -- -- -
-- -- -- -博客表-- -- -- -- 
-- -- -- -- -- -- -- -- -- -
create table t_blog (
  id int(11) not null auto_increment,
  blog_user_id int(11) default null comment '博客创建用户id',
  blog_text_id int(11) not null default '0' comment '博客内容id',
  blog_classify_id int(11) default null comment '博客分类id,引用分类表中的id,多个分类以逗号分隔',
  blog_name varchar(64) not null comment '博文名字',
  blog_keyword varchar(255) not null comment '博客关键字，写入html中keyword中，方便百度收录',
  blog_title varchar(64) default null comment '博客标题',
  blog_status int(11) not null default '0' comment 'blog状态，默认0可用，1为不可用，即在回收站，2为草稿',
  blog_description varchar(255) default null comment '博客描述',
  blog_create_time datetime default null on update current_timestamp comment '博客创建时间',
  blog_modify_time datetime default null on update current_timestamp comment '博客修改时间',
  primary key (id),
  key blog_name_classify_id (blog_classify_id,blog_name) using hash comment '为博客名字和分类id创建hash索引，为以后博文多时，方便搜索'
) engine=innodb auto_increment=43 default charset=utf8;


-- -- -- -- -- -- -- -- -- -
-- -- -博客内容表-- -- -- 
-- -- -- -- -- -- -- -- -- -
create table t_blog_text (
  id int(11) not null auto_increment,
  blog_text longblob comment '博客内容，大文本存储，存储html文本',
  primary key (id)
) engine=innodb auto_increment=46 default charset=utf8;


-- -- -- -- -- -- -- -- -- 
-- -- -- 用户表-- -- -- -
-- -- -- -- -- -- -- -- -- 
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
  user_logo varchar(255) default null comment '用户头像',
  primary key (id),
  unique key user_name_phone_email (user_name,user_real_name,user_phone,user_email) comment '用户名，手机号，邮箱唯一'
) engine=innodb default charset=utf8;

-- -- -- -- -- -- -- -- -- 
-- -- -用户角色表-- -- -- 
-- -- -- -- -- -- -- -- -- 

create table t_user_role (
  id int(11) not null auto_increment,
  user_id int(11) not null comment '用户id',
  role_id int(11) not null comment '角色id',
  create_time datetime default null on update current_timestamp comment '创建时间',
  primary key (id),
  key user_role_userid_and_roleid (user_id,role_id) using hash comment '为用户角色关联表创建hash索引'
) engine=innodb default charset=utf8;

-- -- -- -- -- -- -- -- -- 
-- -- -上传文件 表-- -- -- 
-- -- -- -- -- -- -- -- -- 
create table t_upload_file (
  id int(11) not null auto_increment,
  upload_user_id int(11) not null default '0' comment '文件上传的id',
  upload_original_name varchar(255) default null comment '上传的原始名',
  upload_size bigint(20) not null default '0' comment '文件size',
  upload_title_name varchar(64) default null comment '上传的名字',
  upload_type varchar(32) default null comment '文件类型',
  file_type int(11) not null default '0' comment '文件类型，默认0图片，1视频，2文件',
  upload_url varchar(64) not null comment '上传的url',
  upload_create_time datetime not null on update current_timestamp comment '创建时间',
  primary key (id)
) engine=innodb auto_increment=94 default charset=utf8;

-- 系统地区表
create table sys_area_info (
  id int(11) not null auto_increment,
  area_code int(18) not null,
  area_name varchar(128) default null,
  short_name varchar(64) default null,
  parent_id int(18) not null,
  level int(2) not null,
  create_time datetime default current_timestamp on update current_timestamp,
  primary key (id)
) engine=innodb auto_increment=45457 default charset=utf8;

-- 系统字典表
create table sys_dict (
  id int(11) not null auto_increment,
  dict_name varchar(32) default null comment '字典名称',
  dict_code varchar(16) default null comment '字典代码',
  dict_description varchar(255) default null comment '字典详情，说明',
  dict_status int(11) not null default '0' comment '字典状态，默认为0，可用',
  dict_create_user_id int(11) not null default '0' comment '创建此字典的用户id，后台管理者，暂时不用',
  dict_create_time datetime not null on update current_timestamp comment '创建时间',
  dict_modify_time datetime not null on update current_timestamp comment '修改时间',
  primary key (id)
) engine=innodb default charset=utf8;

create table sys_dict_item (
  id int(11) not null auto_increment,
  item_name varchar(32) default null comment 'item名字',
  item_code varchar(16) default null comment 'item代码',
  item_dict_id int(11) not null default '0' comment 'item所属字典id',
  item_status int(11) not null default '0' comment 'item状态，默认为0可用',
  item_level int(11) not null default '1' comment 'item级别',
  item_parent_id int(11) default null comment 'item父id',
  item_create_user_id int(11) not null default '0' comment 'item创建用户，用于后台管理，暂时不用',
  item_create_time datetime not null on update current_timestamp comment '创建时间',
  item_modify_time datetime not null on update current_timestamp comment '修改时间',
  primary key (id)
) engine=innodb default charset=utf8;
