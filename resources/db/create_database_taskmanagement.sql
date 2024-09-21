CREATE DATABASE IF NOT EXISTS taskmanagement;

USE taskmanagement;

-- Disable foreign key checks
SET foreign_key_checks = 0;


-- Create table task_category

CREATE TABLE IF NOT EXISTS task_category (
    task_category_id INT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(255),
    PRIMARY KEY (task_category_id)
);

-- Create table task_info
CREATE TABLE IF NOT EXISTS task_info (
    task_id INT NOT NULL AUTO_INCREMENT,
    task_due_date TIMESTAMP,
    task_assignee INT NOT NULL,
    task_category_id INT NOT NULL,
    task_description VARCHAR(500),
    task_name VARCHAR(500),
    task_priority VARCHAR(50),
    task_reward_point INT NOT NULL,
    task_status VARCHAR(50),

    created_by VARCHAR(50) NULL,
    created_date TIMESTAMP NULL,
    modified_by VARCHAR(50) NULL,
    modified_date TIMESTAMP NULL,
    delete_flag VARCHAR(50) NULL,

    PRIMARY KEY (task_id)
);

-- Create table task_comments
CREATE TABLE IF NOT EXISTS task_comments (
    task_comment_id INT NOT NULL AUTO_INCREMENT,
    task_id INT NULL,
    task_comment VARCHAR(255),
    created_by VARCHAR(50) NULL,
    created_date TIMESTAMP NULL,
    PRIMARY KEY (task_comment_id)
);

-- Enable foreign key checks
SET foreign_key_checks = 1;



