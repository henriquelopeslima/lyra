DOCKER_COMPOSE?=docker-compose
DOCKER?=docker
ANALYZER=cd analyzer &&
STUDENT=cd student &&
TEACHER=cd teacher &&
TEST=docker exec tests
.DEFAULT_GOAL := help
.PHONY: help install

help: ## Show this help message
	@grep -E '(^[a-zA-Z_-]+:.*?##.*$$)|(^##)' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[32m%-30s\033[0m %s\n", $$1, $$2}' | sed -e 's/\[32m##/[33m/'

run: ## Run all (build + up)
	make build
	make up

build: ## Build project
	$(STUDENT) ./mvnw clean package
	$(TEACHER) ./mvnw clean package
	$(ANALYZER) ./mvnw clean package

up: ## Start the project
	$(DOCKER_COMPOSE) up -d

down: ## Stop the project
	$(DOCKER_COMPOSE) down
	make rmis

rmis: ## Delete images
	$(DOCKER) image rm lyra_student
	$(DOCKER) image rm lyra_teacher
	$(DOCKER) image rm lyra_analyzer
	$(DOCKER) image rm lyra_tests

tests: ## Run tests
	$(TEST) dart bin/main.dart
