param location string = resourceGroup().location
param appName string
param sku string = 'B1'

resource plan 'Microsoft.Web/serverfarms@2022-09-01' = {
  name: '<prefix>-${appName}-plan'
  location: location
  sku: {
    name: sku
    tier: 'Basic'
  }
}

resource webapp 'Microsoft.Web/sites@2022-09-01' = {
  name: appName
  location: location
  properties: {
    serverFarmId: plan.id
    httpsOnly: true
  }
}

output webappName string = webapp.name
output webappUrl string = 'https://${webapp.properties.defaultHostName}'
