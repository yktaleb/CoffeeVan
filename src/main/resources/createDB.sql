CREATE TABLE `beverage_quality` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE `beverage_state` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE `beverage_type` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE `beverage` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `beverage_type` INTEGER NOT NULL,
  `beverage_state` INTEGER NOT NULL,
  `beverage_quality` INTEGER NOT NULL,
  `name` VARCHAR(60) NOT NULL,
  `price` DECIMAL(12, 2) NOT NULL,
  `weight` DECIMAL(12, 2) NOT NULL,
  `volume` DECIMAL(12, 2) NOT NULL
);

CREATE INDEX `idx_beverage__beverage_quality` ON `beverage` (`beverage_quality`);

CREATE INDEX `idx_beverage__beverage_state` ON `beverage` (`beverage_state`);

CREATE INDEX `idx_beverage__beverage_type` ON `beverage` (`beverage_type`);

ALTER TABLE `beverage` ADD CONSTRAINT `fk_beverage__beverage_quality` FOREIGN KEY (`beverage_quality`) REFERENCES `beverage_quality` (`id`);

ALTER TABLE `beverage` ADD CONSTRAINT `fk_beverage__beverage_state` FOREIGN KEY (`beverage_state`) REFERENCES `beverage_state` (`id`);

ALTER TABLE `beverage` ADD CONSTRAINT `fk_beverage__beverage_type` FOREIGN KEY (`beverage_type`) REFERENCES `beverage_type` (`id`);

CREATE TABLE `order_status` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL
);

CREATE TABLE `role` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE `user` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `email` VARCHAR(120) UNIQUE NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `first_name` VARCHAR(60) NOT NULL,
  `last_name` VARCHAR(60) NOT NULL,
  `phone_number` VARCHAR(30) NOT NULL
);

CREATE TABLE `user_role` (
  `user` INTEGER NOT NULL,
  `role` INTEGER NOT NULL,
  CONSTRAINT `pk_user_role` PRIMARY KEY (`user`, `role`)
);

CREATE INDEX `idx_user_role__role` ON `user_role` (`role`);

ALTER TABLE `user_role` ADD CONSTRAINT `fk_user_role__role` FOREIGN KEY (`role`) REFERENCES `role` (`id`);

ALTER TABLE `user_role` ADD CONSTRAINT `fk_user_role__user` FOREIGN KEY (`user`) REFERENCES `user` (`id`);

CREATE TABLE `van_status` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE `van` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `van_status` INTEGER NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `carrying_capacity` DECIMAL(12, 2) NOT NULL,
  `max_volume` DECIMAL(12, 2) NOT NULL
);

CREATE INDEX `idx_van__van_status` ON `van` (`van_status`);

ALTER TABLE `van` ADD CONSTRAINT `fk_van__van_status` FOREIGN KEY (`van_status`) REFERENCES `van_status` (`id`);

CREATE TABLE `order` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `order_status` INTEGER NOT NULL,
  `user` INTEGER NOT NULL,
  `van` INTEGER,
  `address` VARCHAR(120) NOT NULL
);

CREATE INDEX `idx_order__order_status` ON `order` (`order_status`);

CREATE INDEX `idx_order__user` ON `order` (`user`);

CREATE INDEX `idx_order__van` ON `order` (`van`);

ALTER TABLE `order` ADD CONSTRAINT `fk_order__order_status` FOREIGN KEY (`order_status`) REFERENCES `order_status` (`id`);

ALTER TABLE `order` ADD CONSTRAINT `fk_order__user` FOREIGN KEY (`user`) REFERENCES `user` (`id`);

ALTER TABLE `order` ADD CONSTRAINT `fk_order__van` FOREIGN KEY (`van`) REFERENCES `van` (`id`);

CREATE TABLE `beverage_order` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `order` INTEGER NOT NULL,
  `beverage` INTEGER NOT NULL,
  `amount` INTEGER NOT NULL
);

CREATE INDEX `idx_beverage_order__beverage` ON `beverage_order` (`beverage`);

CREATE INDEX `idx_beverage_order__order` ON `beverage_order` (`order`);

ALTER TABLE `beverage_order` ADD CONSTRAINT `fk_beverage_order__beverage` FOREIGN KEY (`beverage`) REFERENCES `beverage` (`id`);

ALTER TABLE `beverage_order` ADD CONSTRAINT `fk_beverage_order__order` FOREIGN KEY (`order`) REFERENCES `order` (`id`)