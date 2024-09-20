
USE taskmanagement;

-- CREATE CATEGORY
INSERT INTO task_category(task_category_id,category_name)
SELECT 1,'Marketing'
FROM DUAL
WHERE NOT EXISTS(SELECT 1 FROM task_category WHERE task_category_id=1);

INSERT INTO task_category(task_category_id,category_name)
SELECT 2,'Testing'
FROM DUAL
WHERE NOT EXISTS(SELECT 1 FROM task_category WHERE task_category_id=2);

INSERT INTO task_category(task_category_id,category_name)
SELECT 3,'Design'
FROM DUAL
WHERE NOT EXISTS(SELECT 1 FROM task_category WHERE task_category_id=3);

INSERT INTO task_category(task_category_id,category_name)
SELECT 4,'Research'
FROM DUAL
WHERE NOT EXISTS(SELECT 1 FROM task_category WHERE task_category_id=4);

INSERT INTO task_category(task_category_id,category_name)
SELECT 5,'Support'
FROM DUAL
WHERE NOT EXISTS(SELECT 1 FROM task_category WHERE task_category_id=5);

INSERT INTO task_category(task_category_id,category_name)
SELECT 6,'Management'
FROM DUAL
WHERE NOT EXISTS(SELECT 1 FROM task_category WHERE task_category_id=6);