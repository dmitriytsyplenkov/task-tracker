INSERT INTO PROJECTS (id, name) VALUES (1,'Project 1');
INSERT INTO PROJECTS (id, name) VALUES (2,'Project 2');

INSERT INTO users (id, name) VALUES (1,'Admin');
INSERT INTO users (id, name, project_id) VALUES (2,'User', 1);

INSERT INTO tasks (id, description, is_closed, total_remaining_time, parent_task_id, user_id, project_id) values (1,'task 1', false, 80, null, null, 1);
INSERT INTO tasks (id, description, is_closed, total_remaining_time, parent_task_id, user_id, project_id) values (2,'task 2', false, 520, null, null, 1);
INSERT INTO tasks (id, description, is_closed, total_remaining_time, parent_task_id, user_id, project_id) values (3,'task 3', false, 30, null, null, 1);
INSERT INTO tasks (id, description, is_closed, total_remaining_time, parent_task_id, user_id, project_id) values (4,'subtask of task 1', false, 40, 1, 2, 1);

INSERT INTO tasks (id, description, is_closed, total_remaining_time, parent_task_id, user_id, project_id) values (5,'subtask # 1 of task 2', false, 200, 2, null, 1);
INSERT INTO tasks (id, description, is_closed, total_remaining_time, parent_task_id, user_id, project_id) values (6,'subtask # 2 of task 2', false, 40, 2, null, 1);

INSERT INTO tasks (id, description, is_closed, total_remaining_time, parent_task_id, user_id, project_id) values (7,'subtask # 1 of subtask # 1 of task 2', false, 40, 5, null, 1);
INSERT INTO tasks (id, description, is_closed, total_remaining_time, parent_task_id, user_id, project_id) values (8,'subtask # 2 of subtask # 1 of task 2', false, 40, 5, null, 1);

INSERT INTO tasks (id, description, is_closed, total_remaining_time, parent_task_id, user_id, project_id) values (9,'task 57', false, 80, null, 2, 2);
INSERT INTO tasks (id, description, is_closed, total_remaining_time, parent_task_id, user_id, project_id) values (10,'task 23', false, 120, null, null, 2);