INSERT INTO tasks (id, title, description, points)
SELECT *
FROM (
    VALUES
    (random_uuid(), 'Task 1', 'Description of task 1', 1),
    (random_uuid(), 'Task 2', 'Description of task 2', 2),
    (random_uuid(), 'Task 3', 'Description of task 3', 3),
    (random_uuid(), 'Task 4', 'Description of task 4', 4),
    (random_uuid(), 'Task 5', 'Description of task 5', 5),
    (random_uuid(), 'Task 6', 'Description of task 6', 6),
    (random_uuid(), 'Task 7', 'Description of task 7', 7),
    (random_uuid(), 'Task 8', 'Description of task 8', 8),
    (random_uuid(), 'Task 9', 'Description of task 9', 9),
    (random_uuid(), 'Task 10', 'Description of task 10', 1),
    (random_uuid(), 'Task 11', 'Description of task 11', 2),
    (random_uuid(), 'Task 12', 'Description of task 12', 3),
    (random_uuid(), 'Task 13', 'Description of task 13', 4),
    (random_uuid(), 'Task 14', 'Description of task 14', 5),
    (random_uuid(), 'Task 15', 'Description of task 15', 6),
    (random_uuid(), 'Task 16', 'Description of task 16', 7),
    (random_uuid(), 'Task 17', 'Description of task 17', 8),
    (random_uuid(), 'Task 18', 'Description of task 18', 9),
    (random_uuid(), 'Task 19', 'Description of task 19', 1),
    (random_uuid(), 'Task 20', 'Description of task 20', 2),
    (random_uuid(), 'Task 21', 'Description of task 21', 3),
    (random_uuid(), 'Task 22', 'Description of task 22', 4),
    (random_uuid(), 'Task 23', 'Description of task 23', 5),
    (random_uuid(), 'Task 24', 'Description of task 24', 6),
    (random_uuid(), 'Task 25', 'Description of task 25', 7)
) AS new_tasks(id, title, description, points) WHERE NOT EXISTS (SELECT 1 FROM tasks);