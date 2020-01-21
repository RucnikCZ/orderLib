# OrderCreationLib

Library for creating online order for both systems POS, AdminPOS

**Minimum SDK 25**

How to add?

## Step 1.

Add it in your root build.gradle at the end of repositories:
```bash
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```


## Step 2. Add the dependency
```bash
implementation 'com.github.RucnikCZ:OrderCreationLib:RELEASE_VERSION'

```

## Usage:

```bash
BillCreation.getInstance().setPrintSettings('SettingsModel',"TAG","Context","Callback","CompanyModel")

1 - Define final bitmap look (Categories,Covers on bon etc..)
2 - Tag appered in logs
3 - Context for overwrite strings
5 - Callback for returned values
  5.1 - Bitmap
  5.2 - PrintArguments
  5.3 - BillModel
4 - Pass current company model visible on receipt header

BillCreation.getInstance().createOrderFromSpeedlo("Fragment", "PosOrder")

1 - Order data (here used fragment for common graphql query)
2 - Boolean value defining order from POS, have impact on returning values

BillCreation.getInstance().reprintOrder(generOrder);

# Only POS
BillCreation.getInstance().cancelOrder(generOrder);
  

```
