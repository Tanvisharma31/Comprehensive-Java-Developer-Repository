# Deployment Automation

Automated deployment scripts and configurations for Java applications.

## Features

- Automated deployment scripts
- Blue-green deployment
- Rolling updates
- Health checks
- Rollback capabilities

## Deployment Methods

### 1. Docker Deployment
- Container-based deployment
- Docker Compose for multi-container apps
- Health checks and restart policies

### 2. Kubernetes Deployment
- Pod definitions
- Service configurations
- Deployment strategies
- Auto-scaling

### 3. Traditional Server Deployment
- SSH-based deployment
- Service management (systemd)
- Nginx reverse proxy

## Scripts

- `deploy.sh` - Main deployment script
- `rollback.sh` - Rollback script
- `health-check.sh` - Health verification
- `backup.sh` - Backup script

## Deployment Process

1. Build application
2. Run tests
3. Create deployment package
4. Backup current version
5. Deploy new version
6. Health check
7. Rollback if needed

## Configuration

- Environment-specific configs
- Database migrations
- Service restarts
- Log rotation
