# CLI Task Tracker

A command-line task tracker application built with Java.

## Features

- Add tasks
- List all tasks
- Mark tasks as complete
- Delete tasks
- Save/load tasks from file

## Usage

```bash
# Compile
javac TaskTracker.java

# Run
java TaskTracker
```

## Commands

- `add <description>` - Add a new task
- `list` - List all tasks
- `complete <id>` - Mark task as complete
- `delete <id>` - Delete a task
- `save` - Save tasks to file
- `load` - Load tasks from file
- `exit` - Exit the application

## Example

```
> add Buy groceries
Task added: Buy groceries (ID: 1)

> add Complete project
Task added: Complete project (ID: 2)

> list
1. [ ] Buy groceries
2. [ ] Complete project

> complete 1
Task 1 marked as complete

> list
1. [X] Buy groceries
2. [ ] Complete project
```
