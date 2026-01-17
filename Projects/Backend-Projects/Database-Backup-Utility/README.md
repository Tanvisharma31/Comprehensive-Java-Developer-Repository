# Database Backup Utility

A comprehensive database backup and restore utility built with Spring Boot supporting multiple database systems.

## Features

- Scheduled automatic backups
- Support for PostgreSQL and MySQL
- Backup validation
- Restore functionality
- Storage management
- Backup history tracking
- Email notifications (optional)
- Compression support

## Technologies

- Java 17+
- Spring Boot 3.x
- Spring Scheduler
- PostgreSQL JDBC
- MySQL JDBC
- Maven

## API Endpoints

- `POST /api/backup` - Create manual backup
- `GET /api/backups` - List all backups
- `GET /api/backups/{id}` - Get backup details
- `POST /api/backups/{id}/restore` - Restore from backup
- `DELETE /api/backups/{id}` - Delete backup
- `GET /api/backups/validate/{id}` - Validate backup integrity

## Setup

1. **Prerequisites:**
   ```bash
   - Java 17+
   - Maven
   - PostgreSQL or MySQL (for testing)
   ```

2. **Configuration:**
   Update `application.properties` with your database credentials

3. **Build and Run:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access:**
   - API: `http://localhost:8080/api`

## Usage Examples

### Create Backup
```bash
POST /api/backup
Content-Type: application/json

{
  "databaseType": "POSTGRESQL",
  "host": "localhost",
  "port": 5432,
  "databaseName": "mydb",
  "username": "user",
  "password": "pass",
  "backupName": "backup_2024_01_17"
}
```

### List Backups
```bash
GET /api/backups
```

### Restore Backup
```bash
POST /api/backups/1/restore
Content-Type: application/json

{
  "targetHost": "localhost",
  "targetPort": 5432,
  "targetDatabase": "restored_db",
  "targetUsername": "user",
  "targetPassword": "pass"
}
```

## Scheduled Backups

Configure scheduled backups in `application.properties`:
```properties
backup.schedule.enabled=true
backup.schedule.cron=0 0 2 * * ?  # Daily at 2 AM
```

## Architecture

- **Controller Layer**: REST endpoints for backup management
- **Service Layer**: Backup/restore logic
- **Scheduler**: Automated backup execution
- **Storage**: File system for backup files
- **Repository**: Backup metadata persistence

## Supported Databases

- PostgreSQL
- MySQL
- (Extensible for other databases)

## Backup Storage

Backups are stored in: `./backups/{databaseType}/{backupName}.sql`

## Features

- **Automatic Scheduling**: Cron-based scheduled backups
- **Validation**: Backup integrity checking
- **Compression**: Optional gzip compression
- **History**: Track all backup operations
- **Notifications**: Email alerts on backup completion/failure
