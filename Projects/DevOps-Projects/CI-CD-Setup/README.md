# CI/CD Setup

Complete CI/CD pipeline setup for Java Spring Boot applications using GitHub Actions.

## Features

- Automated testing
- Build and package
- Docker image creation
- Deployment automation
- Multi-environment support

## GitHub Actions Workflow

The workflow includes:
1. Checkout code
2. Set up Java environment
3. Run tests
4. Build application
5. Create Docker image
6. Push to container registry
7. Deploy to server

## Workflow Files

- `.github/workflows/ci-cd.yml` - Main CI/CD pipeline
- `.github/workflows/test.yml` - Testing workflow
- `.github/workflows/deploy.yml` - Deployment workflow

## Environment Variables

Set in GitHub Secrets:
- `DOCKER_USERNAME` - Docker Hub username
- `DOCKER_PASSWORD` - Docker Hub password
- `DEPLOY_HOST` - Deployment server host
- `DEPLOY_USER` - Deployment user
- `DEPLOY_KEY` - SSH private key

## Usage

1. Push code to repository
2. Workflow runs automatically
3. Tests execute
4. On success, builds and deploys
