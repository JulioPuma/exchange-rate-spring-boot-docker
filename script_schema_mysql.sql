CREATE TABLE `currency_t001` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `exchange_rate_t002` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `exchange_rate` FLOAT NULL,
  `date` DATE NULL,
  `currency_base` INT NOT NULL,
  `currency_destiny` INT NOT NULL,
  PRIMARY KEY (`id`, `currency_base`, `currency_destiny`),
  -- INDEX `fk_currency_base_idx` (`currency_base` ASC) VISIBLE,
  -- INDEX `fk_currency_destiny_idx` (`currency_destiny` ASC) VISIBLE,
  CONSTRAINT `fk_currency_id_base`
    FOREIGN KEY (`currency_base`)
    REFERENCES `currency_t001` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_currency_id_destiny`
    FOREIGN KEY (`currency_destiny`)
    REFERENCES `currency_t001` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);