create table customer
(
    id             bigint auto_increment
        primary key,
    created_at     datetime(6)  not null,
    address        varchar(100) not null,
    address_number int          not null,
    city           varchar(100) not null,
    document       varchar(11)  not null,
    mobile_phone   varchar(11)  not null,
    name           varchar(80)  not null,
    updated_at     datetime(6)  null,
    constraint UK_DOCUMENT
        unique (document)
);

create table orders
(
    id          bigint auto_increment
        primary key,
    amount      decimal(10, 2)                                                not null,
    order_date  datetime(6)                                                   not null,
    status      enum ('AWAITING', 'IN_PROCESSING', 'DELIVERING', 'DELIVERED', 'CANCELED') not null,
    customer_id bigint                                                        null,
    constraint FK_CUSTOMER_ID
        foreign key (customer_id) references customer (id)
);

create table product
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)    not null,
    description varchar(255)   not null,
    price       decimal(10, 2) not null,
    name        varchar(255)   not null,
    updated_at  datetime(6)    null
);

create table order_item
(
    id         bigint auto_increment
        primary key,
    order_id   bigint null,
    product_id bigint null,
    constraint FK_ORDER_ID
        foreign key (order_id) references orders (id),
    constraint FK_PRODUCT_ID
        foreign key (product_id) references product (id)
);



INSERT INTO `projeto-integrador`.product (id, created_at, description, price, name, updated_at)
VALUES (1, '2024-05-13 12:50:31.437141', 'Pão, salsicha, molho, batata palha, ketchup, maionese e mostarda', 10.00,
        'Hot Dog Tradicional', '2024-05-13 12:50:31.437202');
INSERT INTO `projeto-integrador`.product (id, created_at, description, price, name, updated_at)
VALUES (2, '2024-05-13 12:50:53.137315', 'Pão, 2 salsichas, molho, batata palha, ketchup, maionese e mostarda', 12.00,
        'Hot Dog com 2 salsicnhas', '2024-05-13 12:50:53.137338');
INSERT INTO `projeto-integrador`.product (id, created_at, description, price, name, updated_at)
VALUES (3, '2024-05-13 12:51:16.592885', 'Pão, salsicha, molho, catupiry, batata palha, ketchup, maionese e mostarda',
        15.00, 'Hot Dog catupiry', '2024-05-13 12:51:16.592911');
INSERT INTO `projeto-integrador`.product (id, created_at, description, price, name, updated_at)
VALUES (4, '2024-05-13 12:51:37.085988', 'Pão, salsicha, molho, bacon, batata palha, ketchup, maionese e mostarda',
        15.00, 'Hot dog bacon', '2024-05-13 12:51:37.086023');
INSERT INTO `projeto-integrador`.product (id, created_at, description, price, name, updated_at)
VALUES (5, '2024-05-13 12:52:01.625443',
        'Pão, 2 salsichas, molho, bacon, calabresa, ovo, queijo, batata palha, ketchup, maionese e mostarda', 18.00,
        'Hot dog super tudo', '2024-05-13 12:52:01.625473');
INSERT INTO `projeto-integrador`.product (id, created_at, description, price, name, updated_at)
VALUES (6, '2024-05-13 12:52:23.689472',
        'Pão, 2 salsichas, molho, bacon, presunto, queijo, tomate, azeitona, orégano, milho, batata palha, ketchup, maionese e mostarda',
        20.00, 'Hot dog pizza bacon', '2024-05-13 12:52:23.689508');
INSERT INTO `projeto-integrador`.product (id, created_at, description, price, name, updated_at)
VALUES (7, '2024-05-13 12:52:55.950329',
        'Pão de hambúrguer, 2 hambúrgueres de 56g cada, presunto, queijo, batata palha, milho e tomate', 18.00,
        'X - burguer', '2024-05-13 12:52:55.950351');
INSERT INTO `projeto-integrador`.product (id, created_at, description, price, name, updated_at)
VALUES (8, '2024-05-13 12:53:12.375243',
        'Pão de hambúrguer, 2 hambúrgueres de 56g cada, ovo, presunto, queijo, tomate, milho e batata palha', 20.00,
        'X - egg', '2024-05-13 12:53:12.375266');
INSERT INTO `projeto-integrador`.product (id, created_at, description, price, name, updated_at)
VALUES (9, '2024-05-13 12:53:29.586697',
        'Pão de hambúrguer, 2 hambúrgueres de 56g cada, bacon, presunto, queijo, batata palha, milho e tomate', 21.00,
        'X - bacon', '2024-05-13 12:53:29.586759');
INSERT INTO `projeto-integrador`.product (id, created_at, description, price, name, updated_at)
VALUES (10, '2024-05-13 12:53:47.227883',
        'Pão de hambúrguer, 2 hambúrgueres de 56g cada, bacon, ovo, presunto, queijo, milho, batata palha e tomate',
        22.00, 'X bacon egg', '2024-05-13 12:53:47.227918');

INSERT INTO `projeto-integrador`.customer (id, created_at, address, address_number, city, document, mobile_phone, name,
                                           updated_at)
VALUES (1, '2024-05-13 13:12:21.186230', 'Avenida Doutor Edésio Vieira de Melo', 939, 'Cândido Rodrigues',
        '56339813755', '7937650070', 'Vitor Pedro Aragão', '2024-05-13 13:12:21.186259');
INSERT INTO `projeto-integrador`.customer (id, created_at, address, address_number, city, document, mobile_phone, name,
                                           updated_at)
VALUES (2, '2024-05-13 13:12:47.819978', 'Rua Martinho Rodrigues de Freitas', 405, 'Cândido Rodrigues', '74417351732',
        '31984763285', 'Marlene Tânia Luna Freitas', '2024-05-13 13:12:47.820013');
INSERT INTO `projeto-integrador`.customer (id, created_at, address, address_number, city, document, mobile_phone, name,
                                           updated_at)
VALUES (3, '2024-05-13 13:13:24.409946', 'Rua Amapá', 178, 'Cândido Rodrigues', '13299578227', '27998857068',
        'Rebeca Juliana Sabrina Cavalcanti', '2024-05-13 13:13:24.409991');
INSERT INTO `projeto-integrador`.customer (id, created_at, address, address_number, city, document, mobile_phone, name,
                                           updated_at)
VALUES (4, '2024-05-13 13:14:03.806720', 'Avenida Wilson Rodrigues de Almeida', 679, 'Cândido Rodrigues', '86934150180',
        '67999169561', 'Débora Juliana Rayssa Cavalcanti', '2024-05-13 13:14:03.806772');
INSERT INTO `projeto-integrador`.customer (id, created_at, address, address_number, city, document, mobile_phone, name,
                                           updated_at)
VALUES (5, '2024-05-13 13:14:36.942118', 'Avenida Rio Branco 103', 868, 'Cândido Rodrigues', '54328195034',
        '21989930790', 'Carlos Eduardo Vitor Nascimento', '2024-05-13 13:14:36.942158');
