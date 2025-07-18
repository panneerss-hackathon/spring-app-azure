#!/bin/bash

# Variables - replace with your values
RESOURCE_GROUP="my-resource-group"
LOCATION="eastus"
POSTGRES_SERVER="my-postgres-server"
POSTGRES_ADMIN="pgadmin"
POSTGRES_PASSWORD="ChangeMe123!"
COSMOS_ACCOUNT="my-cosmos-account"
ACR_NAME="myacr12345"
AKS_CLUSTER="my-aks-cluster"
LOG_ANALYTICS="my-log-analytics"

# Create resource group
az group create --name $RESOURCE_GROUP --location $LOCATION

# PostgreSQL Flexible Server
az postgres flexible-server create \
  --name $POSTGRES_SERVER \
  --resource-group $RESOURCE_GROUP \
  --location $LOCATION \
  --admin-user $POSTGRES_ADMIN \
  --admin-password $POSTGRES_PASSWORD \
  --sku-name Standard_B1ms

# Cosmos DB for MongoDB API
az cosmosdb create \
  --name $COSMOS_ACCOUNT \
  --resource-group $RESOURCE_GROUP \
  --kind MongoDB

# Container Registry
az acr create \
  --resource-group $RESOURCE_GROUP \
  --name $ACR_NAME \
  --sku Basic

# Log Analytics Workspace and Application Insights
az monitor log-analytics workspace create \
  --resource-group $RESOURCE_GROUP \
  --workspace-name $LOG_ANALYTICS

WORKSPACE_ID=$(az monitor log-analytics workspace show \
  --resource-group $RESOURCE_GROUP \
  --workspace-name $LOG_ANALYTICS \
  --query customerId -o tsv)

az monitor app-insights component create \
  --app spring-app \
  --location $LOCATION \
  --resource-group $RESOURCE_GROUP \
  --workspace $LOG_ANALYTICS

# AKS Cluster
az aks create \
  --resource-group $RESOURCE_GROUP \
  --name $AKS_CLUSTER \
  --node-count 1 \
  --generate-ssh-keys \
  --attach-acr $ACR_NAME \
  --workspace-resource-id "/subscriptions/$(az account show --query id -o tsv)/resourceGroups/$RESOURCE_GROUP/providers/Microsoft.OperationalInsights/workspaces/$LOG_ANALYTICS"
