az acr create --resource-group IronMan-Mark-85 --name acrinstagram --sku Basic --admin-enabled true
az aks create --resource-group IronMan-Mark-85 --name aks-instagram --node-count 2 --enable-addons monitoring --generate-ssh-keys
az aks update --resource-group IronMan-Mark-85 --name aks-instagram --attach-acr acrinstagram