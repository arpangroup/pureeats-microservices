-- INSERT INTO categoryEntity (id, name, description, is_active, created_date, last_modified_date)VALUES (1, 'Demo Category1', null, 1, '2021-08-21 07:40:19', '2021-08-21 07:40:19');
truncate TABLE CATEGORY;
INSERT INTO CATEGORY (id, name)
VALUES
(1, 'Biryani'),
(2, 'Chicken'),
(3, 'Thali'),
(4, 'Pizza'),
(5, 'Momos'),
(6, 'Noodles'),
(7, 'Chingri'),
(8, 'Cake'),
(9, 'Paneer'),
(10, 'Burger');


INSERT INTO ADMIN_TBL_MENU (MENU_ID, MENU_NAME, MENU_ICON)
VALUES
(1, 'Category', 'fa-tachometer-alt'),
(2, 'Brands', 'fa-tachometer-alt'),
(3, 'Attributes', 'fa-tachometer-alt'),
(4, 'Products', 'fa-tachometer-alt'),
(5, 'Orders', 'fa-tachometer-alt'),
(6, 'Reports', 'fa-tachometer-alt'),
(7, 'Organization', 'fa-tachometer-alt'),
(8, 'Profile', 'fa-tachometer-alt'),
(9, 'Setting', 'fa-tachometer-alt');