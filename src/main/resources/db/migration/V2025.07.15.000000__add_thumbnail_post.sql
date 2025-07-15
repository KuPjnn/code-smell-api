SET @table := 'cms_post';
SET @column := 'thumbnail';
SET @column_type := 'VARCHAR(255)';

SET @sql := (
  SELECT IF(
    EXISTS (
      SELECT 1
      FROM INFORMATION_SCHEMA.COLUMNS
      WHERE table_schema = DATABASE()
        AND table_name = @table
        AND column_name = @column
    ),
    'SELECT "Column exists, skip..."',
    CONCAT('ALTER TABLE ', @table, ' ADD COLUMN ', @column, ' ', @column_type)
  )
);

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
